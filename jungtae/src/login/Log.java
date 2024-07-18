package login;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Dao.Dao;
import Frame.Start;
import Matching.Matching;
import member.Member;

public class Log extends JFrame implements ActionListener {
	JLabel log = new JLabel("���̵� ��й�ȣ �Է�");
	JLabel IDIN = new JLabel("id");	JTextField idtf = new JTextField(15);
	JLabel PWIN = new JLabel("pw");	JTextField pwtf = new JTextField(15);
	JLabel idck = new JLabel("���̵� �������� �ʽ��ϴ�");
	JButton login = new JButton("�α����ϱ�");
	JButton back=new JButton("�ڷ�");
	JPanel centerp = new JPanel();
	JPanel[] ba = new JPanel[10];
	Matching matchingchoice = new Matching();
	Dao dao = new Dao();

	public void menu() {
		this.setLayout(new GridLayout(13, 1));
		for (int i = 0; i < ba.length; i++) {
			ba[i] = new JPanel();
			this.add(ba[i]);
		}
		ba[1].add(log);
		ba[3].add(IDIN);
		ba[3].add(idtf);
		ba[4].add(PWIN);
		ba[4].add(pwtf);
		ba[6].add(login);
		ba[6].add(back);
		this.setBounds(100, 100, 500, 500);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		idtf.addActionListener(this);
		login.addActionListener(this);
		pwtf.addActionListener(this);
		back.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String id = idtf.getText();
		String pw = pwtf.getText();
		if (e.getSource().equals(login)) {
			if (dao.ck(id, pw) != null) {
				this.setVisible(false);
				matchingchoice.menu(id);
			} else {
				this.setVisible(true);
				ba[5].add(idck);
			}
		}else if(e.getSource().equals(back)) {
			this.setVisible(false);
			Start a=new Start();
			a.menu();
		}
		}

	public Member idmbti(String id) {
		return dao.Mbti(id);
	}
}