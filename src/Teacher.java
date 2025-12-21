import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
class Teacher extends User{
	String subject;
	 
	Scanner input = new Scanner(System.in);
	
	public Teacher(String name, String sName, String userName, long password, String subject) {
		super(name,sName,userName,password,Role.Teacher);
		this.subject = subject;
		
		
		try {
		FileWriter printer = new FileWriter("users.txt", true);
		printer.write(userName+";"+ password +";"+ name +";"+ sName +";"+ subject +";"+ Role.Teacher +"\n");
		printer.close();
		System.out.println("Öğretmen eklendi.\n");
		}
		catch (IOException e) {
			System.out.println("Öğretmen eklenemedi.\n");
		}
		
}
	public static void UI() {
		 System.out.println("          Hoş geldiniz ");
         System.out.println("Yapmak istediğiniz işlemi seçiniz.\n\n");
         System.out.println("1) Quizleri görüntüle.");
         System.out.println("2) Varolan quizi düzenle.");
         System.out.println("3) Quiz oluştur.");
         System.out.println("4) Öğrenci puanlarını görüntüle.");
         System.out.println("5) Çıkış yap.");
        
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
                
                check = false;
                break;
          	 
          	 case 4:
                
                check = false;
                break;
                
             case 5:
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
