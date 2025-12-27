import java.util.Scanner;
import java.util.InputMismatchException;


public class Admin{
	
    static Scanner input = new Scanner(System.in);
    String name,sName,userName;
	long password;
	
    public Admin(String name,String sName,String userName,long password) throws Exception{
    	this.name = name;
		this.sName = sName;
		this.userName = userName;
		this.password = password;
		UI();
   }
   private static void UI() throws Exception{ //neden
         System.out.println("          Hoş geldiniz ");
         System.out.println("Yapmak istediğiniz işlemi seçiniz.\n\n");
         System.out.println("1) Kullanıcı ekle.");
         System.out.println("2) Kullanıcı çıkar.");
         System.out.println("3) Varolan kullanıcıyı düzenle.");
         System.out.println("4) Varolan kullanıcıyı görüntüle.");
         System.out.println("5) Çıkış yap.");
        
         int choice = input.nextInt();
         boolean check = true;
         while(check){
            
          switch(choice){
          
             case 1:
            	 addUser();
            	 check = false;
            	 break;
              
             case 2:
                 removeUser();
                 check = false;
                 break;
                 
             case 3:
                 changeUser();
                 check = false;
                 break;
                 
             case 4:
                 showUser();
                 check = false;
                 break;
              
             case 5:
            	 Main.logIO();
            	 check = false;
            	 break;
            
             default:
            	 System.out.println("Geçerli bir değer giriniz. (1 veya 2)");
            	 System.out.println("Yapmak istediğin işlemi giriniz.");
            	 choice = input.nextInt();
          }
         }       
   }
   private static void removeUser() throws Exception {
	   System.out.println("Silmek istediğiniz kullanıcının kullanıcı adını giriniz.");
	   String userName = input.nextLine();
	   System.out.println("Silmek istediğiniz kullanıcının şifresini giriniz.");//geri çıkma?
	   long password = input.nextLong();
	   
	   if(Main.isExist(userName,password)) {
		   System.out.println("Silmek istediğinize emin misiniz?");
		   System.out.println("1) Evet ileri     2) Hayır geri");
		   int choice = input.nextInt();
		   if(choice == 1)  User.remove(userName,password);
		   else if(choice == 2) Admin.UI();
		   else Admin.UI();
	   }
   }
   private static void showUser() {
	   System.out.println("Görüntülemek istediğiniz kullanıcının kullanıcı adını giriniz.");
	   String userName = input.nextLine();
	   System.out.println("Görüntülemek istediğiniz kullanıcının şifresini giriniz.");//geri çıkma?
	   long password = input.nextLong();
	   
	   if(Main.isExist(userName,password)) {
		   
		  System.out.println("Görüntülemek istediğiniz veriyi seçiniz.");
		  System.out.println("1) İsmi");
		  System.out.println("2) Soy ismi");
		  System.out.println("3) Kullanıcı ismi");
		  System.out.println("4) Şifresi");
       		if(User.show(userName,password,"role")=="Teacher") {
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
       				System.out.println(User.show(userName,password,"name"));
       				check = false;
       				break;
              
       			case 2:
          		 System.out.println(User.show(userName,password,"sName"));
          		 check = false;
          		 break;
          	 
       			case 3:
       				System.out.println(User.show(userName,password,"userName"));
       				check = false;
       				break;
          	 
       			case 4:
       				System.out.println(User.show(userName,password,"password"));
       				check = false;
       				break;
                
       			case 5:
       				if(User.show(userName,password,"role")=="Teacher") {
       					System.out.println(User.show(userName,password,"subject"));
       				}
 		       		else showUser();	
       				check = false;
       				break;
              
       			case 6:
            	 	showUser();
	                check = false;
	                break;
            
       			default:
       				System.out.println("Geçerli bir değer giriniz.");
       				System.out.println("Yapmak istediğin işlemi giriniz.");
       				choice = input.nextInt();
       			}
       	
       			
       		}
	   }
	   else System.out.println("Kullanıcı bulunamadı!");
   }
   private static void addUser() throws Exception{
       System.out.println("Hangi tür kullanıcı eklemek istersiniz?");
       System.out.println("1) Öğretmen");
       System.out.println("2) Öğrenci");
       System.out.println("3) Admin");
       System.out.println("4) Geri");
       int choice = input.nextInt();
      
       boolean check = true;
      
         while(check){
            
          switch(choice){
             case 1:
             
                  try{
                    System.out.println("Öğretmenin ismini giriniz.");
                    String name = input.nextLine();
                    System.out.println("Öğretmenin soy ismini giriniz.");
                    String sName = input.nextLine();
                    	if(!Main.isExist(name,sName)) {
                    		System.out.println("Öğretmenin dersini giriniz.");
                    		String subject = input.nextLine();
                    		String userName;
                    		while(true) {
                    			System.out.println("Öğretmenin kullanıcı adını giriniz.");
                    			userName = input.nextLine();
                    			if(Main.isExist(userName)) {
                    			System.out.println("Bu kullanıcı adı zaten alınmış! Başka bir kullanıcı adı deneyiniz.");
                    			}
                    			else
                    				break;
                    			}
                    		System.out.println("Öğretmenin şifresini giriniz. (Sadece sayılardan oluşabilir)");
                    		long password = input.nextLong();
                    		User.add(name,sName,userName,password,subject,User.Role.Teacher);
                    	}
                    	else {
                    		System.out.println("Öğretmen halihazırda mevcut.");
                    		check = false;
                    		break;
                    	}
                  }
                  catch (InputMismatchException e){
                      System.out.println("Lütfen geçerli bir veri tipi giriniz!");
                  }                
             
                  check = false;
                  break;
            
             case 2:

                 try{
                   System.out.println("Öğrencinin ismini giriniz.");
                   String name = input.nextLine();
                   System.out.println("Öğrencinin soy ismini giriniz.");
                   String sName = input.nextLine();
                   	if(!Main.isExist(name,sName)) {
                   		String userName;
                   		while(true) {
                			System.out.println("Öğrencinin kullanıcı adını giriniz.");
                			userName = input.nextLine();
                			if(Main.isExist(userName)) {
                			System.out.println("Bu kullanıcı adı zaten alınmış! Başka bir kullanıcı adı deneyiniz.");
                			}
                			else
                				break;
                			}
                   		System.out.println("Öğrencinin şifresini giriniz. (Sadece sayılardan oluşabilir)");
                   		long password = input.nextLong();
                   		User.add(name,sName,userName,password,User.Role.Student);
                   	}
                   	else {
                   		System.out.println("Öğrenci halihazırda mevcut.");
                   		check = false;
                   		break;
                   	}
                 }
                 catch (InputMismatchException e){
                     System.out.println("Lütfen geçerli bir veri tipi giriniz!");
                 }                
            
                 check = false;
                 break;
             
             case 3:

                 try{
                   System.out.println("Adminin ismini giriniz.");
                   String name = input.nextLine();
                   System.out.println("Adminin soy ismini giriniz.");
                   String sName = input.nextLine();
                   	if(!Main.isExist(name,sName)) {
                   		String userName;
                   		while(true) {
                			System.out.println("Öğrencinin kullanıcı adını giriniz.");
                			userName = input.nextLine();
                			if(Main.isExist(userName)) {
                			System.out.println("Bu kullanıcı adı zaten alınmış! Başka bir kullanıcı adı deneyiniz.");
                			}
                			else
                				break;
                			}
                   		System.out.println("Adminin şifresini giriniz. (Sadece sayılardan oluşabilir)");
                   		long password = input.nextLong();
                        User.add(name,sName,userName,password,User.Role.Admin);
                   	}
                   	else {
                   		System.out.println("Admin halihazırda mevcut.");
                   		check = false;
                   		break;
                   	}
                 }
                 catch (InputMismatchException e){
                     System.out.println("Lütfen geçerli bir veri tipi giriniz!");
                 }                
                 
                 check = false;
                 break;
           
             case 4: 
            	 UI();
            	 break;
            	 
            
             default:
            	 
              System.out.println("Geçerli bir değer giriniz. (1, 2 veya 3)");
              System.out.println("Yapmak istediğin işlemi giriniz.");
              choice = input.nextInt();
              
          }
         }
      
   }
   private static void changeUser() throws Exception { 
	  
	   System.out.println("Değiştirmek istediğiniz kullanıcının kullanıcı adını giriniz.");
	   String userName = input.nextLine();
	   System.out.println("Değiştirmek istediğiniz kullanıcının şifresini giriniz.");
	   long password = input.nextLong();
	   
	   if(Main.isExist(userName,password)) {
		   
		  System.out.println("Değiştirmek istediğiniz veriyi seçiniz.");
		  System.out.println("1) İsmi");
		  System.out.println("2) Soy ismi");
		  System.out.println("3) Kullanıcı ismi");
		  System.out.println("4) Şifresi");
       		if(User.show(userName,password,"role")=="Teacher") {
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
       				String newName = input.nextLine();
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
       				if(User.show(userName,password,"role")=="Teacher") {
       					System.out.println("Ders ismini giriniz.");
       					String newSubject = input.nextLine();
       					User.change(userName,password,"subject",newSubject);
       				}
 		       		else Admin.UI();	
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
	   else {
		   System.out.println("Kullanıcı bulunamadı!");
	   }
   }
   
}