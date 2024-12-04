package gu.dit213.group28.model.interfaces;

import gu.dit213.group28.model.user.PortfolioRecord;
import java.util.List;

public interface Iuser {
  void accept(Ievent e);

  List<PortfolioRecord> getState();
}
