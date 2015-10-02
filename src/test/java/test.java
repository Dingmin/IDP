import sso.server.utils.EncodeUrl;



public class test {

	public static void main(String[] args) throws Exception {
//		AssertionLink object=new AssertionLink();
//		String a = object.generateAssertion("122011025");
//		Verify v = new Verify();
//		v.verify(URLDecoder.decode(a, "utf-8"), new DateTime());
//		KeyPath k = KeyPath.getInstance();
//		String ax = EncodeUrl.encrypt("122011025"+"SSO_IDP"+"20150917-11:5920150918-11:59", k.getKey());
//		System.out.println(ax);
//		System.out.println(EncodeUrl.decrypt(ax, k.getKey()));
//	String[] x = "sdf*12".split("\\*");
//		System.out.println(x[0]+"\t"+x[1]);
		System.out.println(EncodeUrl.encrypt("dingding"));
	}
}
