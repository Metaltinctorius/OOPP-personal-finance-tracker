package gu.dit213.group28.model.interfaces;

import java.util.Observer;

public interface IeventExtractor {
  void addObserver(Iobserver observer);

  void extractEvent(Ievent e);
}
