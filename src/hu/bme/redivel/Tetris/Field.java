package hu.bme.redivel.Tetris;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Field extends JPanel implements ActionListener {

    private final int width = 12;
    private final int height = 21;
    private Matrix matrix = new Matrix();

    private Timer timer;
    private boolean paused;
    private int points;

    public void Field(){
        timer = new Timer(400,this);
        timer.start();
    }

    private void draw(Graphics g) {
       for (int i = 0; i < width; ++i) {

            for (int j = 0; j < height; ++j) {

                Block block = matrix.get(i,j+2);

                if (block.notEmpty()) {
                    try {
                        BufferedImage image = ImageIO.read(new File("Blocks.png"));
                        g.drawImage(image, i*20, j*20,i*20+20,j*20+20,block.x1(),block.y1(),block.x2(),block.y2(),null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
