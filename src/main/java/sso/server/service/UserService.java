package sso.server.service;

import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sso.server.dao.Manager;
import sso.server.dao.SsoClient;
import sso.server.dao.User;
import sso.server.imply.UserImply;
import sso.server.utils.EncodeUrl;

@Service
public class UserService {
	@Autowired
	UserImply imply;
	
	@Autowired
	EncodeUrl encodeUrl;
	
	public boolean checkPhone(String phone){
		boolean result = imply.checkPhone(phone);
		return result;
	}
	
	public boolean checkEmail(String email){
		boolean result = imply.CheckEmail(email);
		return result;
	}
	public boolean checkToken(String token){
		boolean result = imply.checkToken(token);
		return result;
	}
	
	
	public boolean userRegister(User u) {
		boolean result = imply.userRegister(u);
		return result;
		}
	
	public boolean ClientRegister(SsoClient client) {
	boolean result = imply.ClientRegister(client);
	return result;
	}

	public boolean ManagerRegister(Manager manager) {
		boolean result = imply.ManagerRegister(manager);
		return result;
	}
	
	public User userLogin(String token, String password) {
		// TODO Auto-generated method stub
       User u = imply.userLogin(token, password);
       return u;
	}
	
	public SsoClient ClientLogin(String email, String password) {
		// TODO Auto-generated method stub
       SsoClient client = imply.ClientLogin(email, password);
       return client;
	}

	public Manager ManagerLogin(String phone, String password) {
		Manager manager = imply.ManagerLogin(phone, password);
		return manager;
	}
	
	/*
	 * url解密
	 */
	 public String[] decrypt(String urls)  
     {  
       try  
       {   
         String flag ="";
         java.util.StringTokenizer st=new java.util.StringTokenizer(URLDecoder.decode(urls,"utf-8"),"*");  
         while (st.hasMoreElements()) {  
           int asc =  Integer.parseInt((String)st.nextElement()) - 40;  
           flag = flag + (char)asc;  
         }  
 
         String[] result =  flag.split(";");
         return result;
       }catch(Exception e)  
       {  
         e.printStackTrace() ;  
         return null;  
       }  
     }  
	 
	 public Integer checkRoot(String[] urls){
		 Integer result = imply.match(urls);
		 return result;
		 
	 }
	 
	 public Integer checkSysIfUseSSO(String sys){
		 return null;
	 }

}
