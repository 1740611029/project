package Item1;
import java.sql.*;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class BankCard {
	public String bank;
	public String name;
	public int card;
	public String password;
	public String ID;
	public String OTime;//开户时间
	public static float balance=5000;
	
	BankCard(){
		bank="中国银行";
		name="无名氏";
		card=100000;
		ID="000000";
		OTime="2018/01/01";
		password="000000";
	}
	
public boolean submit(String mypassword) {
	boolean b=false;
	if(mypassword.equals(password))
		b=true;
	return b;
}
public float balance() {
	float d=balance;
	System.out.println("您的卡内余额为："+d+"元");
	return d;
}
public void takeout(float money) {
	DBConnection dBConnection = new DBConnection();{
		if(money<=balance) {
			float d=balance-money;
			balance=d;
			/*数据库更新*/
			int result = dBConnection.updata(balance,card);
			System.out.println("您的取款金额为："+money+"元\n您的卡内余额为："+d+"元");
		}
		else {
			JOptionPane.showMessageDialog(null, "账户余额不足 !","消息对话框",JOptionPane.WARNING_MESSAGE);
		}
	
	}
}
public void save(float money) {
	DBConnection dBConnection = new DBConnection();
	float d=balance+money;
	balance=d;
	int result = dBConnection.updata(balance,card);
	System.out.println("您的存款金额为："+money+"元\n您的卡内余额为："+d+"元");
}
	
	

}
