package Panels;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingPanel extends JPanel implements ActionListener {
    private JButton backToMenu ;
    private static JCheckBox soundButton ;
    private static JCheckBox saveHistoryButton ;
    private JLayeredPane jLayeredPane;
    public SettingPanel(){
        this.setPreferredSize(new Dimension(900 , 700));

        jLayeredPane = new JLayeredPane();
        jLayeredPane.setPreferredSize(new Dimension(900 , 700));

        Color[] colors = new Color[]{Color.darkGray,Color.GRAY.darker(),Color.darkGray,Color.gray.darker(),Color.darkGray,Color.gray.darker()};
        RotatingPolygonPanel panel = new RotatingPolygonPanel(6 , 20 , colors);
        panel.setBounds(0, 0, 900, 700);

        jLayeredPane.add(panel , JLayeredPane.DEFAULT_LAYER);

        backToMenu = new JButton(" Back");
        MyFrame.designButton(backToMenu , 0 , 640 , "src/resource/icons8-left-2-35.png");
        backToMenu.addActionListener(this);
        jLayeredPane.add(backToMenu , JLayeredPane.PALETTE_LAYER);


        JPanel soundPanel = new JPanel(new BorderLayout());
        JLabel soundLabel = new JLabel("  Play Music : ");
        soundLabel.setFont(new Font( "Comic Sans MS" , Font.ITALIC , 35));
        soundLabel.setForeground(Color.BLACK);


        soundButton = new JCheckBox();
        soundButton.setSelected(true);
        soundButton.setSelectedIcon(new ImageIcon("src/resource/icons8-sound-on-100.png"));
        soundButton.setIcon(new ImageIcon("src/resource/icons8-sound-off-100.png"));

        soundPanel.add(soundButton , BorderLayout.EAST);
        soundPanel.add(soundLabel , BorderLayout.CENTER);
        soundPanel.setBounds(280 , 150 , 350 , 100);

        jLayeredPane.add(soundPanel , JLayeredPane.PALETTE_LAYER);


        JPanel savePanel = new JPanel(new BorderLayout());
        JLabel saveLabel = new JLabel("  Save History : ");
        saveLabel.setFont(new Font( "Comic Sans MS" , Font.ITALIC , 32));
        saveLabel.setForeground(Color.BLACK);


        saveHistoryButton = new JCheckBox();
        saveHistoryButton.setSelected(true);
        saveHistoryButton.setSelectedIcon(new ImageIcon("src/resource/icons8-save-100.png"));
        saveHistoryButton.setIcon(new ImageIcon("src/resource/icons8-not-save-00.png"));

        savePanel.add(saveHistoryButton , BorderLayout.EAST);
        savePanel.add(saveLabel , BorderLayout.CENTER);
        savePanel.setBounds(280 , 430 , 350 , 100);

        jLayeredPane.add(savePanel , JLayeredPane.PALETTE_LAYER);





        this.add(jLayeredPane);


    }

    public static boolean canMusicPlayed (){
        return soundButton.isSelected();
    }
    public static boolean canHistorySaved (){
        return saveHistoryButton.isSelected();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==backToMenu){
            MyFrame.switchPanel("main");
        }

    }

}
