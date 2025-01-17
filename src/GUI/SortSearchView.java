package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SortSearchView extends JFrame {
    public JTextField txtInput, txtN, txtSearchChar;
    public JButton btnSortSelection, btnSortBubble, btnSortInsertion, btnSortMerge, btnSortQuick;
    public JButton btnSearchChar, btnShowStatistics, btnReset;
    public JTextArea lstResult, lstSearchResult;
    public JLabel lblTime;
    public JTable tblStatistics;
    public DefaultTableModel tblModel;
   
    public SortSearchView() {
        this.setTitle("Sort & Search");
        this.setSize(600, 1000);
        this.setLayout(new BorderLayout(10, 10));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panelInputs = new JPanel(new GridLayout(3, 2, 10, 10));
        panelInputs.setBorder(BorderFactory.createTitledBorder("Nhập thông tin"));
        panelInputs.setBackground(Color.WHITE);

        JLabel lblN = new JLabel("Nhập số lượng phần tử (n):");
        txtN = new JTextField(10);
        txtN.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != '\b') { 
                    e.consume(); 
                    JOptionPane.showMessageDialog(null, "Chỉ cho phép nhập số nguyên dương !", 
                                                  "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JLabel lblInput = new JLabel("Nhập phần tử của mảng:");
        txtInput = new JTextField(15);
        txtInput.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != ' ' && c != '-' && c != '.' && c != '\b') {
                    e.consume();
                    JOptionPane.showMessageDialog(null, "Chỉ cho phép nhập số âm, số dương, dấu cách, và dấu chấm giữa các phần tử !",
                                                  "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JLabel lblSearchChar = new JLabel("Nhập ký tự cần tìm:");
        txtSearchChar = new JTextField(15);
        txtSearchChar.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != '-' && c != '\b' && c != '.') {
                    e.consume();
                    JOptionPane.showMessageDialog(null, "Chỉ cho phép nhập số nguyên hoặc số thực !",
                                                  "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panelInputs.add(lblN);
        panelInputs.add(txtN);
        panelInputs.add(lblInput);
        panelInputs.add(txtInput);
        panelInputs.add(lblSearchChar);
        panelInputs.add(txtSearchChar);
        this.add(panelInputs, BorderLayout.NORTH);

        JPanel panelButtons = new JPanel();
        panelButtons.setBorder(BorderFactory.createTitledBorder("Chọn thuật toán"));
        panelButtons.setBackground(Color.WHITE);
        panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.Y_AXIS));

        btnSortSelection = new JButton("Selection Sort");
        btnSortBubble = new JButton("Bubble Sort");
        btnSortInsertion = new JButton("Insertion Sort");
        btnSortMerge = new JButton("Merge Sort");
        btnSortQuick = new JButton("Quick Sort");
        btnSearchChar = new JButton("Tìm Ký Tự");
        btnShowStatistics = new JButton("Hiển Thị Thống Kê");
        btnReset = new JButton("Làm mới");

        Dimension buttonSize = new Dimension(200, 40); 
        btnSortSelection.setMaximumSize(buttonSize);
        btnSortBubble.setMaximumSize(buttonSize);
        btnSortInsertion.setMaximumSize(buttonSize);
        btnSortMerge.setMaximumSize(buttonSize);
        btnSortQuick.setMaximumSize(buttonSize);
        btnSearchChar.setMaximumSize(buttonSize);
        btnShowStatistics.setMaximumSize(buttonSize);
        btnReset.setMaximumSize(buttonSize);
        
        panelButtons.add(Box.createVerticalStrut(5));
        panelButtons.add(btnSortSelection);
        panelButtons.add(Box.createVerticalStrut(5));
        panelButtons.add(btnSortBubble);
        panelButtons.add(Box.createVerticalStrut(5));
        panelButtons.add(btnSortInsertion);
        panelButtons.add(Box.createVerticalStrut(5));
        panelButtons.add(btnSortMerge);
        panelButtons.add(Box.createVerticalStrut(5));
        panelButtons.add(btnSortQuick);
        panelButtons.add(Box.createVerticalStrut(5));
        panelButtons.add(btnSearchChar);
        panelButtons.add(Box.createVerticalStrut(5));
        panelButtons.add(btnShowStatistics);
        panelButtons.add(Box.createVerticalStrut(5));
        panelButtons.add(btnReset);

        this.add(panelButtons, BorderLayout.WEST);

        JPanel panelResults = new JPanel(new GridLayout(3, 1, 10, 10));
        panelResults.setBorder(BorderFactory.createTitledBorder("Kết quả"));
        panelResults.setBackground(Color.WHITE);

        lstResult = new JTextArea(5, 40);
        lstResult.setEditable(false);
        lstResult.setFont(new Font("Arial", Font.PLAIN, 14));
        lstResult.setLineWrap(true);
        lstResult.setWrapStyleWord(true);

        lstSearchResult = new JTextArea(5, 40);
        lstSearchResult.setEditable(false);
        lstSearchResult.setFont(new Font("Arial", Font.PLAIN, 14));
        lstSearchResult.setLineWrap(true);
        lstSearchResult.setWrapStyleWord(true);

        lblTime = new JLabel("Thời gian xử lý:");
        lblTime.setFont(new Font("Arial", Font.PLAIN, 14));

        panelResults.add(new JScrollPane(lstResult));
        panelResults.add(new JScrollPane(lstSearchResult));
        panelResults.add(lblTime);

        this.add(panelResults, BorderLayout.CENTER);

        JPanel panelStatistics = new JPanel(new BorderLayout());
        panelStatistics.setBorder(BorderFactory.createTitledBorder("Thống Kê Thuật Toán"));
        panelStatistics.setBackground(Color.WHITE);

        String[] columnNames = {};
        tblModel = new DefaultTableModel(columnNames, 0);
        tblStatistics = new JTable(tblModel);
        tblStatistics.setDefaultEditor(Object.class, null);

        tblStatistics.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblStatistics.setFillsViewportHeight(true);

        panelStatistics.add(new JScrollPane(tblStatistics), BorderLayout.CENTER);

        this.add(panelStatistics, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    public void addStatisticsRow(int stt, String algorithmName, int runCount, long executionTime) {
        tblModel.addRow(new Object[]{stt, algorithmName, runCount, executionTime});
    }
}
