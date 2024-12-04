package gu.dit213.group28.model.interfaces;

import gu.dit213.group28.model.Observable;
import java.util.Observer;

public interface IeventExtractor {
  Observable getObservable();

  void extractEvent(Ievent e);
}
