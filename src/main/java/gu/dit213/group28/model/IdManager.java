package gu.dit213.group28.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IdManager {
  private final Set<Integer> reservedIds = new HashSet<>();

  private int nextDynamicId;

  public IdManager() {}

  /**
   * Sets and stores the reserved ids loaded from the jsonfile
   *
   * @param reservedIdsFromLoader
   */
  public void setReservedIds(List<Integer> reservedIdsFromLoader) {
    reservedIds.addAll(reservedIdsFromLoader);
    nextDynamicId = reservedIdsFromLoader.size() + 1;
  }

  /**
   * Class purpose: To get the next available Id, independent of json file id amounts.
   *
   * @return
   */
  public int getNextId() {
    while (reservedIds.contains(nextDynamicId)) {
      nextDynamicId++;
    }
    reservedIds.add(nextDynamicId);
    return nextDynamicId++;
  }
}
