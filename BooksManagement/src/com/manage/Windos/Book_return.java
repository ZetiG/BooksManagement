package com.manage.Windos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Book_return extends JFrame {
    private JTextField textField;
    private ResultSet r;

    public static void main(String[] args) {
        new Book_return();
    }
    public Book_return() {
        setTitle("ͼ��黹");
        setVisible(true);
        setBounds(100, 100, 450, 200);
        setLocation(1270, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(20, 21, 387, 47);
        getContentPane().add(panel);

        JLabel lblisbn = new JLabel("\u8BF7\u8F93\u5165\u8FD8\u4E66\u4E66\u53F7\uFF08ISBN\uFF09\uFF1A");
        lblisbn.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        lblisbn.setBounds(10, 10, 156, 27);
        panel.add(lblisbn);

        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(176, 13, 156, 21);
        panel.add(textField);

        JButton btnNewButton = new JButton("\u5F52\u8FD8");
        btnNewButton.setIcon(new ImageIcon(Book_return.class.getResource("/images/reset.png")));
        btnNewButton.setBounds(314, 107, 93, 23);
        getContentPane().add(btnNewButton);


        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

                if (b_return()) {
                    JOptionPane.showMessageDialog(null, "����ɹ�������");
                }
            }
        });
    }

    public Boolean b_return() {
        boolean result = false;
        Connection conn = null;
        LocalDate ld = LocalDate.now();
        try {
            conn = Login.getCon();  //�������ݿ�����
            String sqlupdate2 = null;

            String sqlupdate1 = "select Ld  from lend  "
                    + " where ISBN =" + textField.getText()
                    + "  and rtn is null";
            PreparedStatement stmt = conn.prepareStatement(sqlupdate1);
            r = stmt.executeQuery();
            r.next();
            String LD = r.getString(1);
            String today = ld.toString();
            int diff1 = LocalDate.parse(today).getDayOfYear();
            int diff2 = LocalDate.parse(LD).getDayOfYear();
            int OVERTIME = diff1 - diff2;
            if (OVERTIME >= 30) {
                JOptionPane.showMessageDialog(null, "���ѳ��ڣ��뼰ʱ���巣��");
            }
            sqlupdate2 = "update  lend  set rtn='" + ld + "'"
                    + " where ISBN =" + textField.getText()
                    + "  and rtn is null";
            stmt = conn.prepareStatement(sqlupdate2);   //���׳��쳣
            int i = stmt.executeUpdate();
            if (i == 1) {
                result = true;

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally { //finally���ô��ǲ��ܳ����Ƿ�����쳣����Ҫִ��finally��䣬�����ڴ˴��ر�����
            try {
                conn.close(); //��һ��Connection���Ӻ����һ��Ҫ��������close���������ر����ӣ����ͷ�ϵͳ��Դ�����ݿ���Դ
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;

    }
}
