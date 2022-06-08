package exercise1;

import java.util.ArrayList;

/**
 * Class of the monitor of the workers
 */
public class Monitor {
    private ArrayList<Integer> _list;

    /**
     * Constructor
     * 
     * @param list: list of numbers
     */
    public Monitor(ArrayList<Integer> list) {
        this._list = list;
    }

    /**
     * get first two numbers from list if exist else null
     * 
     * @return two numbers or null
     */
    public synchronized int[] getTwoNumbers() {
        int[] nums = new int[2];
        if (_list.size() >= 2) {
            nums[0] = _list.remove(0);
            nums[1] = _list.remove(0);
            return nums;
        }
        return null;
    }

    /**
     * add given number to list
     * 
     * @param num: number to add
     */
    public synchronized void addNumber(int num) {
        _list.add(num);
        notifyAll();
    }

    /**
     * get the only number left in the list
     * 
     * @return the number
     */
    public synchronized int getResult() {
        while (_list.size() != 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return _list.get(0);
    }
}
