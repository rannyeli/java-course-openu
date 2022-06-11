package exercise2;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class of Worker Thread
 */
public class Worker extends Thread {
    private Integer _id; // this worker id
    private int _i; // this worker index
    private int _leftI; // left neighbor index
    private int _rightI; // right neighbor index
    private Worker[] _workers; // all workers
    private int _m; // maximum rounds
    private int _checked = 0; // number of times that this worker id was checked
    private int _round = 1; // current round of worker
    private static int _done = 0; // number of times that this worker done update his id
    private static Lock lock = new ReentrantLock();
    private static Condition cond = lock.newCondition();

    /**
     * Constructor
     * 
     * @param id:      worker's ID
     * @param i:       worker's index
     * @param workers: all workers array
     * @param m:       maximum rounds
     */
    public Worker(int id, int i, Worker[] workers, int m) {
        this._id = id;
        this._i = i;
        this._workers = workers;
        this._m = m;

        // find neighbors
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

    /**
     * get this worker's ID
     * 
     * @return worker's ID
     */
    public synchronized int getWorkerId() {
        _checked++;
        notifyAll();
        return _id;
    }

    /**
     * Update this worker ID according to neighbors IDs
     */
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

    /**
     * Worker thread run method to update his ID m times
     */
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

    /**
     * return this worker's ID as string
     */
    public String toString() {
        return _id.toString();
    }
}
