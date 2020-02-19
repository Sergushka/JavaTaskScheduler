package scheduler;

import java.util.ArrayDeque;
import java.util.Queue;

public class SchedulerTaskRunner {
    private long delay;
    private long period;
    private Queue<Runnable> queue;
    private boolean shouldStop = false;

    public SchedulerTaskRunner(long delay, long period) {
        this.queue = new ArrayDeque<>();
        this.delay = delay;
        this.period = period;
    }

    public void scheduleTask(Runnable task, long delay, long period) throws InterruptedException {
        this.delay = delay;
        this.period = period;
        addTask(task);
        this.startExecuting();
    }

    public void makeStop() {
        shouldStop = true;
    }

    private Runnable createTask(Runnable task) {
        return () -> {
            try {
                Thread.sleep(period);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            task.run();
        };
    }

    private void addTask(Runnable task) {
        queue.add(createTask(task));
    }

    private void startExecuting() throws InterruptedException {
        Thread.sleep(delay);
        if (!shouldStop) {
            runTask();
            while (!queue.isEmpty()) {
                runTask();
            }
        }
    }

    private void runTask() {
        Runnable task = queue.remove();
        task.run();
        if (!shouldStop) {
            addTask(task);
        } else {
            Thread.currentThread().interrupt();
        }
    }
}
