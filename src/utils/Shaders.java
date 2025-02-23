package utils;

import game.GamePanel;
import java.awt.*;
import java.util.Random;
import javax.swing.ImageIcon;

public class Shaders implements Runnable {
    private static final int NUM_OBJECTS = 5; // Number of images on screen
    private int[] x, y; // Positions of images
    private int dx = 3, dy = 3; // Speed for all images
    private Image image;
    private Random random = new Random();
    private boolean running = true; // Controls thread execution
    private GamePanel gamePanel; // Reference to GamePanel

    public Shaders (GamePanel gamePanel, String path) {
        this.gamePanel = gamePanel;
        image = new ImageIcon(path).getImage();

        // Initialize arrays
        x = new int[NUM_OBJECTS];
        y = new int[NUM_OBJECTS];

        for (int i = 0; i < NUM_OBJECTS; i++) {
            resetPosition(i);
        }

        // Start the movement thread
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (running) {
            for (int i = 0; i < NUM_OBJECTS; i++) {
                x[i] += dx;
                y[i] += dy;

                // If object moves out of bounds, respawn it
                if (x[i] > Defines.SCREEN_WIDTH || y[i] > Defines.SCREEN_HEIGHT) {
                    int index = i;
                    new Thread(() -> {
                        try {
                            Thread.sleep(random.nextInt(1000)); // 0-1 sec delay
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        resetPosition(index);
                    }).start();
                }
            }

            // Request GamePanel to repaint
            gamePanel.repaint();

            try {
                Thread.sleep(1000 / Defines.FPS);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
                e.printStackTrace();
            }
        }
    }

    private void resetPosition(int index) {
        boolean spawnLeft = random.nextBoolean();
        if (spawnLeft) {
            x[index] = random.nextInt(Defines.SCREEN_WIDTH / 2);
        } else {
            x[index] = Defines.SCREEN_WIDTH / 2 + random.nextInt(Defines.SCREEN_WIDTH / 2);
        }
        y[index] = random.nextInt(Defines.SCREEN_HEIGHT / 4);
    }

    public void draw(Graphics g) {
        for (int i = 0; i < NUM_OBJECTS; i++) {
            g.drawImage(image, x[i], y[i], null);
        }
    }
}
