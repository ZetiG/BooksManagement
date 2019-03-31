package com.manage.Mapper;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
            String sql = "select id,account,pwd,role from t_user where account=? and role=? and is_deleted='0'";
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
     * 查询所有专业
     *
     * @return
     */
    public static List getAllClass() {
        try {
            conn = getCon();
            String sql = "select id,major from t_major where is_deleted=0";
            PreparedStatement pstm = conn.prepareStatement(sql);
            result = pstm.executeQuery();
            List list = new ArrayList();
            while (result.next()) {
                list.add(result.getString(2));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            OperationSQL.closeConnet();
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * 根据传入的专业返回对应的ID
     *
     * @param className
     * @return
     */
    public static int getClassId(String className) {
        try {
            conn = getCon();
            String sql = "select id,major from t_major where is_deleted=0 and major=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            if (!className.isEmpty()) {
                pstm.setString(1, className);
                result = pstm.executeQuery();
                if (result.next()) {
                    int classId = Integer.parseInt(result.getString(1));
                    return classId;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            OperationSQL.closeConnet();
        }
        return 0;
    }

    /**
     * 根据传入的ID返回对应的专业
     *
     * @param classID
     * @return
     */
    public static String getClassName(String classID) {
        try {
            conn = getCon();
            String sql = "select id,major from t_major where is_deleted=0 and id=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            if (!classID.isEmpty()) {
                pstm.setString(1, classID);
                result = pstm.executeQuery();
                if (result.next()) {
                    String className = result.getString(2);
                    return className;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            OperationSQL.closeConnet();
        }
        return null;
    }

    /**
     * 根据当前登录的教师查询对应的班级
     *
     * @param userId
     * @return
     */
    public static String getUserClassNo(int userId) {
        try {
            conn = getCon();
            String sql = "select class from t_teacher where is_deleted=0 and user_id=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, userId);
            result = pstm.executeQuery();
            if (result.next()) {
                String classNo = result.getString(1);
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
