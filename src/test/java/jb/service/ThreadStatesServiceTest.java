package jb.service;

import com.github.oxo42.stateless4j.StateMachine;
import jb.model.ThreadState;
import jb.model.ThreadTransition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.function.Supplier;

import static jb.model.ThreadState.TERMINATED;
import static jb.model.ThreadTransition.EXIT;
import static jb.model.ThreadTransition.JOIN;
import static jb.model.ThreadTransition.JOIN_TIMEOUT;
import static jb.model.ThreadTransition.LOCK_ACQUIRED;
import static jb.model.ThreadTransition.NOTIFY;
import static jb.model.ThreadTransition.NOTIFY_ALL;
import static jb.model.ThreadTransition.PARK_NANOS;
import static jb.model.ThreadTransition.START;
import static jb.model.ThreadTransition.TIME_ELAPSED;
import static jb.model.ThreadTransition.WAIT;
import static jb.model.ThreadTransition.WAITING_FOR_LOCK;
import static jb.model.ThreadTransition.WAIT_TIMEOUT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadStatesServiceTest {

    @Autowired
    private ApplicationContext applicationContext;

    private final Supplier<ThreadStatesMachine> threadStatesMachineSupplier = () -> applicationContext.getBean(ThreadStatesMachine.class);

    @Test
    public void testPossibleStates() {
        ThreadStatesMachine threadStatesMachine = threadStatesMachineSupplier.get();
        StateMachine<ThreadState, ThreadTransition> stateMachine = threadStatesMachine.spawn();
        assertFalse(stateMachine.canFire(WAIT));
        assertFalse(stateMachine.canFire(NOTIFY));
        assertFalse(stateMachine.canFire(EXIT));
        assertEquals(1, stateMachine.getPermittedTriggers().size());

        stateMachine.fire(START);
        assertTrue(stateMachine.canFire(WAIT));
        assertTrue(stateMachine.canFire(JOIN));
        assertTrue(stateMachine.canFire(JOIN_TIMEOUT));
        assertTrue(stateMachine.canFire(WAITING_FOR_LOCK));
        assertFalse(stateMachine.canFire(LOCK_ACQUIRED));
        assertFalse(stateMachine.canFire(NOTIFY));

        stateMachine.fire(WAIT_TIMEOUT);
        stateMachine.fire(TIME_ELAPSED);
        stateMachine.fire(WAIT);
        stateMachine.fire(NOTIFY_ALL);
        stateMachine.fire(EXIT);
        assertEquals(TERMINATED, stateMachine.getState());
    }

    @Test(expected = IllegalStateException.class)
    public void testExceptionOnWrongTransition() {
        ThreadStatesMachine threadStatesMachine = threadStatesMachineSupplier.get();
        StateMachine<ThreadState, ThreadTransition> stateMachine = threadStatesMachine.spawn();
        stateMachine.fire(EXIT);
    }

    @Test
    public void testCallbacks() throws InterruptedException {
        ThreadStatesMachine threadStatesMachine = threadStatesMachineSupplier.get();
        StateMachine<ThreadState, ThreadTransition> stateMachine = threadStatesMachine.spawn();
        fireAndWait(stateMachine, START, 500);
        fireAndWait(stateMachine, PARK_NANOS, 500);
        fireAndWait(stateMachine, TIME_ELAPSED, 500);
        fireAndWait(stateMachine, JOIN, 500);
        fireAndWait(stateMachine, NOTIFY_ALL, 500);
        fireAndWait(stateMachine, WAITING_FOR_LOCK, 500);
        fireAndWait(stateMachine, LOCK_ACQUIRED, 500);
        fireAndWait(stateMachine, EXIT, 500);
        assertTrue(threadStatesMachine.runningTime() >= 2_000);
        assertTrue(threadStatesMachine.runningTime() <= 3_000);
    }

    private void fireAndWait(StateMachine<ThreadState, ThreadTransition> stateMachine, ThreadTransition transition, long timeout) throws InterruptedException {
        stateMachine.fire(transition);
        Thread.sleep(timeout);
    }

}
