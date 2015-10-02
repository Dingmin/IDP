package sso.server.service;

import java.util.List;

import org.joda.time.DateTime;
import org.opensaml.saml2.core.Assertion;
import org.opensaml.saml2.core.Attribute;
import org.opensaml.saml2.core.AttributeStatement;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.schema.XSString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sso.server.utils.EncodeUrl;
import sso.server.utils.KeyPath;

/*
 * use to verify if assertion is effective
 */
@Service
public class Verify {

	@Autowired
	AssertionLink link; 
	
	@Autowired 
	EncodeUrl encodeUrl;
	
	@Autowired
	KeyPath keyPath;
	
	public boolean verify(String asser, DateTime date) throws Exception{
	
		System.out.println("server recieve assertion:"+asser);
		Assertion a = null;
			a = link.ParseStringToAssertion(asser);
		keyPath = KeyPath.getInstance();
		String issuer=a.getIssuer().getValue();
    	System.out.println("issuer:"+issuer);
    	AttributeStatement attr = a.getAttributeStatements().get(0);
    	List<Attribute> atribu = attr.getAttributes();
    	System.out.println(atribu.size());
    	Attribute ass = attr.getAttributes().get(0);
    	List<XMLObject> list = ass.getAttributeValues();
    	System.out.println(list.size());
    	XSString username= (XSString) ass.getAttributeValues().get(0);
    	System.out.println(username.getValue());
    	String user = EncodeUrl.decrypt(username.getValue(), keyPath.getKey());
    	Attribute ass1 = attr.getAttributes().get(1);
    	XSString md= (XSString) ass1.getAttributeValues().get(0);
    	System.out.println("username:"+username.getValue());
    	System.out.println("m:"+md.getValue());
    	DateTime c1 = a.getConditions().getNotBefore();
    	DateTime c2 = a.getConditions().getNotOnOrAfter();
    	
    	System.out.println(user+"\t"+issuer+"\t"+c1.toString("yyyyMMdd-hh:mm")+"\t"+c2.toString("yyyyMMdd-hh:mm"));
    	 String destring =EncodeUrl.encrypt(user+issuer+c1.toString("yyyyMMdd-hh:mm")+c2.toString("yyyyMMdd-hh:mm"),keyPath.getKey());
    	 System.out.println("de:"+destring);
    	 if(destring.equals(md.getValue()))
    		 {
    		 System.out.println("right!!");
    		 return true;
    		 }
    	 System.out.println("the assertion is wrong");
		
		return false;
	}

}
