package main.com.manage.Windos;

import main.com.manage.Mapper.OperationSQL;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Book_change extends JFrame {


    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextArea textArea;

    public static void main(String[] args) {
        new Book_change();

    }
    /**
     * Initialize the contents of the  .
     */
    public Book_change() {
        setTitle("查询学生信息");
        setVisible(true);
        setBounds(100, 100, 500, 411);
        setLocation(285, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblNewLabel_2 = new JLabel("学生信息查询:");
        lblNewLabel_2.setFont(new Font("微软雅黑", Font.BOLD, 16));
        lblNewLabel_2.setBounds(23, 10, 156, 29);
        getContentPane().add(lblNewLabel_2);

        JLabel lblNewLabel = new JLabel("请输入学生姓名或学号:");
        lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 12));
        lblNewLabel.setBounds(23, 59, 111, 19);
        getContentPane().add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(133, 59, 130, 21);
        getContentPane().add(textField);
        textField.setColumns(10);

        JLabel label = new JLabel("查询结果(如查询不到请联系管理员)");
        label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        label.setBounds(23, 95, 240, 29);
        getContentPane().add(label);

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel.setBounds(23, 122, 451, 210);
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel label_1 = new JLabel("姓名");
        label_1.setBounds(22, 24, 63, 19);
        label_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        panel.add(label_1);

        textField_1 = new JTextField();
        textField_1.setBounds(95, 24, 92, 21);
        textField_1.setColumns(10);
        panel.add(textField_1);

        JLabel label_2 = new JLabel("\u4F5C\u8005\u540D\uFF1A");
        label_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_2.setBounds(249, 24, 63, 19);
        panel.add(label_2);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(322, 24, 92, 21);
        panel.add(textField_2);

        JLabel label_3 = new JLabel("\u51FA\u7248\u793E\uFF1A");
        label_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_3.setBounds(249, 65, 63, 19);
        panel.add(label_3);

        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(322, 65, 92, 21);
        panel.add(textField_3);

        JLabel label_4 = new JLabel("\u56FE\u4E66\u7B80\u4ECB\uFF1A");
        label_4.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_4.setBounds(22, 148, 70, 15);
        panel.add(label_4);

        JLabel label_5 = new JLabel("\u7C7B\u522B\uFF1A");
        label_5.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_5.setBounds(22, 63, 63, 19);
        panel.add(label_5);

        textField_4 = new JTextField();
        textField_4.setColumns(10);
        textField_4.setBounds(95, 63, 92, 21);
        panel.add(textField_4);

        JLabel label_6 = new JLabel("\u4EF7\u683C\uFF1A");
        label_6.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_6.setBounds(22, 102, 63, 19);
        panel.add(label_6);

        textField_5 = new JTextField();
        textField_5.setColumns(10);
        textField_5.setBounds(95, 102, 92, 21);
        panel.add(textField_5);

        textArea = new JTextArea();
        textArea.setRows(3);
        textArea.setText("\u8BF7\u8F93\u5165\u7B80\u4ECB\uFF0C50\u5B57\u4EE5\u5185\u3002");
        textArea.setBounds(95, 144, 319, 48);
        panel.add(textArea);

        JButton button = new JButton("\u4FEE\u6539");
        button.setIcon(new ImageIcon(Book_change.class.getResource("/main/images/modify.png")));
        button.setBounds(345, 58, 93, 23);
        getContentPane().add(button);

        button.addActionListener(e -> {
            // TODO Auto-generated method stub
            if (b_change()) {
                JOptionPane.showMessageDialog(null, "更改成功啦！！");
            } else {
                JOptionPane.showMessageDialog(null, "请更改为已有作者或分类！！");
            }
        });
    }


    public Boolean b_change() {
        boolean result = false;
        Connection conn = null;
        try {
            conn = OperationSQL.getCon();  //建立数据库连接
            int flag = 0;
            String sqlupdate = "UPDATE book SET ";
            String where = " WHERE ISBN=" + textField.getText();

            if (!textField_1.getText().equals("")) {
                if (flag == 0) {
                    sqlupdate = sqlupdate + "Bname='" + textField_1.getText() + "'";
                    flag = 1;
                }
            }

            if (!textField_2.getText().equals("")) {
                if (flag == 0) {
                    sqlupdate = sqlupdate + "Bauthor='" + textField_2.getText() + "'";
                    flag = 1;
                } else {
                    sqlupdate = sqlupdate + "," + "Bauthor='" + textField_2.getText() + "'";
                }
            }

            if (!textField_3.getText().equals("")) {
                if (flag == 0) {
                    sqlupdate = sqlupdate + "Bpublish='" + textField_3.getText() + "'";
                    flag = 1;
                } else {
                    sqlupdate = sqlupdate + "," + "Bpublish='" + textField_3.getText() + "'";
                }
            }


            if (!textField_4.getText().equals("")) {
                if (flag == 0) {
                    sqlupdate = sqlupdate + "Bsort='" + textField_4.getText() + "'";
                    flag = 1;
                } else {
                    sqlupdate = sqlupdate + "," + "Bsort='" + textField_4.getText() + "'";
                }
            }


            if (!textField_5.getText().equals("")) {
                if (flag == 0) {
                    sqlupdate = sqlupdate + "Bprice=" + textField_5.getText();
                    flag = 1;
                } else {
                    sqlupdate = sqlupdate + "," + "Bprice=" + textField_5.getText();
                }
            }

            if (!textArea.getText().equals("") && !textArea.getText().equals("请输入简介，50字以内。")) {
                if (flag == 0) {
                    sqlupdate = sqlupdate + "Bcomment='" + textArea.getText() + "'";
                    flag = 1;
                } else {
                    sqlupdate = sqlupdate + "," + "Bcomment='" + textArea.getText() + "'";
                }
            }

            sqlupdate = sqlupdate + where;
            PreparedStatement stmt = conn.prepareStatement(sqlupdate);   //会抛出异常
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
