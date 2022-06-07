package exercise1;

public class Worker extends Thread {
    private Monitor _m;

    public Worker(Monitor m) {
        this._m = m;
    }

    @Override
    public void run() {
        super.run();
        int[] nums = _m.getTwoNumbers();
        while (nums != null) {
            _m.addNumber(nums[0] + nums[1]);
            nums = _m.getTwoNumbers();
        }
    }
}
