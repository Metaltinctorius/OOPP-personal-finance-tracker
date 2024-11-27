package gu.dit213.group28.model.interfaces;

import gu.dit213.group28.model.enums.Speed;

public interface Itimer {
  void initTime();

  void setThreshold(Speed s);

  boolean isNextReady();

  void start();

  void pause();

  int getCurrentTick();
}
