package hu.bme.redivel.Tetris;

public enum Variations {
    Empty('E',0,0,0,0),
    Border('B',0,20),
    I('I',20,40),
    O('O',40,60),
    J('J',60,80),
    L('L',80,100),
    Z('Z',100,120),
    S('S',120,140),
    T('T',140,160);

    private final char label;
    private int x1, x2;
    private int y1=0,y2=20;

    Variations(char label, int x1, int x2) {
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

    public int x1() {return x1;}
    public int x2() {return x2;}
    public int y1() {return y1;}
    public int y2() {return y2;}

    @Override
    public String toString() {
        return String.valueOf(label);
    }
}
