package gu.dit213.group28.model.wrappers;

import gu.dit213.group28.model.interfaces.Ilogic;

import java.util.concurrent.locks.ReentrantLock;

public class wLogic implements Ilogic {
    private final ReentrantLock lock = new ReentrantLock();
    private final Ilogic logic;
    public wLogic(Ilogic logic) {
        this.logic = logic;
    }
    @Override
    public void timerUpdate(String m) {
        lock.lock();
        logic.timerUpdate(m);
        lock.unlock();
    }
}
