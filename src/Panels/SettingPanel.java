package Panels;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingPanel extends JPanel implements ActionListener , ChangeListener {
    JButton backToMenu ;
    JSlider soundSlider ;
    JButton saveHistoryButton ;

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void changed(ObservableValue observableValue, Object o, Object t1) {

    }
}
