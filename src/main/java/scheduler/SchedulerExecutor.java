package scheduler;

import java.time.*;

public class SchedulerExecutor {
    private SchedulerTaskRunner taskRunner;

    public void scheduleWithPeriod(Runnable runnable, long delay, long period) throws InterruptedException {
        Thread.sleep(delay);
        runnable.run();
        taskRunner = new SchedulerTaskRunner(period);
        taskRunner.addTask(runnable);
        new Thread(() -> taskRunner.start()).start();
    }

    public long calculateDuration() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextStartTime = calculateNextHour(now);
        return Duration.between(now, nextStartTime).toMillis();
    }

    public void stop() {
        taskRunner.stop();
    }

    private LocalDateTime calculateNextHour(LocalDateTime time) {
        return time.plusHours(1);
    }
}
