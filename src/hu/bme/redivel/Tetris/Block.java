package hu.bme.redivel.Tetris;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class Block {
    private int x,y;
    private int dx1,dy1,dx2,dy2;
    private final Variations color;

    public Block(int x, int y, Variations color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public Block(int[] coords, Variations color) {
        this.x = coords[0]+6;
        this.y = coords[1]+1;
        this.color = color;
    }

    @Override
    public String toString() {
        return color.toString();
    }

    public boolean notEmpty() {return color!=Variations.Empty;}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Variations getColor() {
        return color;
    }

    public void down(){
        y++;
    }

    public void left() {
        x--;
    }

    public void right() {
        x++;
    }

    public void draw(Graphics g){
        dx1=x*20;
        dy1=(y-2)*20;
        dx2=dx1+20;
        dy2=dy1+20;
        try {
            BufferedImage image = ImageIO.read(new File("Blocks.png"));
            g.drawImage(image, dx1, dy1, dx2, dy2, color.x1(), color.y1(), color.x2(), color.y2(),null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToMatrix(Vector<Vector<Block>> matrix) {
        matrix.get(x).setElementAt(this,y);
    }

    public void eraseFromMatrix(Vector<Vector<Block>> matrix) {
        matrix.get(x).setElementAt(new Block(x,y,Variations.Empty),y);
    }
}
