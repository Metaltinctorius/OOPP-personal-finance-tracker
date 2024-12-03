package gu.dit213.group28.model.wrappers;

import gu.dit213.group28.model.interfaces.Ievent;
import gu.dit213.group28.model.interfaces.Iuser;

import java.util.concurrent.locks.ReentrantLock;

public class wUser implements Iuser {
    private final ReentrantLock lock = new ReentrantLock();
    private final Iuser user;
    public wUser(Iuser user) {
        this.user = user;
    }
    @Override
    public void accept(Ievent e) {
        lock.lock();
        user.accept(e);
        lock.unlock();
    }
}
