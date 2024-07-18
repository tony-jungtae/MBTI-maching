package Matchinggood;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Dao.Dao;
import Frame.Start;
import Matching.Matching;
import login.Log;
import member.Member;
import oracle.net.aso.g;

public class Good extends JFrame implements ActionListener {
	String[][] goodMbti ={
			{ "INTP", "ENTJ", "INTJ", "ENTP", "INFP" }, 
			{ "ENFP", "INFJ", "INFP", "ENFJ", "ENTJ" }, 
			{ "ENTP", "INTJ", "ENTJ", "ENFP", "INFJ" },
			{ "INFP", "ENFJ", "INFJ", "ENFP", "INTP" }, 
			{ "INFJ", "ENFP", "ENFJ", "INTJ", "INFP" },
			{ "ENFJ", "INFJ", "INFP", "ENFP", "ENTJ" }, 
			{ "ENTJ", "INTP", "ENTP", "ENFP", "ENFJ" },
			{ "INTJ", "ENTP", "INTP", "INFJ", "ENTJ" },
			{ "ESFP", "ISFJ", "ESFJ", "ESTP", "ESTJ" },
			{ "ESTP", "ISTP", "ESTJ", "ESFP", "ENTP" },
			{ "ESFJ", "ISFJ", "ENFJ", "ESFP", "ESTJ" }, 
			{ "ESTJ", "ISTJ", "ENTJ", "ESFJ", "ESTP" },
			{ "ISFP", "ESFJ", "INFP", "ISFJ", "ISTP" },
			{ "ISTP", "ESTP", "ISFP", "ISTJ", "INTP" },
			{ "ISFJ", "ESFJ", "ENFJ", "ISFP", "ISTJ" }, 
			{ "ISTJ", "ESTP", "ISFJ", "ESFJ", "ESFP" }};	

	Member M = new Member();
	DefaultTableModel view;
	Dao dao = new Dao();
	String userMbti = null;
	JButton hoho=new JButton("뒤로");
	String gg;
	Log epep=null;
	JPanel southp=new JPanel();
	DefaultTableModel view2;
	DefaultTableModel view3;
	DefaultTableModel view4;
	DefaultTableModel view5;
	
	public void menu(String userId) {
		userMbti = idmbti(userId);
		this.setLayout(new GridLayout(3,1));
		gg=userId;
		view = new DefaultTableModel();
		view.addColumn("성별");
		view.addColumn("이름");
		view.addColumn("나이");
		view.addColumn("Mbti");
		JTable table = new JTable(view);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(100, 100));
		this.getContentPane().add(scroll);
		ArrayList<Member>allList = gi(userMbti);
		if (allList != null && view != null) {
			for (Member bd : allList) {
				view.addRow(new Object[] { bd.getSex(), bd.getName(), bd.getAge(), bd.getMbti() });
			}			
		}
	
		view2=new DefaultTableModel();
		view2.addColumn("잘맞는점");
		JTable tael=new JTable(view2);
		JScrollPane scooll = new JScrollPane(tael);
		scooll.setPreferredSize(new Dimension(400, 300));
		this.getContentPane().add(scooll);
		ArrayList<ReaSon> m=dao.search(userMbti);
		if (m != null && view2 != null) {
			for (ReaSon uu : m) {
				view2.addRow(new Object[] {uu.getLo()});	
			}
		}
		ArrayList<ReaSon> m2=dao.search(userMbti);
		if (m != null && view2 != null) {
			for (ReaSon uu : m2) {
				view2.addRow(new Object[] {uu.getLo2()});	
			}
		}

		ArrayList<ReaSon> m3=dao.search(userMbti);
		if (m != null && view2 != null) {
			for (ReaSon uu : m3) {
				view2.addRow(new Object[] {uu.getLo3()});	
			}
		}
		ArrayList<ReaSon> m4=dao.search(userMbti);
		if (m != null && view2 != null) {
			for (ReaSon uu : m4) {
				view2.addRow(new Object[] {uu.getLo4()});	
			}
		}
		southp.add(hoho);
		setVisible(true);
		this.add(southp,"South");
		this.setBounds(10, 100, 1250, 800);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		hoho.addActionListener(this);
	}
	

	public ArrayList<Member> gi(String userMbti) {
	    ArrayList<Member> GoodSearch = new ArrayList<>();
	    for (int i = 0; i < goodMbti.length; i++) {
	        if (goodMbti[i][0].equals(userMbti)) {
	            for (int j = 1; j <= 4; j++) { 
	                String hor = goodMbti[i][j];
	                GoodSearch.addAll(dao.Mbtiinput(hor));
	            }
	            return GoodSearch;
	        }
	    }
	    return GoodSearch;
	}

	public String idmbti(String id) {
		String gggg = null;
		ArrayList<Member> allList = dao.selectAll2();
		for (Member g : allList) {
			if (g.getId().equals(id)) {
				gggg = g.getMbti();
			}
		}
		return gggg;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) { 
		if(e.getSource().equals(hoho)) {
		this.setVisible(false);
		Matching b=new Matching();
		b.menu(gg);
		}
	}
}
