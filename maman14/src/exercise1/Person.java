package exercise1;

/**
 * class which represents a person
 */
public class Person implements Comparable<Person> {
    private String _id;
    private String _firstName;
    private String _lastName;
    private int _birthYear;

    /**
     * constructor
     * 
     * @param id:        person's id
     * @param firstName: person's first name
     * @param lastName:  person's last name
     * @param birthYear: person's birth year
     */
    public Person(String id, String firstName, String lastName, int birthYear) {
        this._id = id;
        this._firstName = firstName;
        this._lastName = lastName;
        this._birthYear = birthYear;
    }

    /**
     * implemantation of compareTo of Comparable interface.
     * compares this person to another by their IDs
     */
    @Override
    public int compareTo(Person p) {
        return this._id.compareTo(p._id);
    }

    /**
     * return a string that represents the person
     */
    public String toString() {
        return "id: " + this._id + ", Full name: " + this._firstName + " " + this._lastName + ", Birth year: "
                + this._birthYear;
    }
}
