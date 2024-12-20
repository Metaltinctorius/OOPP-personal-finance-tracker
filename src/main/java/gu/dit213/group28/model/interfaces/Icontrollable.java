package gu.dit213.group28.model.interfaces;

import gu.dit213.group28.model.enums.Sector;

/**
 * This interface defines which methods the controller can reach. These methods should be defined in
 * the model and called in the controller.
 */
public interface Icontrollable {
  /** Initializes the Icontrollable. */
  void init();

  /** Sets game speed to fast. Exact speed depends on the timer used. */
  void setSpeedFast();

  /** Sets game speed to Normal. Exact speed depends on the timer used. */
  void setSpeedNormal();

  /** Sets game speed to Slow. Exact speed depends on the timer used. */
  void setSpeedSlow();

  /**
   * Initializes a buy event.
   *
   * @param sector The sector of the assets.
   * @param quantity The quantity of assets bought
   */
  void buyAsset(Sector sector, String quantity);

  /**
   * Initializes a sell event.
   *
   * @param sector The sector of the assets.
   * @param quantity The quantity of assets sold
   */
  void sellAsset(Sector sector, String quantity);

  /** Pauses the timer if currently active. Resumes the timer if it is currently paused. */
  void pauseAndResume();
}
