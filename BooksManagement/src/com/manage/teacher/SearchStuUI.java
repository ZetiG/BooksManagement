package com.manage.teacher;

import com.manage.Mapper.OperationSQL;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchStuUI extends JFrame {

    private JTextField userText = new JTextField();
    private JTextField stuName = new JTextField();
    private JTextField stuNo = new JTextField();
    private JTextField stuAge = new JTextField();
    private JTextField stuSex = new JTextField();
    private JTextField stuPhone = new JTextField();
    private JTextField stuMajor = new JTextField();
    private JTextArea motto = new JTextArea();

    private Connection Connec;
    private static ResultSet result;

    public static void main(String[] args) {
        new SearchStuUI();

    }


    public SearchStuUI() {
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

        JLabel lblNewLabel = new JLabel("请输入姓名或学号:");
        lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 12));
        lblNewLabel.setBounds(23, 59, 111, 19);
        getContentPane().add(lblNewLabel);

        userText.setColumns(10);
        userText.setBounds(133, 59, 130, 21);
        getContentPane().add(userText);


        JLabel label = new JLabel("查询结果(如查询不到请联系管理员)");
        label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        label.setBounds(23, 95, 240, 29);
        getContentPane().add(label);

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel.setBounds(23, 122, 451, 210);
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel label_1 = new JLabel("姓名:");
        label_1.setBounds(22, 24, 63, 19);
        label_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        panel.add(label_1);

        stuName.setBounds(95, 24, 115, 21);
        stuName.setColumns(10);
        panel.add(stuName);

        JLabel label_2 = new JLabel("学号:");
        label_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_2.setBounds(249, 24, 63, 19);
        panel.add(label_2);

        stuNo.setColumns(10);
        stuNo.setBounds(310, 24, 115, 21);
        panel.add(stuNo);

        JLabel label_3 = new JLabel("年龄:");
        label_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_3.setBounds(249, 63, 63, 19);
        panel.add(label_3);

        stuAge.setColumns(10);
        stuAge.setBounds(310, 63, 115, 21);
        panel.add(stuAge);


        JLabel label_7 = new JLabel("专业:");
        label_7.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_7.setBounds(249, 102, 63, 19);
        panel.add(label_7);

        stuMajor.setColumns(10);
        stuMajor.setBounds(310, 102, 115, 21);
        panel.add(stuMajor);


        JLabel label_5 = new JLabel("性别:");
        label_5.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_5.setBounds(22, 63, 63, 19);
        panel.add(label_5);

        stuSex.setColumns(10);
        stuSex.setBounds(95, 63, 115, 21);
        panel.add(stuSex);

        JLabel label_6 = new JLabel("联系方式:");
        label_6.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_6.setBounds(22, 102, 63, 19);
        panel.add(label_6);

        stuPhone.setColumns(10);
        stuPhone.setBounds(95, 102, 115, 21);
        panel.add(stuPhone);

        JLabel label_4 = new JLabel("备注信息:");
        label_4.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_4.setBounds(22, 148, 70, 15);
        panel.add(label_4);

        motto.setRows(3);
        motto.setText("备注信息不超过200字");
        motto.setBounds(95, 144, 325, 48);
        panel.add(motto);

        JButton button = new JButton("查询");
        button.setIcon(new ImageIcon(com.manage.Windos.Book_change.class.getResource("/images/search.png")));
        button.setBounds(345, 58, 93, 23);
        getContentPane().add(button);

        button.addActionListener(e -> {
            if (selectStu(userText.getText())) {
                JOptionPane.showMessageDialog(null, "查询成功！");
            }
        });
    }


    /**
     * 输入姓名或学号查询学生信息
     *
     * @param user
     * @return
     */
    private boolean selectStu(String user) {
        if (!user.isEmpty()) {
            try {
                Connec = OperationSQL.getCon();
                String sql = "select stu.name,stu.stu_no,stu.age,mj.major,stu.sex,stu.phone,stu.motto " +
                        "from t_student stu left join t_major mj on stu.major=mj.id where stu.name=? or stu.stu_no=?";
                PreparedStatement pstm = Connec.prepareStatement(sql);
                pstm.setString(1, user);
                pstm.setString(2, user);
                result = pstm.executeQuery();
                if (result.next()) {
                    stuName.setText(result.getString(1));
                    stuNo.setText(result.getString(2));
                    stuAge.setText(result.getString(3));
                    stuMajor.setText(result.getString(4));
                    stuSex.setText(result.getString(5));
                    stuPhone.setText(result.getString(6));
                    motto.setText(result.getString(7));
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "查不到该学生信息，请联系管理员！");
                    return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                OperationSQL.closeConnet();
            }
        }
        JOptionPane.showMessageDialog(null, "请输入正确的信息");
        return false;
    }

}
