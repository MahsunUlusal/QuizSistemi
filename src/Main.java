//Bilgilerimi görüntüle eklenebilir
//Her adımda geri gitme ekle

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) {
	
	}
	
	public static void logIO() throws FileNotFoundException {  // neden throws eklemem lazım?
		String userName;
		long password;
		Scanner input = new Scanner(System.in);
        System.out.println("Kullanıcı adınızı giriniz.");
        userName = input.nextLine();
        System.out.println("Şifrenizi giriniz.");
        password = input.nextLong();
        
        if(isExist(userName,password)) {
        	
        	String role = whatRole(userName,password);
        	
        	switch(role){
        		
        	case "Teacher":
        		Teacher.UI();
        		break;
        		
        	case "Student":
        		Student.UI();
        		break;
        		
        	case "Admin":
        		Admin.UI();
        		break;
        		
        	default:
        		System.out.println("Kullanıcı rolü bulunamadı! Teknik ekip ile görüşünüz.");
        		logIO();
        		break;
        	}
        	
        }
        else {
        	System.out.println("\n\nKullanıcı bulunamadı! Kullanıcı adınızı ya da şifreniz yanlış olablir.\n\n");
        	input.close();
        	logIO();
        }
	}
	
	private static String whatRole(String userName, long password){
		
		try {
			
			File users = new File("users.txt");
			Scanner fileInput = new Scanner(users);
		
			while(fileInput.hasNextLine()) {
				String line = fileInput.nextLine();
				String[] splits = line.split(";");
			
				String passwordString = Long.toString(password);
			
				if(splits.length>=5) {
					int n = splits.length;
					if(splits[0].equals(userName) && splits[1].equals(passwordString)) {
						fileInput.close();
						return splits[n-1];
						}
				}
			}
			fileInput.close();
			return null;
			
		}
		catch (FileNotFoundException e) {
			return null;
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
	
}
