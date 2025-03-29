package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import Elements.PlayMusic;

public class GamePanel extends JPanel implements KeyListener , ActionListener {
    JCheckBox pauseButton ;
    private static boolean play;
    public GamePanel (){
        this.setLayout(null);
//        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(900 , 700));
        PlayMusic playMusic = new PlayMusic("src/resource/SuperHexagonSoundtrack-Hexagoner.wav");
        playMusic.playMusic();

        pauseButton = new JCheckBox();
        pauseButton.setIcon(new ImageIcon("src/resource/icons8-pause-100.png"));
        pauseButton.setSelectedIcon(new ImageIcon("src/resource/icons8-play-100.png"));
        pauseButton.setBounds(0, 600, 100, 100);
        this.add(pauseButton);



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
            play = !pauseButton.isSelected();
        }

    }
    public boolean isPlaying (){
        return play;
    }
    private void drawLine (Graphics2D g2d){

    }
}
