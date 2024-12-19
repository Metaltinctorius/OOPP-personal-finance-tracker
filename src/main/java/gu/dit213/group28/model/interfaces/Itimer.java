package gu.dit213.group28.model.interfaces;

import gu.dit213.group28.model.enums.Speed;

/** Interface for a timer that represents in-game time. */
public interface Itimer {
  /** Initializes the timer. */
  void initTime();

  /**
   * Sets the speed of the timer. The actual definition of the different speeds are defined by the
   * timer.
   *
   * @param s Speed enum
   */
  void setThreshold(Speed s);

  /**
   * Blocks the current thread until the next game tick occurs.
   *
   * @return Always true
   * @throws InterruptedException if the thread is interrupted
   */
  boolean next() throws InterruptedException;

  /** Starts the timer. */
  void start();

  /** Pauses the timer. */
  void pause();

  /** Pauses the timer if currently active, resumes the timer if currently paused. */
  boolean pauseAndResume();

  /**
   * Retrieves the current timer tick. Note that this is not the same as a game tick. A game tick
   * occurs after a certain number of timer ticks, as defined in the timer itself.
   *
   * @return current timer tick
   */
  int getCurrentTick();
}
