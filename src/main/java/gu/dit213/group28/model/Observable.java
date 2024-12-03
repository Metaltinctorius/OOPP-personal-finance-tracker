package gu.dit213.group28.model;

import gu.dit213.group28.model.interfaces.Iobserver;
<<<<<<< HEAD
=======

>>>>>>> merge-market-asset-event
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class that allows the view to register itself as an observer. This class lets us do this
 * without giving the view access to any data in the model.
 */
public abstract class Observable {
  protected final List<Iobserver> observers;

  public Observable() {
    observers = new ArrayList<>();
  }

  public void addObserver(Iobserver observer) {
    observers.add(observer);
  }

  public void removeObserver(Iobserver observer) {
    observers.remove(observer);
  }
}
