package hu.bme.redivel.Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Field extends JPanel implements ActionListener {

    private final Matrix matrix;
    private Tetrimino currentPiece;
    private Tetrimino nextPiece;

    private final Timer timer;
    private boolean paused;
    private int points;

    public Field(){
        setFocusable(true);
        matrix = new Matrix();
        timer = new Timer( 600,this);
        timer.start();
        currentPiece = new Tetrimino();
        nextPiece = new Tetrimino();
        addKeyListener(new ControlAdapter());
    }

    private void draw(Graphics g) {
        matrix.draw(g);
        currentPiece.draw(g);
    }

    private void newShape(){
        Tetrimino t = new Tetrimino();
        matrix.write(currentPiece);
        currentPiece = nextPiece;
        nextPiece = t;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!matrix.gameOver()) {
            if (matrix.crashBottom(currentPiece)) {
                newShape();
            }
            else {
                currentPiece.down();
            }
            repaint();
        }
        else{
            setFocusable(false);
        }
    }

    class ControlAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int keycode = e.getKeyCode();

            if (keycode == 'P') {
                //pause
                return;
            }

            switch (keycode) {

                case KeyEvent.VK_LEFT:
                    if(!matrix.crashLeft(currentPiece))
                        currentPiece.left();
                    break;

                case KeyEvent.VK_RIGHT:
                    if(!matrix.crashRight(currentPiece))
                        currentPiece.right();
                    break;

                case KeyEvent.VK_DOWN:
                    if(!matrix.crashBottom(currentPiece))
                        currentPiece.down();
                    break;

                case KeyEvent.VK_A:
                    currentPiece.rotateLeft();
                    break;

                case KeyEvent.VK_D:
                    currentPiece.rotateRight();
                    break;

                case KeyEvent.VK_SPACE:
                    while(!matrix.crashBottom(currentPiece)) currentPiece.down();
                    break;
            }
            getParent().repaint();
        }
    }
}
