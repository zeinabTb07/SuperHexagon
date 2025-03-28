package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel implements ActionListener {
    JButton settingButton ;
    JButton historyButton;
    JButton startButton ;
    public MainPanel (){
        this.setLayout(null);
        this.setBackground(Color.PINK);
        this.setPreferredSize(new Dimension(900 , 700));
        this.setPreferredSize(new Dimension(900 , 700));
        settingButton = new JButton("Setting");
        MyFrame.designButton(settingButton , 100 , 600 , "src/resource/icons8-settings-30.png" );
        settingButton.addActionListener(this::actionPerformed);
        this.add(settingButton);

        historyButton = new JButton("History");
        MyFrame.designButton(historyButton , 600 , 600 , "src/resource/icons8-history-30.png" );
        historyButton.addActionListener(this::actionPerformed);
        this.add(historyButton);

        startButton = new JButton("Start");
        MyFrame.designButton(startButton , 350 , 550 , "src/resource/icons8-hexagon-50.png" );
        startButton.addActionListener(this::actionPerformed);
        this.add(startButton);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==settingButton){
            MyFrame.switchPanel("setting");
        } else if (e.getSource()==startButton) {
            MyFrame.switchPanel("start");
        } else if(e.getSource()==historyButton){
            MyFrame.switchPanel("history");
        }

    }
}
