package Windos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Book_borrow extends JFrame {
    private JTextField textField;
    private ResultSet r;
    private JTextField textField_1;
    private JTextArea textArea;

    public static void main(String[] args) {
        new Book_borrow();
    }
    /**
     * Initialize the contents of the frame.
     */
    public Book_borrow() {
        setTitle("ͼ����");
        setVisible(true);
        setBounds(100, 100, 450, 300);
        setLocation(1270, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(21, 21, 387, 47);
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("\u8BF7\u8F93\u5165\u501F\u4E66\u4E66\u53F7\uFF08ISBN\uFF09\uFF1A");
        lblNewLabel.setBounds(10, 10, 156, 27);
        panel.add(lblNewLabel);
        lblNewLabel.setFont(new Font("΢���ź�", Font.PLAIN, 12));

        textField = new JTextField();
        textField.setBounds(176, 13, 156, 21);
        panel.add(textField);
        textField.setColumns(10);

        JButton button = new JButton("\u67E5\u770B");
        button.setIcon(new ImageIcon(Book_borrow.class.getResource("/images/search.png")));
        button.setBounds(196, 216, 93, 23);
        getContentPane().add(button);

        JButton button_1 = new JButton("\u501F\u51FA");
        button_1.setIcon(new ImageIcon(Book_borrow.class.getResource("/images/about.png")));
        button_1.setBounds(315, 216, 93, 23);
        getContentPane().add(button_1);

        textArea = new JTextArea();
        textArea.setText("\u4E66\u53F7         \u4E66\u540D         \u4F5C\u8005      \u4EF7\u683C");
        textArea.setBounds(21, 159, 387, 47);
        getContentPane().add(textArea);

        JLabel lblNewLabel_1 = new JLabel("\u5B58\u6709\u72B6\u6001\uFF1A");
        lblNewLabel_1.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        lblNewLabel_1.setBounds(21, 137, 76, 23);
        getContentPane().add(lblNewLabel_1);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(21, 80, 387, 47);
        getContentPane().add(panel_1);
        panel_1.setLayout(null);

        JLabel lblid = new JLabel("\u8BF7\u8F93\u5165\u501F\u4E66\u8BC1\u53F7\uFF08ID\uFF09\uFF1A");
        lblid.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        lblid.setBounds(10, 10, 156, 27);
        panel_1.add(lblid);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(176, 13, 156, 21);
        panel_1.add(textField_1);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if (b_borrow1()) {
                    JOptionPane.showMessageDialog(null, "������Խ���");
                } else {
                    JOptionPane.showMessageDialog(null, "�����ѱ��������ѡ��������");
                }
            }
        });

        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if (b_borrow2()) {
                    JOptionPane.showMessageDialog(null, "����ɹ�������");
                } else {
                    JOptionPane.showMessageDialog(null, "û�д�������Ѿ�����������");
                }
            }
        });

    }

    public Boolean b_borrow1() {
        boolean result = false;
        Connection conn = null;
        try {
            conn = Login.getCon();  //�������ݿ�����
            String sqlrequire;
            sqlrequire = "select *" +
                    "from require_book_now " +
                    "where ISBN =" + textField.getText();
            PreparedStatement stmt = conn.prepareStatement(sqlrequire);   //���׳��쳣
            r = stmt.executeQuery();
            if (r.next()) {
                result = true;
                String s1 = r.getString(1);
                String s2 = r.getString(2);
                String s3 = r.getString(3);
                String s4 = r.getString(5);
                textArea.append("\n" + s1 + "  " + s2 + "   " + s3 + "   " + s4);
            } else {
                result = false;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally { //finally���ô��ǲ��ܳ����Ƿ�����쳣����Ҫִ��finally��䣬�����ڴ˴��ر�����
            try {
                conn.close(); //��һ��Connection���Ӻ����һ��Ҫ��������close���������ر����ӣ����ͷ�ϵͳ��Դ�����ݿ���Դ
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public Boolean b_borrow2() {
        boolean result = false;
        LocalDate ld = LocalDate.now();
        Connection conn = null;
        try {
            conn = Login.getCon();
            String sqlInset = "insert into Lend(ID,ISBN,Ld)"
                    + "values('" + textField_1.getText() + "','" + textField.getText() + "','" + ld + "')";
            PreparedStatement stmt = conn.prepareStatement(sqlInset);
            int i = stmt.executeUpdate();
            if (i == 1) result = true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally { //finally���ô��ǲ��ܳ����Ƿ�����쳣����Ҫִ��finally��䣬�����ڴ˴��ر�����
            try {
                conn.close(); //��һ��Connection���Ӻ����һ��Ҫ��������close���������ر����ӣ����ͷ�ϵͳ��Դ�����ݿ���Դ
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
