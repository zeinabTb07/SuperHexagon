package Panels;
import Elements.GameHistory;
import Elements.HistoryManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameOverDialog extends JDialog {

    public GameOverDialog(JPanel parent) {
        super();
        setLayout(new BorderLayout());

        JLabel message = new JLabel(("Game Over ! Your score was : " + String.format("%.2f", GamePanel.getScore())) , JLabel.CENTER);
        JButton restartButton = new JButton("Main Menu");

        restartButton.addActionListener(e -> {
            if (e.getSource() == restartButton) {
                MyFrame.switchPanel("main");
                this.dispose();
                MyFrame.currentPlayer.setScore(Double.valueOf(String.format("%.2f", GamePanel.getScore())));
                HistoryManager.addHistory(MyFrame.currentPlayer);
                HistoryPanel.update();
                MainPanel.setMaxScore();

            }
        });

        add(message, BorderLayout.CENTER);
        add(restartButton, BorderLayout.SOUTH);

        setSize(300, 150);
        setLocationRelativeTo(parent);
        setVisible(true);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    }
}

