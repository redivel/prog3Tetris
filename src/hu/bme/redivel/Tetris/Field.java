package hu.bme.redivel.Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Field extends JPanel implements ActionListener {

    private Matrix matrix;
    private Tetrimino currentPiece;
    private Tetrimino nextPiece;

    private final Timer timer;
    private boolean paused, gameOver;
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
        this.timer.start();
        setPreferredSize(new Dimension(240,420));
        addKeyListener(new ControlAdapter());
        points = 0;
    }

    public void reset(){
        matrix = new Matrix(12,23);
        currentPiece = new Tetrimino();
        nextPiece = new Tetrimino();
        currentPiece.pushToNext();
        currentPiece.pushToSpawn();
        nextPiece.pushToNext();
        this.timer.start();
        setPreferredSize(new Dimension(240,420));
        addKeyListener(new ControlAdapter());
        points = 0;
    }

    private void draw(Graphics g) {
        matrix.draw(g);
        currentPiece.draw(g);
    }

    public void newPiece(){
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

    public boolean gameOver() {
        return gameOver;
    }

    public void save(String saveName){
        ObjectOutputStream saveFile;
        try{
            saveFile = new ObjectOutputStream(new FileOutputStream("Saves/"+saveName));
            saveFile.writeObject(matrix);
            saveFile.writeObject(currentPiece);
            saveFile.writeObject(nextPiece);
            saveFile.writeObject(points);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void load(String saveName){
        ObjectInputStream saveFile;
        try{
            saveFile = new ObjectInputStream(new FileInputStream("Saves/"+saveName));
            matrix = (Matrix) saveFile.readObject();
            currentPiece = (Tetrimino) saveFile.readObject();
            nextPiece = (Tetrimino) saveFile.readObject();
            points = (int) saveFile.readObject();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        gameOver = matrix.gameOver();
        if(!gameOver) {
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
