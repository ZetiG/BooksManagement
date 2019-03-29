package com.manage.Mapper;

import javax.swing.*;
import java.sql.*;

public class OperationSQL {
    private static Connection conn = null;
    static PreparedStatement preStm = null;
    static ResultSet result = null;

    public static Connection getCon() {
        try {
            Class.forName("com.mysql.jdbc.Driver"); //加载数据库连接驱动
            String url = "jdbc:mysql://47.98.162.196:3306/student_manage?characterEncoding=utf8&useSSL=false"; //连接数据库地址
            String user = "root"; //MySQL配置时的用户名
            String password = "mysql123"; //MySQL配置时的密码
            conn = DriverManager.getConnection(url, user, password);  //获取连接
        } catch (Exception e) {
            System.out.println("连接数据库失败");
            e.printStackTrace();
        }
        return conn;
    }

    public static int queryUser(String account, String pwd, String role) {
        try {
            //创建SQL语句
            String sql = "select id,account,pwd,role from t_user where account=? and role=? and flag='0'";
            preStm = conn.prepareStatement(sql);
            // 给?赋值(可防止SQL注入问题)，不要直接使用拼接的方式
            preStm.setString(1, account);
            preStm.setString(2, role);
            //查询结果集
            result = preStm.executeQuery();

            // 循环取出
            if (result.next()) {
                //判断密码是否正确
                String passWord = result.getString(3);
                if (passWord.equals(pwd)) {
                    int id = result.getInt(1);
                    return id;
                }
                JOptionPane.showMessageDialog(null, "密码错误，请重试！", "提示消息", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "该用户不存在！", "提示消息", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } finally {
            //关闭连接
            closeConnet();
        }
        return 0;
    }

    /**
     * 公共调用，关闭连接
     */
    public static void closeConnet() {
        //关闭连接
        try {
            result.close();
            preStm.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 提取公共方法
     *
     * @param t1
     * @param t2
     * @param t3
     * @param t4
     * @param t5
     * @param t6
     * @param t7
     * @param t8
     * @throws SQLException
     */
    public static void setParams(JTextField t1, JTextField t2, JTextField t3, JTextField t4,
                                 JTextField t5, JTextField t6, JTextArea t7, JTextField t8) throws SQLException {
        t1.setText(result.getString(1));
        t2.setText(result.getString(2));
        t3.setText(result.getString(3));
        t4.setText(result.getString(4));
        t5.setText(result.getString(5));
        t6.setText(result.getString(6));
        t7.setText(result.getString(7));
        t8.setText(result.getString(8));
    }
}
