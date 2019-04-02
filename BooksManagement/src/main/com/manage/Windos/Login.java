package main.com.manage.Windos;

import main.com.manage.Mapper.OperationSQL;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
    //��¼����
    private JButton jb1 = new JButton("��¼");
    private JButton jb2 = new JButton("�˳�");
    private JPasswordField passwordTxt = new JPasswordField(20);
    private JTextField textField = new JTextField(20);

    private static String user;
    private static String psw;
    private String role;
    public static int userId;

    JRadioButton check1, check2;
    ButtonGroup bg;


    public static void main(String arg[]) {
        new Login();
    }

    public Login() {
        super("��ӭ��½ѧ������ϵͳ");

        JPanel p1 = new JPanel();
        JPanel p1_1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p2_1 = new JPanel();
        JPanel p2_2 = new JPanel();
        JPanel p3 = new JPanel();
        JLabel label1 = new JLabel("ѧ������ϵͳ", JLabel.CENTER);
        label1.setFont(new Font("����", Font.BOLD, 25));
        label1.setIcon(new ImageIcon(Login.class.getResource("/main/images/login01.png")));

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(p1, BorderLayout.NORTH);
        cp.add(p2, BorderLayout.CENTER);
        cp.add(p3, BorderLayout.SOUTH);
        p1.setLayout(new BorderLayout());
        p1.add(label1, BorderLayout.CENTER);
        p1.add(p1_1, BorderLayout.SOUTH);
        p1_1.setLayout(new FlowLayout());
        p1_1.add(new JLabel("��¼��:"));
        p1_1.add(textField);

        p2.setLayout(new BorderLayout());
        p2.add(p2_1, BorderLayout.NORTH);
        p2.add(p2_2, BorderLayout.CENTER);

        p2_1.setLayout(new FlowLayout());
        p2_1.add(new JLabel("��    ��:"));
        p2_1.add(passwordTxt);

        check1 = new JRadioButton("��ʦ", true);
        check2 = new JRadioButton("ѧ��");
        bg = new ButtonGroup();
        bg.add(check1);
        bg.add(check2);
        p2_2.add(check1);
        p2_2.add(check2);

        p3.setLayout(new FlowLayout());
        jb1.setIcon(new ImageIcon(Login.class.getResource("/main/images/login.png")));
        p3.add(jb1);
        jb2.setIcon(new ImageIcon(Login.class.getResource("/main/images/reset.png")));
        p3.add(jb2);

        jb1.addActionListener(e -> {
//            setVisible(false); //�����Ƿ�ر�ҳ��
            EventQueue.invokeLater(() -> {
                OperationSQL.getCon();
                try {
                    user = textField.getText();
                    psw = passwordTxt.getText();
                    if (check1.isSelected()) {
                        role = "1";
                    } else if (check2.isSelected()) {
                        role = "2";
                    }
                    userId = OperationSQL.queryUser(user, psw, role);
                    if (userId > 0) {
                        //�رյ�ǰ����
                        dispose();
                        new MainJF();
                    } else {
//                        JOptionPane.showMessageDialog(null, "�û���Ϣ��ȡʧ��,����ϵ����Ա");
                        clear();
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });
        });

        //�˳�ϵͳ
        jb2.addActionListener(e -> System.exit(0));

        setSize(500, 350);
        setVisible(true);
        setLocation(600, 300);
        //���õ��رմ���ʱ����֤JVMҲ�˳�
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    /**
     * ����ı���������
     */
    public void clear() {
        textField.setText("");
        passwordTxt.setText("");
    }
}
