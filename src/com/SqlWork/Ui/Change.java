package com.SqlWork.Ui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.SqlWork.SQL.SqlADCS;
import com.SqlWork.User.User;

public class Change extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ImageIcon icon3 = new ImageIcon("./picture/bt3.png");
	ImageIcon icon4 = new ImageIcon("./picture/bt4.png");
	private JLabel name = new JLabel("姓名",JLabel.CENTER);
	private JLabel id = new JLabel("编号",JLabel.CENTER);
	private JLabel pwd = new JLabel("密码",JLabel.CENTER);
	private JLabel money = new JLabel("网费",JLabel.CENTER);
	private JLabel identity = new JLabel("身份证号",JLabel.CENTER);
	
	private JTextField tfid=new JTextField(10);
	private JTextField tfname=new JTextField(10);
	private JTextField tfpwd=new JTextField(10);
	private JTextField tfmoney=new JTextField(10);
	private JTextField tfidentity=new JTextField(10);
	
	private JButton btchange = new JButton(icon3);
	private JButton btexit = new JButton(icon4);
	public Change(String Id,String Name,String Pwd,String Money,String Identity) {
		super("修改会员");
		this.setLayout(new GridLayout(6,2));
		
		tfid=new JTextField(String.valueOf(Id),10);
		tfname=new JTextField(Name,10);
		tfpwd=new JTextField(Pwd,10);
		tfmoney=new JTextField(String.valueOf(Money),10);
		tfidentity=new JTextField(Identity,10);
		
		
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
		this.add(btchange);this.add(btexit);
		btchange.setFont(new Font("Menu.font", Font.PLAIN, 38));
		btexit.setFont(new Font("Menu.font", Font.PLAIN, 38));
		this.setBounds(((Toolkit.getDefaultToolkit().getScreenSize().width)/2)-300, ((Toolkit.getDefaultToolkit().getScreenSize().height)/2)-300,600,600);
		this.setVisible(true);
		btchange.addActionListener(this);
		btexit.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource()==btchange) {
			try {
				SqlADCS.SQLChange(tfid.getText(),tfname.getText(), tfpwd.getText(), tfmoney.getText(),
						tfidentity.getText(),tfname.getText());
				User.number=0;
				SqlADCS.SQLSelect();			
				
			} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(this,"修改成功");
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
//		new Change("1","2","3","4","5");
//	}
}
