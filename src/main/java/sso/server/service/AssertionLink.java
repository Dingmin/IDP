package sso.server.service;

import java.net.URLEncoder;
import org.joda.time.DateTime;
import org.opensaml.saml2.core.Assertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sso.server.utils.EncodeUrl;
import sso.server.utils.GenerateSAMLObject;
import sso.server.utils.IDPConstants;
import sso.server.utils.KeyPath;

@Service
public class AssertionLink {

	@Autowired
	EncodeUrl encodeUrl;
	@Autowired
	GenerateSAMLObject object;
	@Autowired
	KeyPath keyPath;

	public String generateAssertion(String username) throws Exception {
		// ------
		object = new GenerateSAMLObject();
		// -------

		keyPath = KeyPath.getInstance();
		String encodeUserName = EncodeUrl.encrypt(username, keyPath.getKey());
		object.setUsername(encodeUserName);
		DateTime date1 = new DateTime();
		DateTime date2 = date1.toDateTime();
		date2 = date2.plusDays(1);
		System.out.println("date:" + date1.toString("yyyyMMdd-hh:mm")
				+ date2.toString("yyyyMMdd-hh:mm"));
		String md_string = EncodeUrl.encrypt(username
				+ IDPConstants.IDP_ENTITY_ID + date1.toString("yyyyMMdd-hh:mm")
				+ date2.toString("yyyyMMdd-hh:mm"), keyPath.getKey());
		System.out.println("---------");
		System.out.println("myoa:" + md_string);
		Assertion assertion = (Assertion) object.generate(encodeUserName,
				IDPConstants.IDP_ENTITY_ID, date1, date2, md_string);
		System.out.println("date create from server:"
				+ assertion.getConditions().getNotBefore()
						.toString("yyyyMMdd-hh:mm")
				+ assertion.getConditions().getNotOnOrAfter());
		String xmlString = object.generateToString(assertion);
		System.out.println("sso_idp generate assertion:" + xmlString);
		return URLEncoder.encode(xmlString, "utf-8");
	}

	public Assertion ParseStringToAssertion(String xmlString) throws Exception {
		// xmlString = encodeUrl.decrypt(xmlString);
		// -------
		GenerateSAMLObject object = new GenerateSAMLObject();
		// -------
		Assertion assertion = object.StringToAssertion(xmlString);
		return assertion;
	}
}
