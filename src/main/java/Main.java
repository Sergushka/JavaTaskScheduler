import scheduler.SchedulerTaskRunner;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static scheduler.SchedulerExecutor.MILLIS_IN_SECOND;

public class Main {
    private final static int DELAY = 2 * MILLIS_IN_SECOND;
    private final static int PERIOD = 2 * MILLIS_IN_SECOND;

    public static void main(String[] args) {
        SchedulerTaskRunner taskRunner = new SchedulerTaskRunner(DELAY, PERIOD);
        Executor executor = Executors.newScheduledThreadPool(2);
        Runnable task = () -> System.out.println("I'm in the console");
        Runnable task2 = () -> System.out.println("123");

        executor.execute(() -> {
            try {
                taskRunner.scheduleTask(task, DELAY, PERIOD);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.execute(() -> {
            try {
                taskRunner.scheduleTask(task2, DELAY, PERIOD);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Stop after 10 seconds.
        try {
            Thread.sleep(10 * MILLIS_IN_SECOND);
            taskRunner.makeStop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
