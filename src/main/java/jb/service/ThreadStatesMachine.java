package jb.service;

import com.github.oxo42.stateless4j.StateMachine;
import com.github.oxo42.stateless4j.StateMachineConfig;
import jb.model.ThreadState;
import jb.model.ThreadTransition;

import javax.annotation.PostConstruct;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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

public class ThreadStatesMachine {

    private static final StateMachineConfig<ThreadState, ThreadTransition> config = new StateMachineConfig<>();
    private long timer;
    private LocalDateTime tick;

    @PostConstruct
    public void setup() {
        config.configure(NEW)
                .permit(START, RUNNING, this::startTimer);
        config.configure(RUNNING)
                .permit(SLEEP_TIME, TIME_WAITING, this::stopTimer)
                .permit(WAIT_TIMEOUT, TIME_WAITING, this::stopTimer)
                .permit(JOIN_TIMEOUT, TIME_WAITING, this::stopTimer)
                .permit(PARK_NANOS, TIME_WAITING, this::stopTimer)
                .permit(WAIT, WAITING, this::stopTimer)
                .permit(JOIN, WAITING, this::stopTimer)
                .permit(PARK, WAITING, this::stopTimer)
                .permit(WAITING_FOR_LOCK, BLOCKED, this::stopTimer);
        config.configure(TIME_WAITING)
                .permit(TIME_ELAPSED, RUNNING, this::startTimer);
        config.configure(WAITING)
                .permit(NOTIFY, RUNNING, this::startTimer)
                .permit(NOTIFY_ALL, RUNNING, this::startTimer);
        config.configure(BLOCKED)
                .permit(LOCK_ACQUIRED, RUNNING, this::startTimer);
        config.configure(RUNNING)
                .permit(EXIT, TERMINATED, this::stopTimer);
    }

    public StateMachine<ThreadState, ThreadTransition> spawn() {
        return new StateMachine<>(NEW, config);
    }

    public long runningTime() {
        return timer;
    }

    private void startTimer() {
        if (tick != null) throw new IllegalStateException();
        tick = LocalDateTime.now();
    }

    private void stopTimer() {
        timer += ChronoUnit.MILLIS.between(tick, LocalDateTime.now());
        tick = null;
    }
}
