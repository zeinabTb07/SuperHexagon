package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import Elements.PlayMusic;

public class GamePanel extends JPanel implements KeyListener , ActionListener {
    private JCheckBox pauseButton ;
    private static PlayMusic playMusic ;
    private static boolean play ;
    public GamePanel (){
        this.setLayout(null);
        this.setPreferredSize(new Dimension(900 , 700));
        playMusic = new PlayMusic("src/resource/SuperHexagonSoundtrack-Hexagoner.wav");

        pauseButton = new JCheckBox();
        pauseButton.setIcon(new ImageIcon("src/resource/icons8-pause-100.png"));
        pauseButton.setSelectedIcon(new ImageIcon("src/resource/icons8-play-100.png"));
        pauseButton.addActionListener(this);
        pauseButton.setBounds(0, 600, 100, 100);
        this.add(pauseButton);



    }
    public void paint (Graphics g){
        g.drawLine(0 , 700 , 900 , 700);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==pauseButton){
            if(pauseButton.isSelected()){
                playMusic.stopMusic();
            } else if (play ) {
                playMusic.playMusic();
            }
        }

    }
    public static void playMusic(boolean bool){
        play = bool;
        if(play){
            playMusic.playMusic();
        }
    }
}
