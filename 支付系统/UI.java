package Item1;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class UI extends JFrame implements ActionListener{
	BankCard bc = new BankCard();
	CardLayout card;
	//登陆界面
	JPanel p1=new JPanel();
	JPanel p2=new JPanel();
	JLabel lab1=new JLabel("请输入用户名：");
	JLabel lab3=new JLabel("请输入卡号：");
	JTextField userName=new JTextField("");
	JTextField cardNumber=new JTextField("");
	JLabel labe2=new JLabel("请输入密码：");
	JPasswordField userPassword=new JPasswordField("");
	JButton b1=new JButton("登陆");
	JButton b2=new JButton("退出");
	//菜单
	JButton cx=new JButton("查询余额");
	JButton qk=new JButton("取款");
	JButton ck=new JButton("存款");
	JButton xs=new JButton("显示信息");
	JButton tc=new JButton("退出");

//	密码验证
public int yz() {
	String c1,c2;//卡号
	String p1,p2;//密码
	int flag=0;
	c1=cardNumber.getText();
	p1=userPassword.getText();	
	JTextField dl=new JTextField(""+c1+p1);

	return flag;
}

public void menu() {
	p1.setLayout(new GridLayout(5,1));
	p1.add(cx);
	p1.add(qk);
	p1.add(ck);
	p1.add(xs);
	p1.add(tc);	
	cx.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			p1.removeAll();
			p1.setLayout(new GridLayout(3,1,5,5));
			cha();
			p1.validate();
			repaint();
		}
	});
	qk.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			p1.removeAll();
			qu();
			p1.validate();
			repaint();
		}
	});
	ck.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			p1.removeAll();
			cun();
			p1.validate();
			repaint();
		}
	});
	xs.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			p1.removeAll();
			p1.setLayout(new GridLayout(5,1,4,4));
			xian();
			p1.validate();
			repaint();
		}
	});

	tc.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	});
}
	
public void cha() {
	JLabel cha1=new JLabel("余额:");
	p1.add(cha1);
	JLabel b=new JLabel("当前余额"+bc.balance+"\t￥");
	p1.add(b);
	JButton back=new JButton("返回菜单");
	p1.add(back);
	back.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			p1.removeAll();
			menu();
			p1.validate();
			repaint();
		}
	});

}
public void qu() {
	JTextField b=new JTextField("");
	JLabel qu1=new JLabel("请输入取款金额：");
	p1.add(qu1);
	p1.add(b);
	JButton back=new JButton("返回菜单");
	JButton b1=new JButton("确定");
	p1.add(back);
	p1.add(b1);
	back.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			p1.removeAll();
			menu();
			p1.validate();
			repaint();
		}
	});
	b1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			/*取款*/
			String moneyStr = b.getText();
			bc.takeout(Float.valueOf(moneyStr));
			p1.removeAll();
			menu();
			p1.validate();
			repaint();
		}
	});

}	
public void cun() {
	JTextField b=new JTextField("");
	JLabel qu1=new JLabel("请输入存款金额：");
	p1.add(qu1);
	p1.add(b);
	JButton back=new JButton("返回菜单");
	JButton b1=new JButton("确定");
	p1.add(back);
	p1.add(b1);
	back.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			p1.removeAll();
			menu();
			p1.validate();
			repaint();
		}
	});
	b1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String moneyStr = b.getText();
			bc.save(Float.valueOf(moneyStr));
			p1.removeAll();
			menu();
			p1.validate();
			repaint();
		}
	});

}		
public void xian() {
	JLabel b1=new JLabel("开户名："+bc.name);
	JLabel b2=new JLabel("开户日期:"+bc.OTime);
	JLabel b3=new JLabel("银行卡号："+bc.card);
	JLabel b4=new JLabel("余额："+bc.balance);
	p1.add(b1);
	p1.add(b2);
	p1.add(b3);
	p1.add(b4);
	JButton back=new JButton("返回菜单");
	p1.add(back);
	back.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			p1.removeAll();
			menu();
			p1.validate();
			repaint();
		}
	});

}		

	public UI() {
		Container c = this.getContentPane();// 在这里获取容器面板
		this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
		this.getContentPane().add(p1);
		p1.setLayout(new GridLayout(3,2,4,4));
		p1.add(lab3);
		p1.add(cardNumber);
		p1.add(labe2);
		p1.add(userPassword);
		p1.add(b1);
		p1.add(b2);		
		/*添加登陆监听器*/
		b1.addActionListener(this);
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		setSize(300,200);
		setVisible(true);
		
		
	}
	
	
	public void actionPerformed(ActionEvent e) {
		/*用户账号密码校验*/
		String username = cardNumber.getText();
		String password = new String(userPassword.getPassword());
		
		DBConnection dBConnection = new DBConnection();
		int result = dBConnection.query(username, password);
		
		if(result == -1)
			System.out.println("账号密码异常");
		else {
			/*登陆成功获取账号信息*/
			bc = dBConnection.queryC(username);
			p1.removeAll();
			menu();
			p1.validate();
			repaint();
		}
	}
	
	

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new UI();
//
//	}

}
