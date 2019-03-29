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

    private Connection Connec;
    private static ResultSet result;

    public static void main(String[] args) {
        new TeacherUI();
    }

    /**
     * Create the application.
     */
    public TeacherUI() {
        super("��ʦ������Ϣ");
        //��ѯ���ݿ��ʦ������Ϣչʾ��ҳ��
        selectTeacher();

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

        name.setColumns(10);
        name.setBounds(100, 32, 105, 24);
        getContentPane().add(name);

        JLabel label = new JLabel("�Ա�:");
        label.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(25, 78, 71, 15);
        getContentPane().add(label);

        sex.setColumns(10);
        sex.setBounds(100, 74, 105, 24);
        getContentPane().add(sex);

        JLabel label_1 = new JLabel("����:");
        label_1.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        label_1.setHorizontalAlignment(SwingConstants.CENTER);
        label_1.setBounds(25, 122, 71, 15);
        getContentPane().add(label_1);

        age.setColumns(10);
        age.setBounds(100, 118, 105, 24);
        getContentPane().add(age);

        JLabel label_2 = new JLabel("����༶:");
        label_2.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        label_2.setHorizontalAlignment(SwingConstants.CENTER);
        label_2.setBounds(25, 163, 71, 15);
        getContentPane().add(label_2);

        proxyClass.setColumns(10);
        proxyClass.setBounds(100, 159, 105, 24);
        getContentPane().add(proxyClass);


        JLabel label_3 = new JLabel("��ϵ��ʽ:");
        label_3.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        label_3.setHorizontalAlignment(SwingConstants.CENTER);
        label_3.setBounds(30, 201, 59, 15);
        getContentPane().add(label_3);

        phone.setColumns(10);
        phone.setBounds(100, 197, 105, 24);
        getContentPane().add(phone);

        JLabel label_4 = new JLabel("��ע��Ϣ:");
        label_4.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        label_4.setHorizontalAlignment(SwingConstants.CENTER);
        label_4.setBounds(224, 78, 146, 15);
        getContentPane().add(label_4);

        JButton button = new JButton("�޸�");
        button.setBounds(231, 197, 84, 23);
        getContentPane().add(button);

        JButton btnNewButton = new JButton("����");
        btnNewButton.setBounds(332, 197, 77, 23);
        getContentPane().add(btnNewButton);

        motto.setRows(8);
        motto.setBounds(227, 105, 161, 73);
        getContentPane().add(motto);

        JLabel label_5 = new JLabel("���");
        label_5.setHorizontalAlignment(SwingConstants.LEFT);
        label_5.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        label_5.setBounds(224, 36, 96, 15);
        getContentPane().add(label_5);

        staffNo.setEditable(false);
        staffNo.setColumns(10);
        staffNo.setBounds(286, 32, 102, 24);
        getContentPane().add(staffNo);

        //�������ذ�ť
        btnNewButton.addActionListener(e -> {
            // TODO Auto-generated method stub
            setVisible(false);
        });

        //�����޸İ�ť
        button.addActionListener(e -> {
            // TODO Auto-generated method stub
            if (updateUserInfo()) {
                JOptionPane.showMessageDialog(null, "��Ϣ�޸ĳɹ���");
            } else {
                JOptionPane.showMessageDialog(null, "�޸�ʧ��,�����ԣ�");
            }
        });

        //�ر�����
        OperationSQL.closeConnet();
    }

    /**
     * ��ѯ������Ϣ
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
     * �޸ĸ�����Ϣ
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
