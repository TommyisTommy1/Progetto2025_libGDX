package Gioco;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.JFrame;


import utils.Defines;

public class App {
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        Defines.MAINFRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Defines.MAINFRAME.setResizable(false);
        Defines.MAINFRAME.setUndecorated(true);

        Defines.MAINFRAME.add(Defines.GAME_PANEL);

        Defines.CONTENT_PANEL.add(Defines.GAME_PANEL, BorderLayout.CENTER);
        Defines.CONTENT_PANEL.setBorder(new LineBorder(Color.black, 10));
        // DA SPIEGARE
        Defines.MAINFRAME.setContentPane(Defines.CONTENT_PANEL);
        Defines.MAINFRAME.pack();

        Defines.MAINFRAME.setLocationRelativeTo(null);
        Defines.MAINFRAME.setVisible(true);
        
        Defines.GAME_PANEL.iniziaThreadGioco();
        //Defines.MP3_PLAYER.start();
    }
}
