package gu.dit213.group28.model.interfaces;

/** Top level interface for events, should only include getID() and various execute methods. */
public interface Ievent {
  /**
   * Getter for event ID
   *
   * @return event ID
   */
  int getID();

  /**
   * Executes event on given ImarketEx
   *
   * @param m ImarketEx to be executed upon
   */
  void execute(ImarketEx m);

  /**
   * Executes event on given IuserEx
   *
   * @param u IuserEx to be executed upon
   */
  void execute(IuserEx u);
}
