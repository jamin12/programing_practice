package com.example.jdbc.repository;

import static com.example.jdbc.connection.DBConnectionUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;

import javax.sql.DataSource;

import org.springframework.jdbc.support.JdbcUtils;

import com.example.jdbc.domain.Member;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class MemberRepositoryV1 {
	private final DataSource dataSource;

	public Member save(Member member) throws SQLException {
		String sql = "insert into member (member_id, money) values (?,?)";
		Connection con = null;
		PreparedStatement pstmt = null;

		con = getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setInt(2, member.getMoney());
			pstmt.executeUpdate();
			return member;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(con, pstmt, null);
		}
	}

	public Member findById(String memberId) throws SQLException {
		String sql = "select * from member where member_id =?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		con = getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Member member = new Member();
				member.setMemberId(rs.getString("member_id"));
				member.setMoney(rs.getInt("money"));
				return member;
			} else {
				throw new NoSuchElementException("member not found memberId : " + memberId);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(con, pstmt, rs);
		}
	}

	public void update(String memberId, int money) throws SQLException {
		String sql = "update member set money =? where member_id =?";
		Connection con = null;
		PreparedStatement pstmt = null;

		con = getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, money);
			pstmt.setString(2, memberId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(con, pstmt, null);
		}
	}

	public void delete(String memberId) throws SQLException {
		String sql = "delete from member where member_id =?";
		Connection con = null;
		PreparedStatement pstmt = null;

		con = getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(con, pstmt, null);
		}
	}

	private void close(Connection con, Statement stmt, ResultSet rs) {
		JdbcUtils.closeResultSet(rs);
		JdbcUtils.closeConnection(con);
		JdbcUtils.closeStatement(stmt);
	}

	private Connection getConnection() throws SQLException {
		Connection con = dataSource.getConnection();
		log.info("get connection={}, class={}", con, con.getClass());
		return con;
	}
}