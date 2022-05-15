package exercise1;

import java.util.Iterator;

public class MainB {

    /**
     * generic method that finds the smallest object in a Set of comparable objects
     * 
     * @param s: Set to search in
     * @return the smallest object
     */
    public static <T extends Comparable<T>> T getMinimumOfASet(Set<T> s) {
        Iterator<T> itr = s.iterator();
        T minimum = itr.next();
        while (itr.hasNext()) {
            T element = itr.next();
            if (element.compareTo(minimum) < 0) {
                minimum = element;
            }
        }
        return minimum;
    }

    public static void main(String[] args) throws Exception {
        Person[] persons = new Person[] {
                new Person("1111", "Israel", "Israeli", 1980),
                new Person("2222", "Avi", "Avivi", 1981),
                new Person("3333", "Yitzhak", "Yitzhaki", 1982),
                new Person("4444", "Refael", "Refaeli", 1983),
                new Person("5555", "Yakov", "Yakovi", 1984) };

        Set<Person> personsSet = new Set<Person>(persons);
        System.out.println("Persons:");
        System.out.println(personsSet.toString());
        System.out.println("Minimum person:");
        System.out.println(getMinimumOfASet(personsSet).toString());
    }
}
