package scheduler;

import java.time.Duration;
import java.time.LocalDateTime;

public class SchedulerExecutor {
    public static int MILLIS_IN_SECOND = 1000;

    public static long calculateDuration() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextStartTime = calculateNextHour(now);
        return Duration.between(now, nextStartTime).toMillis();
    }

    private static LocalDateTime calculateNextHour(LocalDateTime time) {
        return time.plusHours(1);
    }
}
