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

    public static boolean queryUser(String account, String pwd, String role) {
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
                    return true;
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
        return false;
    }

    /**
     * 公共调用，关闭连接
     */
    private static void closeConnet() {
        //关闭连接
        try {
            result.close();
            preStm.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
