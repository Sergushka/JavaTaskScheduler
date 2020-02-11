package scheduler;

import java.time.*;

public class SchedulerExecutor {
    public static int MILLIS_IN_SECOND = 1000;
    private SchedulerTaskRunner taskRunner;

    public void scheduleWithPeriod(Runnable runnable, long delay, long period) throws InterruptedException {
        taskRunner = new SchedulerTaskRunner(period);
        taskRunner.addTask(runnable);
        new Thread(() -> {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            taskRunner.start();
        }).start();
    }

    public long calculateDuration() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextStartTime = calculateNextHour(now);
        return Duration.between(now, nextStartTime).toMillis();
    }

    public void stop() throws InterruptedException {
        taskRunner.stop();
    }

    private LocalDateTime calculateNextHour(LocalDateTime time) {
        return time.plusHours(1);
    }
}
