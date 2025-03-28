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

        backToMenu = new JButton(" Back");
        MyFrame.designButton(backToMenu , 0 , 620 , "src/resource/icons8-left-2-35.png");
        backToMenu.addActionListener(this);
        this.add(backToMenu);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==backToMenu){
            MyFrame.switchPanel("main");
        }

    }
}
