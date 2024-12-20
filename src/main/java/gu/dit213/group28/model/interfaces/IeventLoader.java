package gu.dit213.group28.model.interfaces;

import gu.dit213.group28.model.events.EventPredef;
import java.util.List;

/** Interface for loading events. */
public interface IeventLoader {

  /**
   * Getter for predefined events.
   *
   * @return list of predefined events.
   */
  List<EventPredef> getPredefinedEvents();

  /**
   * Getter for reserved IDs.
   *
   * @return list of reserved IDs.
   */
  List<Integer> getReservedIds();

  /**
   * Method to load events.
   *
   * @param path path to the file with events.
   */
  void loadEvents(String path);
}
