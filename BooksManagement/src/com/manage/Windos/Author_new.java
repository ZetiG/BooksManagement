package com.manage.Windos;

import com.manage.Mapper.OperationSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Author_new extends JFrame {
    private JTextArea textArea;
    private JTextArea textArea_1;
    private JTextArea textArea_2;
    private JTextArea textArea_3;

    /**
     * Create the application.
     */
    public Author_new() {
        setTitle("图书作者新增");
        setVisible(true);
        setBounds(100, 100, 450, 300);
        setLocation(800, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel label = new JLabel("\u4F5C\u8005\u5E74\u9F84\uFF1A");
        label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(203, 88, 96, 15);
        getContentPane().add(label);

        textArea = new JTextArea();
        textArea.setColumns(10);
        textArea.setBounds(108, 35, 83, 24);
        getContentPane().add(textArea);

        JLabel label_1 = new JLabel("\u4F5C\u8005\u63CF\u8FF0\uFF1A");
        label_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_1.setHorizontalAlignment(SwingConstants.CENTER);
        label_1.setBounds(10, 129, 96, 15);
        getContentPane().add(label_1);

        textArea_1 = new JTextArea();
        textArea_1.setColumns(10);
        textArea_1.setBounds(309, 84, 83, 24);
        getContentPane().add(textArea_1);


        JButton button = new JButton("\u767B\u8BB0");
        button.setIcon(new ImageIcon(IDcard_new.class.getResource("/images/modify.png")));
        button.setBounds(171, 218, 84, 23);
        getContentPane().add(button);

        textArea_2 = new JTextArea();
        textArea_2.setText("\u5173\u4E8E\u4F5C\u8005\uFF0C50\u5B57\u4EE5\u5185\u3002");
        textArea_2.setBounds(33, 155, 359, 53);
        getContentPane().add(textArea_2);

        JLabel label_2 = new JLabel("\u4F5C\u8005\u6027\u522B\uFF1A");
        label_2.setHorizontalAlignment(SwingConstants.CENTER);
        label_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_2.setBounds(10, 88, 96, 15);
        getContentPane().add(label_2);

        textArea_3 = new JTextArea();
        textArea_3.setColumns(10);
        textArea_3.setBounds(108, 84, 83, 24);
        getContentPane().add(textArea_3);

        JLabel label_3 = new JLabel("\u4F5C\u8005\u59D3\u540D\uFF1A");
        label_3.setHorizontalAlignment(SwingConstants.CENTER);
        label_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_3.setBounds(10, 39, 96, 15);
        getContentPane().add(label_3);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if (Author_new()) {
                    JOptionPane.showMessageDialog(null, "登记成功啦！！");
                }
            }
        });
    }

    public Boolean Author_new() {
        boolean result = false;
        Connection conn = null;
        try {
            conn = OperationSQL.getCon();  //建立数据库连接
            if (!textArea_2.getText().equals("") && !textArea_2.getText().equals("关于作者，50字以内。")) {
                String sqlInset = "insert into Author(Bauthor,sex,about,age)  "
                        + "values('" + textArea.getText() + "','" + textArea_3.getText() + "','" + textArea_2.getText() + "','" + textArea_1.getText() + "')";
                PreparedStatement stmt = conn.prepareStatement(sqlInset); //会抛出异常
                int i = stmt.executeUpdate();
                if (i == 1) {
                    result = true;
                }
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

    public static void main(String[] args) {
        new Author_new();
    }

}
