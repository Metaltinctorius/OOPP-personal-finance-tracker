package gu.dit213.group28.model.wrappers;

import gu.dit213.group28.model.interfaces.Ievent;
import gu.dit213.group28.model.interfaces.Ieventfacade;

import java.util.concurrent.locks.ReentrantLock;

public class wEventFacade implements Ieventfacade {
    private final ReentrantLock lock = new ReentrantLock();
    private final Ieventfacade facade;

    public wEventFacade(Ieventfacade facade) {
        this.facade = facade;
    }

    @Override
    public Ievent getEmpty() {
        lock.lock();
        Ievent e = facade.getEmpty();
        lock.unlock();
        return e;
    }
}
