package hu.bme.redivel.Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class TetrisFrame extends JFrame implements ActionListener{

    private Field field;
    private JPanel right;
    private JPanel next;
    private JPanel sidePanel;
    private JLabel lineLabel;
    private BufferedImage image;
    private Timer timer;
    private Sound tetris;
    private int lines;

    public TetrisFrame() throws HeadlessException {
        setTitle("Tetris");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
        timer = new Timer(300,this);
        timer.start();

        GridLayout layout = new GridLayout(1,2);
        setLayout(layout);

        field = new Field(timer);
        add(field);


        right = new JPanel();
        GridLayout rightLayout = new GridLayout(2,1);
        right.setLayout(rightLayout);

        next = new JPanel();
        right.add(next);

        sidePanel = new JPanel(new FlowLayout());
        JLabel lines_string = new JLabel("Lines: ");
        sidePanel.add(lines_string);
        lines = 0;
        lineLabel = new JLabel(String.valueOf(lines));

        sidePanel.add(lineLabel);


        right.add(sidePanel);

        add(right);

        tetris = new SoundLooped("TetrisDnB.wav");
        tetris.play();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (field.getPoints() != lines) {
            lines = field.getPoints();
            lineLabel.setText(String.valueOf(lines));
            lineLabel.repaint();
        }
    }
}
