package gu.dit213.group28.model.interfaces;

/** Interface for a model */
public interface Imodel {
  /**
   * Extracts an event and updates the view.
   *
   * @param e Event to be extracted.
   */
  void extractEvent(Ievent e);

  /**
   * Informs view about game pause
   *
   * @param pause true if paused, false if resumed
   */
  void updatePause(boolean pause);
}
