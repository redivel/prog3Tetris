package hu.bme.redivel.Tetris;

import java.io.Serializable;

public class Shape implements Serializable {
    private Block[] blocks;

    public Shape(int[][] coords, Variations color){
        blocks = new Block[]{
                new Block(coords[0],color),
                new Block(coords[1],color),
                new Block(coords[2],color),
                new Block(coords[3],color)
        };
    }

    public Block[] getBlocks(){
        return blocks;
    }
}
