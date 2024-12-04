package gu.dit213.group28.model.interfaces;

import gu.dit213.group28.model.enums.Sector;
import java.util.List;

/**
 * This interface defines which methods the controller can reach. These methods whould be defined in
 * the model and called in the controller.
 */
public interface Icontrollable {
  void init();

  void setSpeedFast();

  void setSpeedNormal();

  void setSpeedSlow();

  void buyAsset(Sector sector, String amount);

  void sellAsset(Sector sector, String amount);
}
