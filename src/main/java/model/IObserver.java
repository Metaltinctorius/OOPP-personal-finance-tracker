package model;

/**
 * This interface defines the methods that the model can use to update the view. Note that the
 * methods themselves are implemented in the view.
 */
public interface IObserver {
  void update(String s);
}
