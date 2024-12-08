package gu.dit213.group28.model.interfaces;

import gu.dit213.group28.model.Observable;

/** Interface for an evenExtractor which extracts output from events and sends them to the view. */
public interface IeventExtractor {
  /** Getter for the observable. Should be the eventExtractor itself. */
  Observable getObservable();

  /**
   * Extracts an event based on its ID and updates the view accordingly.
   *
   * @param e The Ievent to be extracted
   */
  void extractEvent(Ievent e);
}
