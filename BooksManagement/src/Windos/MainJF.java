package Windos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

public class MainJF extends JFrame{

	/**
	 * Create the application.
	 */
	public MainJF() {
		super("广东财经大学图书管理系统");
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("\u6B22\u8FCE\u5149\u4E34\u5E7F\u4E1C\u8D22\u7ECF\u5927\u5B66\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF\uFF01\uFF01\r\n");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("新宋体", Font.ITALIC, 16));
		getContentPane().add(lblNewLabel_3, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.WEST);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel_1 = new JLabel("\u56FE\u4E66\u7EF4\u62A4");
		lblNewLabel_1.setFont(new Font("楷体", Font.BOLD, 15));
		lblNewLabel_1.setIcon(new ImageIcon(MainJF.class.getResource("/images/bookmaintain.png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JButton btnNewButton = new JButton("\u56FE\u4E66\u5F55\u5165");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 2;
		panel.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u56FE\u4E66\u4FEE\u6539");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 3;
		panel.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\u56FE\u4E66\u5220\u9664");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.gridx = 0;
		gbc_btnNewButton_2.gridy = 4;
		panel.add(btnNewButton_2, gbc_btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(MainJF.class.getResource("/images/main.PNG")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblNewLabel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.EAST);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u56FE\u4E66\u7BA1\u7406");
		lblNewLabel_2.setFont(new Font("楷体", Font.BOLD, 15));
		lblNewLabel_2.setIcon(new ImageIcon(MainJF.class.getResource("/images/bookManager.png")));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 0;
		panel_1.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JButton btnNewButton_3 = new JButton("\u56FE\u4E66\u501F\u51FA");
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_3.gridx = 0;
		gbc_btnNewButton_3.gridy = 2;
		panel_1.add(btnNewButton_3, gbc_btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("\u56FE\u4E66\u5F52\u8FD8");
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_4.gridx = 0;
		gbc_btnNewButton_4.gridy = 3;
		panel_1.add(btnNewButton_4, gbc_btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("\u56FE\u4E66\u67E5\u8BE2");
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.gridx = 0;
		gbc_btnNewButton_5.gridy = 4;
		panel_1.add(btnNewButton_5, gbc_btnNewButton_5);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.SOUTH);
		panel_2.setPreferredSize(new Dimension(450,70));
		panel_2.setLayout(null);
		
		JLabel label = new JLabel("\u7CFB\u7EDF\u7EF4\u62A4");
		label.setIcon(new ImageIcon(MainJF.class.getResource("/images/bookmaintain.png")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("楷体", Font.BOLD, 15));
		label.setBounds(166, 0, 84, 18);
		panel_2.add(label);
		
		JButton btnNewButton_6 = new JButton("\u65B0\u501F\u8BB0\u5361\u767B\u8BB0");
		btnNewButton_6.setBounds(10, 24, 115, 23);
		panel_2.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("\u56FE\u4E66\u7C7B\u522B\u65B0\u589E");
		btnNewButton_7.setBounds(155, 24, 122, 23);
		panel_2.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("\u56FE\u4E66\u4F5C\u8005\u65B0\u589E");
		btnNewButton_8.setBounds(302, 24, 122, 23);
		panel_2.add(btnNewButton_8);
		
		
		
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			Book_insert bjf1=new Book_insert();
				}
	});	
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			Book_change bjf2=new Book_change();
				}
	});
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			Book_delete bjf3=new Book_delete();
				}
	});
		btnNewButton_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			Book_borrow bjf4=new Book_borrow();
				}
	});
		btnNewButton_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			Book_return bjf5=new Book_return();
				}
	});
		btnNewButton_5.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			Book_require bjf6=new Book_require();
			}
	});
		btnNewButton_6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IDcard_new bjf7=new IDcard_new();
				}
	});
		btnNewButton_7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Sort_new bjf8=new Sort_new();
				}
	});
		btnNewButton_8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Author_new bjf9=new Author_new();
				}
	});
		
		setVisible(true);
		setBounds(100, 100, 450, 300);
		setLocation(800,400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	}


