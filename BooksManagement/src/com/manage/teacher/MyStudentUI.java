package com.manage.teacher;

import com.manage.Mapper.OperationSQL;
import com.manage.Windos.Login;
import com.manage.entity.Student;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyStudentUI extends JFrame {

    private JTextField stuName = new JTextField();
    private JTextField stuNo = new JTextField();
    DefaultTableModel tableModel;

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
        selectAllStu(null);

        setTitle("�ҵ�ѧ��");
        setVisible(true);
        setBounds(100, 100, 800, 600);
        setLocation(600, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel.setBounds(25, 30, 760, 73);
        getContentPane().add(panel);

        JLabel label = new JLabel("����/ѧ��:");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        label.setBounds(10, 27, 95, 15);
        panel.add(label);

        stuName.setColumns(10);
        stuName.setBounds(101, 24, 125, 21);
        panel.add(stuName);

        JButton button = new JButton("��ѯѧ��");
        button.setIcon(new ImageIcon(com.manage.Windos.Book_require.class.getResource("/images/search.png")));
        button.setBounds(255, 21, 107, 26);
        panel.add(button);

        JLabel label2 = new JLabel("ѧ��:");
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        label2.setBounds(360, 27, 95, 15);
        panel.add(label2);

        stuNo.setColumns(10);
        stuNo.setBounds(450, 24, 125, 21);
        panel.add(stuNo);

        JButton button_3 = new JButton("ɾ��ѧ��");
        button_3.setIcon(new ImageIcon(com.manage.Windos.Book_require.class.getResource("/images/delete.png")));
        button_3.setBounds(600, 21, 107, 26);
        panel.add(button_3);


        JLabel label_2 = new JLabel("����������ѧ�Ų�ѯ/ɾ��");
        label_2.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        label_2.setBounds(25, 10, 143, 15);
        getContentPane().add(label_2);

        String[] colums = {"ѧ��", "����", "רҵ", "�Ա�", "����", "��ϵ��ʽ"};
        Object[][] objects = setTable(list, colums);

        tableModel = new DefaultTableModel();
        tableModel.setDataVector(objects, colums);
        //����һ��tableʵ��
        JTable table = new JTable(objects, colums);
        table.setModel(tableModel);
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

        //������ѯѧ����ť������table
        button.addActionListener(e -> {
            String stu = stuName.getText();
            list.clear();
            List<Student> studentList = selectAllStu(stu);
            if (studentList.size() > 0) {
                tableModel = new DefaultTableModel();
                Object[][] obj = setTable(studentList, colums);
                tableModel.setDataVector(obj, colums);
                table.setModel(tableModel);

            } else {
                JOptionPane.showMessageDialog(null, "�鲻����ѧ����Ϣ��");
            }
        });

        button_3.addActionListener(e -> {
            String stuNoText = stuNo.getText();
            if (null != stuNoText && !stuNoText.isEmpty()) {
                list.clear();
                if (deleteStu(stuNoText)) {
                    JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
                    List<Student> studentList = selectAllStu(null);
                    tableModel = new DefaultTableModel();
                    Object[][] obj = setTable(studentList, colums);
                    tableModel.setDataVector(obj, colums);
                    table.setModel(tableModel);

                } else {
                    JOptionPane.showMessageDialog(null, "ɾ��ʧ��,û�д��˻���ɾ����");
                }
            } else {
                JOptionPane.showMessageDialog(null, "������Ҫɾ����ѧ�ţ�");
            }
        });

    }


    /**
     * ����������ѧ�Ų�ѯѧ��������ղ�ѯ����
     *
     * @param student
     * @return
     */
    public List<Student> selectAllStu(String student) {
        try {
            conn = OperationSQL.getCon();  //�������ݿ�����
            String sql2 = "select stu.stu_no,stu.name,mj.major,stu.sex,stu.age,stu.phone " +
                    "from t_student stu join t_major mj on stu.major=mj.id where stu.is_deleted=0 and stu.major=?";
            if (null != student && !student.isEmpty()) {
                sql2 = sql2 + " and (stu.name=? or stu_no=?)";
            }
            PreparedStatement stmt = conn.prepareStatement(sql2);   //���׳��쳣
            stmt.setString(1, classNo);
            if (null != student && !student.isEmpty()) {
                stmt.setString(2, student);
                stmt.setString(3, student);
            }
            System.err.println("ִ��SQL->:" + sql2);
            r = stmt.executeQuery();
            r.last();
            if (r.getRow() > 0) {
                r.beforeFirst();
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
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            OperationSQL.closeConnet();
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * ��ѯ�õ�¼�˺Ž�ʦ�����༶
     *
     * @return
     */
    private String selectClass() {
        try {
            conn = OperationSQL.getCon();  //�������ݿ�����
            String sql = "select class from t_teacher where is_deleted=0 and user_id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);   //���׳��쳣
            stmt.setString(1, String.valueOf(Login.userId));
            System.err.println("ִ��SQL->:" + sql);
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

    /**
     * ����ѧ��ɾ��ѧ��
     *
     * @param stuNo
     * @return
     */
    private boolean deleteStu(String stuNo) {
        try {
            conn = OperationSQL.getCon();  //�������ݿ�����
            String sql = "update t_student set is_deleted=1 where stu_no=?";
            PreparedStatement stmt = conn.prepareStatement(sql);   //���׳��쳣
            stmt.setString(1, stuNo);
            System.err.println("ִ��SQL->:" + sql);
            int update = stmt.executeUpdate();
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


    /**
     * ��ȡ�ظ����룬����table��ֵ
     *
     * @param list   ѧ������
     * @param colums �ַ����飬��ͷ
     * @return
     */
    private Object[][] setTable(List<Student> list, String[] colums) {
        Object[][] objects = new Object[list.size()][colums.length];
        int k = 0;
        for (Student student : list) {
            for (int j = 0; j < 6; j++) {
                switch (j) {
                    case 0:
                        objects[k][j] = student.getStuNo();
                        break;
                    case 1:
                        objects[k][j] = student.getStuName();
                        break;
                    case 2:
                        objects[k][j] = student.getStuMajor();
                        break;
                    case 3:
                        objects[k][j] = student.getStuSex();
                        break;
                    case 4:
                        objects[k][j] = student.getStuAge();
                        break;
                    case 5:
                        objects[k][j] = student.getStuPhone();
                        break;
                }
            }
            k++;
        }
        return objects;
    }
}
