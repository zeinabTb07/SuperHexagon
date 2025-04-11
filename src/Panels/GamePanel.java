package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import Elements.PlayMusic;
import Elements.Mahlar;
import Elements.Wall;

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
        jLayeredPane.setBounds(0 , 0 , 900 , 700);
        panel = new GamePlayField(6 , 20 , 0 , 0 );
        jLayeredPane.add(panel , JLayeredPane.DEFAULT_LAYER);


        pauseButton = new JCheckBox();
        pauseButton.setIcon(new ImageIcon("src/resource/icons8-pause-100.png"));
        pauseButton.setSelectedIcon(new ImageIcon("src/resource/icons8-play-100.png"));
        pauseButton.addActionListener(this);
        pauseButton.setBounds(0, 600, 100, 100);
        jLayeredPane.add(pauseButton , JLayeredPane.PALETTE_LAYER);

        jLayeredPane.setBounds(0 , 0 , 900 , 700);
        panel.addNotify();
        panel.prepareListeners();
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
        panel.requestFocus();
    }
    private void changePanel(int n){
        double currentMahlarAngle = panel.mahlar.getCurrentAngle();
        jLayeredPane.remove(panel);
        panel = new GamePlayField(n , 10 , panel.getBaseHue() , currentMahlarAngle+panel.getRotationAngle());
        jLayeredPane.add(panel , JLayeredPane.DEFAULT_LAYER);
        panel.prepareListeners();
        panel.requestFocus();
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
        private static int R ;
        private boolean firstColoring = true;

        Wall wall ;
        public float getBaseHue(){
            return baseHue;
        }
        public double getRotationAngle(){
            return rotationAngle;
        }
        GamePlayField(int n , int delay , float baseHue , double currentAngle ){
            R = 50 ;
            this.n = n ;

            fieldColors = new Color[n];
            this.baseHue = baseHue ;
            updateColors();
            this.setPreferredSize(new Dimension(900 , 700));
            setFocusable(true);

            mahlar = new Mahlar(450 , 350 , R + 10 , 15 );
            mahlar.rotateRelative(currentAngle);
            wall = new Wall(n , 800 , 450 , 350 , 1 , Color.black);

            timer = new Timer(delay, new ActionListener() {
                int i = 0 ;
                int j = 0 ;
                int k = 0 ;
                int counter = 0 ;
                @Override
                public void actionPerformed(ActionEvent e) {
                   if(GamePanel.play){

                       rotationAngle+=Math.toRadians(0.5);
                       wall.updateWall();

                       k++;
                       i++;
                       j++;

                       if(i==50) {
                           updateColors();
                           i = 0;
                           counter++;
                       }

                       if((counter+1)%40==0){
                           Random ran = new Random();
                           changePanel(ran.nextInt(3, 7));
                           counter++;
                       }
                       if(i % 20 == 0) {
                           hardness++;
                       }
                       if(j == 80 - hardness){
                           // TODO : spawn wall
                           j = 0;
                       }
                       repaint();
                   }
                }
            });
        timer.start();

        this.setBounds(0 , 0 , 900 , 700);

            setFocusable(true);
            requestFocus();

        }

        private int getR(){
            return R ;
        }
        public void prepareListeners() {
            InputMap inputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
            ActionMap actionMap = this.getActionMap();

            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");

            actionMap.put("left", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (GamePanel.play) rotateMahlar(-0.15);
                }
            });

            actionMap.put("right", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (GamePanel.play) rotateMahlar(0.2);
                }
            });
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
            int circleR = 700;

            for (int i = 0; i < n; i++) {
                double startAngle = Math.toRadians((360) / n * i);
                g2d.setColor(fieldColors[i]);
                g2d.fillArc(centerX - circleR - 10, centerY - circleR - 10,
                        2 * (circleR + 10), 2 * (circleR + 10),
                        (int) Math.toDegrees(startAngle), ((360) / n) + 1);
            }


            int radius = ((360) / n);
            Polygon polygon = new Polygon();
            for (int i = 0; i < n; i++) {
                double angle = Math.toRadians(radius * i);
                int x = centerX + (int) (R * Math.cos(angle));

                int y = centerY + (int) (R * Math.sin(angle));
                polygon.addPoint(x, y);
            }



            g2d.setColor(Color.WHITE);
            g2d.fillPolygon(polygon);
            g2d.setColor(Color.BLACK);

            wall.draw(g2d);
            mahlar.draw(g2d);

        }

        private void updateColors() {
            Random random = new Random();

            if(firstColoring) firstColoring = false ;
            else  baseHue += random.nextFloat();

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

