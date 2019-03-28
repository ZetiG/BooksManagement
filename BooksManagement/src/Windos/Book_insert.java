package Windos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Book_insert extends JFrame {
    private JTextArea textArea;
    private JTextArea textArea_1;
    private JTextArea textArea_2;
    private JTextArea textArea_3;
    private JTextArea textArea_4;
    private JTextArea textArea_5;
    private JTextArea textArea_6;

    public static void main(String[] args) {
        new Book_insert();
    }
    /**
     * Create the application.
     */
    public Book_insert() {
        super("图书添加");
        setVisible(true);
        setBounds(100, 100, 450, 300);
        setLocation(330, 400);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel label = new JLabel("\u56FE\u4E66\u540D\u5B57\uFF1A");
        label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(35, 78, 71, 15);
        getContentPane().add(label);

        textArea = new JTextArea();
        textArea.setColumns(10);
        textArea.setBounds(114, 74, 83, 24);
        getContentPane().add(textArea);

        JLabel label_1 = new JLabel("\u56FE\u4E66\u4F5C\u8005\uFF1A");
        label_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_1.setHorizontalAlignment(SwingConstants.CENTER);
        label_1.setBounds(35, 122, 71, 15);
        getContentPane().add(label_1);

        textArea_1 = new JTextArea();
        textArea_1.setColumns(10);
        textArea_1.setBounds(114, 118, 83, 24);
        getContentPane().add(textArea_1);

        JLabel label_2 = new JLabel("\u56FE\u4E66\u4EF7\u683C\uFF1A");
        label_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_2.setHorizontalAlignment(SwingConstants.CENTER);
        label_2.setBounds(35, 163, 71, 15);
        getContentPane().add(label_2);

        textArea_2 = new JTextArea();
        textArea_2.setColumns(10);
        textArea_2.setBounds(114, 159, 83, 24);
        getContentPane().add(textArea_2);

        JLabel lblIsbn = new JLabel("\u4E66\u53F7\uFF08ISBN\uFF09\uFF1A");
        lblIsbn.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        lblIsbn.setHorizontalAlignment(SwingConstants.CENTER);
        lblIsbn.setBounds(10, 36, 96, 15);
        getContentPane().add(lblIsbn);

        textArea_3 = new JTextArea();
        textArea_3.setColumns(10);
        textArea_3.setBounds(114, 32, 83, 24);
        getContentPane().add(textArea_3);

        JLabel label_3 = new JLabel("\u51FA\u7248\u793E\uFF1A");
        label_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_3.setHorizontalAlignment(SwingConstants.CENTER);
        label_3.setBounds(47, 201, 59, 15);
        getContentPane().add(label_3);

        textArea_4 = new JTextArea();
        textArea_4.setColumns(10);
        textArea_4.setBounds(114, 197, 83, 24);
        getContentPane().add(textArea_4);

        JLabel label_4 = new JLabel("\u5185\u5BB9\u7B80\u4ECB\uFF0850\u5B57\u5185\uFF09\uFF1A");
        label_4.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_4.setHorizontalAlignment(SwingConstants.CENTER);
        label_4.setBounds(224, 78, 146, 15);
        getContentPane().add(label_4);

        JButton button = new JButton("\u63D0\u4EA4");
        button.setBounds(231, 197, 84, 23);
        getContentPane().add(button);

        JButton btnNewButton = new JButton("\u8FD4\u56DE");
        btnNewButton.setBounds(332, 197, 77, 23);
        getContentPane().add(btnNewButton);

        textArea_5 = new JTextArea();
        textArea_5.setRows(8);
        textArea_5.setBounds(227, 105, 161, 73);
        getContentPane().add(textArea_5);

        JLabel label_5 = new JLabel("   \u7C7B\u522B\uFF1A");
        label_5.setHorizontalAlignment(SwingConstants.LEFT);
        label_5.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_5.setBounds(224, 36, 96, 15);
        getContentPane().add(label_5);

        textArea_6 = new JTextArea();
        textArea_6.setColumns(10);
        textArea_6.setBounds(286, 32, 102, 24);
        getContentPane().add(textArea_6);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                setVisible(false);
                ;
            }
        });

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if (b_insert()) {
                    JOptionPane.showMessageDialog(null, "添加成功啦！！");
                } else {
                    JOptionPane.showMessageDialog(null, "请输入已有作者或分类！！");
                }
            }
        });

    }

    public Boolean b_insert() {
        boolean result = false;
        Connection conn = null;
        try {
            conn = Login.getCon();  //建立数据库连接
            String sqlInset = "insert into Book(ISBN,Bname,Bprice,Bcomment,Bpublish,Bauthor,Bsort)"
                    + "values('" + textArea_3.getText() + "','" + textArea.getText() + "','" + textArea_2.getText() + "','" + textArea_5.getText() + "','" + textArea_4.getText() + "','" + textArea_1.getText() + "','" + textArea_6.getText() + "')";
            PreparedStatement stmt = conn.prepareStatement(sqlInset);   //会抛出异常

            int i = stmt.executeUpdate();
            if (i == 1) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();


        } finally { //finally的用处是不管程序是否出现异常，都要执行finally语句，所以在此处关闭连接
            try {
                conn.close(); //打开一个Connection连接后，最后一定要调用它的close（）方法关闭连接，以释放系统资源及数据库资源
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
