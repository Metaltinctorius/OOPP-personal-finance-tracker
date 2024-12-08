package gu.dit213.group28.model;

import gu.dit213.group28.model.interfaces.Iobserver;
import java.util.ArrayList;
import java.util.List;

/** Abstract class that allows the view to register itself as an observer. */
public abstract class Observable {
  protected final List<Iobserver> observers;

  /** Abstract class that allows the view to register itself as an observer. */
  public Observable() {
    observers = new ArrayList<>();
  }

  /**
   * Adds a given Iobserver to the observer list of this Observable
   *
   * @param observer Iobserver to be added
   */
  public void addObserver(Iobserver observer) {
    observers.add(observer);
  }

  /**
   * Removes a given Iobserver from the observer list of this Observable
   *
   * @param observer Iobserver to be removed
   */
  public void removeObserver(Iobserver observer) {
    observers.remove(observer);
  }
}
