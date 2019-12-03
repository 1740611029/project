package Item1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
public class DBConnection {
	public static Connection con=null;
	
	static{
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String url="jdbc:sqlserver://localhost:1433;DatabaseName=asd";
		String user="sa",password="123456";
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, password);
			System.out.println("数据库连接成功!");
		}catch(ClassNotFoundException e) {
			System.out.println("驱动程序装载失败!");
		}
		catch(SQLException e) {
			System.out.println("数据库连接失败!");
		}
	}
	
	public int query(String username , String password) {
		int result = -1;
		try {
			Statement sql = con.createStatement();
			String sqlString = "select * from asd.dbo.login where 1=1 and card = '"+username+"' and password = '"+password+"';";
			ResultSet rs = sql.executeQuery(sqlString);
			
			while(rs.next()) {
				result++;
				JOptionPane.showMessageDialog(null, "登陆成功！","消息对话框",JOptionPane.DEFAULT_OPTION); 
			}
			
			if(result == -1)
				JOptionPane.showMessageDialog(null, "账号或密码错误 !","消息对话框",JOptionPane.WARNING_MESSAGE);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int queryb(float balance) {
		int result = -1;
		try {
			Statement sql = con.createStatement();
			String sqlString = "select * from asd.dbo.login where 1=1 and card = "+balance;
			ResultSet rs = sql.executeQuery(sqlString);
			
			while(rs.next()) {
				result++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 通过账号查询用户信息
	 * @param cardNumber
	 * @return
	 */
	public BankCard queryC(String card) {
		int result = -1;
		BankCard bankCard = new BankCard();
		try {
			Statement sql = con.createStatement();
			String sqlString = "select * from asd.dbo.login where 1=1 and card = "+card;
			ResultSet rs = sql.executeQuery(sqlString);
			while(rs.next()){
				bankCard.OTime = rs.getString("opentime");
				bankCard.name = rs.getString("username");
				bankCard.card = rs.getInt("card");
				bankCard.balance = rs.getFloat("balance");
				bankCard.password = rs.getString("password");
			}
			
			while(rs.next()) {
				result++;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bankCard;
	}
	
	
	
	public static void main(String[] args) {
		DBConnection dBConnection = new DBConnection();
		dBConnection.query("刘晨", "123456");
	}
}
