package Windos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;

public class Login extends JFrame {
    //登录界面
    private JButton jb1 = new JButton("登录");
    private JButton jb2 = new JButton("退出");
    private JPasswordField passwordTxt = new JPasswordField(20);
    private JTextField textField = new JTextField(20);
    private static Connection conn = null;
    private static String user;
    private static String psw;

    JRadioButton check1, check2;
    ButtonGroup bg;


    public static void main(String arg[]) {
        new Login();
    }

    public Login() {
        super("管理员登录");

        JPanel p1 = new JPanel();
        JPanel p1_1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p4 = new JPanel();
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
        p2.setLayout(new FlowLayout());
        p2.add(new JLabel("密    码:"));
        p2.add(passwordTxt);

        check1 = new JRadioButton("教师", true);
        check2 = new JRadioButton("学生");
        bg = new ButtonGroup();
        bg.add(check1);
        bg.add(check2);
        p4.add(check1);
        p4.add(check2);

        p3.setLayout(new FlowLayout());
        jb1.setIcon(new ImageIcon(Login.class.getResource("/images/login.png")));
        p3.add(jb1);
        jb2.setIcon(new ImageIcon(Login.class.getResource("/images/reset.png")));
        p3.add(jb2);

        jb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                setVisible(false);
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            user = textField.getText();
                            psw = passwordTxt.getText();
                            if (getCon() != null) {
                                MainJF mjf = new MainJF();
                            } else {
                                JOptionPane.showMessageDialog(null, "请输入正确管理员账号密码");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

        });

        jb2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                System.exit(0);
            }
        });

        setSize(450, 280);
        setVisible(true);
        setLocation(600, 300);
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

}
