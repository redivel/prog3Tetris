package hu.bme.redivel.Tetris;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Field extends JPanel implements ActionListener {

    private final int width = 10;
    private final int height = 22;

    private Timer timer;
    private boolean paused;
    private int points;

    public void Field(){
        timer = new Timer(400,this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
