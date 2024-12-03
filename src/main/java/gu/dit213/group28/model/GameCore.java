package gu.dit213.group28.model;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.events.EventFacade;
import gu.dit213.group28.model.interfaces.*;
import gu.dit213.group28.model.market.Market;
import gu.dit213.group28.model.user.Portfolio;
import gu.dit213.group28.model.wrappers.wEventFacade;
import gu.dit213.group28.model.wrappers.wLogic;
import gu.dit213.group28.model.wrappers.wUser;

import java.util.concurrent.locks.ReentrantLock;


public class GameCore {

    private final Itimer timer;
    private final Ieventfacade eventFacade;
    private final Ilogic logic;
    private final Imarket market;
    private Iuser user;

    public GameCore(Logic logic) {
        timer = new Time();
        timer.initTime();
        eventFacade = new wEventFacade(new EventFacade());
        this.logic = new wLogic(logic);
        market = Market.getInstance();
        Iuser user = new wUser(new Portfolio());

    }
    public void init() {
        timer.start();
        new Thread(() -> {
            Ievent e = eventFacade.getEmpty();
            market.accept(e);
            String m = market.getState();
            logic.timerUpdate(m);
        }).start();
    }

    public void makePurchase(Sector s, int quantity) {
        // get event
        // send event to market (get price)
        // send event to user (make transaction)
        // send output to logic
    }
    public void makeSell(Sector s, int quantity) {

    }

}
