package gu.dit213.group28.model.interfaces;

public interface Imarket
{
  void accept(Ievent e);

  void decrementAllModifiers();

  String getState();
}
