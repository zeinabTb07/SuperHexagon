package Elements;

import Panels.GamePanel;
import com.sun.source.tree.ReturnTree;

import java.awt.*;


public class Wall {
    private Polygon trapzoid ;
    private int n ;
    private double r ;
    private double R ;
    private int circleCenterX ;
    private int circleCenterY ;
    private int i ;
    private double angle ;
    private double speed = 2 ;
    private static Color color ;
    public Wall (int n , double R , int circleCenterX , int circleCenterY , int  i , Color color){
        this.n = n ;
        this.R = R ;
        this.r = R-50;
        this.circleCenterX = circleCenterX ;
        this.circleCenterY = circleCenterY ;
        this.i = i ;
        this.color = color ;
        angle = 2*Math.PI/n;

        updateWall();

    }
    public double getSpeed(){
        return speed;
    }
    public void setSpeed(double speed){
        this.speed = speed;
    }

    public double getR(){
        return r ;
    }
    public void updateWall(){
        trapzoid = new Polygon();
        color = GamePanel.color;

        int x1 = (int) (circleCenterX + R*Math.cos(i*angle));
        int y1 = (int) (circleCenterY + R*Math.sin(i*angle));

        int x2 = (int) (circleCenterX + r*Math.cos((i)*(angle)));
        int y2 = (int) (circleCenterY+ r*Math.sin((i)*(angle)));


        int x3 = (int) (circleCenterX + R*Math.cos((i+1)%n)*(angle));
        int y3 = (int) (circleCenterY + R*Math.sin(((i+1)%n)*(angle)));

        int x4 = (int) (circleCenterX + r*Math.cos(((i+1)%n)*(angle)));
        int y4 = (int) (circleCenterY + r*Math.sin(((i+1)%n)*(angle)));

        trapzoid.addPoint(x2 , y2);
        trapzoid.addPoint(x1 , y1);
        trapzoid.addPoint(x3 , y3);
        trapzoid.addPoint(x4 , y4);


        R = R-speed-0.1;
        r = r-speed;

    }


    public void draw (Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fill(trapzoid);
    }

}
