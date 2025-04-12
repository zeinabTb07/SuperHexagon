package Elements;

import Panels.GamePanel;

import java.awt.*;

public class Mahlar {
    private double angleOffset = 0;
    private int radius;
    private int size;
    private Polygon triangle;
    private int circleCenterX ;
    private int circleCenterY ;
    public Mahlar(int centerX, int centerY, int radius, int size ) {
        circleCenterX = centerX;
        circleCenterY = centerY;
        this.radius = radius;
        this.size = size;
        updateTriangle();
    }

    public double getCurrentAngle() {
        return angleOffset;
    }

    private void updateTriangle() {

        int  centerX = circleCenterX + (int)(radius * Math.cos(angleOffset));
        int centerY = circleCenterY + (int)(radius * Math.sin(angleOffset));
        int tipX = centerX + (int)(( size) * Math.cos(angleOffset));
        int tipY = centerY + (int)(( size) * Math.sin(angleOffset));


        double baseAngle1 = angleOffset + Math.toRadians(120);
        double baseAngle2 = angleOffset + Math.toRadians(240);

        int baseX1 = centerX + (int)(size * Math.cos(baseAngle1));
        int baseY1 = centerY + (int)(size * Math.sin(baseAngle1));

        int baseX2 = centerX + (int)(size * Math.cos(baseAngle2));
        int baseY2 = centerY + (int)(size * Math.sin(baseAngle2));

        triangle = new Polygon();
        triangle.addPoint(tipX, tipY);
        triangle.addPoint(baseX1, baseY1);
        triangle.addPoint(baseX2, baseY2);
    }

    public Point getLocation() {
        return new Point(circleCenterX + (int)(radius * Math.cos(angleOffset))
                , circleCenterY + (int)(radius * Math.sin(angleOffset)));

    }

    public Polygon getTriangle(){
        return triangle;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(GamePanel.color);
        g2d.fill(triangle);
    }

    public void rotateRelative(double deltaAngle) {
        angleOffset += deltaAngle;
        updateTriangle();
    }
}
