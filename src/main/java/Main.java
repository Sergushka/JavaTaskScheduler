import scheduler.SchedulerExecutor;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        SchedulerExecutor schedulerExecutor = new SchedulerExecutor();
        long delay = schedulerExecutor.calculateDuration();
        Runnable task = () -> System.out.println("I'm in the console");
        schedulerExecutor.scheduleWithPeriod(task, delay, 1, TimeUnit.HOURS);

        SchedulerExecutor schedulerExecutor2 = new SchedulerExecutor();
        Runnable task2 = () -> System.out.println(123);
        schedulerExecutor2.scheduleWithPeriod(task2, delay, 4, TimeUnit.SECONDS);

        schedulerExecutor.stop();
    }
}
