package exercise2;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Worker extends Thread {
    private Integer _id;
    private int _i;
    private int _leftI;
    private int _rightI;
    private Worker[] _workers;
    private int _m;
    private int _checked = 0;
    private int _round = 1;
    private static int _done = 0;
    private static Lock lock = new ReentrantLock();
    private static Condition cond = lock.newCondition();

    public Worker(int id, int i, Worker[] workers, int m) {
        this._id = id;
        this._i = i;
        this._workers = workers;
        this._m = m;

        if (_i == 0) {
            _leftI = _workers.length - 1;
            _rightI = _i + 1;
        } else if (_i == _workers.length - 1) {
            _leftI = _i - 1;
            _rightI = 0;
        } else {
            _leftI = _i - 1;
            _rightI = _i + 1;
        }
    }

    public synchronized int getWorkerId() {
        _checked++;
        notifyAll();
        return _id;
    }

    public void process() {
        int leftId = _workers[_leftI].getWorkerId();
        int rightId = _workers[_rightI].getWorkerId();
        synchronized (this) {
            while (_checked == 0 || _checked % 2 != 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (_id < leftId && _id < rightId) {
                _id++;
            } else if (_id > leftId && _id > rightId) {
                _id--;
            }
            _done++;
        }
    }

    @Override
    public void run() {
        super.run();
        while (_round <= _m) {
            process();
            _round++;

            lock.lock();
            try {
                while (_done == 0 || _done % _m != 0) {
                    cond.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

            lock.lock();
            try {
                if (_done != 0 && _done % _m == 0) {
                    System.out.println(Arrays.toString(_workers));
                    cond.signalAll();
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public String toString() {
        return _id.toString();
    }
}
