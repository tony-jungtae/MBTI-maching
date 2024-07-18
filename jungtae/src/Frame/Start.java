package Frame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import MemberJoin.MemberJoin;
import login.Log;

public class Start extends JFrame implements ActionListener {
	Log  he=new Log();
	MemberJoin sh=new MemberJoin(); 
	JButton login=new JButton("로그인하기");
	JButton make=new JButton("회원가입");
	JLabel jung=new JLabel("성격유형 매칭");
	JPanel[] pa=new JPanel[10];
	public void menu() {		
		this.setLayout(new GridLayout(13,1));
		for(int i=0; i<pa.length; i++) {
		pa[i]=new JPanel();

		this.add(pa[i]);
		}
		pa[1].add(jung);
		pa[5].add(login);
		pa[5].add(make);
	
	login.addActionListener(this);
	make.addActionListener(this);	
		
		

		this.setBounds(100,100,600,600);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
	}
	

	public void actionPerformed(ActionEvent e) {
	if(e.getSource().equals(login)) {
	this.setVisible(false);
	he.menu();
	}else if(e.getSource().equals(make)) {
	this.setVisible(false);
	sh.menu();
	}		
	}
}