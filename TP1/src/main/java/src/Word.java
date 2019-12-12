package src;

import java.util.*;

public class Word {

    private ArrayList<Letter> contain;

    public ArrayList<Letter> getContain() {
        return this.contain;
    }

    Iterator<Letter> iterator() {
        return contain.iterator();
    }
}

