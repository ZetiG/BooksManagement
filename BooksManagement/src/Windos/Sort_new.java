package Windos;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class Sort_new extends JFrame{
	private JTextArea textArea ;
	private JTextArea textArea_1 ;
	private JTextArea textArea_2 ;
	/**
	 * Create the application.
	 */
	public Sort_new() {
		setTitle("图书类别新增");
		setVisible(true);
		setBounds(100, 100, 450, 300);
		setLocation(800,720);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		

		JLabel label = new JLabel("\u6240\u5728\u4E66\u67B6\u53F7\uFF1A");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(201, 39, 96, 15);
		getContentPane().add(label);
		
		textArea = new JTextArea();
		textArea.setColumns(10);
		textArea.setBounds(87, 35, 83, 24);
		getContentPane().add(textArea);
		
		JLabel label_1 = new JLabel("\u7C7B\u522B\u63CF\u8FF0\uFF1A");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(10, 88, 96, 15);
		getContentPane().add(label_1);
		
		textArea_1 = new JTextArea();
		textArea_1.setColumns(10);
		textArea_1.setBounds(309, 35, 83, 24);
		getContentPane().add(textArea_1);
		
		
		JLabel label_3 = new JLabel("\u65B0\u7C7B\u522B\uFF1A");
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(20, 39, 69, 15);
		getContentPane().add(label_3);
		
		
		JButton button = new JButton("\u767B\u8BB0");
		button.setIcon(new ImageIcon(IDcard_new.class.getResource("/images/modify.png")));
		button.setBounds(167, 191, 84, 23);
		getContentPane().add(button);
		
		textArea_2 = new JTextArea();
		textArea_2.setText("\u8BF7\u8F93\u5165\u7C7B\u522B\u63CF\u8FF0\uFF0C50\u5B57\u4EE5\u5185\u3002");
		textArea_2.setBounds(33, 113, 359, 53);
		getContentPane().add(textArea_2);
		
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(sort_insert()){
					JOptionPane.showMessageDialog(null,"登记成功啦！！"); 
				}
		} 
		 });			
	}
	
	 public Boolean sort_insert() {
		  boolean result = false;
		           Connection conn = null;
		         try {	        
		           conn = Login.getCon();  //建立数据库连接
		           if(!textArea_2.getText().equals("") && !textArea_2.getText().equals("请输入类别描述，50字以内。")) {
		          String sqlInset = "insert into sort(Bsort,place,comment)  "
		          		+ "values('"+textArea.getText()+"','"+textArea_1.getText()+"','"+textArea_2.getText()+"')";
		          System.out.println(sqlInset);
		          PreparedStatement stmt = conn.prepareStatement(sqlInset); //会抛出异常    
		          int i = stmt.executeUpdate();           
		          if (i == 1) {
		              result = true;
		          }
		          }
		          } catch (SQLException e) {
		              // TODO Auto-generated catch block
		              e.printStackTrace();
		           } finally { //finally的用处是不管程序是否出现异常，都要执行finally语句，所以在此处关闭连接
		               try {
		                   conn.close(); //打开一个Connection连接后，最后一定要调用它的close（）方法关闭连接，以释放系统资源及数据库资源
		              } catch(SQLException e) {
		                   e.printStackTrace();
		              }
		          }
		          
		          return result;        
		}

	}
	



