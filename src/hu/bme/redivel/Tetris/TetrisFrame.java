package hu.bme.redivel.Tetris;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class TetrisFrame extends JFrame {

    private JPanel field;
    private JPanel next;
    private JPanel sidePanel;
    private BufferedImage image;
    private int points;

    public TetrisFrame() throws HeadlessException {
        setTitle("Tetris");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,500);

        GridLayout layout = new GridLayout(1,2);
        setLayout(layout);

        try {
            image = ImageIO.read(new File("profil.jpg"));
        }
        catch (Exception e){
            System.err.println(e);
        }

        field = new JPanel();

        add(field);

        JPanel right = new JPanel();
        GridLayout rightLayout = new GridLayout(2,1);
        right.setLayout(rightLayout);

        next = new JPanel();
        GridLayout nextLayout = new GridLayout();
        nextLayout.setRows(8);
        nextLayout.setColumns(8);
        next.setLayout(nextLayout);

        JLabel nextpic[] = new JLabel[64];
        for (int i = 0; i < 64; i++) {
            nextpic[i] = new JLabel(new ImageIcon(image));
            nextpic[i].setSize(2,2);
            next.add(nextpic[i]);
        }

        right.add(next);

        sidePanel = new JPanel(new FlowLayout());
        points = 15;
        String side = "Points: " + points;
        JLabel pointLabel = new JLabel(side);
        sidePanel.add(pointLabel);

        right.add(sidePanel);

        add(right);

        Sound tetris = new SoundLooped("TetrisDnB.wav");
        tetris.play();

    }
}
