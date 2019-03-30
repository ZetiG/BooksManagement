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
        selectAllStu();

        setTitle("我的学生");
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

        JLabel label = new JLabel("学生:");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label.setBounds(10, 27, 95, 15);
        panel.add(label);

        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(101, 24, 125, 21);
        panel.add(textField);

        JButton button = new JButton("查询现有");
        button.setIcon(new ImageIcon(com.manage.Windos.Book_require.class.getResource("/images/search.png")));
        button.setBounds(255, 10, 107, 23);
        panel.add(button);

        JButton button_3 = new JButton("查询全部");
        button_3.setIcon(new ImageIcon(com.manage.Windos.Book_require.class.getResource("/images/search.png")));
        button_3.setBounds(255, 40, 107, 23);
        panel.add(button_3);


        JLabel label_2 = new JLabel("输入姓名或学号查询");
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


/*
        button.addActionListener(e -> {
            if (null != selectClass(sql)) {
                if (selectAllStu(sql2)) {
                    JOptionPane.showMessageDialog(null, "查询成功");
                }
            }
            JOptionPane.showMessageDialog(null, "查不到您所代理的班级,请联系管理员！");
        });
*/
    }


    public Boolean selectAllStu() {
        try {
            conn = OperationSQL.getCon();  //建立数据库连接
            String sql2 = "select stu.stu_no,stu.name,mj.major,stu.sex,stu.age,stu.phone " +
                    "from t_student stu join t_major mj on stu.major=mj.id where stu.is_deleted=0 and stu.major=?";
            PreparedStatement stmt = conn.prepareStatement(sql2);   //会抛出异常
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
            conn = OperationSQL.getCon();  //建立数据库连接
            String sql = "select class from t_teacher where is_deleted=0 and user_id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);   //会抛出异常
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
