package gu.dit213.group28.model.interfaces;

import gu.dit213.group28.model.events.Event;
import gu.dit213.group28.model.events.EventPredef;
import java.util.List;

public interface IeventLoader {

  List<EventPredef> getPredefinedEvents();

  List<Integer> getReservedIds();

  void loadEvents();
}
