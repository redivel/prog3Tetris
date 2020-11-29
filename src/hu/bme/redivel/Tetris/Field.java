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

    public Field(Timer timer){
        setFocusable(true);
        matrix = new Matrix(12,23);
        this.timer = timer;
        currentPiece = new Tetrimino();
        nextPiece = new Tetrimino();
        currentPiece.pushToNext();
        currentPiece.pushToSpawn();
        nextPiece.pushToNext();
        setPreferredSize(new Dimension(240,420));
        addKeyListener(new ControlAdapter());
        points = 0;
    }

    private void draw(Graphics g) {
        matrix.draw(g);
        currentPiece.draw(g);
    }

    private void newPiece(){
        Tetrimino t = new Tetrimino();
        matrix.write(currentPiece);
        currentPiece = nextPiece;
        nextPiece = t;
        currentPiece.pushToSpawn();
        nextPiece.pushToNext();
    }

    public Tetrimino getNextPiece() {
        return nextPiece;
    }

    public int getPoints() {
        return points;
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
                newPiece();
                points += matrix.removeFull();
                matrix.print();
            }
            else {
                currentPiece.down();
            }
            repaint();
        }
        else{
            timer.stop();
            GameOverDialog popup = new GameOverDialog();
            String name = JOptionPane.showInputDialog(getParent().getParent().getParent().getParent(), "What is your name?", null);

        }
    }

    class ControlAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int keycode = e.getKeyCode();

            if (keycode == 'P') {
                if(paused){
                    paused = false;
                    timer.start();
                }
                else {
                    paused = true;
                    timer.stop();
                }
                return;
            }

            switch (keycode) {

                case KeyEvent.VK_LEFT:
                    if(!matrix.crashLeft(currentPiece) && !matrix.crashBottom(currentPiece))
                        currentPiece.left();
                    break;

                case KeyEvent.VK_RIGHT:
                    if(!matrix.crashRight(currentPiece) && !matrix.crashBottom(currentPiece))
                        currentPiece.right();
                    break;

                case KeyEvent.VK_DOWN:
                    if(!matrix.crashBottom(currentPiece))
                        currentPiece.down();
                    break;

                case KeyEvent.VK_A:
                    if(matrix.canRotate(currentPiece,-1))
                        currentPiece.rotate(-1);
                    break;

                case KeyEvent.VK_D:
                    if(matrix.canRotate(currentPiece,1))
                        currentPiece.rotate(1);
                    break;

                case KeyEvent.VK_SPACE:
                    while(!matrix.crashBottom(currentPiece)) currentPiece.down();
                    break;
            }
            repaint();
        }
    }
}
