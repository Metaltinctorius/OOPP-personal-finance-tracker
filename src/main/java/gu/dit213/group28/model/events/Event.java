package gu.dit213.group28.model.events;

public abstract class Event {

  private final int id;
  private final String description;

  public Event(int id, String description) {
    this.id = id;
    this.description = description;
  }

  public int getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public void execute() {}

  public void unpackage() {
    // return something
  }
}
