package gu.dit213.group28.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class IdManager {
  private final Set<Integer> reservedIds = new HashSet<>();

  private int nextDynamicId;
  public IdManager(List<Integer> reservedIdsFromLoader) {
    nextDynamicId = reservedIdsFromLoader.size() + 1;
    reservedIds.addAll(reservedIdsFromLoader);
  }

  public int getNextId() {
    while (reservedIds.contains(nextDynamicId)) {
      nextDynamicId++;
    }
    reservedIds.add(nextDynamicId);
    return nextDynamicId++;
  }

}
