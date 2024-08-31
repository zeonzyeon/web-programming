package ch09;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class NewsDAO {
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

	public int addNews(News n) /* throws SQLException */ {
		open();
		int result = 0;
		String sql = "INSERT INTO news (title, img, date, content) VALUES (?, ?, CURRENT_TIMESTAMP(0),?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, n.getTitle());
			pstmt.setBlob(2, n.getImg());
			pstmt.setString(3, n.getContent());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	public List<News> getAll() /* throws SQLException */ {
		open();
		List<News> newsList = new ArrayList<>();
		String sql = "SELECT id, title, img, PARSEDATETIME(date, 'yyyy-MM-dd HH:mm:ss') as cdate FROM news";

		try {
			pstmt = conn.prepareStatement(sql);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					News n = new News();
					n.setId(rs.getInt("id"));
					n.setTitle(rs.getString("title"));
					n.setImg(rs.getBlob("img"));
					n.setDate(rs.getString("cdate"));
					newsList.add(n);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return newsList;
	}

	public News getNews(int id) /* throws SQLException */ {
		open();
		News n = new News();
		String sql = "SELECT id, title, img," 
		           + "PARSEDATETIME(date, 'yyyy-MM-dd HH:mm:ss') as cdate, content "
				   + "FROM news WHERE id =?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					n.setId(rs.getInt("id"));
					n.setTitle(rs.getString("title"));
					n.setImg(rs.getBlob("img"));
					n.setDate(rs.getString("cdate"));
					n.setContent(rs.getString("content"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	public void delNews(int id) /* throws SQLException */ {
		open();
		String sql = "DELETE FROM news WHERE id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			if (pstmt.executeUpdate() == 0)
				throw new RuntimeException("error");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public byte[] getImageById(int id) {
		open();
		byte[] imageBytes = null;
		ResultSet rs = null;
		String sql = "SELECT img FROM news WHERE id = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				Blob imageBlob = rs.getBlob("img");
				imageBytes = imageBlob.getBytes(1, (int) imageBlob.length());
				imageBlob.free();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return imageBytes;
	}

}
