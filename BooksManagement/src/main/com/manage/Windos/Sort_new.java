package main.com.manage.Windos;

import main.com.manage.Mapper.OperationSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Sort_new extends JFrame {
    private JTextArea textArea;
    private JTextArea textArea_1;
    private JTextArea textArea_2;

    public static void main(String[] args) {
        new Sort_new();
    }
    /**
     * Create the application.
     */
    public Sort_new() {
        setTitle("ͼ���������");
        setVisible(true);
        setBounds(100, 100, 450, 300);
        setLocation(800, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);


        JLabel label = new JLabel("\u6240\u5728\u4E66\u67B6\u53F7\uFF1A");
        label.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(201, 39, 96, 15);
        getContentPane().add(label);

        textArea = new JTextArea();
        textArea.setColumns(10);
        textArea.setBounds(87, 35, 83, 24);
        getContentPane().add(textArea);

        JLabel label_1 = new JLabel("\u7C7B\u522B\u63CF\u8FF0\uFF1A");
        label_1.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        label_1.setHorizontalAlignment(SwingConstants.CENTER);
        label_1.setBounds(10, 88, 96, 15);
        getContentPane().add(label_1);

        textArea_1 = new JTextArea();
        textArea_1.setColumns(10);
        textArea_1.setBounds(309, 35, 83, 24);
        getContentPane().add(textArea_1);


        JLabel label_3 = new JLabel("\u65B0\u7C7B\u522B\uFF1A");
        label_3.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        label_3.setHorizontalAlignment(SwingConstants.CENTER);
        label_3.setBounds(20, 39, 69, 15);
        getContentPane().add(label_3);


        JButton button = new JButton("\u767B\u8BB0");
        button.setIcon(new ImageIcon(IDcard_new.class.getResource("/main/images/modify.png")));
        button.setBounds(167, 191, 84, 23);
        getContentPane().add(button);

        textArea_2 = new JTextArea();
        textArea_2.setText("\u8BF7\u8F93\u5165\u7C7B\u522B\u63CF\u8FF0\uFF0C50\u5B57\u4EE5\u5185\u3002");
        textArea_2.setBounds(33, 113, 359, 53);
        getContentPane().add(textArea_2);


        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if (sort_insert()) {
                    JOptionPane.showMessageDialog(null, "�Ǽǳɹ�������");
                }
            }
        });
    }

    public Boolean sort_insert() {
        boolean result = false;
        Connection conn = null;
        try {
            conn = OperationSQL.getCon();  //�������ݿ�����
            if (!textArea_2.getText().equals("") && !textArea_2.getText().equals("���������������50�����ڡ�")) {
                String sqlInset = "insert into sort(Bsort,place,comment)  "
                        + "values('" + textArea.getText() + "','" + textArea_1.getText() + "','" + textArea_2.getText() + "')";
                System.out.println(sqlInset);
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
	



