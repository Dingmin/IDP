package sso.server.imply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import sso.server.interfaces.DB;

@Repository
public class DB_Action implements DB<Object> {

	@Autowired
	JdbcTemplate jdbcTemplate;

	/*
	 * 批量操作
	 */
	@Override
	public boolean batchUpdate(String sql, List<Object[]> t) {
		// TODO Auto-generated method stub
		return jdbcTemplate.batchUpdate(sql, t).length == t.size() ? true
				: false;
	}

	@Override
	public boolean isMatched(String sql, Object[] t, Class<?> class1) {
		// TODO Auto-generated method stub

		int a = (Integer) jdbcTemplate.queryForObject(sql, class1, t);
		return a > 0 ? true : false;

	}

	/*
	 * use to insert / update / delete data
	 */
	@Override
	public boolean update(String sql, Object[] t) {
		// TODO Auto-generated method stub

		return jdbcTemplate.update(sql, t) > 0 ? true : false;
	}

	/*
	 * use to search data
	 */
	@Override
	public List<?> searchList(String sql, Object[] t, RowMapper<?> args) {
		// TODO Auto-generated method stub
		List<?> list = jdbcTemplate.query(sql, args, t);
		;
		return list;
	}

	public Integer getNewId(final String sql, final Object[] t) {
		return jdbcTemplate.execute(new ConnectionCallback<Integer>() {

			@Override
			public Integer doInConnection(Connection con) throws SQLException,
					DataAccessException {
				// TODO Auto-generated method stub
				PreparedStatement ps = con.prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS);
				for (int i = 0; i < t.length; i++) {
					System.out.println(t[i]);
					ps.setObject(i + 1, t[i]);
				}

				ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next())
					return rs.getInt(1);
				return 0;
			}
		});

	}

	@Override
	public Integer Count(String sql, Object[] t, Class<?> type) {
		// TODO Auto-generated method stub
		int a = (Integer) jdbcTemplate.queryForObject(sql, type, t);
		return a;
	}
}
