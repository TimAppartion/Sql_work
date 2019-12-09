package com.SqlWork.SQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import com.SqlWork.User.*;
public class SqlADCS {
		 
	 Statement sta=null;
	 static String url="jdbc:mysql://localhost:3306/sqlist?useUnicode=true&characterEncoding=utf8";
     static String username="root";
     static String password="wh2234040";
     /**********************增加*************************/
     public static void SQLAdd(int id,String name,String pwd,int money,String identity)throws SQLException, ClassNotFoundException{
    	Connection cn = null;
 		Class.forName("com.mysql.jdbc.Driver");
 		cn =  DriverManager.getConnection(url,username,password);
 		String sql="insert into t_user " +"(uid,uname,pwd,money,identity)"+"values(?,?,?,?,?)";
 		PreparedStatement ptmt=cn.prepareStatement(sql);
 			 System.out.println(id+name+pwd+money+identity);
 			 ptmt.setInt(1, id);
 			 ptmt.setString(2, name);
 			 ptmt.setString(3, pwd);
 			 ptmt.setInt(4, money);
 			 ptmt.setString(5, identity);
 			 ptmt.executeUpdate();
 			 User.number++;
 			 SqlADCS.flush();
 			 cn.close();
 	 	    
 	     
 	}
     public static void flush() throws SQLException {
    	 Connection cn = null;
  		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn =  DriverManager.getConnection(url,username,password);
	  		String sql="FLUSH PRIVILEGES;";
	  		PreparedStatement ptmt=cn.prepareStatement(sql);
	  		ptmt.executeUpdate();
	  		cn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		
     }
     /**********************删除*************************/
     public static void SQLDelete(String name)throws SQLException, ClassNotFoundException{
    	Connection cn = null;
  		Class.forName("com.mysql.jdbc.Driver");
  		cn =  DriverManager.getConnection(url,username,password);
  		String sql = "delete from t_user where uname=? ";
  		PreparedStatement ptmt=cn.prepareStatement(sql);
  			
  			 ptmt.setString(1, name);
  			 
  			 ptmt.executeUpdate();
  			 cn.close();
  	 	     
 	     
 	} 
     /**********************修改*************************/
     public static void SQLChange(String id,String name,String pwd,String money,String identity,String w)throws SQLException, ClassNotFoundException{
    	 Connection cn = null;
   		Class.forName("com.mysql.jdbc.Driver");
   		cn =  DriverManager.getConnection(url,username,password);
   		String sql = "UPDATE t_user " + 
   				"SET pwd= ?,money=?,identity=?,uid=?"+ 
   				"WHERE uname=?;";
   		PreparedStatement ptmt=cn.prepareStatement(sql);
   		
   		
   		ptmt.setString(1, pwd);
   		ptmt.setString(2, money);
   		ptmt.setString(3, identity);
   		ptmt.setString(4, id);
   		ptmt.setString(5, name);
   		ptmt.executeUpdate();
   		cn.close();
 	     
 	}
     /**********************查询************************/
 	public static  void SQLSelect()throws SQLException, ClassNotFoundException{
 		Connection cn = null;
		Class.forName("com.mysql.jdbc.Driver");
		cn =  DriverManager.getConnection(url,username,password);
			String sql = "select * from t_user";
			
			try
			{
				PreparedStatement ps = cn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) 
				{
					User.uid[User.number]=rs.getInt("uid");
					User.uname[User.number]=rs.getString("uname");
					User.pwd[User.number]=rs.getString("pwd");
					User.money[User.number]=rs.getInt("money");
					User.identity[User.number]=rs.getString("identity");
					User.number++;
//					System.out.println(rs.getInt("uid")+"..."+rs.getString("uname")+
//		 	    			 "..."+rs.getString("pwd")+"..."+rs.getString("money")+"..."+rs.getString("identity")+User.uname[User.number]);
					
				}
   				rs.close();
   				ps.close();
  				}
  			catch(Exception e)
  			{
   				e.printStackTrace();
  			}
  			finally
  			{
   				cn.close();
  			} 
		
 	}
	
 	 /**********************查询************************/
 	public static  int SQLSelect(String name,String pwd)throws SQLException, ClassNotFoundException{
 		Connection cn = null;
		Class.forName("com.mysql.jdbc.Driver");
		cn =  DriverManager.getConnection(url,username,password);
			String sql = "select * from master";
			
			try
			{
				PreparedStatement ps = cn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) 
				{
					if(name.equals(rs.getString("name"))&&pwd.equals(rs.getString("pwd"))) {
						
						return 1; 
					}
//					System.out.println(rs.getInt("uid")+"..."+rs.getString("uname")+
//		 	    			 "..."+rs.getString("pwd")+"..."+rs.getString("money")+"..."+rs.getString("identity")+User.uname[User.number]);
					
				}
   				rs.close();
   				ps.close();
  				}
  			catch(Exception e)
  			{
   				e.printStackTrace();
  			}
  			finally
  			{
   				cn.close();
  			}
			return 0; 
		
 	}
 	
	/*********************实例*************************/
// 	 public static void SQLDelete()throws SQLException, ClassNotFoundException{
// 		 Connection conn=DriverManager.getConnection(url, username, password);
// 		 Statement sta=conn.createStatement();   
// 		 String sql="";
// 		 //do something
//// 	     rs.close();
// 	     conn.close();
// 	     sta.close();
// 	     
// 	}
 	
 	
 	

}

