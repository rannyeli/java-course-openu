package exercise1;

import java.util.ArrayList;
import java.util.Iterator;

public class Set<T> {
    private ArrayList<T> _set;

    public Set() {
        this._set = new ArrayList<T>();
    }

    public Set(T[] elements) {
        this._set = new ArrayList<T>();
        for (T element : elements) {
            this.insert(element);
        }
    }

    public void union(Set<T> s) {
        for (T element : s._set) {
            this.insert(element);
        }
    }

    public void intersect(Set<T> s) {
        ArrayList<T> temp = new ArrayList<T>();
        for (T element : s._set) {
            if (this.isMember(element)) {
                temp.add(element);
            }
        }
        this._set = temp;
    }

    public boolean isSubset(Set<T> s) {
        return this._set.containsAll(s._set);
    }

    public boolean isMember(T element) {
        return this._set.contains(element);
    }

    public boolean insert(T element) {
        if (!this.isMember(element)) {
            this._set.add(element);
            return true;
        }
        return false;
    }

    public void delete(T element) {
        this._set.remove(element);
    }

    public Iterator<T> iterator() {
        return this._set.iterator();
    }

    public String toString() {
        return this._set.toString();
    }

}
