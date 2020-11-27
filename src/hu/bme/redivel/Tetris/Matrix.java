package hu.bme.redivel.Tetris;

import java.awt.*;
import java.util.Vector;

public class Matrix {
    private final Vector<Vector<Block>> matrix;

    public Matrix() {
        matrix = new Vector<>();
        for (int i = 0; i < 12; i++) {
            matrix.add(new Vector<>());
            for (int j = 0; j < 23; j++){
                if (i == 0 || i == 11 || j == 22){
                    matrix.get(i).add(j,new Block(i,j,Variations.Border));
                }
                else{
                    matrix.get(i).add(j,new Block(i,j,Variations.Empty));
                }
            }
        }
    }

    public void print(){
        for (int j = 0; j < 23; j++){
            for (int i = 0; i < 12; i++){
                System.out.print(get(i,j));
                if(i < 11){
                    System.out.print("\t");
                }
                else{
                    System.out.print("\n");
                }
            }
        }
    }

    private Block get(int x, int y){
        return matrix.get(x).get(y);
    }

    public void draw(Graphics g) {
        Block b;
        for (int i = 0; i < 12; ++i) {
            for (int j = 0; j < 21; ++j) {
                if((b = get(i,j+2)).notEmpty())
                    b.draw(g);
            }
        }
    }

    public void write(Tetrimino t){
        for (Block b:t.getCurShape().getBlocks()) {
            b.writeToMatrix(matrix);
        }
    }

    public boolean crashBottom(Tetrimino t) {
        for(Block b:t.getCurShape().getBlocks()){
            if(get(b.getX(),b.getY()+1).getColor() != Variations.Empty)
                return true;
        }
        return false;
    }

    public boolean crashLeft(Tetrimino t) {
        for(Block b:t.getCurShape().getBlocks()){
            if(get(b.getX()-1,b.getY()).getColor() != Variations.Empty)
                return true;
        }
        return false;
    }

    public boolean crashRight(Tetrimino t) {
        for(Block b:t.getCurShape().getBlocks()){
            if(get(b.getX()+1,b.getY()).getColor() != Variations.Empty)
                return true;
        }
        return false;
    }

    public boolean gameOver() {
        for (int i = 1; i < matrix.size()-1; i++) {
            if(get(i,2).notEmpty()) return true;
        }
        return false;
    }

    public void delete(Block b){
        b.eraseFromMatrix(matrix);
    }
 
}
