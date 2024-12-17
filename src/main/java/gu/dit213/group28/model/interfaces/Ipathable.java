package gu.dit213.group28.model.interfaces;

public interface Ipathable {
  void executeOnMarket(Ievent e);

  void executeOnUser(Ievent e);

  void extract(Ievent e);
}
