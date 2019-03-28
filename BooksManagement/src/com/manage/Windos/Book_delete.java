package com.manage.Windos;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Book_delete extends JFrame {
    private JTextField textField;
    private JTextField textField_1;

    public static void main(String[] args) {
        new Book_delete();
    }
    /**
     * Initialize the contents of the frame.
     */
    public Book_delete() {
        setTitle("图书删除");
        setVisible(true);
        setBounds(100, 100, 396, 328);
        setLocation(330, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel.setBounds(23, 30, 334, 73);
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel_2 = new JLabel("\u4E66\u53F7\uFF08ISBN\uFF09\uFF1A");
        lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        lblNewLabel_2.setBounds(10, 27, 95, 15);
        panel.add(lblNewLabel_2);

        textField = new JTextField();
        textField.setBounds(101, 24, 125, 21);
        panel.add(textField);
        textField.setColumns(10);


        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_1.setBounds(22, 144, 334, 83);
        getContentPane().add(panel_1);
        panel_1.setLayout(null);

        JLabel label = new JLabel("\u4E66\u540D\uFF1A");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label.setBounds(10, 37, 95, 15);
        panel_1.add(label);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(101, 34, 125, 21);
        panel_1.add(textField_1);

        JLabel lblNewLabel = new JLabel("\u6309\u4E66\u53F7\uFF08ISBN\uFF09\u5220\u9664");
        lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        lblNewLabel.setBounds(22, 10, 143, 15);
        getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("\u6309\u4E66\u540D\u5220\u9664");
        lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        lblNewLabel_1.setBounds(22, 124, 143, 15);
        getContentPane().add(lblNewLabel_1);

        JButton button = new JButton("\u5220\u9664");
        button.setBounds(164, 246, 93, 23);
        getContentPane().add(button);
        button.setIcon(new ImageIcon(Book_delete.class.getResource("/images/delete.png")));

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

                if (b_delete()) {
                    JOptionPane.showMessageDialog(null, "删除成功啦！！");
                }
            }
        });
    }

    public Boolean b_delete() {
        boolean result = false;
        Connection conn = null;
        try {
            conn = Login.getCon();  //建立数据库连接
            String sqldelete;
            if (textField.getText() != null) {
                sqldelete = "delete from Book  where ISBN =" + textField.getText();
            } else {
                sqldelete = "delete from Book where Bname ='" + textField_1.getText() + "'";
            }

            PreparedStatement stmt = conn.prepareStatement(sqldelete);   //会抛出异常
            int i = stmt.executeUpdate();
            if (i == 1) {
                result = true;
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
