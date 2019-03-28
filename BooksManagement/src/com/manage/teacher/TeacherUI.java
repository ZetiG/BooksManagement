package com.manage.teacher;

import com.manage.Windos.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TeacherUI extends JFrame {

    private JTextArea textArea;
    private JTextArea textArea_1;
    private JTextArea textArea_2;
    private JTextArea textArea_3;
    private JTextArea textArea_4;
    private JTextArea textArea_5;
    private JTextArea textArea_6;

    public static void main(String[] args) {
        new TeacherUI();
    }

    /**
     * Create the application.
     */
    public TeacherUI() {
        super("��ʦ������Ϣ");
        setVisible(true);
        setBounds(100, 100, 450, 300);
        setLocation(330, 400);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel lblIsbn = new JLabel("����:");
        lblIsbn.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        lblIsbn.setHorizontalAlignment(SwingConstants.CENTER);
        lblIsbn.setBounds(10, 36, 96, 15);
        getContentPane().add(lblIsbn);

        textArea_3 = new JTextArea();
        textArea_3.setColumns(10);
        textArea_3.setBounds(100, 32, 105, 24);
        getContentPane().add(textArea_3);

        JLabel label = new JLabel("�Ա�:");
        label.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(25, 78, 71, 15);
        getContentPane().add(label);

        textArea = new JTextArea();
        textArea.setColumns(10);
        textArea.setBounds(100, 74, 105, 24);
        getContentPane().add(textArea);

        JLabel label_1 = new JLabel("����:");
        label_1.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        label_1.setHorizontalAlignment(SwingConstants.CENTER);
        label_1.setBounds(25, 122, 71, 15);
        getContentPane().add(label_1);

        textArea_1 = new JTextArea();
        textArea_1.setColumns(10);
        textArea_1.setBounds(100, 118, 105, 24);
        getContentPane().add(textArea_1);

        JLabel label_2 = new JLabel("����༶:");
        label_2.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        label_2.setHorizontalAlignment(SwingConstants.CENTER);
        label_2.setBounds(25, 163, 71, 15);
        getContentPane().add(label_2);

        textArea_2 = new JTextArea();
        textArea_2.setColumns(10);
        textArea_2.setBounds(100, 159, 105, 24);
        getContentPane().add(textArea_2);


        JLabel label_3 = new JLabel("��ϵ��ʽ:");
        label_3.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        label_3.setHorizontalAlignment(SwingConstants.CENTER);
        label_3.setBounds(30, 201, 59, 15);
        getContentPane().add(label_3);

        textArea_4 = new JTextArea();
        textArea_4.setColumns(10);
        textArea_4.setBounds(100, 197, 105, 24);
        getContentPane().add(textArea_4);

        JLabel label_4 = new JLabel("\u5185\u5BB9\u7B80\u4ECB\uFF0850\u5B57\u5185\uFF09\uFF1A");
        label_4.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        label_4.setHorizontalAlignment(SwingConstants.CENTER);
        label_4.setBounds(224, 78, 146, 15);
        getContentPane().add(label_4);

        JButton button = new JButton("\u63D0\u4EA4");
        button.setBounds(231, 197, 84, 23);
        getContentPane().add(button);

        JButton btnNewButton = new JButton("\u8FD4\u56DE");
        btnNewButton.setBounds(332, 197, 77, 23);
        getContentPane().add(btnNewButton);

        textArea_5 = new JTextArea();
        textArea_5.setRows(8);
        textArea_5.setBounds(227, 105, 161, 73);
        getContentPane().add(textArea_5);

        JLabel label_5 = new JLabel("   \u7C7B\u522B\uFF1A");
        label_5.setHorizontalAlignment(SwingConstants.LEFT);
        label_5.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        label_5.setBounds(224, 36, 96, 15);
        getContentPane().add(label_5);

        textArea_6 = new JTextArea();
        textArea_6.setColumns(10);
        textArea_6.setBounds(286, 32, 102, 24);
        getContentPane().add(textArea_6);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                setVisible(false);
            }
        });

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if (b_insert()) {
                    JOptionPane.showMessageDialog(null, "��ӳɹ�������");
                } else {
                    JOptionPane.showMessageDialog(null, "�������������߻���࣡��");
                }
            }
        });

    }

    public Boolean b_insert() {
        boolean result = false;
        Connection conn = null;
        try {
            conn = Login.getCon();  //�������ݿ�����
            String sqlInset = "insert into Book(ISBN,Bname,Bprice,Bcomment,Bpublish,Bauthor,Bsort)"
                    + "values('" + textArea_3.getText() + "','" + textArea.getText() + "','" + textArea_2.getText() + "','" + textArea_5.getText() + "','" + textArea_4.getText() + "','" + textArea_1.getText() + "','" + textArea_6.getText() + "')";
            PreparedStatement stmt = conn.prepareStatement(sqlInset);   //���׳��쳣

            int i = stmt.executeUpdate();
            if (i == 1) {
                result = true;
            }
        } catch (SQLException e) {
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
