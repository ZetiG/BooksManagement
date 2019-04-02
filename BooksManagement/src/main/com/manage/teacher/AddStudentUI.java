package main.com.manage.teacher;

import main.com.manage.Mapper.OperationSQL;
import main.com.manage.Windos.IDcard_new;
import main.com.manage.Windos.Login;
import main.com.manage.utils.UserNo;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AddStudentUI extends JFrame {

    private JTextArea stuName;
    private JTextArea stuNo;
    private JTextArea stuSex = new JTextArea();
    private JTextArea stuMajor = new JTextArea();
    private JTextArea stuAge = new JTextArea();
    private JTextArea stuPhone;
    private JTextArea stuMotto;
    private JTextArea stuAccount;
    private JTextArea stuPwd;

    JRadioButton check1, check2;
    ButtonGroup bg;
    JComboBox jcb1;
    JComboBox jcb2;

    private static final String number = UserNo.generateStuNo();

    public static void main(String[] args) {
        new AddStudentUI();
    }

    /**
     * Create the application.
     */
    public AddStudentUI() {
        setTitle("学生登记");
        setVisible(true);
        setBounds(100, 100, 500, 450);
        setLocation(800, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel label_3 = new JLabel("学生姓名：");
        label_3.setHorizontalAlignment(SwingConstants.CENTER);
        label_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_3.setBounds(10, 39, 96, 15);
        getContentPane().add(label_3);

        stuName = new JTextArea();
        stuName.setColumns(10);
        stuName.setBounds(100, 35, 130, 24);
        getContentPane().add(stuName);


        JLabel label_4 = new JLabel("学号(默认)：");
        label_4.setHorizontalAlignment(SwingConstants.CENTER);
        label_4.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_4.setBounds(260, 39, 96, 15);
        getContentPane().add(label_4);

        stuNo = new JTextArea();
        stuNo.setEditable(false);
        stuNo.setText(number);
        stuNo.setColumns(10);
        stuNo.setBounds(351, 35, 130, 24);
        getContentPane().add(stuNo);

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

        JLabel label = new JLabel("年龄：");
        label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(260, 88, 96, 15);
        getContentPane().add(label);

        String[] ages = {"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35",
                "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50"};
        jcb1 = new JComboBox(ages);
        jcb1.setBounds(351, 84, 100, 24);
        getContentPane().add(jcb1);

        //专业
        JLabel label_5 = new JLabel("专业(默认)：");
        label_5.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_5.setHorizontalAlignment(SwingConstants.CENTER);
        label_5.setBounds(13, 137, 96, 15);
        getContentPane().add(label_5);

        java.util.List<String> list = OperationSQL.getAllClass();
        String[] classes = list.toArray(new String[list.size()]);
        jcb2 = new JComboBox(classes);
        jcb2.setBounds(100, 137, 130, 24);
        String classId = OperationSQL.getUserClassNo(Login.userId);
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


        //联系方式
        JLabel label_6 = new JLabel("联系方式：");
        label_6.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_6.setHorizontalAlignment(SwingConstants.CENTER);
        label_6.setBounds(260, 137, 96, 15);
        getContentPane().add(label_6);

        stuPhone = new JTextArea();
        stuPhone.setColumns(10);
        stuPhone.setBounds(351, 137, 130, 24);
        getContentPane().add(stuPhone);

        //设置账号密码
        JLabel label_7 = new JLabel("登录账号(默认学号)：");
        label_7.setHorizontalAlignment(SwingConstants.CENTER);
        label_7.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_7.setBounds(28, 186, 116, 15);
        getContentPane().add(label_7);

        stuAccount = new JTextArea();
        stuAccount.setText(number);
        stuAccount.setColumns(10);
        stuAccount.setBounds(28, 206, 150, 24);
        getContentPane().add(stuAccount);

        JLabel label_8 = new JLabel("密码(默认)：");
        label_8.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_8.setHorizontalAlignment(SwingConstants.CENTER);
        label_8.setBounds(270, 186, 96, 15);
        getContentPane().add(label_8);

        stuPwd = new JTextArea();
        stuPwd.setText("123456");
        stuPwd.setColumns(10);
        stuPwd.setBounds(285, 206, 150, 24);
        getContentPane().add(stuPwd);


        JLabel label_1 = new JLabel("学生简介：");
        label_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_1.setHorizontalAlignment(SwingConstants.CENTER);
        label_1.setBounds(10, 259, 96, 15);
        getContentPane().add(label_1);

        stuMotto = new JTextArea();
        stuMotto.setText("关于学生，200字以内。");
        stuMotto.setBounds(33, 285, 436, 53);
        getContentPane().add(stuMotto);


        JButton button = new JButton("登记");
        button.setIcon(new ImageIcon(IDcard_new.class.getResource("/main/images/modify.png")));
        button.setBounds(180, 355, 84, 23);
        getContentPane().add(button);


        button.addActionListener(e -> {
            if (stuName.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "学生姓名不能为空！");
            }
            if (stuPhone.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "联系方式不能为空！");
            }
            if (stuAccount.getText().length() < 6) {
                if (stuAccount.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "登录账号不能为空！");
                } else {
                    JOptionPane.showMessageDialog(null, "账号长度不能小于6位！");
                }
            }
            if (stuPwd.getText().length() < 6) {
                if (stuPwd.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "登录密码不能为空！");
                } else {
                    JOptionPane.showMessageDialog(null, "密码长度不能小于6位！");
                }
            }

            //设置对象的年龄
            if (check1.isSelected()) {
                stuSex.setText("男");
            } else {
                stuSex.setText("女");
            }
            int setAge = jcb1.getSelectedIndex() + 16;
            stuAge.setText(String.valueOf(setAge));
            //设置专业对应的ID
            int index = jcb2.getSelectedIndex();
            String className = list.get(index);
            int id = OperationSQL.getClassId(className);
            stuMajor.setText(String.valueOf(id));
            //新增学生
            if (addStudent()) {
                JOptionPane.showMessageDialog(null, "登记成功！");
            }
        });
    }

    public boolean addStudent() {
        try {
            Connection conn = OperationSQL.getCon();  //建立数据库连接
            String sql = "insert into t_user (account, pwd, role) value (?,?,2)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, stuAccount.getText());
            stmt.setString(2, stuPwd.getText());
            int addUser = stmt.executeUpdate();

            if (addUser > 0) {
                ResultSet resultSet = stmt.getGeneratedKeys();
                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String sql2 = "insert into t_student (user_id, major, stu_no, name, sex, age, phone, motto) value (?,?,?,?,?,?,?,?)";
                    PreparedStatement pstm = conn.prepareStatement(sql2);
                    pstm.setString(1, String.valueOf(id));
                    pstm.setString(2, stuMajor.getText());
                    pstm.setString(3, stuNo.getText());
                    pstm.setString(4, stuName.getText());
                    pstm.setString(5, stuSex.getText());
                    pstm.setString(6, stuAge.getText());
                    pstm.setString(7, stuPhone.getText());
                    pstm.setString(8, stuMotto.getText());
                    int addStu = pstm.executeUpdate();
                    if (addStu > 0) {
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            OperationSQL.closeConnet();
        }

        return false;
    }


}
