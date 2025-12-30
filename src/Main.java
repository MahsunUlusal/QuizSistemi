import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws Exception {
		logIO();
	}
	
	public static void logIO() {  // neden throws eklemem lazım?
		try {
			String userName;
			long password;
			Scanner input = new Scanner(System.in);
			System.out.println("Kullanıcı adınızı giriniz.");
			userName = input.nextLine();
			System.out.println("Şifrenizi giriniz.");
			String passwordStr = input.nextLine().trim();
			password = Long.parseLong(passwordStr);
			if(isExist(userName,password)) {
				String name = User.show(userName,password,"name");
				String sName = User.show(userName,password,"sName");        	
				String role = User.show(userName,password,"role");
				
				switch(role){
					
				case "Teacher":
					String subject = User.show(userName,password,"subject");
					Teacher teacher = new Teacher(name,sName,userName,password,subject);
					break;
					
				case "Student":
					Student student = new Student(name,sName,userName,password);
					break;
					
				case "Admin":
					Admin admin = new Admin(name,sName,userName,password);
					break;
					
				default:
					System.out.println("Kullanıcı rolü bulunamadı! Teknik ekip ile görüşünüz.");
					logIO();
					break;
				}
				
			}
			else {
				System.out.println("\n\nKullanıcı bulunamadı! Kullanıcı adınızı ya da şifreniz yanlış olablir.\n\n");
				logIO();
			}
		} catch (Exception e) {
			System.out.println("Hata!");
			logIO();
		}
	}
	
	public static boolean isExist(String name,String sName){
		
		try {	
			
			File users = new File("users.txt");
			Scanner fileInput = new Scanner(users);
		
			while(fileInput.hasNextLine()) {
				String line = fileInput.nextLine();
				String[] splits = line.split(";");
			
				if(splits.length>=4) {
					if(splits[2].equals(name) && splits[3].equals(sName)) {
						fileInput.close();
						return true;
					}
				}
			}
		
			fileInput.close();
			return false;
		
		}
		catch (FileNotFoundException e) {
			return false;
		}
	}
	
	public static boolean isExist(String userName,long password) {
		
		try {
			
			File users = new File("users.txt");
			Scanner fileInput = new Scanner(users);
	
			while(fileInput.hasNextLine()) {
				String line = fileInput.nextLine();
				String[] splits = line.split(";");
		
				String passwordString = Long.toString(password);
		
				if(splits.length>=4) {
					if(splits[0].equals(userName) && splits[1].equals(passwordString)) {
						fileInput.close();
						return true;
						}
				}
			}

			fileInput.close();
			return false;
		
		}
		catch (FileNotFoundException e) {
			return false;
		}
	}
	
	public static boolean isExist(String userName) {
		
		try {
			
			File users = new File("users.txt");
			Scanner fileInput = new Scanner(users);
	
			while(fileInput.hasNextLine()) {
				String line = fileInput.nextLine();
				String[] splits = line.split(";");

				if(splits.length>=4) {
					if(splits[0].equals(userName)) {
						fileInput.close();
						return true;
						}
				}
			}

			fileInput.close();
			return false;
		
		}
		catch (FileNotFoundException e) {
			return false;
		}
	}
}
