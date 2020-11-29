package hu.bme.redivel.Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TetrisFrame extends JFrame implements ActionListener{
    private JPanel right,sidePanel;
    private Field field;
    private Next next;
    private JLabel lineLabel, exitLabel, pauseLabel;
    private JButton saveButton, loadButton, highscoresButton;
    private Timer timer;
    private Sound tetrisSound;
    private int lines;
    String lineString;
    HighScores highscores;

    public TetrisFrame() throws HeadlessException {
        setTitle("Tetris");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
        timer = new Timer(300,this);
        highscores = new HighScores();

        setLayout(new GridLayout(1,2));

        field = new Field(timer);
        add(field);

        right = new JPanel(new GridLayout(2,1));

        next = new Next(field.getNextPiece());
        right.add(next);

        sidePanel = new JPanel(new GridBagLayout());
        sidePanel.setPreferredSize(new Dimension(240,210));
        sidePanel.setMaximumSize(new Dimension(240,210));
        sidePanel.setMinimumSize(new Dimension(240,210));
        sidePanel.setFocusable(false);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.NORTH;

        lines = 0;
        lineString = "Lines: " + lines;
        lineLabel = new JLabel(lineString);
        sidePanel.add(lineLabel,constraints);

        constraints.gridy = 1;
        pauseLabel = new JLabel("Press P to pause");
        sidePanel.add(pauseLabel,constraints);

        constraints.gridy = 2;
        exitLabel = new JLabel("Save before exit");
        sidePanel.add(exitLabel,constraints);

        constraints.gridy = 3;
        saveButton = new JButton("SAVE");
        saveButton.setFocusable(false);
        sidePanel.add(saveButton,constraints);

        constraints.gridy = 4;
        loadButton = new JButton("LOAD");
        loadButton.setFocusable(false);
        sidePanel.add(loadButton,constraints);

        constraints.gridy = 5;
        highscoresButton = new JButton("HIGHSCORES");
        highscoresButton.setFocusable(false);
        sidePanel.add(highscoresButton,constraints);

        right.add(sidePanel);
        add(right);

        timer.addActionListener(field);
        timer.start();

        tetrisSound = new SoundLooped("tetris.wav");
//        tetrisSound.play();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (field.getPoints() != lines) {
            lines = field.getPoints();
            lineString = "Lines: " + lines;
            lineLabel.setText(lineString);
            lineLabel.repaint();
        }
        next.setNextPiece(field.getNextPiece());
        next.repaint();
    }
}
