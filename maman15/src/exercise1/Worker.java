package exercise1;

/**
 * Class of worker which sum numbers
 */
public class Worker extends Thread {
    private Monitor _m;

    /**
     * Constructor
     * 
     * @param m: monitor
     */
    public Worker(Monitor m) {
        this._m = m;
    }

    /**
     * Worker thread run method to sum any two numbers in the moniotrs list
     */
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
