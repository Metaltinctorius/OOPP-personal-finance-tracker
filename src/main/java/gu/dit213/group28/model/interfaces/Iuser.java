package gu.dit213.group28.model.interfaces;

/** Interface for users */
public interface Iuser {
  /**
   * Accepts incoming events
   *
   * @param e Event that will execute on this market
   */
  void accept(Ievent e);
}
