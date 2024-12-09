package gu.dit213.group28.model;

import gu.dit213.group28.model.enums.Speed;
import gu.dit213.group28.model.interfaces.Itimer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/** Timer class that schedules a ticker task every 33 milliseconds. */
public class Time implements Itimer {
  private final ReentrantLock lock = new ReentrantLock();
  private final ScheduledExecutorService timer;
  private int tick;
  private int threshold;
  private boolean running;
  private final SynchronousQueue<Boolean> queue;

  /** Timer class that schedules a ticker task every 33 milliseconds. */
  public Time() {
    timer = Executors.newSingleThreadScheduledExecutor();
    threshold = 100;
    running = false;
    queue = new SynchronousQueue<>();
  }

  /** Initializes the timer */
  public void initTime() {
    timer.scheduleAtFixedRate(
        () -> {
          if (!running) {
            return;
          }
          lock.lock();
          tick++;
          if (tick >= threshold) {
            tick = 0;

            queue.offer(true);
          }
          lock.unlock();
        },
        33L,
        33L,
        TimeUnit.MILLISECONDS);
    // 30fps would 33.333... ms so the timing is a bit off, might not be a problem?
  }

  /**
   * Sets the speed of the timer. The actual definition of the different speeds are defined by the
   * timer.
   *
   * @param s Speed enum
   */
  public void setThreshold(Speed s) {
    lock.lock();
    int oldThreshold = threshold;
    switch (s) {
      case SLOW:
        threshold = 1200; // Change these to alter speed of the game
        break;
      case NORMAL:
        threshold = 600;
        break;
      case FAST:
        threshold = 100;
        break;
      default:
        return;
    }
    tick = threshold * tick / oldThreshold;
    lock.unlock();
  }

  /**
   * Blocks the current thread until the next game tick occurs-
   *
   * @return Always true
   * @throws InterruptedException
   */
  public boolean next() throws InterruptedException {
    return queue.take();
  }

  /** Starts the timer */
  public void start() {
    running = true;
  }

  /** Pauses the timer */
  public void pause() {
    running = false;
  }
  /** Pauses the timer if currently active, resumes the timer if currently paused. */
  public void pauseAndResume(){
    if (running) {
      pause();
    }
    else {
      start();
    }
  }

  /**
   * Retrieves the current timer tick. Note that this is not the same as a game tick. A game tick
   * occurs after a certain number of timer ticks, as defined in the timer itself.
   *
   * @return current timer tick
   */
  public int getCurrentTick() {
    return tick;
  }
}
