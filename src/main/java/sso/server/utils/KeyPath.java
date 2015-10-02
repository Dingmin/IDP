package sso.server.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.springframework.stereotype.Component;
@Component
public class KeyPath {

	private String key;
	private  static KeyPath keyPath=null;


	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	private KeyPath() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "KeyPath [key=" + key + "]";
	}

	public static KeyPath getInstance(){
		if(keyPath==null){
			keyPath = new KeyPath();
			keyPath = read(keyPath);
			System.out.println(keyPath);
		}
		return keyPath;
	}
	@SuppressWarnings("resource")
	public static KeyPath read(KeyPath keyPath){
		try {
			ObjectInputStream stream = new ObjectInputStream(
					new FileInputStream(new File("../webapps/sso_conf/share.key")));
			
			try {
				keyPath.setKey((String)stream.readObject());
				stream.close();
				return keyPath;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("读取对象出错");
				e.printStackTrace();
				return null;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("密钥文件找不到");
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("读取文件找不到");
			e.printStackTrace();
			return null;
		}
	}
//	public static void main(String[] args) {
//		KeyPath k =keyPath.getInstance();
//		System.out.println(k);
//	}
	}

