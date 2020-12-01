package hu.bme.redivel.Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;

public class HighscoreFrame extends JFrame {
    private Timer timer;
    private JPanel grid;

    public HighscoreFrame(HighScores highscores, Timer timer) throws HeadlessException {
        this.timer = timer;
        setFocusable(false);
        setSize(300,500);
        grid = new JPanel(new GridBagLayout());
        add(grid);
        GridBagConstraints cPlace = new GridBagConstraints();
        cPlace.weightx = 1;
        cPlace.anchor = GridBagConstraints.EAST;
        cPlace.gridx =0;
        cPlace.gridy =0;
        GridBagConstraints cName = new GridBagConstraints();
        cName.weightx = 26;
        cName.anchor = GridBagConstraints.CENTER;
        cName.gridx =1;
        cName.gridy =0;
        GridBagConstraints cPoint = new GridBagConstraints();
        cPoint.weightx = 3;
        cPoint.anchor = GridBagConstraints.WEST;
        cPoint.gridx =2;
        cPoint.gridy =0;
        int i = 1;
        for (Entry e:highscores.getHighscores()) {
            grid.add(new JLabel(String.valueOf(i)),cPlace);
            grid.add(new JLabel(e.getName()),cName);
            grid.add(new JLabel(String.valueOf(e.getPoints())),cPoint);
            i++;
            cPlace.gridy++;
            cName.gridy++;
            cPoint.gridy++;
        }
        addWindowListener(new HighscoreWindowAdapter());
    }

    class HighscoreWindowAdapter extends WindowAdapter{
        @Override
        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            timer.start();
        }
    }
}
