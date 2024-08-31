package ch09;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FeedsDAO {
	Connection conn = null;
	PreparedStatement pstmt;

	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/jwbookdb";

	public void open() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, "jwbooks", "1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int write(Feeds f, String id) {
		open();
		int result = 0;
		String sql = "INSERT INTO feeds (id, img, content, ts) values (?, ?, ?, CURRENT_TIMESTAMP(0))";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id); // 세션 아이디에서 얻은 게시자 아이디
			pstmt.setString(2, f.getImg());
			pstmt.setString(3, f.getContent());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	public List<Feeds> getAll()  {
		open();
		List<Feeds> feedList = new ArrayList<>();
		String sql = "SELECT id, img, content, PARSEDATETIME(ts, 'yyyy-MM-dd HH:mm:ss') as cdate FROM feeds";

		try {
			pstmt = conn.prepareStatement(sql);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					Feeds f = new Feeds();
					f.setId(rs.getString("id"));
					f.setImg(rs.getString("img"));
					f.setContent(rs.getString("content"));
					f.setTs(rs.getString("cdate"));
					feedList.add(f);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return feedList;
	}

	public void delNews(int aid) {
		open();
		String sql = "DELETE FROM feeds WHERE aid = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, aid);
			if (pstmt.executeUpdate() == 0)
				throw new RuntimeException("error");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
}
