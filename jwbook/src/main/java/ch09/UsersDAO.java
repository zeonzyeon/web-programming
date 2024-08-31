package ch09;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDAO {
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

	public int signup(Users u) {
		open();
		int result = 0;
		String sql = "INSERT INTO users (id, password, name, ts) values (?, ?, ?, CURRENT_TIMESTAMP(0))";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u.getId());
			pstmt.setString(2, u.getPassword());
			pstmt.setString(3, u.getName());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	public Users login(String id, String pw) {
		open();
		String sql = "SELECT * FROM users WHERE id = ? AND password = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					Users u = new Users();
					u.setId(rs.getString("id"));
					u.setName(rs.getString("name"));
					return u;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return null;
	}

	public boolean logout(String id, String pw) {
		open();
		String sql = "DELETE FROM users WHERE id = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return false;
	}

	public Users getInfo(String id) throws SQLException {
		open();
		Users u = new Users();
		String sql = "SELECT * FROM users WHERE id = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					u.setId(rs.getString("id"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return u;
	}

	/*public byte[] getImageById(String id) {
		open();
		byte[] imageBytes = null;
		ResultSet rs = null;
		String sql = "SELECT img FROM news WHERE id = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
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
	}*/
}
