package gu.dit213.group28.model.interfaces;

/** An interface for event paths. */
public interface Ipath {
  /**
   * Starts a path for an event.
   *
   * @return true if path is finished and can be discarded, false otherwise
   */
  boolean start();

  /**
   * A pending event is an event that needs to be stored for future calls to start().
   *
   * @return true if event is pending, false otherwise
   */
  boolean isPending();
}
