import java.io.FileWriter;
import java.io.IOException;
class teacher {
	
	String name,sName,userName,subject;
	long password;
		
	public teacher(String name, String sName, String userName, long password, String subject) {
		this.name = name;
		this.sName = sName;
		this.userName = userName;
		this.subject = subject;
		this.password = password;
		
		try {
		FileWriter printer = new FileWriter("users.txt", true);
		printer.write(userName+" "+ password +" "+ subject +" "+ name +" "+ sName +" \n");
		printer.close();
		System.out.println("Öğretmen eklendi.\n");
		}
		catch (IOException e) {
			System.out.println("Öğretmen eklenemedi.\n");
		}
		
}

}
