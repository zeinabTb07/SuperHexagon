package Panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import Elements.HistoryManager;
import Elements.GameHistory;

public class HistoryPanel extends JPanel implements ActionListener {
    private JButton backToMenu ;
    private JScrollPane scrollPane  ;
    public HistoryPanel() {
        this.setLayout(null);
        this.setBackground(Color.blue);
        this.setPreferredSize(new Dimension(900, 700));

        backToMenu = new JButton(" Back");
        MyFrame.designButton(backToMenu, 0, 650, "src/resource/icons8-left-2-35.png");
        backToMenu.addActionListener(this);
        this.add(backToMenu);


        List<GameHistory> history = HistoryManager.loadHistory();
        String[] columnNames = {"Player" , "Date&Time" , "Record"} ;
        String[][] data = new String[history.size()][3];
        int i = 0 ;
        for(GameHistory g : history){
            data[i][0]=g.getPlayerName();
            data[i][1] = g.getPlayTime();
            data[i][2] = String.valueOf(g.getScore());
            i++;
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        table.setFont(new Font( "Comic Sans MS" , Font.ITALIC , 30));
        table.setRowHeight(40);
        scrollPane = new JScrollPane(table);
        JTableHeader header = table.getTableHeader();

        header.setFont(new Font("Ink Free", Font.BOLD, 35));
        header.setBackground(Color.darkGray);
        header.setForeground(Color.WHITE);
        scrollPane.setBounds(10 , 10 , 865 , 675);


        this.add(scrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==backToMenu){
            MyFrame.switchPanel("main");
        }

    }
}
