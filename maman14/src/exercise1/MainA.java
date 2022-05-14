package exercise1;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class MainA {
    private static final int MIN_RANDOM = 0;
    private static final int MAX_RANDOM = 100;
    private static final int NUM_OF_ELEMENTS = 10;

    private static void fillSetWithRandomIntegers(Set<Integer> s) {
        int randomNum;
        for (int i = 0; i < NUM_OF_ELEMENTS; i++) {
            randomNum = ThreadLocalRandom.current().nextInt(MIN_RANDOM, MAX_RANDOM);
            if (!s.insert(randomNum)) {
                i--;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Set<Integer> s1 = new Set<Integer>();
        Set<Integer> s2 = new Set<Integer>();
        Set<Integer> s3 = new Set<Integer>();
        fillSetWithRandomIntegers(s1);
        fillSetWithRandomIntegers(s2);
        fillSetWithRandomIntegers(s3);

        System.out.println("First Set: " + s1.toString());
        System.out.println("Second Set: " + s2.toString());
        System.out.println("Third Set: " + s3.toString());

        s1.union(s2);
        System.out.println("Union of the first and the second Sets:");
        System.out.println(s1.toString());

        s1.intersect(s3);
        System.out.println("Intersection of the unioned Set and the third Set");
        System.out.println(s1.toString());

        Scanner sc = new Scanner(System.in);

        Integer[] nums = new Integer[2];
        System.out.print("Please enter two numbers: ");
        nums[0] = sc.nextInt();
        nums[1] = sc.nextInt();
        Set<Integer> s4 = new Set<Integer>(nums);
        System.out.println("Fourth Set:" + s4.toString());
        System.out.printf("Is the fourth Set is a susbset of the first Set? - %b\n", s1.isSubset(s4));
        System.out.printf("Is the fourth Set is a susbset of the second Set? - %b\n", s2.isSubset(s4));
        System.out.printf("Is the fourth Set is a susbset of the third Set? - %b\n", s3.isSubset(s4));

        System.out.print("Please enter a number: ");
        int num = sc.nextInt();
        System.out.printf("Is the number is a member of the first Set? - %b\n", s1.isMember(num));
        s2.insert(num);
        System.out.println("Second Set after inserting the number into it:");
        System.out.println(s2.toString());
        s3.delete(num);
        System.out.println("Third Set after deleting the number from it:");
        System.out.println(s3.toString());

        sc.close();
    }
}
