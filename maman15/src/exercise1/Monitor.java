package exercise1;

import java.util.ArrayList;

public class Monitor {
    private ArrayList<Integer> _list;

    public Monitor(ArrayList<Integer> list) {
        this._list = list;
    }

    public synchronized int[] getTwoNumbers() {
        int[] nums = new int[2];
        if (_list.size() >= 2) {
            nums[0] = _list.remove(0);
            nums[1] = _list.remove(0);
            return nums;
        }
        return null;
    }

    public synchronized void addNumber(int num) {
        _list.add(num);
        notifyAll();
    }

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
