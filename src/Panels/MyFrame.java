package Panels;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MyFrame extends JFrame {
    static JPanel panel ;
    static CardLayout cardLayout ;
    private static Clip soundClip ;
    public MyFrame(){
        this.setPreferredSize(new Dimension(900 , 730));
        this.setTitle("Super Hexagon");
        this.setIconImage(new ImageIcon
                ("src/resource/icons8-beeswax-48.png")
                .getImage());
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(90 , 70);
        playSound("src/resource/superhexagon.wav");
        cardLayout = new CardLayout();
        panel = new JPanel(cardLayout);

        SettingPanel settingPanel = new SettingPanel();
        MainPanel mainPanel = new MainPanel();
        StartPanel startPanel = new StartPanel();
        HistoryPanel historyPanel = new HistoryPanel();
        GamePanel gamePanel = new GamePanel();
        panel.add(mainPanel , "main");
        panel.add(startPanel , "start");
        panel.add(settingPanel , "setting");
        panel.add(historyPanel , "history");
        panel.add(gamePanel , "game");

        cardLayout.show(panel , "main");

        this.add(panel);
        this.pack();

        this.setVisible(true);
    }

    public static void  switchPanel (String panelName) {
        cardLayout.show(panel, panelName);
    }

    public static void designButton (JButton button , int x , int y , String path){
        ImageIcon icon = new ImageIcon(path);
        button.setBounds(x , y , 200 , 40);
        button.setIcon(icon);
        //Color.decode("#edc9af")
        button.setBackground(Color.white);
        button.setFocusable(false);
        // "Ink Free"
        button.setFont(new Font( "Comic Sans MS" , Font.PLAIN , 30));
    }
    public static void playSound (String path){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path)) ;
            soundClip = AudioSystem.getClip();
            soundClip.open (audioInputStream);
            soundClip.start();
        } catch (Exception ex) {
            System.out.println("Error loading sound: " + ex.getMessage());
        }
    }

}
