package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import Elements.PlayMusic;
import Elements.Mahlar;
import Elements.Wall;
import Elements.WallManager;

public class GamePanel extends JPanel implements ActionListener {
    private JCheckBox pauseButton ;
    private static PlayMusic playMusic ;
    private static JLayeredPane jLayeredPane ;
    protected static boolean play = true  ;
    private static GamePlayField panel ;
    public static Color color ;
    protected static WallManager wallManager ;
    private  Random random;
    private static float baseHue ;
    private static int R ;
    private static double score ;
    private static JLabel maxScoreLabel ;
    private static JLabel scoreLabel ;

    public static void resetGame(){
        score = 0 ;
        wallManager.setWallsList();
        wallManager.setSpeed();
        panel.timer.stop();
        GamePlayField.base = 50 ;
        startGame();
        maxScoreLabel.setText("Max Score : "  + HistoryPanel.getMaxScore() );
        panel.requestFocus();
    }

    public GamePanel (){
        this.setLayout(null);
        score = 0 ;
        this.setPreferredSize(new Dimension(900 , 700));
        playMusic = new PlayMusic("src/resource/SuperHexagonSoundtrack-Hexagoner.wav");
        color = Color.decode("#edc9af");
        random = new Random();
        wallManager = new WallManager(6);

        jLayeredPane = new JLayeredPane();

        maxScoreLabel = new JLabel("Max Score : "  + HistoryPanel.getMaxScore() );
        maxScoreLabel.setBounds(10 , 5 , 250 , 40);
        maxScoreLabel.setForeground(Color.white);
        maxScoreLabel.setFont(new Font( "Comic Sans MS" , Font.BOLD , 25));
        jLayeredPane.add(maxScoreLabel, JLayeredPane.PALETTE_LAYER);

        scoreLabel = new JLabel(" Score : "  + score);
        scoreLabel.setBounds(640 , 5 , 250 , 40);
        scoreLabel.setForeground(Color.white);
        scoreLabel.setFont(new Font( "Comic Sans MS" , Font.BOLD , 25));
        jLayeredPane.add(scoreLabel, JLayeredPane.PALETTE_LAYER);

        pauseButton = new JCheckBox();
        pauseButton.setIcon(new ImageIcon("src/resource/icons8-pause-100.png"));
        pauseButton.setSelectedIcon(new ImageIcon("src/resource/icons8-play-100.png"));
        pauseButton.addActionListener(this);
        pauseButton.setBounds(0, 600, 100, 100);
        jLayeredPane.add(pauseButton , JLayeredPane.PALETTE_LAYER);

        panel = new GamePlayField(6 , 25 , panel.getBaseHue() , 0);
        jLayeredPane.add(panel , JLayeredPane.DEFAULT_LAYER);

        jLayeredPane.setBounds(0 , 0 , 900 , 700);

        panel.prepareListeners();

        this.add(jLayeredPane);



    }

    public static double getScore(){
        return score;
    }
    public static float getBasehue(){
        return baseHue;
    }

    public static double getR(){
        return R ;
    }

    public static void playMusic(boolean bool){
        if(SettingPanel.canMusicPlayed()){
            playMusic.playMusic();
        }
    }


    private static void stopGame(){
        playMusic.stopMusic();
        play = false;
        panel.timer.stop();
    }

    public static void startGame(){
        if (SettingPanel.canMusicPlayed()) {
            playMusic.playMusic();
        }
        play = true;
        panel.timer.start();
        panel.requestFocus();
    }

    public  void changePanel(int n){
        panel.timer.stop();
        double currentMahlarAngle ;
        if(panel.mahlar!=null){
            currentMahlarAngle = panel.mahlar.getCurrentAngle();
        } else currentMahlarAngle = 0 ;
        wallManager.setWallsList();
        wallManager.setN(n);
        jLayeredPane.remove(panel);
        System.gc();
        panel = new GamePlayField(n , 25 , panel.getBaseHue() , currentMahlarAngle+panel.getRotationAngle());
        jLayeredPane.add(panel , JLayeredPane.DEFAULT_LAYER);
        panel.prepareListeners();
        panel.requestFocus();
        panel.timer.start();
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
        private Mahlar mahlar;
        private boolean firstColoring = true;
        static int base ;

        GamePlayField(int n , int delay , float hue , double currentAngle){
            R = 50 ;
            this.n = n ;
            base = 50 ;
            fieldColors = new Color[n];
            baseHue = hue ;
            updateColors();

            this.setPreferredSize(new Dimension(900 , 700));
            setFocusable(true);

            mahlar = new Mahlar(450 , 350 , R + 5 , 10);
            mahlar.rotateRelative(currentAngle);

            timer = new Timer(delay, new ActionListener()  {
                int i = 0 ;
                int j = 0 ;
                int k = 0 ;
                int counter = 0 ;
                boolean first = true;
                @Override
                public void actionPerformed(ActionEvent e) {
                   if(GamePanel.play){
                       if(first){
                           wallManager.spawnWalls(random.nextInt(0 , 4));
                           first = false;
                       }
                       rotationAngle+=Math.toRadians(1);

                       increaseCounters();

                       for(ArrayList<Wall> walls : wallManager.getWalls()){
                           for(Wall wall : walls){
                               wall.updateWall();
                           }
                       }
                       if (!wallManager.getWalls().isEmpty()) {
                           for (Wall wall : wallManager.getWalls().get(0)) {
                               if (checkCollision(mahlar.getTriangle(), wall.getTrapzoid())) {
                                   gameOver();
                               }
                           }
                       }

                       if(i==30) {
                           updateColors();
                           GamePanel.color = Color.getHSBColor(GamePanel.getBasehue() , 0.6f , 0.9f);
                           i = 0;
                       }

                       if(i % 20 == 0) {
                           wallManager.increaseSpeed();
                       }

                       if(j==base) {
                           j = 0 ;
                           wallManager.spawnWalls(random.nextInt(0 , 3));
                           base--;
                       }

                       if(base < 10) base = 20 ;

                       if(k==5) {
                           wallManager.checkInteredCenter();
//                           if(!wallManager.getWalls().isEmpty()) {
//                               for (Wall wall : wallManager.getWalls().get(0)) {
//                                   if (mahlar.getTriangle().intersects(wall.getTrapzoid().getBounds2D())) {
//                                       resetCounters();
//                                       gameOver();
//                                       break;
////
////                                       System.out.println("X : " + Arrays.toString(mahlar.getTriangle().xpoints));
////                                       System.out.println("Y : " + Arrays.toString(mahlar.getTriangle().ypoints));
////
////                                       System.out.println("X : " + Arrays.toString(wall.getTrapzoid().xpoints));
////                                       System.out.println("Y : " + Arrays.toString(wall.getTrapzoid().ypoints));
////                                       System.out.println("Game Over" );
//                                   }
//                               }
                       //}

                           wallManager.checkInteredCenter();
                           if (score > HistoryPanel.getMaxScore()) {
                               recordBreaked();
                           }

                           k=0;
                       }
                       if(counter==400){
                           changePanel(random.nextInt(4 , 7));
                           resetCounters();
                       }
                       repaint();
                   }

                }
                private boolean isPointInPolygon(int x, int y, Polygon polygon) {
                    return polygon.contains(x, y);
                }
                private boolean checkCollision(Polygon triangle, Polygon trapezoid) {
                    for (int i = 0; i < triangle.npoints; i++) {
                        if (isPointInPolygon(triangle.xpoints[i], triangle.ypoints[i], trapezoid)) {
                            return true;
                        }
                    }
                    for (int i = 0; i < trapezoid.npoints; i++) {
                        if (isPointInPolygon(trapezoid.xpoints[i], trapezoid.ypoints[i], triangle)) {
                            return true;
                        }
                    }
                    return false;
                }

                private void resetCounters(){
                    i = 0 ;
                    k = 0 ;
                    j = 0 ;
                    counter = 0 ;
                }
                private void increaseCounters(){
                    k++;
                    i++;
                    j++;
                    counter++;
                    score+=0.02;
                    scoreLabel.setText(" Score : "  + String.format("%.2f", score));
                }
            });

        this.setBounds(0 , 0 , 900 , 700);

        setFocusable(true);

        }

        public void recordBreaked(){
            GamePanel.maxScoreLabel.setText("Record Breaked!");
        }
        public GamePlayField(){}

        public void prepareListeners() {
            InputMap inputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
            ActionMap actionMap = this.getActionMap();

            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");

            actionMap.put("left", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (GamePanel.play) rotateMahlar(-0.2);
                }
            });

            actionMap.put("right", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (GamePanel.play) rotateMahlar(0.3);
                }
            });
        }
        public static float getBaseHue(){
            return baseHue;
        }

        public double getRotationAngle(){
            return rotationAngle;
        }

        public void rotateMahlar (double rotationSpeed){
            mahlar.rotateRelative(rotationSpeed);
        }
        public void gameOver(){
            stopGame();
            MyFrame.playSound("src/resource/gameover.wav");
            changePanel(6);
            new GameOverDialog(panel);
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
                double startAngle = (360/n)*i;
                g2d.setColor(fieldColors[i]);
                g2d.fillArc(centerX - circleR - 10, centerY - circleR - 10,
                        2 * (circleR + 10), 2 * (circleR + 10),
                        (int) startAngle , 360/n);
            }


            double radius =  2*Math.PI/n;
            Polygon polygon = new Polygon();
            for (int i = 0; i < n; i++) {
                double angle = radius*i;
                int x = centerX + (int) (R * Math.cos(angle));

                int y = centerY + (int) (R * Math.sin(angle));
                polygon.addPoint(x, y);
            }



            g2d.setColor(GamePanel.color);
            g2d.fillPolygon(polygon);
            g2d.setColor(Color.BLACK);

            for(ArrayList<Wall> walls : wallManager.getWalls()){
                for(Wall wall : walls){
                    wall.draw(g2d);
                }
            }

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

