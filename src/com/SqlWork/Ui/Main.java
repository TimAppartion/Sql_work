package com.SqlWork.Ui;

import java.sql.SQLException;
import java.util.Vector;

import com.SqlWork.SQL.SqlADCS;
import com.SqlWork.User.User;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.*;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

 
public class Main extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;
	private JTable jtable;
	private JTextField textfield1;
	private JTextField textfield2;
	
	private JScrollPane jscrollpane = new JScrollPane();
	private JPanel jpanel = new JPanel();
	
	private JButton button1 = new JButton("添加");
	private JButton button2 = new JButton("删除");
	private JButton button3 = new JButton("修改");
	
	private Vector<String> dataTitle = new Vector<String>();//表格列名
	private Vector<Vector<String>> data = new Vector<Vector<String>>();//表格单元格内容
	
	public Main() {
		setTitle("网吧管理系统");
		setVisible(true);
		setBounds(400,100,1024,860);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		/************添加表格列名***********************/
		dataTitle.add("编号");
		dataTitle.add("姓名");
		dataTitle.add("密码");
		dataTitle.add("网费");
		dataTitle.add("身份证号");
		/*************添加表格单元格内容****************/
		for(int i = 0; i < User.number; i++) {
			Vector<String> W = new Vector<String>();
			W.add(String.valueOf(User.uid[i]));
			
			W.add(User.uname[i]);
			W.add(User.pwd[i]);
			W.add(String.valueOf(User.money[i]));
			W.add(User.identity[i]);
			data.add(W);
		}
		
		
		
		//创建指定表格模型的表格,并设置表格排序器，表格的选择模式为单选
		tableModel = new DefaultTableModel(data, dataTitle);
		jtable = new JTable(tableModel);
		jtable.setRowSorter(new TableRowSorter<TableModel>(tableModel));
		jtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setColumnWidth(jtable,"编号",100);
	    setColumnWidth(jtable,"姓名",100);
	    setColumnWidth(jtable,"密码",180);
	    setColumnWidth(jtable,"网费",100);
	    setColumnWidth(jtable,"身份证号",180);
		
		
		
		JTableHeader head = jtable.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 40));// 设置表头大小
        head.setFont(new Font("楷体", Font.PLAIN, 38));// 设置表格字体
        jtable.setFont(new Font("Menu.font", Font.PLAIN, 38));
        jtable.setRowHeight(100);// 设置表格行宽
        
        
		//为表格添加鼠标事件监听器(单击事件)
		jtable.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				int selectRow = jtable.getSelectedRow();//获取鼠标选择行的索引值
				Object selectValues1 = jtable.getValueAt(selectRow, 0);//获取选择的单元格的内容
				Object selectValues2 = jtable.getValueAt(selectRow, 1);
				textfield1.setText(selectValues1.toString());//将单元格的内容显示在文本框中
				textfield2.setText(selectValues2.toString());
			}
		});
		
		jscrollpane.setViewportView(jtable);
		add(jscrollpane, BorderLayout.NORTH);
		add(jpanel, BorderLayout.SOUTH);
		
		jpanel.add(new JLabel("编号："));
		jpanel.add(textfield1 = new JTextField("显示的是编号",10));
		jpanel.add(new JLabel("内容："));
		jpanel.add(textfield2 = new JTextField("显示的是姓名",10));
		
		/*************向表格中添加内容*********/
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Add();
				dispose();
			}
		});
		jpanel.add(button1);
		
		/***********删除选中的表格行的内容**********/
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = jtable.getSelectedRow();// 获得被选中行的索引
				if(row != -1) {// 判断是否存在被选中行
					
					try {
						String name = jtable.getValueAt(row, 1).toString();//获取选择的单元格的内容
						System.out.println(name);
						SqlADCS.SQLDelete(name);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					tableModel.removeRow(row);//从表格模型中删除
				}
			}
		});
		jpanel.add(button2);
		
		/*********修改选中的表格行的内容************/
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = jtable.getSelectedRow();// 获得被选中行的索引
				if (row != -1) {// 判断是否存在被选中行
					String s1 = jtable.getValueAt(row, 0).toString();
					String s2 = jtable.getValueAt(row, 1).toString();
					String s3 = jtable.getValueAt(row, 2).toString();
					String s4 = jtable.getValueAt(row, 3).toString();
					String s5 = jtable.getValueAt(row, 4).toString();
					new Change(s1,s2,s3,s4,s5);
					dispose();
					
				}
			}
		});
		jpanel.add(button3);
		
		
	}
 
	public  void setColumnWidth(JTable table,Object colname,int width){
        //此方法是通过equals方法查找的，需要注意列名重复问题
        table.getColumn(colname).setPreferredWidth(width);
    }
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		
// 
//	}
// 
}
