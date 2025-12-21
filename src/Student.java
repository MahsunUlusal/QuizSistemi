import java.io.FileWriter;
import java.io.IOException;
class Student {
	
	String name,sName,userName;
	long password;
		
	public Student (String name, String sName, String userName, long password ){
		this.name = name;
		this.sName = sName;
		this.userName = userName;
		this.password = password;
		
		try {
		FileWriter printer = new FileWriter("users.txt", true);
		printer.write(userName +" "+ password +" "+ name +" "+ sName +" \n");
		printer.close();
		System.out.println("Öğrenci eklendi.\n");
		}
		catch (IOException e) {
			System.out.println("Öğrenci eklenemedi.\n");
		}
		
}

}