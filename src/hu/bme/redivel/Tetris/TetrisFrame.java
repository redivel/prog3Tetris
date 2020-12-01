package hu.bme.redivel.Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

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
        add(field,0);

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
        saveButton.addActionListener(new SaveButtonActionListener());
        sidePanel.add(saveButton,constraints);

        constraints.gridy = 4;
        loadButton = new JButton("LOAD");
        loadButton.setFocusable(false);
        loadButton.addActionListener(new LoadButtonActionListener());
        sidePanel.add(loadButton,constraints);

        constraints.gridy = 5;
        highscoresButton = new JButton("HIGHSCORES");
        highscoresButton.setFocusable(false);
        highscoresButton.addActionListener(new HighscoresButtonActionListener());
        sidePanel.add(highscoresButton,constraints);

        right.add(sidePanel);
        add(right,1);

        timer.addActionListener(field);

        tetrisSound = new SoundLooped("tetris.wav");
//        tetrisSound.play();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!field.gameOver()) {
            if (field.getPoints() != lines) {
                lines = field.getPoints();
                lineString = "Lines: " + lines;
                lineLabel.setText(lineString);
                lineLabel.repaint();
            }
            next.setNextPiece(field.getNextPiece());
            next.repaint();
        }
        else{
            String name = JOptionPane.showInputDialog(this, "Enter name and press OK to add to Highscores!", null);
            if (name != null) highscores.addEntry(name,lines);
            field.reset();
            lines = 0;
        }
    }

    private class SaveButtonActionListener implements ActionListener{

        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            timer.stop();
            String saveName;
            saveName = JOptionPane.showInputDialog(TetrisFrame.this, "Enter name for save file and press OK to save!", null);
            if (saveName != null) field.save(saveName);
            timer.start();
        }
    }

    private class LoadButtonActionListener implements ActionListener{

        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            timer.stop();
            String saveName = JOptionPane.showInputDialog(TetrisFrame.this, "Enter name of save file and press OK to load!", null);
            if (saveName != null) field.load(saveName);
            timer.start();
        }
    }

    private class HighscoresButtonActionListener implements ActionListener{

        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            timer.stop();
            HighscoreFrame highscoresFrame = new HighscoreFrame(highscores,timer);
            highscoresFrame.setVisible(true);
        }
    }
}
