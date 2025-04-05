package Panels;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class StartPanel extends JPanel implements ActionListener {
    private JLayeredPane jLayeredPane;
    private JButton submitButton ;
    private JButton backToMenu ;
    private JTextField jTextField ;
    public StartPanel (){
        jLayeredPane = new JLayeredPane();
        jLayeredPane.setPreferredSize(new Dimension(900, 700));
        jLayeredPane.setLayout(null);

        Color[] colors = new Color[]{Color.red.darker().darker().darker().darker(),Color.BLACK,Color.red.darker().darker().darker().darker(),Color.BLACK,Color.red.darker().darker().darker().darker(),Color.BLACK};
        RotatingPolygonPanel panel = new RotatingPolygonPanel(6 , 20 , colors);
        panel.setBounds(0, 0, 900, 700);

        jLayeredPane.add(panel , JLayeredPane.DEFAULT_LAYER);

        this.setPreferredSize(new Dimension(900 , 700));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.setOpaque(false);

        JLabel nameLabel = new JLabel();

        nameLabel.setForeground(Color.WHITE);
        inputPanel.add(nameLabel, BorderLayout.NORTH);

        jTextField = new JTextField("Please Enter Your Name");
        jTextField.setPreferredSize(new Dimension(400, 40));
        jTextField.setFont(new Font( "Comic Sans MS" , Font.PLAIN , 30));
        jTextField.addFocusListener(new FocusAdapter() {
            boolean firstTime = true;
            @Override
            public void focusGained(FocusEvent e) {
                if (firstTime && jTextField.getText().equals("Please Enter Your Name")) {
                    jTextField.setText("");
                    firstTime = false;
                }
            }
        });


        submitButton = new JButton("GO");
        submitButton.setPreferredSize(new Dimension(100 , 40 ));
        submitButton.setFont(new Font( "Comic Sans MS" , Font.PLAIN , 40));
        submitButton.addActionListener(this);

        inputPanel.add(jTextField, BorderLayout.CENTER);
        inputPanel.add(submitButton , BorderLayout.EAST);

        inputPanel.setBounds(200, 150, 500, 50);
        jLayeredPane.add(inputPanel, JLayeredPane.PALETTE_LAYER);



        backToMenu = new JButton(" Back");
        MyFrame.designButton(backToMenu , 0 , 640  , "src/resource/icons8-left-2-35.png");
        backToMenu.addActionListener(this);
        jLayeredPane.add(backToMenu , JLayeredPane.PALETTE_LAYER);

        this.add(jLayeredPane);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==backToMenu){
            MyFrame.switchPanel("main");
        } else if(e.getSource()==submitButton){
            if(!jTextField.getText().equals("Please Enter Your Name") && !jTextField.getText().isEmpty()){
                System.out.println(jTextField.getText());
                GamePanel.playMusic(SettingPanel.canMusicPlayed());
                MyFrame.switchPanel("game");
            }


        }

    }
}
