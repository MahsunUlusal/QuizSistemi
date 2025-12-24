import java.io.File;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

abstract class User implements FileOp{
	String name,sName,userName;
	long password;
	
	Scanner input = new Scanner(System.in);
	
	public enum Role{
		Admin,Teacher,Student
	}
	
	Role role;
	
	public User (String name, String sName, String userName, long password, Role role){
		this.name = name;
		this.sName = sName;
		this.userName = userName;
		this.password = password;
		this.role = role;
	}
	public static void UI() {
	}
	
	public static void add(String name, String sName, String userName, long password, Role role ) {
		
		try{
            FileWriter printer = new FileWriter("users.txt",true);
            printer.write( userName +";"+ password +";"+ name +";"+ sName +";"+ role +"\n");
            System.out.println(role + " eklendi.\n");
            printer.close();
        }
       catch(IOException e){
           System.out.println(role + " eklenemedi.\n");
       }
		
	}
	
	//overload
	public static void add(String name, String sName, String userName, long password, String subject, Role role ) {
		
		try{
            FileWriter printer = new FileWriter("users.txt",true);
            printer.write( userName +";"+ password +";"+ name +";"+ sName +";"+ subject +";"+ role +"\n");
            System.out.println(role + " eklendi.\n");
            printer.close();
        }
       catch(IOException e){
           System.out.println(role + " eklenemedi.\n");
       }
		
	}
	
	
		public static void change(String userName, long password, String target, String newTarget ) {
			
			File users = new File("Users.txt");
	        File newUsers = new File("temp.txt");
			
	        try (BufferedReader br = new BufferedReader(new FileReader(users)); BufferedWriter bw = new BufferedWriter(new FileWriter(newUsers))){
	        	String line;
	        	String passwordString = Long.toString(password);
	        	
	        	 while ((line = br.readLine()) != null) {
	        		 String[] splits = line.split(";");
	        		 
	        		 if(splits.length >=5) {
	        			 
	 					if(splits[0].equals(userName) && splits[1].equals(passwordString)) {
	 						
	 						if(User.show(userName,password,"role")=="Teacher") {
	 							
	 							switch(target) {
	 						
	 							case "name":
	 								bw.write( splits[0] +";"+ splits[1] +";"+ newTarget +";"+ splits[3] +";"+ splits[4] +";"+ splits[5] +"\n");
	 								System.out.println("Başarıyla değiştirildi.");
	 								break;
	 							
	 							case "sName":
	 								bw.write( splits[0] +";"+ splits[1] +";"+ splits[2] +";"+ newTarget +";"+ splits[4] +";"+ splits[5] +"\n");
	 								System.out.println("Başarıyla değiştirildi.");
	 								break;
	 								
	 							case "userName":
	 								bw.write( newTarget +";"+ splits[1] +";"+ splits[2] +";"+ splits[3] +";"+ splits[4] +";"+ splits[5] +"\n");
	 								System.out.println("Başarıyla değiştirildi.");
	 								break;
	 								
	 							case "password":
	 								bw.write(splits[0] +";"+ newTarget +";"+ splits[2] +";"+ splits[3] +";"+ splits[4] +";"+ splits[5] +"\n");
	 								System.out.println("Başarıyla değiştirildi.");
	 								break;
	 								
	 							case "subject":
	 								bw.write(splits[0] +";"+ splits[1]  +";"+ splits[2] +";"+ splits[3] +";"+ newTarget +";"+ splits[5] +"\n");
	 								System.out.println("Başarıyla değiştirildi.");
	 								break;
	 								
	 							}
	 							bw.newLine();
	 						}
	 						else {
	 							switch(target) {
		 						
	 							case "name":
	 								bw.write( splits[0] +";"+ splits[1] +";"+ newTarget +";"+ splits[3] +";"+ splits[4] +"\n");
	 								System.out.println("Başarıyla değiştirildi.");
	 								break;
	 							
	 							case "sName":
	 								bw.write( splits[0] +";"+ splits[1] +";"+ splits[2] +";"+ newTarget +";"+ splits[4] +"\n");
	 								System.out.println("Başarıyla değiştirildi.");
	 								break;
	 								
	 							case "userName":
	 								bw.write( newTarget +";"+ splits[1] +";"+ splits[2] +";"+ splits[3] +";"+ splits[4] +"\n");
	 								System.out.println("Başarıyla değiştirildi.");
	 								break;
	 								
	 							case "password":
	 								bw.write(splits[0] +";"+ newTarget +";"+ splits[2] +";"+ splits[3] +";"+ splits[4] +"\n");
	 								System.out.println("Başarıyla değiştirildi.");
	 								break;
	 								
	 							}
	 							bw.newLine();
	 						}		
	 				}

	 					else {
	 						if(User.show(userName,password,"role")=="Teacher"){
	 							bw.write(splits[0] +";"+ splits[1]  +";"+ splits[2] +";"+ splits[3] +";"+ splits[4] +";"+ splits[5] +"\n");
	 							bw.newLine();
	 						}
	 						else {
	 							bw.write(splits[0] +";"+ splits[1] +";"+ splits[2] +";"+ splits[3] +";"+ splits[4] +"\n");
	 							bw.newLine();
	 						}
	 					}
	        	 }
	        }   	 
		}
	        catch (IOException e) {
            System.out.println("Hata: " + e.getMessage());
	        }
	        users.delete();
	        newUsers.renameTo(users);
	}
		
		public static String show (String userName, long password, String target) {
			
			try {
				File users = new File("users.txt");
				Scanner fileInput = new Scanner(users);
		
				while(fileInput.hasNextLine()) {
					String line = fileInput.nextLine();
					String[] splits = line.split(";");
			
					String passwordString = Long.toString(password);
			
					if(splits.length==5 || splits.length==6) {
						if(splits[0].equals(userName) && splits[1].equals(passwordString)) {
							
							switch(target) {
							
							case "subject":
								if(splits.length==6) {
									fileInput.close();
									return splits[4];
								}
								else {
									System.out.println("Kullanıcı öğretmen değil!");
									fileInput.close();
									return null;
								}
				
							case "name":
								fileInput.close();
								return splits[2];
							
							case "sName":
								fileInput.close();
								return splits[3];
								
							case "role":
								if(splits.length==5) {
									fileInput.close();
									return splits[4];
								}
								else {
									fileInput.close();
									return splits[5];
								}
								
							default:
								System.out.println("Hata!");
								fileInput.close();
								return null;
							}
							}
					}
				}
			}
			catch (FileNotFoundException e) {
				return null;
			}
			System.out.println("Hata!");
			return null;
		}
		
	public static void remove(String userName,long password) {
		
		File users = new File("Users.txt");
        File newUsers = new File("temp.txt");
		
        try (BufferedReader br = new BufferedReader(new FileReader(users)); BufferedWriter bw = new BufferedWriter(new FileWriter(newUsers))){
        	String line;
        	String passwordString = Long.toString(password);
        	
        	 while ((line = br.readLine()) != null) {
        		 String[] splits = line.split(";");
        		 
        		 if(splits.length >=5) {
        			 
 					if(splits[0].equals(userName) && splits[1].equals(passwordString)) {
 						bw.newLine();
 						continue;
 						}

 					else {
 						if(User.show(userName,password,"role")=="Teacher"){
 							bw.write(splits[0] +";"+ splits[1]  +";"+ splits[2] +";"+ splits[3] +";"+ splits[4] +";"+ splits[5] +"\n");
 							bw.newLine();
 						}
 						else {
 							bw.write(splits[0] +";"+ splits[1] +";"+ splits[2] +";"+ splits[3] +";"+ splits[4] +"\n");
 							bw.newLine();
 						}
 					}
        	 }
        }   	 
	}
        catch (IOException e) {
        System.out.println("Hata: " + e.getMessage());
        }
        users.delete();
        newUsers.renameTo(users);	
	}
	
		
}
