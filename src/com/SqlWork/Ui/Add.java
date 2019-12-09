package com.SqlWork.Ui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import com.SqlWork.SQL.SqlADCS;
import com.SqlWork.User.User;

public class Add extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ImageIcon icon5 = new ImageIcon("./picture/bt5.png");
	ImageIcon icon6 = new ImageIcon("./picture/bt6.png");
	private JLabel name = new JLabel("姓名");
	private JLabel id = new JLabel("编号");
	private JLabel pwd = new JLabel("密码");
	private JLabel money = new JLabel("网费");
	private JLabel identity = new JLabel("身份证号");
	
	private JTextField tfid=new JTextField(String.valueOf(User.number+1),10);
	private JTextField tfname=new JTextField(10);
	private JTextField tfpwd=new JTextField(10);
	private JTextField tfmoney=new JTextField(10);
	private JTextField tfidentity=new JTextField(10);
	
	private JButton btadd = new JButton(icon5);
	private JButton btexit = new JButton(icon6);
	public Add() {
		super("增加会员");
		this.setLayout(new GridLayout(6,2));
		name.setFont(new Font("Menu.font", Font.PLAIN, 38));
		id.setFont(new Font("Menu.font", Font.PLAIN, 38));
		pwd.setFont(new Font("Menu.font", Font.PLAIN, 38));
		money.setFont(new Font("Menu.font", Font.PLAIN, 38));
		identity.setFont(new Font("Menu.font", Font.PLAIN, 38));
		tfname.setFont(new Font("Menu.font", Font.PLAIN, 38));
		tfid.setFont(new Font("Menu.font", Font.PLAIN, 38));
		tfpwd.setFont(new Font("Menu.font", Font.PLAIN, 38));
		tfmoney.setFont(new Font("Menu.font", Font.PLAIN, 38));
		tfidentity.setFont(new Font("Menu.font", Font.PLAIN, 38));
		this.add(id);this.add(tfid);
		this.add(name);this.add(tfname);
		this.add(pwd);this.add(tfpwd);
		this.add(money);this.add(tfmoney);
		this.add(identity);this.add(tfidentity);
		this.add(btadd);this.add(btexit);
		btadd.setFont(new Font("Menu.font", Font.PLAIN, 38));
		btexit.setFont(new Font("Menu.font", Font.PLAIN, 38));
		this.setBounds(((Toolkit.getDefaultToolkit().getScreenSize().width)/2)-300, ((Toolkit.getDefaultToolkit().getScreenSize().height)/2)-300,600,600);
		this.setVisible(true);
		btadd.addActionListener(this);
		btexit.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource()==btadd) {
			try {
				SqlADCS.SQLAdd(Integer.parseInt(tfid.getText()), tfname.getText(), tfpwd.getText(),
						Integer.parseInt(tfmoney.getText()),tfidentity.getText());
//				System.out.println(Integer.parseInt(tfid.getText())+tfname.getText()+tfpwd.getText()+
//						Integer.parseInt(tfmoney.getText())+tfidentity.getText());
				User.uid[User.number]=Integer.parseInt(tfid.getText());
				User.uname[User.number]=tfname.getText();
				User.pwd[User.number]=tfpwd.getText();
				User.money[User.number]=Integer.parseInt(tfmoney.getText());
				User.identity[User.number]=tfidentity.getText();
				User.number=0;
				SqlADCS.SQLSelect();
							
				
			} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(this,"添加成功");
			this.dispose();
			new Main();
			return ;
//			new Main();
		}else if(arg0.getSource()==btexit) {
			this.dispose();
			new Main();
		}
		
	}
//	public static void main (String arg[]) {
//		new Add();
//	}
}
