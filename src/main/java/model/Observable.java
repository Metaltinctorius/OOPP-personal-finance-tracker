package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class that allows the view to register itself as an observer. This class lets us do this
 * without giving the view access to any data in the model.
 */
public abstract class Observable {
  protected final List<IObserver> observers;

  public Observable() {
    observers = new ArrayList<>();
  }

  public void addObserver(IObserver observer) {
    observers.add(observer);
  }

  public void removeObserver(IObserver observer) {
    observers.remove(observer);
  }
}
