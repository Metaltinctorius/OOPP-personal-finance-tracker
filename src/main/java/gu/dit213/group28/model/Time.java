package gu.dit213.group28.model;

import gu.dit213.group28.model.enums.Speed;
import gu.dit213.group28.model.interfaces.Itimer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Time implements Itimer {
  private final ReentrantLock lock = new ReentrantLock();
  private final ScheduledExecutorService timer;
  private int tick;
  private int threshold;
  private boolean running;
  private boolean next;

  public Time() {
    timer = Executors.newSingleThreadScheduledExecutor();
    threshold = 100;
    running = false;
    next = true;
  }

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
            next = true;
          }
          lock.unlock();
        },
        33L,
        33L,
        TimeUnit.MILLISECONDS);
    // 30fps would 33.333... ms so the timing is a bit off, might not be a problem?
  }

  public void setThreshold(Speed s) {
    lock.lock();
    int oldThreshold = threshold;
    switch (s) {
      case SLOW:
        threshold = 300; // Change these to alter speed of the game
        break;
      case NORMAL:
        threshold = 100;
        break;
      case FAST:
        threshold = 30;
        break;
      default:
        return;
    }
    tick = threshold * tick / oldThreshold;
    lock.unlock();
  }

  public boolean isNextReady() {
    if (!next) {
      return false;
    }
    next = false;
    return true;
  }

  public void start() {
    running = true;
  }

  public void pause() {
    running = false;
  }

  public int getCurrentTick() {
    return tick;
  }
}
