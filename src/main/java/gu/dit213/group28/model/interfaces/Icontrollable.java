package gu.dit213.group28.model.interfaces;

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
}
