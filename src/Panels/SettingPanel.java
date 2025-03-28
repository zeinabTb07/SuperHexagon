package Panels;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingPanel extends JPanel implements ActionListener , ChangeListener {
    JButton backToMenu ;
    JSlider soundSlider ;
    JButton saveHistoryButton ;
    public SettingPanel(){
        this.setLayout(null);
        this.setBackground(Color.green);
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

    @Override
    public void changed(ObservableValue observableValue, Object o, Object t1) {

    }
}
