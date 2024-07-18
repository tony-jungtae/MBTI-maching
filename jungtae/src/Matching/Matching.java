package Matching;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Matchinggood.Good;
import Matchinggood.Sad;
import login.Log;
import member.Member;
import oracle.net.aso.d;

public class Matching extends JFrame implements ActionListener {
	JPanel[] ba = new JPanel[10];
	JLabel matching = new JLabel("매칭 서비스");
	JButton mo = new JButton("잘맞는 성향 매칭");
	JButton no = new JButton("안맞는 성향 매칭");
	JButton back = new JButton("뒤로");
	Good good = new Good();
	Sad sad = new Sad();
	String ree = null;
	public void menu(String id) {

		ree = id;
		this.setLayout(new GridLayout(10, 1));
		for (int i = 0; i < ba.length; i++) {
			ba[i] = new JPanel();
			this.add(ba[i]);
		}
		this.setBounds(100, 200, 600, 600);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setVisible(true);
		ba[2].add(matching);
		ba[4].add(mo);
		ba[4].add(no);
		ba[6].add(back);
		mo.addActionListener(this);
		no.addActionListener(this);
		back.addActionListener(this);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(mo)) {
			this.setVisible(false);
			good.menu(ree);
		}
		if (e.getSource().equals(no)) {
			this.setVisible(false);
			sad.menu(ree);
		} else if (e.getSource().equals(back)) {
			this.setVisible(false);
			Log a=new Log();
			a.menu();
		}
	}
}
