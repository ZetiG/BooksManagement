package com.manage.teacher;

import com.manage.Mapper.OperationSQL;
import com.manage.Windos.Login;
import com.manage.entity.Student;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyStudentUI extends JFrame {

    private JTextField textField;

    private Connection conn;
    private ResultSet r;
    private String classNo;

    //�������student����
    List<Student> list = new ArrayList<>();


    public static void main(String[] args) {
        new MyStudentUI();
    }

    /**
     * Create the application.
     */
    public MyStudentUI() {
        selectClass();
        selectAllStu();

        setTitle("�ҵ�ѧ��");
        setVisible(true);
        setBounds(100, 100, 800, 600);
        setLocation(600, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel.setBounds(25, 30, 385, 73);
        getContentPane().add(panel);

        JLabel label = new JLabel("ѧ��:");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        label.setBounds(10, 27, 95, 15);
        panel.add(label);

        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(101, 24, 125, 21);
        panel.add(textField);

        JButton button = new JButton("��ѯ����");
        button.setIcon(new ImageIcon(com.manage.Windos.Book_require.class.getResource("/images/search.png")));
        button.setBounds(255, 10, 107, 23);
        panel.add(button);

        JButton button_3 = new JButton("��ѯȫ��");
        button_3.setIcon(new ImageIcon(com.manage.Windos.Book_require.class.getResource("/images/search.png")));
        button_3.setBounds(255, 40, 107, 23);
        panel.add(button_3);


        JLabel label_2 = new JLabel("����������ѧ�Ų�ѯ");
        label_2.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        label_2.setBounds(25, 10, 143, 15);
        getContentPane().add(label_2);

        String[] colums = {"ѧ��", "����", "רҵ", "�Ա�", "����", "��ϵ��ʽ"};
        Object[][] obj = new Object[list.size()][colums.length];
        int i = 0;
        for (Student student : list) {
            for (int j = 0; j < 6; j++) {
                switch (j) {
                    case 0:
                        obj[i][j] = student.getStuNo();
                        break;
                    case 1:
                        obj[i][j] = student.getStuName();
                        break;
                    case 2:
                        obj[i][j] = student.getStuMajor();
                        break;
                    case 3:
                        obj[i][j] = student.getStuSex();
                        break;
                    case 4:
                        obj[i][j] = student.getStuAge();
                        break;
                    case 5:
                        obj[i][j] = student.getStuPhone();
                        break;
                }
            }
            i++;
        }
        //����һ��tableʵ��
        JTable table = new JTable(obj, colums);
        table.setBounds(25, 130, 760, 400);
        table.setBorder(new LineBorder(new Color(0, 0, 0)));
        //����JTable����Ĭ�ϵĿ�Ⱥ͸߶�
        TableColumn column;
        int row = table.getColumnCount();
        for (int k = 0; k < row; k++) {
            column = table.getColumnModel().getColumn(k);
            /*��ÿһ�е�Ĭ�Ͽ������Ϊ100*/
            column.setPreferredWidth(100);
        }
        /*��JScrollPaneװ��JTable������������Χ���оͿ���ͨ�����������鿴*/
        JScrollPane scroll = new JScrollPane(table);
        scroll.setSize(300, 200);
        scroll.setBounds(25, 130, 760, 400);
        getContentPane().add(scroll);


/*
        button.addActionListener(e -> {
            if (null != selectClass(sql)) {
                if (selectAllStu(sql2)) {
                    JOptionPane.showMessageDialog(null, "��ѯ�ɹ�");
                }
            }
            JOptionPane.showMessageDialog(null, "�鲻����������İ༶,����ϵ����Ա��");
        });
*/
    }


    public Boolean selectAllStu() {
        try {
            conn = OperationSQL.getCon();  //�������ݿ�����
            String sql2 = "select stu.stu_no,stu.name,mj.major,stu.sex,stu.age,stu.phone " +
                    "from t_student stu join t_major mj on stu.major=mj.id where stu.is_deleted=0 and stu.major=?";
            PreparedStatement stmt = conn.prepareStatement(sql2);   //���׳��쳣
            stmt.setString(1, classNo);
            r = stmt.executeQuery();
            while (r.next()) {
                Student stu = new Student();
                stu.setStuNo(r.getString(1));
                stu.setStuName(r.getString(2));
                stu.setStuMajor(r.getString(3));
                stu.setStuSex(r.getString(4));
                stu.setStuAge(r.getString(5));
                stu.setStuPhone(r.getString(6));
                list.add(stu);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            OperationSQL.closeConnet();
        }
        return false;
    }

    private String selectClass() {
        try {
            conn = OperationSQL.getCon();  //�������ݿ�����
            String sql = "select class from t_teacher where is_deleted=0 and user_id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);   //���׳��쳣
            stmt.setString(1, String.valueOf(Login.userId));
            r = stmt.executeQuery();
            while (r.next()) {
                classNo = r.getString(1);
                return classNo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            OperationSQL.closeConnet();
        }
        return null;
    }

}
