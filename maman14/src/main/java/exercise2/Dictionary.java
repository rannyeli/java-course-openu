package exercise2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

/**
 * Class of a Dictionary
 */
public class Dictionary implements Serializable {
    private SortedMap<String, String> _dict;

    /**
     * constructor
     */
    public Dictionary() {
        this._dict = new TreeMap<String, String>();
    }

    /**
     * save a term and it's meaning to the dictionary
     * 
     * @param term:    term to define
     * @param meaning: meaning of the term
     */
    public void save(String term, String meaning) {
        this._dict.put(term, meaning);
    }

    /**
     * delete a term and it's meaning
     * 
     * @param term: term to delete
     */
    public void delete(String term) {
        this._dict.remove(term);
    }

    /**
     * an iterator for all the terms in the dictionary
     * 
     * @return iterator object
     */
    public Iterator<Entry<String, String>> iterator() {
        Set<Entry<String, String>> s = this._dict.entrySet();
        return s.iterator();
    }

    /**
     * imports terms and meanings from a given file and saves to this dictionary
     * 
     * @param file: file to import from
     */
    public void importDict(File file) {
        if (file != null) {
            try {
                FileInputStream fi = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fi);
                this._dict = (SortedMap<String, String>) ois.readObject();
                ois.close();
                fi.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * exports terms and meanings to a given file
     * 
     * @param file: file to export to
     */
    public void exportDict(File file) {
        try {
            FileOutputStream fo = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fo);
            out.writeObject(this._dict);
            out.close();
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
