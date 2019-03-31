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

    public static int queryUser(String account, String pwd, String role) {
        try {
            //����SQL���
            String sql = "select id,account,pwd,role from t_user where account=? and role=? and is_deleted='0'";
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
                    int id = result.getInt(1);
                    return id;
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
        return 0;
    }

    /**
     * �������ã��ر�����
     */
    public static void closeConnet() {
        //�ر�����
        try {
            result.close();
            preStm.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * ��ѯ����רҵ
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
     * ���ݴ����רҵ���ض�Ӧ��ID
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
     * ���ݴ����ID���ض�Ӧ��רҵ
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
     * ���ݵ�ǰ��¼�Ľ�ʦ��ѯ��Ӧ�İ༶
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
