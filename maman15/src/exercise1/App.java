package exercise1;

import java.util.ArrayList;
import java.util.Random;

/**
 * Main class
 */
public class App {

    public static void main(String[] args) throws Exception {
        final int M = 2; // number of workers
        final int N = 10; // number of number in list
        final int MAX_VALUE = 100;
        ArrayList<Integer> list = new ArrayList<Integer>();
        Random rand = new Random();
        for (int i = 0; i < N; i++) { // generate values for list
            list.add(i, rand.nextInt(MAX_VALUE));
        }
        System.out.println("The list: " + list);
        Monitor mon = new Monitor(list);
        Worker[] workers = new Worker[M];
        for (int i = 0; i < workers.length; i++) { // init workers
            workers[i] = new Worker(mon);
        }
        for (int i = workers.length - 1; i >= 0; i--) { // start workers
            workers[i].start();
        }
        System.out.println("Total sum: " + mon.getResult());

    }
}
