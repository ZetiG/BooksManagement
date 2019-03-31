package com.manage.teacher;

import com.manage.Mapper.OperationSQL;
import com.manage.Windos.Login;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherUI extends JFrame {

    private static JTextField name = new JTextField();
    private static JTextField sex = new JTextField();
    private static JTextField age = new JTextField();
    private static JTextField proxyClass = new JTextField();
    private static JTextField phone = new JTextField();
    private static JTextField staffNo = new JTextField();
    private static JTextArea motto = new JTextArea();

    JRadioButton check1, check2;
    ButtonGroup bg;
    //初始化单选按钮
    boolean checked01 = false;
    boolean checked02 = false;

    JComboBox jcb1;
    JComboBox jcb2;

    private Connection Connec;
    private static ResultSet result;

    public static void main(String[] args) {
        new TeacherUI();
    }

    /**
     * Create the application.
     */
    public TeacherUI() {
        super("教师个人信息");
        //查询数据库教师表，将信息展示在页面
        selectTeacher();

        setVisible(true);
        setBounds(100, 100, 450, 300);
        setLocation(330, 400);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel lblIsbn = new JLabel("姓名:");
        lblIsbn.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        lblIsbn.setHorizontalAlignment(SwingConstants.CENTER);
        lblIsbn.setBounds(10, 36, 96, 15);
        getContentPane().add(lblIsbn);

        name.setColumns(10);
        name.setBounds(100, 32, 105, 24);
        getContentPane().add(name);

        JLabel label = new JLabel("性别:");
        label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(25, 78, 71, 15);
        getContentPane().add(label);

        //如何查询为女， 按钮2设为true，反之按钮1设为true
        if (sex.getText().equals("女")) {
            checked02 = true;
        } else {
            checked01 = true;
        }
        check1 = new JRadioButton("男", checked01);
        check2 = new JRadioButton("女", checked02);
        check1.setBounds(100, 74, 60, 25);
        check2.setBounds(160, 74, 60, 25);
        bg = new ButtonGroup();
        bg.add(check1);
        bg.add(check2);
        getContentPane().add(check1);
        getContentPane().add(check2);


        JLabel label_1 = new JLabel("年龄:");
        label_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_1.setHorizontalAlignment(SwingConstants.CENTER);
        label_1.setBounds(25, 122, 71, 15);
        getContentPane().add(label_1);

        String[] ages = {"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35",
                "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50"};
        jcb1 = new JComboBox(ages);
        jcb1.setBounds(100, 118, 105, 24);
        String teaAge = age.getText();
        if (null != teaAge) {
            int age = Integer.parseInt(teaAge) - 16;
            jcb1.setSelectedIndex(age);
        }
        getContentPane().add(jcb1);


        JLabel label_2 = new JLabel("代理班级:");
        label_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_2.setHorizontalAlignment(SwingConstants.CENTER);
        label_2.setBounds(25, 163, 71, 15);
        getContentPane().add(label_2);

        java.util.List<String> list = OperationSQL.getAllClass();
        String[] classes = list.toArray(new String[list.size()]);
        jcb2 = new JComboBox(classes);
        jcb2.setBounds(100, 159, 105, 24);
        String classId = proxyClass.getText();
        if (null != classId) {
            String className = OperationSQL.getClassName(classId);
            int index = 0;
            for (String aClass : classes) {
                if (aClass.equals(className)) {
                    jcb2.setSelectedIndex(index);
                    break;
                }
                index++;
            }

        }
        getContentPane().add(jcb2);


        JLabel label_3 = new JLabel("联系方式:");
        label_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_3.setHorizontalAlignment(SwingConstants.CENTER);
        label_3.setBounds(30, 201, 59, 15);
        getContentPane().add(label_3);

        phone.setColumns(10);
        phone.setBounds(100, 197, 105, 24);
        getContentPane().add(phone);

        JLabel label_4 = new JLabel("备注信息:");
        label_4.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_4.setBounds(235, 78, 146, 15);
        getContentPane().add(label_4);

        motto.setRows(8);
        motto.setBounds(235, 105, 161, 73);
        getContentPane().add(motto);

        JButton button = new JButton("修改");
        button.setBounds(231, 197, 84, 23);
        getContentPane().add(button);

        JButton btnNewButton = new JButton("返回");
        btnNewButton.setBounds(332, 197, 77, 23);
        getContentPane().add(btnNewButton);


        JLabel label_5 = new JLabel("职工号:");
        label_5.setHorizontalAlignment(SwingConstants.LEFT);
        label_5.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_5.setBounds(235, 36, 96, 15);
        getContentPane().add(label_5);

        staffNo.setEditable(false);
        staffNo.setColumns(10);
        staffNo.setBounds(286, 32, 102, 24);
        getContentPane().add(staffNo);

        //监听返回按钮
        btnNewButton.addActionListener(e -> {
            setVisible(false);
        });

        //监听修改按钮
        button.addActionListener(e -> {
            //设置对象的年龄
            if (check1.isSelected()) {
                sex.setText("男");
            } else {
                sex.setText("女");
            }
            int setAge = jcb1.getSelectedIndex() + 16;
            age.setText(String.valueOf(setAge));
            //设置专业对应的ID
            int index = jcb2.getSelectedIndex();
            String className = list.get(index);
            int id = OperationSQL.getClassId(className);
            proxyClass.setText(String.valueOf(id));
            //更新数据库
            if (updateUserInfo()) {
                JOptionPane.showMessageDialog(null, "信息修改成功！");
            } else {
                JOptionPane.showMessageDialog(null, "修改失败,请重试！");
            }
        });

        //关闭连接
        OperationSQL.closeConnet();
    }

    /**
     * 查询个人信息
     */
    private void selectTeacher() {
        try {
            Connec = OperationSQL.getCon();
            String sql = "select staff_no,name,sex,age,class,phone,motto from t_teacher where is_deleted=0 and user_id = ?";
            PreparedStatement pstm = Connec.prepareStatement(sql);
            pstm.setString(1, String.valueOf(Login.userId));
            result = pstm.executeQuery();
            if (result.next()) {
                staffNo.setText(result.getString(1));
                name.setText(result.getString(2));
                sex.setText(result.getString(3));
                age.setText(result.getString(4));
                proxyClass.setText(result.getString(5));
                phone.setText(result.getString(6));
                motto.setText(result.getString(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            OperationSQL.closeConnet();
        }
    }

    /**
     * 修改个人信息
     *
     * @return
     */
    private boolean updateUserInfo() {
        try {
            Connec = OperationSQL.getCon();
            String sql = "update t_teacher set name=?,sex=?,age=?,class=?,phone=?,motto=? where user_id = ?";
            PreparedStatement pstm = Connec.prepareStatement(sql);
            pstm.setString(1, name.getText());
            pstm.setString(2, sex.getText());
            pstm.setString(3, age.getText());
            pstm.setString(4, proxyClass.getText());
            pstm.setString(5, phone.getText());
            pstm.setString(6, motto.getText());
            pstm.setString(7, String.valueOf(Login.userId));
            int update = pstm.executeUpdate();
            if (update > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            OperationSQL.closeConnet();
        }
        return false;
    }

}
