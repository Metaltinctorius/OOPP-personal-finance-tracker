package gu.dit213.group28.model.interfaces;

import gu.dit213.group28.model.enums.Sector;

/** A facade that allows for simple creation of events. */
public interface IeventFacade {
  /**
   * Creates a basic tick event.
   *
   * @param tick current game tick.
   */
  Ievent getTickEvent(int tick);

  /**
   * Creates a basic buy event.
   *
   * @param sector The sector of the assets.
   * @param quantity The quantity of assets bought
   * @return Basic buy event
   */
  Ievent getBuyEvent(Sector sector, int quantity);

  /**
   * Creates a basic sell event.
   *
   * @param sector The sector of the assets.
   * @param quantity The quantity of assets sold.
   * @return Basic sell event.
   */
  Ievent getSellEvent(Sector sector, int quantity);

  /**
   * Creates an event based on a set predefined events.
   *
   * @return Predefined event.
   */
  Ievent getPredefinedEvent();

  /**
   * Checks if a random event should be created. Each call increases the probability of the next.
   */
  boolean isRandomEventReady();
}
