package com.manage.teacher;

import com.manage.Mapper.OperationSQL;
import com.manage.Windos.IDcard_new;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddStudentUI extends JFrame {

    private JTextArea textArea;
    private JTextArea textArea_1;
    private JTextArea textArea_2;
    private JTextArea textArea_3;
    private JTextArea textArea_4;

    JRadioButton check1, check2;
    ButtonGroup bg;

    public static void main(String[] args) {
        new AddStudentUI();
    }

    /**
     * Create the application.
     */
    public AddStudentUI() {
        setTitle("学生登记");
        setVisible(true);
        setBounds(100, 100, 600, 450);
        setLocation(800, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel label_3 = new JLabel("学生姓名：");
        label_3.setHorizontalAlignment(SwingConstants.CENTER);
        label_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_3.setBounds(10, 39, 96, 15);
        getContentPane().add(label_3);

        textArea = new JTextArea();
        textArea.setColumns(10);
        textArea.setBounds(100, 35, 100, 24);
        getContentPane().add(textArea);



        JLabel label_4 = new JLabel("学生学号：");
        label_4.setHorizontalAlignment(SwingConstants.CENTER);
        label_4.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_4.setBounds(210, 39, 96, 15);
        getContentPane().add(label_4);

        textArea_4 = new JTextArea();
        textArea_4.setColumns(10);
        textArea_4.setBounds(301, 35, 100, 24);
        getContentPane().add(textArea_4);


        JLabel label = new JLabel("学生姓名：");
        label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(210, 88, 96, 15);
        getContentPane().add(label);

        textArea_1 = new JTextArea();
        textArea_1.setColumns(10);
        textArea_1.setBounds(301, 84, 100, 24);
        getContentPane().add(textArea_1);

        JLabel label_2 = new JLabel("学生性别：");
        label_2.setHorizontalAlignment(SwingConstants.CENTER);
        label_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_2.setBounds(10, 88, 96, 15);
        getContentPane().add(label_2);

        check1 = new JRadioButton("男", true);
        check2 = new JRadioButton("女");
        check1.setBounds(100, 84, 60, 25);
        check2.setBounds(160, 84, 60, 25);
        bg = new ButtonGroup();
        bg.add(check1);
        bg.add(check2);
        getContentPane().add(check1);
        getContentPane().add(check2);


        JLabel label_1 = new JLabel("学生简介：");
        label_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_1.setHorizontalAlignment(SwingConstants.CENTER);
        label_1.setBounds(10, 129, 96, 15);
        getContentPane().add(label_1);

        textArea_2 = new JTextArea();
        textArea_2.setText("关于学生，200字以内。");
        textArea_2.setBounds(33, 155, 376, 53);
        getContentPane().add(textArea_2);


        JButton button = new JButton("登记");
        button.setIcon(new ImageIcon(IDcard_new.class.getResource("/images/modify.png")));
        button.setBounds(171, 218, 84, 23);
        getContentPane().add(button);


        button.addActionListener(e -> {
            // TODO Auto-generated method stub
            if (Author_new()) {
                JOptionPane.showMessageDialog(null, "登记成功啦！！");
            }
        });
    }

    public boolean Author_new() {
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


}
