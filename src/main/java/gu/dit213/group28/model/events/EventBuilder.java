package gu.dit213.group28.model.events;

import gu.dit213.group28.model.enums.EventType;
import gu.dit213.group28.model.enums.PlayerAction;
import gu.dit213.group28.model.enums.Sector;

import java.util.List;

public class EventBuilder {
    private final int id;
    private final String description;
    private final List<Sector> categories;
    private final double modifier;
    private final EventType type;
    private PlayerAction action;
    private int iterations;


    /** For sequential events */
    private int totalStages;
    private int stage;

    public EventBuilder(int  id, String description, EventType type, int iterations, List<Sector> categories, double modifier) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.iterations = iterations;
        this.categories = categories;
        this.modifier = modifier;
    }

    /**
     * Optional field. Primarily intended to be used to define an event as a purchase
     * or sell made by the player.
     * @param action The action made by the player for "purchase asset" or "selling asset".
     * @return Builder returns itself.
     */
    public  EventBuilder setPlayerAction(PlayerAction action){
        this.action = action;
        return this;
    }

    /**
     * Used for sequential events. To be done later.
     * @param stage The stage the sequence is currently at
     * @param totalStages The total number of stages in the event.
     * @return Returns builder.
     */
    public EventBuilder setStage(int stage, int totalStages) {
        if (type != EventType.SEQUENTIAL) {
            throw new IllegalStateException(
                    "Can only set stages for sequential event types! Invalid argument: " + type);
        }
        if(totalStages <= 0 || stage < 0) {
            throw new IllegalStateException("Stages and totalStages need to be larger than 0");
        }
        this.stage = stage;
        this.totalStages = totalStages;
        return this;
    }

    /**
     * Used to validate the iterations specified in the json file, in order to make the handling
     * of events safer. No event that is set to "ONCE" can have iterations > 0, no event
     * set to "REPEATING" can have iterations < 1.
     * @param iterations Iterations either given from creating an event or the json file.
     */
    public void checkIterations(int iterations) {
        if (type == EventType.REPEATING && iterations <= 0) {
            throw new IllegalArgumentException("Repeating events must have iterations > 0: " + iterations);
        }
        if (type == EventType.ONCE && iterations != 0) {
            throw new IllegalArgumentException("One-time events must have iterations = 0: " + iterations);
        }
        this.iterations = iterations;
    }

    /**
     * Primary method to produce (create) an event. The builder calls this to create the event.
     * @return Event.
     */
    public OldEvent build() {
        checkIterations(iterations);
        return new OldEvent(this);
    }
}
