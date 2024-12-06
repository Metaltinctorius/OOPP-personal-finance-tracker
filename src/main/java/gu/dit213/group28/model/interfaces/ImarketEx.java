package gu.dit213.group28.model.interfaces;

import gu.dit213.group28.model.market.Asset;
import gu.dit213.group28.model.market.TrendModifier;
import java.util.List;

public interface ImarketEx {
  List<Asset> getAssets();
<<<<<<< HEAD
  double getIndexValue();
=======

  void addTrendModifier(TrendModifier mod);
>>>>>>> implement-predefined
}
