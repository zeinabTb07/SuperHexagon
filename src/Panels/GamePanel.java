package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import Elements.PlayMusic;
import Elements.Mahlar;

public class GamePanel extends JPanel implements ActionListener {
    private JCheckBox pauseButton ;
    private static PlayMusic playMusic ;
    private JLayeredPane jLayeredPane ;
    protected static boolean play = true  ;
    private GamePlayField panel ;
    public GamePanel (){
        this.setLayout(null);
        this.setPreferredSize(new Dimension(900 , 700));
        playMusic = new PlayMusic("src/resource/SuperHexagonSoundtrack-Hexagoner.wav");

        jLayeredPane = new JLayeredPane();
        panel = new GamePlayField(6 , 20);
        jLayeredPane.add(panel , JLayeredPane.DEFAULT_LAYER);



        pauseButton = new JCheckBox();
        pauseButton.setIcon(new ImageIcon("src/resource/icons8-pause-100.png"));
        pauseButton.setSelectedIcon(new ImageIcon("src/resource/icons8-play-100.png"));
        pauseButton.addActionListener(this);
        pauseButton.setBounds(0, 600, 100, 100);
        jLayeredPane.add(pauseButton , JLayeredPane.PALETTE_LAYER);

        jLayeredPane.setBounds(0 , 0 , 900 , 700);
        panel.addNotify();
        setFocusable(true);
        requestFocus();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                double rotationSpeed = 0.7;
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT && play)  panel.rotateMahlar(-rotationSpeed);
                if (key == KeyEvent.VK_RIGHT && play) panel.rotateMahlar(rotationSpeed+0.2);
            }
        });
        this.add(jLayeredPane);

    }



    public static void playMusic(boolean bool){
        if(SettingPanel.canMusicPlayed()){
            playMusic.playMusic();
        }
    }

    private void stopGame(){
        playMusic.stopMusic();
        play = false;
    }
    private void startGame(){
        if (SettingPanel.canMusicPlayed()) {
            playMusic.playMusic();
        }
        play = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==pauseButton){
            if(pauseButton.isSelected()){
                stopGame();
            } else startGame();
        }
    }

    class GamePlayField extends JPanel {
        private int n = 1;
        private double rotationAngle = 0;
        private Timer timer;
        private Color[] fieldColors;
        private float baseHue ;
        private Mahlar mahlar;
        private int hardness = 0 ;
        GamePlayField(int n , int delay ){
            this.n = n ;
            fieldColors = new Color[n];
            baseHue = 0 ;
            updateColors();
            this.setPreferredSize(new Dimension(900 , 700));
            setFocusable(true);


            timer = new Timer(delay, new ActionListener() {
                int i = 0 ;
                int j = 0 ;
                @Override
                public void actionPerformed(ActionEvent e) {
                   if(play){
                       rotationAngle+=Math.toRadians(0.5);
                       i++;
                       j++;
                       if(i==80) {
                           updateColors();
                           i = 0;
                       }
                       if(j == 60 - hardness){
                           // TODO : spawn wall
                           j = 0;
                       }
                       repaint();
                   }
                }
            });
        timer.start();

        mahlar = new Mahlar(450 , 350 , 70 , 15);

        this.setBounds(0 , 0 , 900 , 700);

        }
        public void rotateMahlar (double rotationSpeed){
            mahlar.rotateRelative(rotationSpeed);
        }

        protected void paintComponent(Graphics g){
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // گویا یکم گرافیکو بهتر می کنه

            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;

            g2d.rotate(rotationAngle, centerX , centerY);
            int R = 700;

            for (int i = 0; i < n; i++) {
                double startAngle = Math.toRadians((360) / n * i);
                g2d.setColor(fieldColors[i]);
                g2d.fillArc(centerX - R - 10, centerY - R - 10,
                        2 * (R + 10), 2 * (R + 10),
                        (int) Math.toDegrees(startAngle), ((360) / n) + 1);
            }

            int radius = ((360) / n);
            Polygon polygon = new Polygon();
            for (int i = 0; i < n; i++) {
                double angle = Math.toRadians(radius * i);
                int x = centerX + (int) (50 * Math.cos(angle));

                int y = centerY + (int) (50 * Math.sin(angle));
                polygon.addPoint(x, y);
            }

            mahlar.draw(g2d);

            g2d.setColor(Color.WHITE);
            g2d.fillPolygon(polygon);
            g2d.setColor(Color.BLACK);

        }

        private void updateColors() {
            Random random = new Random();

            baseHue += random.nextFloat();
            float saturation = 0.7f;
            float brightnessOdd = 0.3f;
            float brightnessEven = 0.4f;

            Color odd = Color.getHSBColor(baseHue, saturation, brightnessOdd);
            Color even = Color.getHSBColor(baseHue + 0.01f, saturation, brightnessEven);
            if((n&1)==1){
                fieldColors[0]=Color.getHSBColor(baseHue - 0.01f , saturation , 0.35f);
            } else fieldColors[0]=odd ;
            for (int i = 1; i < n; i++) {
                if ((i & 1) == 1) {
                    fieldColors[i] = even;
                } else fieldColors[i] = odd;
            }
        }
    }
}

