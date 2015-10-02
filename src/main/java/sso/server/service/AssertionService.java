package sso.server.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import sso.server.dao.Doc_Assertion;
import sso.server.dao.SysConnected;
import sso.server.dao.SysRegister;
import sso.server.imply.DB_Action;
import sso.server.utils.AssertionSQL;
import sso.server.utils.Check_If_Service_SQL;

@Service
public class AssertionService {

	@Autowired
	DB_Action action;

	// 保存session
	public boolean SaveAssertion(String assertion,String jsessionid, Integer Sys, Date date) {
		boolean flag = action.update(AssertionSQL.ADD_ASSERTION, new Object[] {
				jsessionid,Sys,assertion,date });
		System.out.println("保存assertion:"+flag);
		return flag;
	}

	// 删除assertion
	public boolean dropAssertion(Integer sid, String cookieSys) {
		// TODO Auto-generated method stub
		boolean flag = action.update(AssertionSQL.DEL_ASSERTION,
				new Object[] { sid });
		return flag;
	}

	// 找到对应的assertion
	public String findAssertion(String ssoSys, String jsessionid) {
		// TODO Auto-generated method stub
		List<Doc_Assertion> list = search_assertionList(jsessionid,ssoSys);		
		System.out.println("当前的jsessionid:"+jsessionid);
		System.out.println("assertion list"+list);
		if (list != null && list.size() > 0)
			return list.get(0).getAssertion();
		return null;
	}

	private List<Doc_Assertion> search_assertionList(String jsessionid,
			String ssoSys) {
		// TODO Auto-generated method stub
		RowMapper<Doc_Assertion> mapper = new BeanPropertyRowMapper<Doc_Assertion>(
				Doc_Assertion.class);
		@SuppressWarnings("unchecked")
		List<Doc_Assertion> list = (List<Doc_Assertion>) action.searchList(
				AssertionSQL.SEARCH_ASSERTION, new Object[] {
						jsessionid , ssoSys}, mapper);
		return list;
	}

	public String FindRelativeSys(Integer flag, String ssoSys, String jsessionid) {
		// TODO Auto-generated method stub
		List<SysConnected> list = findBeBelievedSys(flag);
	System.out.println("信任"+flag+"的系统由有"+list);
		boolean a = false;
		if (list != null && list.size() > 0) {
			for (SysConnected c : list) {
				if (c.getFlag() == 1) {
					a = confirm(c.getSid_2(), ssoSys);
					if (a){
						break;
						}

				}
				a = confirm(c.getSid_1(), ssoSys);
				if (a){
					break;
					}

			}
		}
		if (a) {
			System.out.println("找到信任系统的assertion");
			String assertion = findAssertion(ssoSys, jsessionid);
			upateAssertion(flag,ssoSys,jsessionid);
			return assertion;
		}
		System.out.println("找不到信任系统的assertion");
		return null;
	}



	public boolean upateAssertion(Integer flag, String ssoSys, String jsessionid) {
		// TODO Auto-generated method stub
		boolean result = action.update(AssertionSQL.UPDATE_SYS, new Object[]{"*"+flag,ssoSys,jsessionid});
		return result;
	}

	// 查找被信任的系统
	private List<SysConnected> findBeBelievedSys(Integer flag) {
		// TODO Auto-generated method stub
		RowMapper<SysConnected> mapper = new BeanPropertyRowMapper<SysConnected>(
				SysConnected.class);
		@SuppressWarnings("unchecked")
		List<SysConnected> list = (List<SysConnected>) action.searchList(
				AssertionSQL.SEARCH_BeBelievedSys, new Object[] { flag, flag,
						flag, flag }, mapper);
		return list;
	}

	// 确定是否已登录被信任系统
	private boolean confirm(Integer sid, String ssoSys) {
		// TODO Auto-generated method stub
		if (ssoSys.startsWith("" + sid) || ssoSys.endsWith("*" + sid)
				|| ssoSys.contains("*" + sid + "*"))
			return true;
		return false;
	}

	/*
	 * 处理单点登录流程
	 * 返回更新后的cookie值ssosys
	 */
	public String dropAssertion(Integer sid, String ssoSys, String jsessionid) {
		// TODO Auto-generated method stub
		//找到被信任的系统
		List<SysConnected> list = findBeBelievedSys(sid);
		ArrayList<Integer> conne_list = new ArrayList<Integer>();
		for(SysConnected c:list){
			if(!list.contains(c.getSid_1()))
				conne_list.add(c.getSid_1());
			if(!list.contains(c.getSid_2()))
				conne_list.add(c.getSid_2());
		}
		//找到assertion表中符合的数据
		List<Doc_Assertion> assertion_list = search_assertionList(jsessionid, ssoSys);
		//一次删除相对应的系统
		String[] target = assertion_list.get(0).getSys().split("*");
		String result = "";
		for(String a:target)
		{
			if(!list.contains(Integer.valueOf(a)))
				result =result+"*"+a;
		}
		result = result.substring(1);
		updateAssertion(ssoSys,jsessionid);
		return result;
	}

	private void updateAssertion(String ssoSys, String jsessionid) {
		// TODO Auto-generated method stub
		boolean result = action.update(AssertionSQL.UPDATE_SYS_LOGOUT, new Object[]{ssoSys,jsessionid});
		if(result)
			System.out.println("删除成功");
		
	}

	public boolean dropAssertion(String ssoSys, String jsessionid) {
		// TODO Auto-generated method stub
		System.out.println("删除assertion");
		boolean flag = action.update(AssertionSQL.DEL_ASSERTION, new Object[]{ssoSys,jsessionid});
		return flag;
	}

	public SysRegister findRegister(String url) {
		// TODO Auto-generated method stub
		RowMapper<SysRegister> mapper = new BeanPropertyRowMapper<SysRegister>(
				SysRegister.class);
		@SuppressWarnings("unchecked")
		List<SysRegister> list = (List<SysRegister>) action.searchList(
				Check_If_Service_SQL.CHECK_URL,
				new Object[] {url }, mapper);
		if(list!=null&&list.size()>0)
			return list.get(0);
		return null;
	}

	public boolean upateAssertion(String result, String ssoSys,
			String jsessionid) {
		// TODO Auto-generated method stub
		boolean flag = action.update(AssertionSQL.UPDATE_SYS_LOGOUT, new Object[]{result,ssoSys,jsessionid});
		return flag;
	}

	public String DelRelativeSys(Integer sid, String ssoSys) {
		// TODO Auto-generated method stub
		String result = "";
		ArrayList<Integer> relative_List = new ArrayList<Integer>();
		//找到被信任的系统，取消他们的登录状态
		List<SysConnected> list = findBeBelievedSys(sid);
		for(SysConnected s:list){
			if(!relative_List.contains(s.getSid_1()))
				relative_List.add(s.getSid_1());
			if(!relative_List.contains(s.getSid_2()))
				relative_List.add(s.getSid_2());
		}
		String[] x = ssoSys.split("\\*");
		for(String i:x){
			if(!relative_List.contains(Integer.valueOf(i)))
				result=result+"*"+i;
		}
		
		if(result.length()>0)
			result = result.substring(1);
		
		return result;
	}

}
