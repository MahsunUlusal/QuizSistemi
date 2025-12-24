import java.io.FileWriter;
import java.io.IOException;

import User.Role;
class Student extends User{
	
	String name,sName,userName;
	long password;
		
	public Student (String name, String sName, String userName, long password ){
		super(name,sName,userName,password,Role.Student);
		User.add(name,sName,userName,password,Role.Student);
		
}
	public static void UI() {
		
	}
		 System.out.println("          Hoş geldiniz ");
         System.out.println("Yapmak istediğiniz işlemi seçiniz.\n\n");
         System.out.println("1) Quizlerimi görüntüle.");
         System.out.println("2) Quize gir.");
         System.out.println("3) Çıkış yap.");
        
         int choice = input.nextInt();
         boolean check = true;
         
         while(check){
            
          switch(choice){
             
          	 case 1:
              
              check = false;
              break;
              
          	 case 2:
                
                check = false;
                break;
          	 
             case 3:
              Main.logIO();
              check = false;
              break;
            
             default:
              System.out.println("Geçerli bir değer giriniz. (1, 2 veya 3)");
              System.out.println("Yapmak istediğin işlemi giriniz.");
              choice = input.nextInt();
	}

}