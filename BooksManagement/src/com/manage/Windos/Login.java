package com.manage.Windos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame {
    //登录界面
    private JButton jb1 = new JButton("登录");
    private JButton jb2 = new JButton("退出");
    private JPasswordField passwordTxt = new JPasswordField(20);
    private JTextField textField = new JTextField(20);
    private static Connection conn = null;
    static PreparedStatement preStm = null;
    static ResultSet result = null;
    private static String user;
    private static String psw;
    private String role;

    JRadioButton check1, check2;
    ButtonGroup bg;


    public static void main(String arg[]) {
        new Login();
    }

    public Login() {
        super("欢迎登陆学生管理系统");

        JPanel p1 = new JPanel();
        JPanel p1_1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p2_1 = new JPanel();
        JPanel p2_2 = new JPanel();
        JPanel p3 = new JPanel();
        JLabel label1 = new JLabel("学生管理系统", JLabel.CENTER);
        label1.setFont(new Font("黑体", Font.BOLD, 25));
        label1.setIcon(new ImageIcon(Login.class.getResource("/images/login_logo1.png")));

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(p1, BorderLayout.NORTH);
        cp.add(p2, BorderLayout.CENTER);
        cp.add(p3, BorderLayout.SOUTH);
        p1.setLayout(new BorderLayout());
        p1.add(label1, BorderLayout.CENTER);
        p1.add(p1_1, BorderLayout.SOUTH);
        p1_1.setLayout(new FlowLayout());
        p1_1.add(new JLabel("登录名:"));
        p1_1.add(textField);

        p2.setLayout(new BorderLayout());
        p2.add(p2_1, BorderLayout.NORTH);
        p2.add(p2_2, BorderLayout.CENTER);

        p2_1.setLayout(new FlowLayout());
        p2_1.add(new JLabel("密    码:"));
        p2_1.add(passwordTxt);

        check1 = new JRadioButton("教师", true);
        check2 = new JRadioButton("学生");
        bg = new ButtonGroup();
        bg.add(check1);
        bg.add(check2);
        p2_2.add(check1);
        p2_2.add(check2);

        p3.setLayout(new FlowLayout());
        jb1.setIcon(new ImageIcon(Login.class.getResource("/images/login.png")));
        p3.add(jb1);
        jb2.setIcon(new ImageIcon(Login.class.getResource("/images/reset.png")));
        p3.add(jb2);

        jb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
//                setVisible(false); //监听是否关闭页面
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        getCon();
                        try {
                            user = textField.getText();
                            psw = passwordTxt.getText();
                            if (check1.isSelected()) {
                                role = "1";
                            } else if (check2.isSelected()) {
                                role = "2";
                            }
                            boolean login = queryUser(user, psw, role);
                            if (login) {
                                //关闭当前界面
                                dispose();
                                new MainJF();
                            } else {
                                clear();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

        });

        //退出系统
        jb2.addActionListener(e -> {
            System.exit(0);
        });

        setSize(500, 350);
        setVisible(true);
        setLocation(600, 300);
        //设置当关闭窗口时，保证JVM也退出
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static Connection getCon() {
        try {
            Class.forName("com.mysql.jdbc.Driver"); //加载数据库连接驱动
            String url = "jdbc:mysql://47.98.162.196:3306/student_manage?characterEncoding=utf8&useSSL=false"; //连接数据库地址
            String user = "root"; //MySQL配置时的用户名
            String password = "mysql123"; //MySQL配置时的密码
            conn = DriverManager.getConnection(url, user, password);  //获取连接
        } catch (Exception e) {
            System.out.println("连接数据库失败");
            e.printStackTrace();
        }
        return conn;
    }

    public static boolean queryUser(String account, String pwd, String role) {
        try {
            //创建SQL语句
            String sql = "select id,account,pwd,role from t_user where account=? and role=? and flag='0'";
            preStm = conn.prepareStatement(sql);
            // 给?赋值(可防止SQL注入问题)，不要直接使用拼接的方式
            preStm.setString(1, account);
            preStm.setString(2, role);
            //查询结果集
            result = preStm.executeQuery();

            // 循环取出
            if (result.next()) {
                //判断密码是否正确
                String passWord = result.getString(3);
                if (passWord.equals(pwd)) {
                    return true;
                }
                JOptionPane.showMessageDialog(null, "密码错误，请重试！", "提示消息", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "该用户不存在！", "提示消息", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } finally {
            //关闭连接
            closeConnet();
        }
        return false;
    }

    /**
     * 公共调用，关闭连接
     */
    private static void closeConnet() {
        //关闭连接
        try {
            result.close();
            preStm.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 清空文本框和密码框
     */
    public void clear() {
        textField.setText("");
        passwordTxt.setText("");
    }
}
