package scheduler;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchedulerExecutor {
    ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    public long calculateDuration() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextStartTime = calculateNextHour(now);
        long delay = Duration.between(now, nextStartTime).getSeconds();
        return delay;
    }

    public void scheduleWithPeriod(Runnable runnable, long delay, int period, TimeUnit timeUnit) {
        runnable.run();
        scheduledExecutorService.scheduleAtFixedRate(runnable,
                delay,
                period,
                timeUnit);
    }

    public void stop() {
        scheduledExecutorService.shutdown();
        try {
            scheduledExecutorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private LocalDateTime calculateNextHour(LocalDateTime dateTime) {
        return dateTime.plusHours(1).withMinute(0).withSecond(0).withNano(0);
    }
}
