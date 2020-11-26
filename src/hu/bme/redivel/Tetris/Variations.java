package hu.bme.redivel.Tetris;

import java.util.HashMap;
import java.util.Map;

public enum Variations {
    Empty('E',0,0,0,0),
    Border(-1,'B',0,20),
    I(0,'I',20,40),
    O(1,'O',40,60),
    J(2,'J',60,80),
    L(3,'L',80,100),
    Z(4,'Z',100,120),
    S(5,'S',120,140),
    T(6,'T',140,160);

    private int value = -1;
    private static Map map = new HashMap<>();
    private final char label;
    private final int x1, x2;
    private int y1=0,y2=20;

    Variations(int value, char label, int x1, int x2) {
        this.value = value;
        this.label = label;
        this.x1 = x1;
        this.x2 = x2;
    }

    Variations(char label, int x1, int x2, int y1, int y2) {
        this.label = label;
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    static {
        for(Variations variation : Variations.values()){
            map.put(variation.value, variation);
        }
    }

    public int x1() {return x1;}
    public int x2() {return x2;}
    public int y1() {return y1;}
    public int y2() {return y2;}
    public int value() {return value;}

    public static Variations valueOf(int variation){
        return (Variations)map.get(variation);
    }

    @Override
    public String toString() {
        return String.valueOf(label);
    }
}
