package com.manage.Mapper;

import javax.swing.*;
import java.sql.*;

public class OperationSQL {
    private static Connection conn = null;
    static PreparedStatement preStm = null;
    static ResultSet result = null;

    public static Connection getCon() {
        try {
            Class.forName("com.mysql.jdbc.Driver"); //�������ݿ���������
            String url = "jdbc:mysql://47.98.162.196:3306/student_manage?characterEncoding=utf8&useSSL=false"; //�������ݿ��ַ
            String user = "root"; //MySQL����ʱ���û���
            String password = "mysql123"; //MySQL����ʱ������
            conn = DriverManager.getConnection(url, user, password);  //��ȡ����
        } catch (Exception e) {
            System.out.println("�������ݿ�ʧ��");
            e.printStackTrace();
        }
        return conn;
    }

    public static boolean queryUser(String account, String pwd, String role) {
        try {
            //����SQL���
            String sql = "select id,account,pwd,role from t_user where account=? and role=? and flag='0'";
            preStm = conn.prepareStatement(sql);
            // ��?��ֵ(�ɷ�ֹSQLע������)����Ҫֱ��ʹ��ƴ�ӵķ�ʽ
            preStm.setString(1, account);
            preStm.setString(2, role);
            //��ѯ�����
            result = preStm.executeQuery();

            // ѭ��ȡ��
            if (result.next()) {
                //�ж������Ƿ���ȷ
                String passWord = result.getString(3);
                if (passWord.equals(pwd)) {
                    return true;
                }
                JOptionPane.showMessageDialog(null, "������������ԣ�", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "���û������ڣ�", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } finally {
            //�ر�����
            closeConnet();
        }
        return false;
    }

    /**
     * �������ã��ر�����
     */
    private static void closeConnet() {
        //�ر�����
        try {
            result.close();
            preStm.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
