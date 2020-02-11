package scheduler;

import java.util.ArrayDeque;
import java.util.Queue;

public class SchedulerTaskRunner {
    private long period;
    private Queue<Runnable> queue;
    private Runnable task;
    private boolean shouldStop = false;

    SchedulerTaskRunner(long period) {
        this.queue = new ArrayDeque<>();
        this.period = period;
    }

    public void start() {
        if (!shouldStop) {
            runTask();
            while (!queue.isEmpty()) {
                runTask();
            }
        }
    }

    public void addTask(Runnable task) {
        if (this.task == null) {
            this.task = createTask(task);
        }
        queue.add(this.task);
    }

    public void stop() {
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

    private void runTask() {
        queue.remove().run();
        if (!shouldStop) {
            addTask(task);
        }
    }
}
