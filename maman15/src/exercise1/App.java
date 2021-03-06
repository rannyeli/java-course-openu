package exercise1;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Main class
 */
public class App {

    public static void main(String[] args) throws Exception {
        final int MAX_VALUE = 100;
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter number of workers: ");
        int m = sc.nextInt();
        System.out.print("Please enter number of rounds: ");
        int n = sc.nextInt();
        sc.close();

        ArrayList<Integer> list = new ArrayList<Integer>();
        Random rand = new Random();
        for (int i = 0; i < n; i++) { // generate values for list
            list.add(i, rand.nextInt(MAX_VALUE));
        }
        System.out.println("The list: " + list);
        Monitor mon = new Monitor(list);
        Worker[] workers = new Worker[m];
        for (int i = 0; i < workers.length; i++) { // init workers
            workers[i] = new Worker(mon);
        }
        for (int i = workers.length - 1; i >= 0; i--) { // start workers
            workers[i].start();
        }
        System.out.println("Total sum: " + mon.getResult());
    }
}
