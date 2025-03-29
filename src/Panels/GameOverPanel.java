package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverPanel extends JPanel implements ActionListener {
    JButton replayButton ;
    JButton mainMenuButton ;
    public GameOverPanel(){
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(900 , 700));

        replayButton = new JButton("Replay");
        MyFrame.designButton(replayButton , 200 , 550 , "src/resource/icons8-hexagon-50.png");
        replayButton.addActionListener(this);

        mainMenuButton = new JButton("Main Menu");
        MyFrame.designButton(mainMenuButton , 500 , 550 , "src/resource/icons8-menu-32.png");
        mainMenuButton.addActionListener(this);
    }

    public static void playGameOverSound(){
        MyFrame.playSound("src/resource/gameover.wav");
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

