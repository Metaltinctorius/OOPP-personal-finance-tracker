package gu.dit213.group28.model.interfaces;

import gu.dit213.group28.model.enums.Sector;

public interface Ieventfacade {
  Ievent getTickEvent();

  Ievent getBuyEvent(Sector s, int quantity);

  Ievent getSellEvent(Sector s, int quantity);
}
