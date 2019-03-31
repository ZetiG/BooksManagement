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
        setTitle("ѧ���Ǽ�");
        setVisible(true);
        setBounds(100, 100, 600, 450);
        setLocation(800, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel label_3 = new JLabel("ѧ��������");
        label_3.setHorizontalAlignment(SwingConstants.CENTER);
        label_3.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        label_3.setBounds(10, 39, 96, 15);
        getContentPane().add(label_3);

        textArea = new JTextArea();
        textArea.setColumns(10);
        textArea.setBounds(100, 35, 100, 24);
        getContentPane().add(textArea);



        JLabel label_4 = new JLabel("ѧ��ѧ�ţ�");
        label_4.setHorizontalAlignment(SwingConstants.CENTER);
        label_4.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        label_4.setBounds(210, 39, 96, 15);
        getContentPane().add(label_4);

        textArea_4 = new JTextArea();
        textArea_4.setColumns(10);
        textArea_4.setBounds(301, 35, 100, 24);
        getContentPane().add(textArea_4);


        JLabel label = new JLabel("ѧ��������");
        label.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(210, 88, 96, 15);
        getContentPane().add(label);

        textArea_1 = new JTextArea();
        textArea_1.setColumns(10);
        textArea_1.setBounds(301, 84, 100, 24);
        getContentPane().add(textArea_1);

        JLabel label_2 = new JLabel("ѧ���Ա�");
        label_2.setHorizontalAlignment(SwingConstants.CENTER);
        label_2.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        label_2.setBounds(10, 88, 96, 15);
        getContentPane().add(label_2);

        check1 = new JRadioButton("��", true);
        check2 = new JRadioButton("Ů");
        check1.setBounds(100, 84, 60, 25);
        check2.setBounds(160, 84, 60, 25);
        bg = new ButtonGroup();
        bg.add(check1);
        bg.add(check2);
        getContentPane().add(check1);
        getContentPane().add(check2);


        JLabel label_1 = new JLabel("ѧ����飺");
        label_1.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        label_1.setHorizontalAlignment(SwingConstants.CENTER);
        label_1.setBounds(10, 129, 96, 15);
        getContentPane().add(label_1);

        textArea_2 = new JTextArea();
        textArea_2.setText("����ѧ����200�����ڡ�");
        textArea_2.setBounds(33, 155, 376, 53);
        getContentPane().add(textArea_2);


        JButton button = new JButton("�Ǽ�");
        button.setIcon(new ImageIcon(IDcard_new.class.getResource("/images/modify.png")));
        button.setBounds(171, 218, 84, 23);
        getContentPane().add(button);


        button.addActionListener(e -> {
            // TODO Auto-generated method stub
            if (Author_new()) {
                JOptionPane.showMessageDialog(null, "�Ǽǳɹ�������");
            }
        });
    }

    public boolean Author_new() {
        boolean result = false;
        Connection conn = null;
        try {
            conn = OperationSQL.getCon();  //�������ݿ�����
            if (!textArea_2.getText().equals("") && !textArea_2.getText().equals("�������ߣ�50�����ڡ�")) {
                String sqlInset = "insert into Author(Bauthor,sex,about,age)  "
                        + "values('" + textArea.getText() + "','" + textArea_3.getText() + "','" + textArea_2.getText() + "','" + textArea_1.getText() + "')";
                PreparedStatement stmt = conn.prepareStatement(sqlInset); //���׳��쳣
                int i = stmt.executeUpdate();
                if (i == 1) {
                    result = true;
                }
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


}
