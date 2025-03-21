package com.example.jdbc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;

import com.example.jdbc.domain.Member;
import com.example.jdbc.repository.ex.MyDbException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 트랜잭션 - 트랜잭션 매니저
 * DataSourceUtils.getConnection()
 * DataSourceUtils.releaseConnection()
 */
@Slf4j
public class MemberRepositoryV4_2 implements MemberRepository {
	private final DataSource dataSource;
	private final SQLExceptionTranslator sqlExceptionTranslator;

	public MemberRepositoryV4_2(DataSource dataSource) {
		this.dataSource = dataSource;
		this.sqlExceptionTranslator = new SQLErrorCodeSQLExceptionTranslator(dataSource);
	}

	@Override
	public Member save(Member member) {
		String sql = "insert into member (member_id, money) values (?,?)";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setInt(2, member.getMoney());
			pstmt.executeUpdate();
			return member;
		} catch (SQLException e) {
			throw sqlExceptionTranslator.translate("save", sql, e);
		} finally {
			close(con, pstmt, null);
		}
	}

	@Override
	public Member findById(String memberId) {
		String sql = "select * from member where member_id =?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
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
			throw sqlExceptionTranslator.translate("findById", sql, e);
		} finally {
			close(con, pstmt, rs);
		}
	}

	@Override
	public void update(String memberId, int money) {
		String sql = "update member set money =? where member_id =?";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, money);
			pstmt.setString(2, memberId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw sqlExceptionTranslator.translate("update", sql, e);
		} finally {
			close(con, pstmt, null);
		}
	}

	@Override
	public void delete(String memberId) {
		String sql = "delete from member where member_id =?";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw sqlExceptionTranslator.translate("delete", sql, e);
		} finally {
			close(con, pstmt, null);
		}
	}

	private void close(Connection con, Statement stmt, ResultSet rs) {
		JdbcUtils.closeResultSet(rs);
		//주의! 트랜잭션 동기화를 사용하려면 DataSourceUtils를 사용해야 한다.
		DataSourceUtils.releaseConnection(con, dataSource);
		JdbcUtils.closeStatement(stmt);
	}

	private Connection getConnection() throws SQLException {
		//주의! 트랜잭션 동기화를 사용하려면 DataSourceUtils를 사용해야 한다.
		Connection con = DataSourceUtils.getConnection(dataSource);
		log.info("get connection={}, class={}", con, con.getClass());
		return con;
	}
}