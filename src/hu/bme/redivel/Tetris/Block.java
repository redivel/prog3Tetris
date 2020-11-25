package hu.bme.redivel.Tetris;

public class Block {
    private int x,y;
    private Variations color;

    public Block(int x, int y, Variations color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override
    public String toString() {
        return color.toString();
    }

    public boolean notEmpty() {return color!=Variations.Empty;}
    public int x1() {return color.x1();}
    public int x2() {return color.x2();}
    public int y1() {return color.y1();}
    public int y2() {return color.y2();}

}
