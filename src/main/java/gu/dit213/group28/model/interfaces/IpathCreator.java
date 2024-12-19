package gu.dit213.group28.model.interfaces;

/** Interface for classes that creates paths for events. */
public interface IpathCreator {
  /**
   * Creates a new Ipath for a given Ipathable and Ievent.
   *
   * @param p Ipathable to be 'pathed' on.
   * @param e Event that needs a path.
   * @return New Path for the given Ipathable and Ievent.
   */
  Ipath getEventPath(Ipathable p, Ievent e);
}
