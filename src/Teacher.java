import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Teacher{
	
	String name,sName,userName,subject;
	long password;
	static Scanner input = new Scanner(System.in);
	
	public Teacher(String name, String sName, String userName, long password, String subject) throws Exception {
		this.name = name;
		this.sName = sName;
		this.userName = userName;
		this.password = password;
		this.subject = subject;
		UI();
		}
	
	private void UI() throws Exception {
		
		 System.out.println("          Hoş geldiniz ");
         System.out.println("Yapmak istediğiniz işlemi seçiniz.\n\n");
         System.out.println("1) Quizleri görüntüle.");
         System.out.println("2) Quiz oluştur.");
         System.out.println("3) Öğrenci puanlarını görüntüle.");
         System.out.println("4) Çıkış yap.");
        
         int choice = input.nextInt();
         
         boolean check = true;
         while(check){
            
          switch(choice){
             
          	 case 1:
              
              check = false;
              break;
              
          	 case 2:
          		System.out.println("Quizin ismini giriniz.");
         		String quizName = input.nextLine();
         		 Quiz quiz = new Quiz (quizName,subject,userName);
                check = false;
                break;
          	 
          	 case 3:
                
                check = false;
                break;
          	 
          	 case 4:
          		 Main.logIO();
                check = false;
                break;
                
             default:
              System.out.println("Geçerli bir değer giriniz. (1, 2, 3, 4 veya 5)");
              System.out.println("Yapmak istediğin işlemi giriniz.");
              choice = input.nextInt();
          }
         }      
	}
	
	
}
