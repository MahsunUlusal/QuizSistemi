import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.InputMismatchException;


public class Admin extends User{
    Scanner input = new Scanner(System.in);

    private Admin(String name,String sName,String userName,long password){
      super(name,sName,userName,password,Role.Admin); 
      User.add(name,sName,userName,password,Role.Admin);
    }
    
    public static void UI(){
         System.out.println("          Hoş geldiniz ");
         System.out.println("Yapmak istediğiniz işlemi seçiniz.\n\n");
         System.out.println("1) Kullanıcı ekle/çıkar.");
         System.out.println("2) Çıkış yap.");
        
         int choice = input.nextInt();
         boolean check = true;
         while(check){
            
          switch(choice){
          
             case 1:
              addUser();
              check = false;
              break;
              
             case 2:
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
    
   private void addUser(){
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
                    		System.out.println("Öğretmenin kullanıcı ismini giriniz.");
                    		String userName = input.nextLine();
                    		System.out.println("Öğretmenin şifresini giriniz. (Sadece sayılardan oluşabilir)");
                    		long password = input.nextLong();
                    		Teacher t = new Teacher(name,sName,userName,password,subject);
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
                   		System.out.println("Öğrencinin kullanıcı ismini giriniz.");
                   		String userName = input.nextLine();
                   		System.out.println("Öğrencinin şifresini giriniz. (Sadece sayılardan oluşabilir)");
                   		long password = input.nextLong();
                   		Student s = new Student(name,sName,userName,password);
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
                   		System.out.println("Adminin kullanıcı ismini giriniz.");
                   		String userName = input.nextLine();
                   		System.out.println("Adminin şifresini giriniz. (Sadece sayılardan oluşabilir)");
                   		long password = input.nextLong();
                   		Admin a = new Admin(name,sName,userName,password);
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
   public void changeUser(String userName,long password) {
		
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
}