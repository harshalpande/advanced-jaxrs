package org.harshal.javabrains.jwt.model;

public class TestClass {

	public static void main(String[] args) {
		
		/*CRUDOperations dboperations = new CRUDOperations();
		
		KeyModel model = new KeyModel();*/
		
		/*model.setUser("harshal");
		model.setPassword("harshal123");
		Key key = KeyFunctions.getInstance().getKey();
		model.setKey(KeyFunctions.getInstance().getKeyInString(key));
		model.setToken("fjdsfjapnwei ufh7q324mucrfgc923yfg9vn8a3809CE qpHEPF8QYG4APFHEX7 3HEXFYEVR97HCEFNHG VOEALIFBC9WER3H29RCHAFEWMF83NUAY298CYF984F893HF98");
		model.setTtl(Timestamp.valueOf(LocalDateTime.now()));
		Session session = dboperations.getSession();
		session.beginTransaction();
		
		session.save(model);
		
		session.getTransaction().commit();
		session.close();*/
		
		/*Session session2 = dboperations.getSession(); 
		session2.beginTransaction();
		KeyModel model2 = (KeyModel) session2.get(KeyModel.class, "harshal");
		
		System.out.println(model2.getToken());
		System.out.println(model2.toString());*/
	}

}
