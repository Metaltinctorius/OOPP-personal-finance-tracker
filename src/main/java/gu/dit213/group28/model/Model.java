package gu.dit213.group28.model;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.interfaces.Icontrollable;
import gu.dit213.group28.model.interfaces.Icore;
import gu.dit213.group28.model.interfaces.Ievent;
import gu.dit213.group28.model.interfaces.IeventExtractor;
import gu.dit213.group28.model.interfaces.Imodel;

/** Class that receives and delivers input. */
public class Model implements Icontrollable, Imodel {
  private final Icore gameCore;
  private final IeventExtractor eventExtractor;

  /** Class that receives and delivers input. */
  public Model() {
    eventExtractor = new EventExtractor();
    gameCore = new GameCore(this);
  }

  /** Initializes the Icontrollable. */
  public void init() {
    gameCore.init();
  }

  /** Sets game speed to Fast. Exact speed depends on the timer used. */
  @Override
  public void setSpeedFast() {
    gameCore.setSpeedFast();
  }

  /** Sets game speed to Normal. Exact speed depends on the timer used. */
  @Override
  public void setSpeedNormal() {
    gameCore.setSpeedNormal();
  }

  /** Sets game speed to Slow. Exact speed depends on the timer used. */
  @Override
  public void setSpeedSlow() {
    gameCore.setSpeedSlow();
  }

  /**
   * Initializes a buy event.
   *
   * @param sector The sector of the assets.
   * @param quantity The quantity of assets bought
   */
  @Override
  public void buyAsset(Sector sector, String quantity) {
    try {
      int qty = Integer.parseInt(quantity);
      if (qty <= 0) {
        return;
      }
      gameCore.makePurchase(sector, qty);
    } catch (NumberFormatException e) {
      System.out.println("Invalid quantity");
    }
  }

  /**
   * Initializes a sell event.
   *
   * @param sector The sector of the assets.
   * @param quantity The quantity of assets sold
   */
  @Override
  public void sellAsset(Sector sector, String quantity) {
    try {
      int qty = Integer.parseInt(quantity);
      if (qty <= 0) {
        return;
      }
      gameCore.makeSell(sector, qty);
    } catch (NumberFormatException e) {
      System.out.println("Invalid quantity");
    }
  }

  /** Gets the Observable that this model holds. */
  public Observable getObservable() {
    return eventExtractor.getObservable();
  }

  /**
   * Extracts an event and updates the view.
   *
   * @param e Event to be extracted.
   */
  @Override
  public void extractEvent(Ievent e) {
    eventExtractor.extractEvent(e);
  }

  /**
   * Informs view about game pause.
   *
   * @param pause true if paused, false if resumed
   */
  @Override
  public void updatePause(boolean pause) {
    eventExtractor.updatePause(pause);
  }

  /** Pauses the timer if currently active. Resumes the timer if it is currently paused. */
  @Override
  public void pauseAndResume() {
    gameCore.pauseAndResume();
  }
}
