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
	
	

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		BankCard c1=new BankCard();
//		System.out.println("请输入密码：");
//		Scanner sc=new Scanner(System.in);
//		String mypassword=sc.next();
//		boolean b=c1.submit(mypassword);
//		int flag=1;
//		while(flag==1)
//		{
//		if(b)
//		{
//			System.out.println("【"+c1.bank+"】欢迎您！"+c1.name+"您的卡号为："+c1.cardNumber+"请您选择下列操作：");
//			System.out.println("1.查询余额");
//			System.out.println("2.取款");
//			System.out.println("3.存款");
//			System.out.println("4.显示账户信息");
//			System.out.println("5.退出");
//		}
//		else
//			System.out.println("密码错误！！！");
//		int i=sc.nextInt();
//		
//			switch(i)
//			{
//			case 1:
//				c1.balance();
//				System.out.println("是否继续？\n继续 （1）   退出 （0）");
//				flag=sc.nextInt();
//				if(flag==0)
//					System.out.println("谢谢使用，再见！");
//				break;
//			case 2:
//				System.out.print("请输入取款金额：");
//				double j=sc.nextDouble();
//				c1.takeout(j);
//				System.out.println("是否继续？\n继续 （1）   退出 （0）");
//				flag=sc.nextInt();
//				if(flag==0)
//					System.out.println("谢谢使用，再见！");
//				break;
//			case 3:
//				System.out.print("请输入取款金额：");
//				double k=sc.nextDouble();
//				c1.save(k);
//				System.out.println("是否继续？\n继续 （1）   退出 （0）");
//				flag=sc.nextInt();
//				if(flag==0)
//					System.out.println("谢谢使用，再见！");
//				break;
//			case 4:
//				System.out.println(""+c1.bank);
//				System.out.println("卡号："+c1.cardNumber);
//				System.out.println("姓名："+c1.name);
//				System.out.println("身份证号："+c1.ID);
//				System.out.println("开户时间："+c1.OTime);
//				System.out.println("余额："+c1.balance());
//				System.out.println("是否继续？\n继续 （1）   退出 （0）");
//				flag=sc.nextInt();
//				if(flag==0)
//					System.out.println("谢谢使用，再见！");
//				break;
//			case 5:
//				System.out.println("确定退出？\n继续 （1）   退出 （0）");
//				flag=sc.nextInt();
//				if(flag==0)
//					System.out.println("谢谢使用，再见！");
//				break;
//			default:
//				System.out.println("输入错误");
//			}
//		}
//	}

}
