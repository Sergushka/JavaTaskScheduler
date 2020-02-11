import scheduler.SchedulerExecutor;

import static scheduler.SchedulerExecutor.MILLIS_IN_SECOND;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        SchedulerExecutor schedulerExecutor = new SchedulerExecutor();
        long hourDelay = schedulerExecutor.calculateDuration();
        Runnable task = () -> System.out.println("I'm in the console");
        schedulerExecutor.scheduleWithPeriod(task, 2 * MILLIS_IN_SECOND, 2 * MILLIS_IN_SECOND);

        SchedulerExecutor schedulerExecutor2 = new SchedulerExecutor();
        Runnable task2 = () -> System.out.println("123");
        schedulerExecutor2.scheduleWithPeriod(task2, hourDelay, MILLIS_IN_SECOND);

        Thread.sleep(6000);
        schedulerExecutor.stop();
    }
}
