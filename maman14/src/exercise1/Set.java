package exercise1;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * generic class of a Set
 */
public class Set<T> {
    private ArrayList<T> _set;

    /**
     * constructor for empty Set
     */
    public Set() {
        this._set = new ArrayList<T>();
    }

    /**
     * constructor with init elements
     * 
     * @param elements: array of the elements to initiate the Set with
     */
    public Set(T[] elements) {
        this._set = new ArrayList<T>();
        for (T element : elements) {
            this.insert(element);
        }
    }

    /**
     * union another Set to this Set
     * 
     * @param s: another Set
     */
    public void union(Set<T> s) {
        for (T element : s._set) {
            this.insert(element);
        }
    }

    /**
     * save to this Set the common elements of this Set with an another Set
     * 
     * @param s: another Set
     */
    public void intersect(Set<T> s) {
        ArrayList<T> temp = new ArrayList<T>();
        for (T element : s._set) {
            if (this.isMember(element)) {
                temp.add(element);
            }
        }
        this._set = temp;
    }

    /**
     * check if this Set contains another Set's elements
     * 
     * @param s: another Set
     * @return true if it does, else return false
     */
    public boolean isSubset(Set<T> s) {
        return this._set.containsAll(s._set);
    }

    /**
     * check if this Set contains an element
     * 
     * @param element: element to check
     * @return true if it does, else return false
     */
    public boolean isMember(T element) {
        return this._set.contains(element);
    }

    /**
     * insert an element to this Set if not already exist
     * 
     * @param element: element to insert
     * @return true if the element was inserted, else if it was already exist return
     *         false
     */
    public boolean insert(T element) {
        if (!this.isMember(element)) {
            this._set.add(element);
            return true;
        }
        return false;
    }

    /**
     * delete an element from this Set
     * 
     * @param element: element to delete
     */
    public void delete(T element) {
        this._set.remove(element);
    }

    /**
     * get an iterator over this Set
     * 
     * @return iterator
     */
    public Iterator<T> iterator() {
        return this._set.iterator();
    }

    /**
     * return a string that represents the Set
     */
    public String toString() {
        return this._set.toString();
    }

}
