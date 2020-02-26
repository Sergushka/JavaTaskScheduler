import scheduler.SchedulerTaskRunner;

import static scheduler.SchedulerExecutor.MILLIS_IN_SECOND;

public class Main {
    private final static int DELAY = 2 * MILLIS_IN_SECOND;
    private final static int PERIOD = 2 * MILLIS_IN_SECOND;

    public static void main(String[] args) {
        SchedulerTaskRunner taskRunner = new SchedulerTaskRunner(DELAY, PERIOD);
        Runnable task = () -> System.out.println("I'm in the console");
        Runnable task2 = () -> System.out.println("123");

        taskRunner.setDaemon(true);
        taskRunner.scheduleTask(task, DELAY, PERIOD);
        taskRunner.scheduleTask(task2, 2 * DELAY, MILLIS_IN_SECOND);

        taskRunner.start();

        // Stop after 10 seconds.
        try {
            Thread.sleep(15 * MILLIS_IN_SECOND);
            taskRunner.makeStop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
