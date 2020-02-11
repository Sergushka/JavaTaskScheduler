import scheduler.SchedulerExecutor;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        SchedulerExecutor schedulerExecutor = new SchedulerExecutor();
        long hourDelay = schedulerExecutor.calculateDuration();
        Runnable task = () -> System.out.println("I'm in the console");
        schedulerExecutor.scheduleWithPeriod(task, 1000, 2000);

        SchedulerExecutor schedulerExecutor2 = new SchedulerExecutor();
        Runnable task2 = () -> System.out.println("123");
        schedulerExecutor2.scheduleWithPeriod(task2, 2000, 1000);

        Thread.sleep(6000);
        schedulerExecutor.stop();
    }
}
