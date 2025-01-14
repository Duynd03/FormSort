package GUI;

import java.awt.*;
import javax.swing.*;

public class SortSearchView extends JFrame {
    public JTextField txtInput, txtN, txtSearchChar;
    public JButton btnSortSelection, btnSortBubble, btnSortInsertion, btnSortMerge, btnSortQuick;
    public JButton btnSearchChar;
    public JTextArea lstResult, lstSearchResult;
    public JLabel lblTime;

    public SortSearchView() {
        this.setTitle("Sort");
        this.setSize(500, 600);
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblN = new JLabel("Nhập số lượng phần tử (n):");
        lblN.setPreferredSize(new Dimension(200, 20));
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
        
        JLabel lblInput = new JLabel("Nhập phần tử của mảng :");
        lblInput.setPreferredSize(new Dimension(200, 20));
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
        lblSearchChar.setPreferredSize(new Dimension(200, 20));
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

        JPanel panelInputs = new JPanel(new GridLayout(3, 2, 5, 5));
        panelInputs.add(lblN);
        panelInputs.add(txtN);
        panelInputs.add(lblInput);
        panelInputs.add(txtInput);
        panelInputs.add(lblSearchChar);
        panelInputs.add(txtSearchChar);
        this.add(panelInputs);

        btnSortSelection = new JButton("Selection Sort");
        btnSortBubble = new JButton(" Bubble Sort");
        btnSortInsertion = new JButton("Insertion Sort");
        btnSortMerge = new JButton("Merge Sort");
        btnSortQuick = new JButton("Quick Sort");
        btnSearchChar = new JButton("Tìm Ký Tự");

        JPanel panelButtons = new JPanel(new GridLayout(2, 3, 5, 5));
        panelButtons.add(btnSortSelection);
        panelButtons.add(btnSortBubble);
        panelButtons.add(btnSortInsertion);
        panelButtons.add(btnSortMerge);
        panelButtons.add(btnSortQuick);
        panelButtons.add(btnSearchChar);
        this.add(panelButtons);

        lstResult = new JTextArea(5, 40);
        lstResult.setEditable(false);
        this.add(new JScrollPane(lstResult));

        lstSearchResult = new JTextArea(5, 40);
        lstSearchResult.setEditable(false);  
        this.add(new JScrollPane(lstSearchResult));

        lblTime = new JLabel();
        this.add(lblTime);

        this.setVisible(true);
    }
}
