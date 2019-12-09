package com.SqlWork.Ui;


import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Random;


import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

import com.SqlWork.SQL.SqlADCS;


public class Login extends JFrame implements ActionListener {
	 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/****************************定义各控件**************************/
//    private Icon welcomeIcon=new ImageIcon("welcome.png");
//    private JLabel lbWelcome=new JLabel(welcomeIcon);
	ImageIcon icon1 = new ImageIcon("./picture/bt1.png");
	ImageIcon icon2 = new ImageIcon("./picture/bt2.png");
	private JLabel lbAccount=new JLabel("账号:",JLabel.CENTER);
    private JTextField tfAccount=new JTextField(10);
    private JLabel lbPassword=new JLabel("密码:",JLabel.CENTER);
    private JPasswordField pfPassword=new JPasswordField(10);
    private JButton btLogin=new JButton(icon1);
    private JButton btExit=new JButton(icon2);
    Random r=new Random();
	int i=r.nextInt(8999)+1000;    //生成四位数的验证码
	String str=String.valueOf(i);
    private JLabel lbma=new JLabel("验证码: "+str,JLabel.CENTER);
    private JTextField tfma=new JTextField(10);
    public Login() {
        /**********************界面初始化*****************************/
    	
        super("登录");
        
        this.setLayout(new GridLayout(5,2));
//        this.add(lbWelcome);
        lbAccount.setFont(new Font("Menu.font", Font.PLAIN, 28));
        tfAccount.setFont(new Font("Menu.font", Font.PLAIN, 28));
        lbPassword.setFont(new Font("Menu.font", Font.PLAIN, 28));
        pfPassword.setFont(new Font("Menu.font", Font.PLAIN, 28));
        lbma.setFont(new Font("Menu.font", Font.PLAIN, 16));
        tfma.setFont(new Font("Menu.font", Font.PLAIN, 28));
        this.add(lbAccount);
        this.add(tfAccount);
        this.add(lbPassword);
        this.add(pfPassword);
        this.add(lbma);
        this.add(tfma);
        this.add(btLogin);
        this.add(btExit);
        this.setSize(300,250);
        this.setLocationRelativeTo(null);//在屏幕中居中显示
        GUIUtil.toCenter(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        /*****************************增加监听************************/
        btLogin.addActionListener(this);
       
        btExit.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btLogin) {
           String name = tfAccount.getText();
           String pwd = new String(pfPassword.getPassword());
//           SqlADCS.SQLSelect("*","uname","'"+name+"'");
//           System.out.println(User.getUname()+User.getMoney());
        try {
        	
			if(SqlADCS.SQLSelect(name, pwd)==1)
			   {
				   
				   JOptionPane.showMessageDialog(this,"登录成功");
				   
				   this.dispose();
				   SqlADCS.SQLSelect();
				   new Main();
			   }
			else {
				JOptionPane.showMessageDialog(this,"账号、密码或验证码错误");
			}
		} catch (HeadlessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        }
        else {
            JOptionPane.showMessageDialog(this,"谢谢使用");
            System.exit(0);
        }
    }
    
    
    public static void main(String args[]) throws SQLException, ClassNotFoundException{
		new Login();
		
	}
}