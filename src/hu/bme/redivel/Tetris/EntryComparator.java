package hu.bme.redivel.Tetris;

import java.util.Comparator;

public class EntryComparator implements Comparator<Entry> {
    @Override
    public int compare(Entry e1, Entry e2) {
        return Double.compare(e2.getPoints(), e1.getPoints());
    }
}
