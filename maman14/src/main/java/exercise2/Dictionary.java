package exercise2;

import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

public class Dictionary {
    private SortedMap<String, String> _dict;

    public Dictionary() {
        this._dict = new TreeMap<String, String>();
    }

    public void save(String term, String meaning) {
        this._dict.put(term, meaning);
    }

    public void delete(String term) {
        this._dict.remove(term);
    }

    public Iterator<Entry<String, String>> iterator() {
        Set<Entry<String, String>> s = this._dict.entrySet();
        return s.iterator();
    }
}
