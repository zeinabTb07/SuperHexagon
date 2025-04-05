package Panels;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RotatingPolygonPanel extends JPanel {
    private int n = 1;
    private double rotationAngle = 0;
    private Timer rotationTimer;
    private Timer colorChangeTimer;
    private Color[] fieldColors;
    private float baseHue ;

    RotatingPolygonPanel(int n, int rotationDelay, Color[] fieldColors) {
        this(n, rotationDelay);
        this.fieldColors = fieldColors;

    }

    RotatingPolygonPanel(int n, int rotationDelay, int colorChangeDelay) {
        this(n, rotationDelay);
        colorChangeTimer = new Timer(colorChangeDelay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSectorColors();
                repaint();
            }
        });
        colorChangeTimer.start();
        fieldColors = new Color[n];

    }

    private  RotatingPolygonPanel (int n, int rotationDelay) {
        this.n = n;
        rotationTimer = new Timer(rotationDelay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rotationAngle += Math.toRadians(0.5);
                repaint();
            }
        });
        rotationTimer.start();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // گویا یکم گرافیکو بهتر می کنه

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int R = 700;

        g2d.rotate(rotationAngle, centerX, centerY);

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
            int x = centerX + (int) (70 * Math.cos(angle));
            int y = centerY + (int) (70 * Math.sin(angle));
            polygon.addPoint(x, y);
        }
        g2d.setColor(Color.WHITE);
        g2d.fillPolygon(polygon);
        g2d.setColor(Color.BLACK);
    }

    private void updateSectorColors() {
        Random random = new Random();

        baseHue += random.nextFloat();
        float saturation = 0.7f;
        float brightnessOdd = 0.3f;
        float brightnessEven = 0.4f;

        Color odd = Color.getHSBColor(baseHue, saturation, brightnessOdd);
        Color even = Color.getHSBColor(baseHue + 0.01f, saturation, brightnessEven);

        for (int i = 0; i < n; i++) {
            if ((i & 1) == 1) {
                fieldColors[i] = even;
            } else fieldColors[i] = odd;
        }

    }

}