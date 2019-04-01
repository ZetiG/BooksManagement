package com.manage.Windos;

import com.manage.teacher.AddStudentUI;
import com.manage.teacher.MyStudentUI;
import com.manage.teacher.SearchStuUI;
import com.manage.teacher.TeacherUI;

import javax.swing.*;
import java.awt.*;

public class MainJF extends JFrame {
    public static void main(String[] args) {
        new MainJF();
    }

    /**
     * Create the application.
     */
    public MainJF() {
        super("���ϴ�ѧ����ѧԺ-ѧ������ϵͳ");
        getContentPane().setLayout(new BorderLayout(0, 0));

        JLabel lblNewLabel_3 = new JLabel("��ӭ��������ѧԺѧ������ϵͳ����");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3.setFont(new Font("������", Font.ITALIC, 16));
        getContentPane().add(lblNewLabel_3, BorderLayout.NORTH);

        //��߲˵�ͼ��
        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.WEST);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{0, 0};
        gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
        gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);

        //��߲˵�
        JLabel lblNewLabel_1 = new JLabel("��Ϣά��");
        lblNewLabel_1.setFont(new Font("����", Font.BOLD, 15));
        lblNewLabel_1.setIcon(new ImageIcon(MainJF.class.getResource("/images/bookmaintain.png")));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewLabel_1.gridx = 0;
        gbc_lblNewLabel_1.gridy = 0;
        panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

        JButton btnNewButton = new JButton("�ҵ���Ϣ");
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
        gbc_btnNewButton.gridx = 0;
        gbc_btnNewButton.gridy = 2;
        panel.add(btnNewButton, gbc_btnNewButton);

        JButton btnNewButton_1 = new JButton("��ѯѧ��");
        GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
        gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
        gbc_btnNewButton_1.gridx = 0;
        gbc_btnNewButton_1.gridy = 3;
        panel.add(btnNewButton_1, gbc_btnNewButton_1);

        //�����������ͼƬ
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon(MainJF.class.getResource("/images/main.PNG")));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblNewLabel, BorderLayout.CENTER);

        //�ұ߲˵�ͼ��
        JPanel panel_1 = new JPanel();
        getContentPane().add(panel_1, BorderLayout.EAST);
        GridBagLayout gbl_panel_1 = new GridBagLayout();
        gbl_panel_1.columnWidths = new int[]{0, 0};
        gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
        gbl_panel_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
        gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel_1.setLayout(gbl_panel_1);

        //�ұ����˵�
        JLabel lblNewLabel_2 = new JLabel("ѧ������");
        lblNewLabel_2.setFont(new Font("����", Font.BOLD, 15));
        lblNewLabel_2.setIcon(new ImageIcon(MainJF.class.getResource("/images/bookManager.png")));
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewLabel_2.gridx = 0;
        gbc_lblNewLabel_2.gridy = 0;
        panel_1.add(lblNewLabel_2, gbc_lblNewLabel_2);

        JButton btnNewButton_3 = new JButton("ѧ���Ǽ�");
        GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
        gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 0);
        gbc_btnNewButton_3.gridx = 0;
        gbc_btnNewButton_3.gridy = 2;
        panel_1.add(btnNewButton_3, gbc_btnNewButton_3);

        JButton btnNewButton_4 = new JButton("�ҵ�ѧ��");
        GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
        gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 0);
        gbc_btnNewButton_4.gridx = 0;
        gbc_btnNewButton_4.gridy = 3;
        panel_1.add(btnNewButton_4, gbc_btnNewButton_4);

        JPanel panel_2 = new JPanel();
        getContentPane().add(panel_2, BorderLayout.SOUTH);
        panel_2.setPreferredSize(new Dimension(450, 70));
        panel_2.setLayout(null);

        JLabel label = new JLabel("ϵͳά��");
        label.setIcon(new ImageIcon(MainJF.class.getResource("/images/bookmaintain.png")));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("����", Font.BOLD, 15));
        label.setBounds(166, 0, 84, 18);
        panel_2.add(label);

        JButton btnNewButton_6 = new JButton("������");
        btnNewButton_6.setBounds(10, 24, 115, 23);
        panel_2.add(btnNewButton_6);

        JButton btnNewButton_7 = new JButton("������");
        btnNewButton_7.setBounds(155, 24, 122, 23);
        panel_2.add(btnNewButton_7);

        JButton btnNewButton_8 = new JButton("������");
        btnNewButton_8.setBounds(302, 24, 122, 23);
        panel_2.add(btnNewButton_8);

        //Lambda���ʽ,�����ת��Ӧҳ��
        btnNewButton.addActionListener(e -> new TeacherUI());
        btnNewButton_1.addActionListener(e -> new SearchStuUI());
        btnNewButton_3.addActionListener(e -> new AddStudentUI());
        btnNewButton_4.addActionListener(e -> new MyStudentUI());
/*        btnNewButton_6.addActionListener(e -> new IDcard_new());
        btnNewButton_7.addActionListener(e -> new Sort_new());
        btnNewButton_8.addActionListener(e -> new Author_new());*/

        setVisible(true);
        setBounds(100, 100, 450, 300);
        setLocation(600, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}


