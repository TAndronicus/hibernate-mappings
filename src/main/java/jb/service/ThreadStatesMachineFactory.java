package jb.service;

import com.github.oxo42.stateless4j.StateMachine;
import com.github.oxo42.stateless4j.StateMachineConfig;
import jb.model.ThreadState;
import jb.model.ThreadTransition;

import javax.annotation.PostConstruct;

import static jb.model.ThreadState.BLOCKED;
import static jb.model.ThreadState.NEW;
import static jb.model.ThreadState.RUNNING;
import static jb.model.ThreadState.TERMINATED;
import static jb.model.ThreadState.TIME_WAITING;
import static jb.model.ThreadState.WAITING;
import static jb.model.ThreadTransition.EXIT;
import static jb.model.ThreadTransition.JOIN;
import static jb.model.ThreadTransition.JOIN_TIMEOUT;
import static jb.model.ThreadTransition.LOCK_ACQUIRED;
import static jb.model.ThreadTransition.NOTIFY;
import static jb.model.ThreadTransition.NOTIFY_ALL;
import static jb.model.ThreadTransition.PARK;
import static jb.model.ThreadTransition.PARK_NANOS;
import static jb.model.ThreadTransition.SLEEP_TIME;
import static jb.model.ThreadTransition.START;
import static jb.model.ThreadTransition.TIME_ELAPSED;
import static jb.model.ThreadTransition.WAIT;
import static jb.model.ThreadTransition.WAITING_FOR_LOCK;
import static jb.model.ThreadTransition.WAIT_TIMEOUT;

public class ThreadStatesMachineFactory {

    private static final StateMachineConfig<ThreadState, ThreadTransition> config = new StateMachineConfig<>();

    @PostConstruct
    public void setup() {
        config.configure(NEW)
                .permit(START, RUNNING);
        config.configure(RUNNING)
                .permit(SLEEP_TIME, TIME_WAITING)
                .permit(WAIT_TIMEOUT, TIME_WAITING)
                .permit(JOIN_TIMEOUT, TIME_WAITING)
                .permit(PARK_NANOS, TIME_WAITING)
                .permit(WAIT, WAITING)
                .permit(JOIN, WAITING)
                .permit(PARK, WAITING)
                .permit(WAITING_FOR_LOCK, BLOCKED);
        config.configure(TIME_WAITING)
                .permit(TIME_ELAPSED, RUNNING);
        config.configure(WAITING)
                .permit(NOTIFY, RUNNING)
                .permit(NOTIFY_ALL, RUNNING);
        config.configure(BLOCKED)
                .permit(LOCK_ACQUIRED, RUNNING);
        config.configure(RUNNING)
                .permit(EXIT, TERMINATED);
    }

    public StateMachineConfig<ThreadState, ThreadTransition> getConfig() {
        return config;
    }

    public StateMachine<ThreadState, ThreadTransition> pure() {
        return new StateMachine<>(NEW, config);
    }
}
