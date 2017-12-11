package io.github.nikmang.dec0617;

/**
 * Created by Nik on 12/6/2017.
 *
 * Isn't exactly a tuple per se but something just for this assignment
 */
public class Tuple implements Comparable<Tuple>{

    public final Integer k;
    public final Integer v;

    public Tuple(Integer key, Integer value) {
        this.k = key;
        this.v = value;
    }

    @Override
    public int compareTo(Tuple tuple) {
        return k.compareTo(tuple.k);
    }

    @Override
    public String toString() {
        return "(" + k + "," + v + ")";
    }

    @Override
    public int hashCode() {
        return 37*(k.hashCode()^v.hashCode());
    }

    @Override
    public boolean equals(Object o) {
        if(o == this)
            return true;

        if(!(o instanceof Tuple))
            return false;

        Tuple tuple = (Tuple) o; //Not an issue this time round

        return tuple.k.equals(k) && tuple.v.equals(v);
    }
}
