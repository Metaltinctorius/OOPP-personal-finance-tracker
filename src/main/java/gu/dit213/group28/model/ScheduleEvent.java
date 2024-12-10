package gu.dit213.group28.model;

import gu.dit213.group28.model.interfaces.Ievent;

public class ScheduleEvent
{
  Ievent event;
  int trigger;
  ScheduleEvent(Ievent event, int triggerTick) {
    this.event = event;
    this.trigger = triggerTick;
  }

}
