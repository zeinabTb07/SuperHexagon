package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPanel extends JPanel implements ActionListener {
    JButton submitButton ;
    JButton backToMenu ;
    public StartPanel (){
        this.setLayout(null);
        this.setBackground(Color.orange);
        this.setPreferredSize(new Dimension(900 , 700));

        submitButton = new JButton("Submit");
        MyFrame.designButton(submitButton , 350 , 320 , null);
        submitButton.addActionListener(this);
        this.add(submitButton);

        backToMenu = new JButton(" Back");
        MyFrame.designButton(backToMenu , 0 , 650  , "src/resource/icons8-left-2-35.png");
        backToMenu.addActionListener(this);
        this.add(backToMenu);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==backToMenu){
            MyFrame.switchPanel("main");
        } else if(e.getSource()==submitButton){
            MyFrame.switchPanel("game");
        }

    }
}
