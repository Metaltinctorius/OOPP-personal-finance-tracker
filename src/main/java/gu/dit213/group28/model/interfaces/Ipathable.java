package gu.dit213.group28.model.interfaces;

/** An interface for classes that events can be passed through, i.e. a path for the event. */
public interface Ipathable {
  /**
   * Passes an event for execution on an Imarket.
   *
   * @param e event to be executed
   */
  void executeOnMarket(Ievent e);

  /**
   * Passes an event for execution on a user.
   *
   * @param e event to be executed
   */
  void executeOnUser(Ievent e);

  /**
   * Passes an event for extraction.
   *
   * @param e event to be executed
   */
  void extract(Ievent e);
}
