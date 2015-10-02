package sso.server.interfaces;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

public interface DB<T> {

	public boolean batchUpdate(String sql,List<Object[]> t);//批量插入，删除，更新
	public boolean isMatched(String sql,T[] t,Class<?> type);//check if has this message ,if did,true
	public Integer Count(String sql,T[] t,Class<?> type);//check if has this message ,if did,true
	public boolean update(String sql,T[] t);//for insert delete update
	public List<?> searchList(String sql,T[] t,RowMapper<?> args);//search a list of Object
}
