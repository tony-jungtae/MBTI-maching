package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Matchinggood.ReaSon;
import member.Member;

public class Dao {
	private Connection conn = null;
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String dd;

	public Dao() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		try {
			Class.forName(driver);
			 System.out.println("로드 성공");
		} catch (Exception e) {
			System.out.println("로드 실패");
		}	
	}

	public Connection getConnection() {
		try {
			conn = DriverManager.getConnection(url, "system", "1111");
			 System.out.println("연결성공");
			return conn;
		} catch (Exception e) {
			System.out.println("연결실패");
		}
		return null;
	}

	public ArrayList<ReaSon> search2(String mbti) {
		ArrayList<ReaSon> g = new ArrayList<>();
		if (getConnection() != null) {
			try {
				String sql = "SELECT * FROM mbti2  WHERE mbti = ?";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(1, mbti);
				ResultSet rs = psmt.executeQuery();		
				while (rs.next()) {
					ReaSon ho = new ReaSon();
					ho.setMbti(rs.getString("mbti"));
					ho.setLo(rs.getString("lo"));
					ho.setLo2(rs.getString("lo2"));
					ho.setLo3(rs.getString("lo3"));
					ho.setLo4(rs.getString("lo4"));
					g.add(ho);
				}
			} catch (Exception e) {

			}		
		}
		return g;
	}

	public ArrayList<ReaSon> search(String mbti) {
		ArrayList<ReaSon> g = new ArrayList<>();
		if (getConnection() != null) {
			try {
				String sql = "SELECT * FROM mbti  WHERE mbti = ?";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(1, mbti);
				ResultSet rs = psmt.executeQuery();		
				while (rs.next()) {
					ReaSon ho = new ReaSon();
					ho.setMbti(rs.getString("mbti"));
					ho.setLo(rs.getString("lo"));
					ho.setLo2(rs.getString("lo2"));
					ho.setLo3(rs.getString("lo3"));
					ho.setLo4(rs.getString("lo4"));
					g.add(ho);
				}
			} catch (Exception e) {

			}		
		}
		return g;
	}

	public void insert(Member m) {
		if (getConnection() != null) {
			try {
				String sql = "insert into dateGuide values (?,?,?,?,?,?)";
				PreparedStatement psmt = null;
				psmt = conn.prepareCall(sql);
				psmt.setString(1, m.getId());
				psmt.setString(2, m.getPw());
				psmt.setString(3, m.getAge());
				psmt.setString(4, m.getName());
				psmt.setString(5, m.getSex());
				psmt.setString(6, m.getMbti());
				// psmt.setString(7, m.getHobby());
				// psmt.setString(8, m.getFood());
				psmt.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public ArrayList<Member> selectAll() {
		ArrayList<Member> m = new ArrayList<>();
		if (getConnection() != null) {
			try {
				String sql = "select * from dateGuide";
				Statement stmt = null;
				stmt = conn.createStatement();
				ResultSet rs = null;
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					Member g = new Member();
					g.setSex(rs.getString("sex"));
					g.setAge(rs.getString("age"));
					g.setName(rs.getString("name"));
					g.setMbti(rs.getString("mbti"));
					// g.setFood(rs.getString("food"));
					// g.setHobby(rs.getString("hobby"));
					m.add(g);
				}
			} catch (Exception e) {
			}
		}
		return m;
	}

	public ArrayList<Member> selectAll2() {
		ArrayList<Member> m = new ArrayList<>();
		if (getConnection() != null) {
			try {
				String sql = "select * from dateGuide";
				Statement stmt = null;
				stmt = conn.createStatement();
				ResultSet rs = null;
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					Member g = new Member();
					g.setId(rs.getString("id"));
					g.setSex(rs.getString("sex"));
					g.setAge(rs.getString("age"));
					g.setName(rs.getString("name"));
					g.setMbti(rs.getString("mbti"));
					// g.setFood(rs.getString("food"));
					// g.setHobby(rs.getString("hobby"));
					m.add(g);
				}
			} catch (Exception e) {
			}
		}
		return m;
	}

	public ArrayList<Member> ck(String id, String pw) {
		if (getConnection() != null) {
			ArrayList<Member> m = new ArrayList<>();
			try {			
				String sql = "select * from dateGuide";
				Statement stmt = null;
				stmt = conn.createStatement();
				ResultSet rs = null;
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					Member g = new Member();
					g.setId(rs.getString("id"));
					g.setPw(rs.getString("pw"));
					g.setAge(rs.getString("age"));
					g.setMbti(rs.getString("mbti"));
					g.setSex(rs.getString("sex"));
					m.add(g);
				}
			} catch (Exception e) {
			}
			for (Member i : m) {
				if (i.getId().equals(id) && i.getPw().equals(pw)) {
					return m;
				}
			}
		}
		return null;
	}

	public Member Mbti(String id) {
		int a = 0;
		if (getConnection() != null) {
			Member g = new Member();
			try {
				ResultSet rs = null;
				String sql = "select mbti from dateGuide where id like '%'||?||'%'";
				PreparedStatement psmt = null;
				psmt = conn.prepareCall(sql);
				psmt.setString(1, id);
				psmt.executeUpdate();
				while (rs.next()) {
					Member ho = new Member();
					ho.setMbti(rs.getString("mbti"));
					return ho;
				}
			} catch (Exception e) {
			}
		}
		return null;
	}

	public ArrayList<Member> Mbtiinput(String mbti) {
		if (getConnection() != null) {
			ArrayList<Member> g = new ArrayList<>();
			try {
				String sql = "SELECT * FROM dateGuide WHERE mbti LIKE ?";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(1, mbti);
				ResultSet rs = psmt.executeQuery();
				while (rs.next()) {
					Member ho = new Member();
					ho.setSex(rs.getString("sex"));
					ho.setName(rs.getString("name"));
					ho.setAge(rs.getString("age"));
					ho.setMbti(rs.getString("mbti"));
					g.add(ho);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return g;
		}
		return null;
	}

	public boolean idck(String id) {
		int a = 0;
		if (getConnection() != null) {
			Member g = new Member();
			try {
				String sql = "select * from dateGuide where id like '%'||?||'%'";
				PreparedStatement psmt = null;
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, id);
				a = psmt.executeUpdate();
			} catch (Exception e) {
			}
		}
		if (a == 0) {
			return false;
		}
		return true;
	}
}