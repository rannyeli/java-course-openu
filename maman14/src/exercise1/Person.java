package exercise1;

public class Person implements Comparable<Person> {
    private String _id;
    private String _firstName;
    private String _lastName;
    private int _birthYear;

    public Person(String id, String firstName, String lastName, int birthYear) {
        this._id = id;
        this._firstName = firstName;
        this._lastName = lastName;
        this._birthYear = birthYear;
    }

    @Override
    public int compareTo(Person p) {
        return this._id.compareTo(p._id);
    }

    public String toString() {
        return "id: " + this._id + ", Full name: " + this._firstName + " " + this._lastName + ", Birth year: "
                + this._birthYear;
    }
}
