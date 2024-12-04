package gu.dit213.group28.model.interfaces;

public interface Ievent {
  int getID();

  void execute(ImarketEx m);

  void execute(IuserEx u);
}
