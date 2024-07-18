package Matchinggood;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import Dao.Dao;
import Matching.Matching;
import member.Member;

public class Sad extends JFrame implements ActionListener {
	String[][] MbtiSad = { 
			{ "ENFP", "ISTJ", "ESTJ", "ISTP", "ISFP" }, 
			{ "INFJ", "ESTP", "INTJ", "ISFP", "ISFJ" },
			{ "INFP", "ISTJ", "ISTP", "ISFJ", "ISFP" }, 
			{ "ENFJ", "ISTP", "INTP", "ISFJ", "ISFP" },
			{ "ENTJ", "ISFP", "INTP", "ISFJ", "INFP" }, 
			{ "ENTP", "ISFJ", "ESFP", "ESTP", "ISFP" },
			{ "INTP", "ISFJ", "ESFJ", "ENFP", "ESTP" }, 
			{ "INTJ", "ISFP", "ESFP", "ENFJ", "ESTJ" },
			{ "ESFP", "INTJ", "ENTJ", "INFJ", "INTP" }, 
			{ "ISFJ", "ENTP", "INTP", "ENFP", "ENTJ" },
			{ "ESFJ", "INTP", "ENTP", "INFJ", "ENTJ" }, 
			{ "ESTP", "INFJ", "ENFJ", "ISFP", "INTJ" },
			{ "ESTJ", "INFJ", "ENFJ", "ISFJ", "INTP" }, 
			{ "ISFP", "ENTJ", "INTJ", "ENFJ", "ENTP" },
			{ "ISTP", "ENFJ", "INFJ", "ESFJ", "INTJ" } };
	Member gi = new Member();
	DefaultTableModel view;
	DefaultTableModel view2;
	DefaultTableModel view3;
	DefaultTableModel view4;
	Dao dao = new Dao();
	JPanel[] ba = new JPanel[10];
	String fo = null;
	JButton hoho = new JButton("뒤로");
	JPanel centerp = new JPanel();
	String gg;
	public void menu(String ree) {
		gg=ree;
		this.setLayout(new GridLayout(3, 1));
		fo = idmbti(ree);
		view = new DefaultTableModel();
		view.addColumn("성별");
		view.addColumn("이름");
		view.addColumn("나이");
		view.addColumn("Mbti");
		JTable table = new JTable(view);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(100, 100));
		this.getContentPane().add(scroll);
		ArrayList<Member> allList = gi(fo);
		if (allList != null && view != null) {
			for (Member bd : allList) {
				view.addRow(new Object[] { bd.getSex(), bd.getName(), bd.getAge(), bd.getMbti() });
			}
		}
		view2 = new DefaultTableModel();
		view2.addColumn("안맞는점");
		JTable tael = new JTable(view2);
		JScrollPane scooll = new JScrollPane(tael);
		scooll.setPreferredSize(new Dimension(100, 100));
		this.getContentPane().add(scooll);
		ArrayList<ReaSon> m = dao.search2(fo);
		if (m != null && view2 != null) {
			for (ReaSon uu : m) {
				view2.addRow(new Object[] { uu.getLo() });
			}
		}
		view3 = new DefaultTableModel();
		view3.addColumn("안맞는점");
		JTable tael2 = new JTable(view2);
		JScrollPane scooll2 = new JScrollPane(tael2);
		scooll.setPreferredSize(new Dimension(100, 100));
		this.getContentPane().add(scooll);
		ArrayList<ReaSon> m2 = dao.search2(fo);
		if (m != null && view2 != null) {
			for (ReaSon uu : m2) {
				view2.addRow(new Object[] { uu.getLo2() });
			}
		}
		view4 = new DefaultTableModel();
		view4.addColumn("안맞는점");
		JTable tael3 = new JTable(view2);
		JScrollPane scooll3 = new JScrollPane(tael3);
		scooll.setPreferredSize(new Dimension(100, 100));
		this.getContentPane().add(scooll);
		ArrayList<ReaSon> m3 = dao.search2(fo);
		if (m != null && view2 != null) {
			for (ReaSon uu : m3) {
				view2.addRow(new Object[] { uu.getLo3() });
			}
		}
		view4 = new DefaultTableModel();
		view4.addColumn("안맞는점");
		JTable tael4 = new JTable(view2);
		JScrollPane scooll4 = new JScrollPane(tael4);
		scooll.setPreferredSize(new Dimension(100, 100));
		this.getContentPane().add(scooll);
		ArrayList<ReaSon> m4 = dao.search2(fo);
		if (m != null && view2 != null) {
			for (ReaSon uu : m4) {
				view2.addRow(new Object[] { uu.getLo4() });
			}
		}
		this.add(hoho);
		this.setBounds(10, 100, 1250, 800);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		hoho.addActionListener(this);
		setVisible(true);
	}


	public ArrayList<ReaSon> gy(String fo) {
		ArrayList<ReaSon> resultList = new ArrayList<>();
		resultList.addAll(dao.search(fo));
		return resultList;
	}

	public ArrayList<Member> gi(String fo) {
		ArrayList<Member> resultList = new ArrayList<>();
		for (int i = 0; i < MbtiSad.length; i++) {
			if (MbtiSad[i][0].equals(fo)) {
				for (int j = 1; j <= 4; j++) {
					String hor = MbtiSad[i][j];
					resultList.addAll(dao.Mbtiinput(hor));
				}
				return resultList;
			}
		}
		return resultList;
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
		if (e.getSource().equals(hoho)) {
			this.setVisible(false);
			Matching b=new Matching();
			b.menu(gg);
		}
	}
}
