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
import java.util.Collections;
import java.util.List;

public class MyStudentUI extends JFrame {

    private JTextField stuName = new JTextField();
    private JTextField stuNo = new JTextField();

    private Connection conn;
    private ResultSet r;
    private String classNo;

    //用来存放student对象
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

        setTitle("我的学生");
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

        JLabel label = new JLabel("姓名/学号:");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label.setBounds(10, 27, 95, 15);
        panel.add(label);


        stuName.setColumns(10);
        stuName.setBounds(101, 24, 125, 21);
        panel.add(stuName);

        JButton button = new JButton("查询学生");
        button.setIcon(new ImageIcon(com.manage.Windos.Book_require.class.getResource("/images/search.png")));
        button.setBounds(255, 21, 107, 26);
        panel.add(button);

        JLabel label2 = new JLabel("学号:");
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label2.setBounds(360, 27, 95, 15);
        panel.add(label2);

        stuNo.setColumns(10);
        stuNo.setBounds(450, 24, 125, 21);
        panel.add(stuNo);

        JButton button_3 = new JButton("删除学生");
        button_3.setIcon(new ImageIcon(com.manage.Windos.Book_require.class.getResource("/images/delete.png")));
        button_3.setBounds(600, 21, 107, 26);
        panel.add(button_3);


        JLabel label_2 = new JLabel("输入姓名或学号查询/删除");
        label_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_2.setBounds(25, 10, 143, 15);
        getContentPane().add(label_2);

        String[] colums = {"学号", "姓名", "专业", "性别", "年龄", "联系方式"};
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
        //创建一个table实例
        JTable table = new JTable(obj, colums);
        table.setBounds(25, 130, 760, 400);
        table.setBorder(new LineBorder(new Color(0, 0, 0)));
        //设置JTable的列默认的宽度和高度
        TableColumn column;
        int row = table.getColumnCount();
        for (int k = 0; k < row; k++) {
            column = table.getColumnModel().getColumn(k);
            /*将每一列的默认宽度设置为100*/
            column.setPreferredWidth(100);
        }
        /*用JScrollPane装载JTable，这样超出范围的列就可以通过滚动条来查看*/
        JScrollPane scroll = new JScrollPane(table);
        scroll.setSize(300, 200);
        scroll.setBounds(25, 130, 760, 400);
        getContentPane().add(scroll);


        button.addActionListener(e -> {
            String stu = stuName.getText();
            if (null != stu && !stu.isEmpty()) {
                list.clear();
                List<Student> studentList = selectAllStu(stu);
                if (studentList.size() > 0) {
                    // TODO: 2019-03-30
                    //final DefaultTableModel dtm = new DefaultTableModel();

                } else {
                    JOptionPane.showMessageDialog(null, "查不到该学生信息！");
                }
            }
        });

        button_3.addActionListener(e -> {
            if (null != stuName.getText() && !stuName.getText().isEmpty()) {
                System.err.println(stuNo.getText());
            }
        });

    }


    public List<Student> selectAllStu(String student) {
        try {
            conn = OperationSQL.getCon();  //建立数据库连接
            String sql2 = "select stu.stu_no,stu.name,mj.major,stu.sex,stu.age,stu.phone " +
                    "from t_student stu join t_major mj on stu.major=mj.id where stu.is_deleted=0 and stu.major=?";
            if (null != student) {
                sql2 = sql2 + " and (stu.name=? or stu_no=?)";
            }
            PreparedStatement stmt = conn.prepareStatement(sql2);   //会抛出异常
            stmt.setString(1, classNo);
            if (null != student) {
                stmt.setString(2, student);
                stmt.setString(3, student);
            }
            System.err.println("执行SQL->:" + sql2);
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

    private String selectClass() {
        try {
            conn = OperationSQL.getCon();  //建立数据库连接
            String sql = "select class from t_teacher where is_deleted=0 and user_id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);   //会抛出异常
            stmt.setString(1, String.valueOf(Login.userId));
            System.err.println("执行SQL->:" + sql);
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
