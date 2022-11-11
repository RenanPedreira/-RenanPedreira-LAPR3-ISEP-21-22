package lapr.project.ui;

import lapr.project.utils.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Luís Araújo
 */
public class ShowSketchUI implements Runnable {
    String filename;

    public ShowSketchUI(String filename) {
        this.filename = filename;
        run();
    }

    @Override
    public void run() {
        Utils.print("Sketch:");

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("src//main//resources//" + filename));
            ImageIcon icon = new ImageIcon(img);
            JFrame frame = new JFrame();
            frame.setLayout(new FlowLayout());
            frame.setSize(img.getWidth(), img.getHeight());
            JLabel lbl = new JLabel();
            lbl.setIcon(icon);
            frame.add(lbl);
            frame.setAlwaysOnTop(true);
            frame.setVisible(true);
            frame.toFront();
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
