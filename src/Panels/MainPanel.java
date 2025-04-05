package Panels;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel implements ActionListener {
    private JLayeredPane jLayeredPane ;
    private JButton settingButton ;
    private JButton historyButton;
    private JButton startButton ;
    public MainPanel (){
        jLayeredPane = new JLayeredPane();
        jLayeredPane.setPreferredSize(new Dimension(900, 700));
        jLayeredPane.setLayout(null);


        Color[] colors = new Color[]{Color.darkGray,Color.BLACK,Color.darkGray,Color.BLACK,Color.darkGray,Color.BLACK};
        RotatingPolygonPanel panel = new RotatingPolygonPanel(6 , 20 , colors);
        panel.setBounds(0, 0, 900, 700);

        jLayeredPane.add(panel , JLayeredPane.DEFAULT_LAYER);


        this.setPreferredSize(new Dimension(900 , 700));

        settingButton = new JButton("Setting");
        MyFrame.designButton(settingButton , 100 , 630 , "src/resource/icons8-settings-30.png" );
        settingButton.addActionListener(this::actionPerformed);
        jLayeredPane.add(settingButton , JLayeredPane.PALETTE_LAYER);

        historyButton = new JButton("History");
        MyFrame.designButton(historyButton , 600 , 630 , "src/resource/icons8-history-30.png" );
        historyButton.addActionListener(this::actionPerformed);
        jLayeredPane.add(historyButton , JLayeredPane.PALETTE_LAYER);

        startButton = new JButton("Start");
        MyFrame.designButton(startButton , 350 , 600 , "src/resource/icons8-hexagon-50.png" );
        startButton.addActionListener(this::actionPerformed);
        jLayeredPane.add(startButton , JLayeredPane.PALETTE_LAYER);

        JLabel maxScoreLabel = new JLabel("Max Score : "  + HistoryPanel.getMaxScore() );
        maxScoreLabel.setBounds(10 , 5 , 250 , 40);
        maxScoreLabel.setForeground(Color.white);
        maxScoreLabel.setFont(new Font( "Comic Sans MS" , Font.BOLD , 25));
        jLayeredPane.add(maxScoreLabel, JLayeredPane.PALETTE_LAYER);

        setLayout(new BorderLayout());
        add(jLayeredPane, BorderLayout.CENTER);
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
