package gu.dit213.group28.model.interfaces;

/** Interface for Markets */
public interface Imarket {
  /**
   * Accepts incoming events
   *
   * @param e Event that will execute on this market
   */
  void accept(Ievent e);

  /** Decrements the timer on all current modifiers inside the market */
  void decrementAllModifiers();
}
