package gu.dit213.group28.model.interfaces;

import gu.dit213.group28.model.enums.Sector;
import java.util.List;

public interface Ievent {
  int getID();


  void execute(ImarketEx m);

  void execute(IuserEx u);
}
