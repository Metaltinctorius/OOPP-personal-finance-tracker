package gu.dit213.group28.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** Class that manages the ids of different events. */
public class IdManager {
  private final Set<Integer> reservedIds = new HashSet<>();

  private int nextDynamicId;

  public IdManager() {}

  /**
   * Sets and stores the reserved ids loaded from the jsonfile.
   *
   * @param reservedIdsFromLoader List of reserved ids from the json file.
   */
  public void setReservedIds(List<Integer> reservedIdsFromLoader) {
    reservedIds.addAll(reservedIdsFromLoader);
    nextDynamicId = reservedIdsFromLoader.size() + 1;
  }

  /**
   * Gets the next available Id, independent of json file id amounts.
   *
   * @return The next available id.
   */
  public int getNextId() {
    while (reservedIds.contains(nextDynamicId)) {
      nextDynamicId++;
    }
    reservedIds.add(nextDynamicId);
    return nextDynamicId++;
  }
}
