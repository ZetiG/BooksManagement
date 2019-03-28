package com.manage.Windos;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Book_require extends JFrame {
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextArea textArea;
    private ResultSet r;

    public static void main(String[] args) {
        new Book_require();
    }
    /**
     * Create the application.
     */
    public Book_require() {
        setTitle("图书查询");
        setVisible(true);
        setBounds(100, 100, 450, 558);
        setLocation(1270, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel.setBounds(22, 30, 385, 73);
        getContentPane().add(panel);

        JLabel label = new JLabel("\u4E66\u540D\uFF1A");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label.setBounds(10, 27, 95, 15);
        panel.add(label);

        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(101, 24, 125, 21);
        panel.add(textField);

        JButton button = new JButton("\u67E5\u8BE2\u73B0\u6709");
        button.setIcon(new ImageIcon(Book_require.class.getResource("/images/search.png")));
        button.setBounds(255, 10, 107, 23);
        panel.add(button);

        JButton button_3 = new JButton("\u67E5\u8BE2\u5168\u90E8");
        button_3.setIcon(new ImageIcon(Book_require.class.getResource("/images/search.png")));
        button_3.setBounds(255, 40, 107, 23);
        panel.add(button_3);

        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_1.setBounds(21, 144, 388, 73);
        getContentPane().add(panel_1);

        JLabel label_1 = new JLabel("\u4F5C\u8005\uFF1A");
        label_1.setHorizontalAlignment(SwingConstants.CENTER);
        label_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_1.setBounds(10, 37, 95, 15);
        panel_1.add(label_1);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(101, 34, 125, 21);
        panel_1.add(textField_1);

        JButton button_1 = new JButton("\u67E5\u8BE2\u73B0\u6709");
        button_1.setIcon(new ImageIcon(Book_require.class.getResource("/images/search.png")));
        button_1.setBounds(257, 10, 105, 23);
        panel_1.add(button_1);

        JButton button_4 = new JButton("\u67E5\u8BE2\u5168\u90E8");
        button_4.setIcon(new ImageIcon(Book_require.class.getResource("/images/search.png")));
        button_4.setBounds(257, 40, 105, 23);
        panel_1.add(button_4);

        JLabel label_2 = new JLabel("\u6309\u4E66\u540D\u67E5\u8BE2\u56FE\u4E66");
        label_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_2.setBounds(21, 10, 143, 15);
        getContentPane().add(label_2);

        JLabel label_3 = new JLabel("\u6309\u4F5C\u8005\u67E5\u8BE2\u56FE\u4E66");
        label_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_3.setBounds(21, 124, 143, 15);
        getContentPane().add(label_3);

        JPanel panel_2 = new JPanel();
        panel_2.setLayout(null);
        panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_2.setBounds(23, 270, 385, 73);
        getContentPane().add(panel_2);

        JLabel label_4 = new JLabel("\u7C7B\u522B\uFF1A");
        label_4.setHorizontalAlignment(SwingConstants.CENTER);
        label_4.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_4.setBounds(10, 27, 95, 15);
        panel_2.add(label_4);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(101, 24, 125, 21);
        panel_2.add(textField_2);

        JButton button_2 = new JButton("\u67E5\u8BE2\u73B0\u6709");
        button_2.setIcon(new ImageIcon(Book_require.class.getResource("/images/search.png")));
        button_2.setBounds(255, 10, 104, 23);
        panel_2.add(button_2);

        JButton button_5 = new JButton("\u67E5\u8BE2\u5168\u90E8");
        button_5.setIcon(new ImageIcon(Book_require.class.getResource("/images/search.png")));
        button_5.setBounds(255, 40, 104, 23);
        panel_2.add(button_5);

        JLabel label_5 = new JLabel("\u6309\u5206\u7C7B\u67E5\u8BE2\u56FE\u4E66");
        label_5.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_5.setBounds(22, 250, 143, 15);
        getContentPane().add(label_5);

        textArea = new JTextArea();
        textArea.setText("\u4E66\u53F7         \u4E66\u540D         \u4F5C\u8005      \u4EF7\u683C");
        textArea.setBounds(22, 370, 385, 123);
        getContentPane().add(textArea);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String sqlrequire;
                sqlrequire = "EXEC sort_by_Book " +
                        "@Bname='" + textField.getText() + "'";

                if (!b_require(sqlrequire)) {
                    JOptionPane.showMessageDialog(null, "查询不到记录！！");
                }
            }
        });

        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String sqlrequire;
                sqlrequire = "EXEC sort_by_author " +
                        "@author='" + textField_1.getText() + "'";

                if (!b_require(sqlrequire)) {
                    JOptionPane.showMessageDialog(null, "查询不到记录！！");
                }
            }
        });

        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String sqlrequire;
                sqlrequire = "EXEC sort_by_BSort " +
                        "@sort='" + textField_2.getText() + "'";

                if (!b_require(sqlrequire)) {
                    JOptionPane.showMessageDialog(null, "查询不到记录！！");
                }
            }
        });
        button_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String sqlrequire;
                sqlrequire = "select ISBN,Bname,Bauthor,Bpublish,Bprice,Bcomment from book  " +
                        "where Bname='" + textField.getText() + "'";

                if (!b_require(sqlrequire)) {
                    JOptionPane.showMessageDialog(null, "查询不到记录！！");
                }
            }
        });

        button_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String sqlrequire;
                sqlrequire = "select ISBN,Bname,Bauthor,Bpublish,Bprice,Bcomment from book  " +
                        "where Bauthor='" + textField_1.getText() + "'";

                if (!b_require(sqlrequire)) {
                    JOptionPane.showMessageDialog(null, "查询不到记录！！");
                }
            }
        });

        button_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String sqlrequire;
                sqlrequire = "select ISBN,Bname,Bauthor,Bpublish,Bprice,Bcomment from book  " +
                        "where Bsort='" + textField_2.getText() + "'";

                if (!b_require(sqlrequire)) {
                    JOptionPane.showMessageDialog(null, "查询不到记录！！");
                }
            }
        });

    }

    public Boolean b_require(String sqlrequire) {
        boolean result = false;
        Connection conn = null;
        try {
            conn = Login.getCon();  //建立数据库连接
            PreparedStatement stmt = conn.prepareStatement(sqlrequire);   //会抛出异常
            r = stmt.executeQuery();
            while (r.next()) {
                result = true;
                String s1 = r.getString(1);
                String s2 = r.getString(2);
                String s3 = r.getString(3);
                String s4 = r.getString(5);
                textArea.append("\n" + s1 + "  " + s2 + "   " + s3 + "   " + s4);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally { //finally的用处是不管程序是否出现异常，都要执行finally语句，所以在此处关闭连接
            try {
                conn.close(); //打开一个Connection连接后，最后一定要调用它的close（）方法关闭连接，以释放系统资源及数据库资源
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
