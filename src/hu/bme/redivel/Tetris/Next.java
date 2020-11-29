package hu.bme.redivel.Tetris;

import javax.swing.*;
import java.awt.*;

public class Next extends JPanel {
    private Tetrimino nextPiece;
    private Matrix matrix;

    public Next(Tetrimino nextPiece) {
        this.nextPiece = nextPiece;
        matrix = new Matrix(12,12);
    }

    private void draw(Graphics g) {
        nextPiece.draw(g);
        matrix.draw(g);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void setNextPiece(Tetrimino nextPiece){
        this.nextPiece = nextPiece;
    }
}
