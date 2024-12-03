package gu.dit213.group28.model;

import gu.dit213.group28.model.events.EventFacade;
import gu.dit213.group28.model.interfaces.*;
import gu.dit213.group28.model.market.Market;
import gu.dit213.group28.model.user.Portfolio;

import java.util.concurrent.locks.ReentrantLock;


public class GameCore {
    private ReentrantLock lockEvent;
    private ReentrantLock lockMarket;
    private ReentrantLock lockUser;
    private ReentrantLock lockLogic;
    private Itimer timer;
    private Ieventfacade eventFacade;
    private Ilogic logic;
    private Imarket market;
    private Iuser user;

    public GameCore(Logic logic) {
        lockEvent = new ReentrantLock();
        lockMarket = new ReentrantLock();
        lockUser = new ReentrantLock();
        lockLogic = new ReentrantLock();
        timer = new Time();
        timer.initTime();
        eventFacade = new EventFacade();
        this.logic = logic;
        market = Market.getInstance("hej", 1.07);
        Iuser user = new Portfolio();

    }
    public void init() {
        timer.start();
        new Thread(new Runnable() {
            public void run() {
                lockEvent.lock();
                Ievent e = eventFacade.getEmpty();
                lockEvent.unlock();
                lockMarket.lock();
                market.accept(e);
                String m = market.getState();
                lockMarket.unlock();
                lockLogic.lock();
                logic.timerUpdate(m);
                lockLogic.unlock();
            }
        }).start();
    }

}
