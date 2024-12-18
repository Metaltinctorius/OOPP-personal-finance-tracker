package gu.dit213.group28.model.interfaces;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.enums.Speed;

public interface Icore {
  /** Initializes the GameCore, starting the timer. */
  void init();

  /** Sets the game speed to Normal. */
  void setSpeedNormal();

  /** Sets the game speed to Slow. */
  void setSpeedSlow();

  /** Sets the game speed to Fast. */
  void setSpeedFast();

  /** Pauses the timer if currently active, resumes the timer if currently paused. */
  void pauseAndResume();

  /**
   * Creates a basic buy event and, if successful, delivers it to the market first then to the user.
   * Finally, it delivers the event for extraction.
   *
   * @param sector The sector of the assets.
   * @param quantity The quantity of assets bought
   */
  void makePurchase(Sector sector, int quantity);

  /**
   * Creates a basic sell event and, if successful, delivers it to the market first then to the
   * user. Finally, it delivers the event for extraction.
   *
   * @param sector The sector of the assets.
   * @param quantity The quantity of assets sold
   */
  void makeSell(Sector sector, int quantity);
}
