package exercise2;

import java.util.Arrays;
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
        int n = sc.nextInt();
        System.out.print("Please enter number of rounds: ");
        int m = sc.nextInt();
        sc.close();

        Random rand = new Random();
        Worker[] workers = new Worker[n];
        for (int i = 0; i < workers.length; i++) { // init workers
            workers[i] = new Worker(rand.nextInt(MAX_VALUE), i, workers, m);
        }
        System.out.println(Arrays.toString(workers));
        for (int i = workers.length - 1; i >= 0; i--) { // start workers
            workers[i].start();
        }

    }
}
