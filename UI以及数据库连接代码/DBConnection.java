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
		}catch(ClassNotFoundException e) {
			System.out.println("��������װ��ʧ��!");
		}
		catch(SQLException e) {
			System.out.println("���ݿ�����ʧ��!");
		}
	}
	
	public int query(String username , String password) {
		int result = -1;
		try {
			Statement sql = con.createStatement();//���ݿ�ִ���ֱ�
			String sqlString = "select * from asd.dbo.login where 1=1 and card = '"+username+"' and password = '"+password+"';";
			ResultSet rs = sql.executeQuery(sqlString);
			
			while(rs.next()) {
				result++;
				JOptionPane.showMessageDialog(null, "��½�ɹ���","��Ϣ�Ի���",JOptionPane.DEFAULT_OPTION); 
			}
			
			if(result == -1)
				JOptionPane.showMessageDialog(null, "�˺Ż�������� !","��Ϣ�Ի���",JOptionPane.WARNING_MESSAGE);
			
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
	

	public BankCard queryC(String card) {
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
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bankCard;
	}
	
	public int updata(float balance,int card) {
		int result=-1;
		try {
			Statement sql = con.createStatement();
			String sqlString = "update asd.dbo.login set balance = "+balance+"where card = '"+card+"';";
			System.out.println(sqlString);
			sql.executeUpdate(sqlString);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return  result;
	}
	
	public static void main(String[] args) {
		DBConnection dBConnection = new DBConnection();
	}
}