package MemberJoin;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Dao.Dao;
import Frame.Start;
import member.Member;

public class MemberJoin extends JFrame implements ActionListener {
	JLabel meking = new JLabel("회원가입");
	JLabel IDIN = new JLabel("id"); JTextField id = new JTextField(12);
	JLabel PWIN = new JLabel("pw");  JTextField pw = new JTextField(25);
	JLabel name = new JLabel("이름"); JTextField namein = new JTextField(25);
	JLabel age = new JLabel("나이");	JTextField agein = new JTextField(25);
	JLabel mbti = new JLabel("MBTI");
	JTextField mbtiin = new JTextField(25);
	String[] mbtiTypes = {
		    "ESTJ", "ESFJ", "ENFJ", "ENTJ",
		    "ESTP", "ESFP", "ENFP", "ENTP",
		    "ISTJ", "ISFJ", "INFJ", "INTJ",
		    "ISTP", "ISFP", "INFP", "INTP"
		};
	 JComboBox<String> mbtiComboBox = new JComboBox<>(mbtiTypes);
	   
	
	JLabel sex = new JLabel("성별");
	JCheckBox sex1 = new JCheckBox("남성");
	JCheckBox sex2 = new JCheckBox("여성");
	JButton back=new JButton("뒤로");
	JButton make = new JButton("회원가입");
	JButton idck = new JButton("아이디 중복확인");
	JLabel duplication = new JLabel("중복");
	JLabel sure = new JLabel("사용가능");
	JLabel bbu=new JLabel("중복이라니까?");
	JPanel[] ba = new JPanel[10];
	FlowLayout layout = new FlowLayout();
	Dao dao = new Dao();
	Member member = new Member();

	public void menu() {
	
		this.setLayout(new GridLayout(13, 1));
		for (int i = 0; i < ba.length; i++) {
			ba[i] = new JPanel();
			this.add(ba[i]);
		}
		ba[1].add(meking);
		ba[3].add(IDIN);
		ba[3].add(id);
		ba[3].add(idck);
		ba[4].add(PWIN);
		ba[4].add(pw);
		ba[5].add(age);
		ba[5].add(agein);
		ba[6].add(name);
		ba[6].add(namein);
		ba[7].add(mbti);
		ba[7].add(mbtiComboBox);
		ba[8].add(sex);
		ba[8].add(sex1);
		ba[8].add(sex2);
		ba[9].add(make);
		ba[9].add(back);
		this.setBounds(100, 100, 500, 500);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
		sex1.addActionListener(this);
		sex2.addActionListener(this);
		make.addActionListener(this);
		idck.addActionListener(this);
		back.addActionListener(this);
		mbtiComboBox.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent c) {
		if(c.getSource().equals(idck)) {
			String getid = id.getText();
			if(dao.idck(getid)){
				this.setVisible(true);
				ba[3].remove(sure);
				ba[3].add(duplication);
			}else {
				this.setVisible(true);
				ba[3].remove(duplication);
				ba[3].add(sure);
			}				
		}
		
	

		
			if (c.getSource().equals(sex1)) {
				member.setSex("남자");
			}
			if (c.getSource().equals(sex2)) {
				member.setSex("여자");
			}
			if (c.getSource().equals(make)) {
				String getid = id.getText();
				if(dao.idck(getid)) {
				this.setVisible(true);
				ba[3].add(bbu);		
				}else {
				String getid2 = id.getText();
				String getpw = pw.getText();
				String getage = agein.getText();
	      		String getmbti = mbtiComboBox.getSelectedItem().toString();
				String getname = namein.getText();
				member.setAge(getage);
				member.setId(getid2);
				member.setPw(getpw);
				member.setMbti(getmbti);
				member.setName(getname);
				dao.insert(member);
				Start ee = new Start();
				this.setVisible(false);
				ee.menu();
				}
			}else if(c.getSource().equals(back)) {
			this.setVisible(false);
			Start a =new Start();
			a.menu();
			}
		}
}
