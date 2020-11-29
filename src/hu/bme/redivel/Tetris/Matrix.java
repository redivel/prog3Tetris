package hu.bme.redivel.Tetris;

import java.awt.*;
import java.util.Vector;

public class Matrix {
    private final Vector<Vector<Block>> matrix;
    private int width, height;
    private int removed;

    public Matrix(int width, int height) {
        this.width = width;
        this.height = height;
        matrix = new Vector<>();
        for (int i = 0; i < height; i++) {
            matrix.add(new Vector<>());
            for (int j = 0; j < width; j++){
                if (j == 0 || j == width-1 || i == height-1){
                    matrix.get(i).add(j,new Block(j,i,Variations.Border));
                }
                else{
                    matrix.get(i).add(j,new Block(j,i,Variations.Empty));
                }
            }
        }
    }

    public void print(){
        for (int j = 0; j < height; j++){
            for (int i = 0; i < width; i++){
                System.out.print(get(i,j));
                if(i < width-1){
                    System.out.print("\t");
                }
                else{
                    System.out.print("\n");
                }
            }
        }
        System.out.println("\n");
    }

    private Block get(int x, int y){
        return matrix.get(y).get(x);
    }

    public void draw(Graphics g) {
        Block b;
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                if((b = get(i,j)).notEmpty())
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
        for (int i = 1; i < width-1; i++) {
            if(get(i,2).notEmpty()) return true;
        }
        return false;
    }

    private boolean rowFull(int row){
        if( row >= 2 && row < height - 1 ) {
            for (int i = 1; i < width - 1; i++) {
                if (!get(i, row).notEmpty()) return false;
            }
            return true;
        }
        return false;
    }

    public int removeFull(){
        removed = 0;
        for (int i = 0; i < height - 1; i++) {
            if (rowFull(i)){
                removeRow(i);
                removed++;
            }
        }
        return removed;
    }

    private void removeRow(int row){
        for (int i = row; i >= 0; i--) {
            Vector<Block> prev;
            if (i == 0){
                prev = newLine();
            }
            else {
                prev = matrix.get(i-1);
            }
            matrix.setElementAt(prev,i);
            for (Block b:matrix.get(i)){
                b.down();
            }
        }
    }

    private Vector<Block> newLine(){
        Vector<Block> newLine = new Vector<>();
        for (int i = 0; i < width; i++) {
            if (i == 0 || i == width-1){
                newLine.add(i,new Block(i,0,Variations.Border));
            }
            else{
                newLine.add(i,new Block(i,0,Variations.Empty));
            }
        }
        return newLine;
    }

    public boolean canRotate(Tetrimino t, int dir) {
        t.rotate(dir);
        for(Block b:t.getCurShape().getBlocks()){
            if(get(b.getX(),b.getY()).getColor() != Variations.Empty) {
                t.rotate(-dir);
                return false;
            }
        }
        t.rotate(-dir);
        return true;
    }

    public void delete(Block b){
        b.eraseFromMatrix(matrix);
    }
 
}
