import java.io.File;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

abstract class User {
	String name,sName,userName;
	long password;
	
	Scanner input = new Scanner(System.in);
	
	enum Role{
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
	
	public void change(String userName,long password) {
				
		       System.out.println("Değiştirmek istediğiniz veriyi seçiniz.");
			   System.out.println("1) İsmi");
		       System.out.println("2) Soy ismi");
		       System.out.println("3) Kullanıcı ismi");
		       System.out.println("4) Şifresi");
		       	if(User.show(userName,password,role)=="Teacher") {
		       		System.out.println("5) Girdiği ders");
		       		System.out.println("6) Geri");
		       	}
		       		else
		       			System.out.println("5) Geri");
		       	
		       	int choice = input.nextInt();
		       	
		       	boolean check = true;
		        
		       	while(check){
		            
		          switch(choice){
		             
		          	 case 1:
		          		 System.out.println("Kullanıcının ismini giriniz.");
		          		 String name = input.nextLine();
		          		 User.change(userName,password,"name",newName);
		          		 check = false;
		          		 break;
		              
		          	 case 2:
		          		System.out.println("Kullanıcının soy ismini giriniz.");
		    			String newSName = input.nextLine();
		    			User.change(userName,password,"sName",newSName);
		                check = false;
		                break;
		          	 
		          	 case 3:
		          		System.out.println("Kullanıcının kullanıcı adını giriniz.");
		    			String newUserName = input.nextLine();
		    			User.change(userName,password,"userName",newUserName);
		                check = false;
		                break;
		          	 
		          	 case 4:
		          		System.out.println("Kullanıcının şifresini giriniz.");
		    			long newPassword = input.nextLong();
		    			String passwordString = Long.toString(newPassword);
		    			User.change(userName,password,"password",passwordString);
		                check = false;
		                break;
		                
		             case 5:
		            	 if(User.show(userName,password,role)=="Teacher") {
				          		System.out.println("Ders ismini giriniz.");
				    			String newSubject = input.nextLine();
				    			User.change(userName,password,"subject",newSubject);
		 		       	}
		 		       		else
		 		       			Admin.UI();
		              check = false;
		              break;
		              
		             case 6:
		            	 	Admin.UI();
			                check = false;
			                break;
		            
		             default:
		              System.out.println("Geçerli bir değer giriniz.");
		              System.out.println("Yapmak istediğin işlemi giriniz.");
		              choice = input.nextInt();
		          }
		       	

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
	 						
	 						if(user.show((userName,password,role)=="Teacher"))
	 							
	 							switch(target) {
	 						
	 							case "name":
	 								bw.write( splits[0] +";"+ splits[1] +";"+ newTarget +";"+ splits[3] +";"+ splits[4] +";"+ splits[5] "\n");
	 						}
	 						}
	 				}

	        	 }
	        }
	        
	        
			

		}
	
}
