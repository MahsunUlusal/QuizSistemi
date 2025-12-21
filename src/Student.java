import java.io.FileWriter;
import java.io.IOException;
class Student extends User{
	
	String name,sName,userName;
	long password;
		
	public Student (String name, String sName, String userName, long password ){
		super(name,sName,userName,password,Role.Student);
		
		try {
		FileWriter printer = new FileWriter("users.txt", true);
		printer.write(userName +";"+ password +";"+ name +";"+ sName +";"+ Role.Student + "\n");
		printer.close();
		System.out.println("Öğrenci eklendi.\n");
		}
		catch (IOException e) {
			System.out.println("Öğrenci eklenemedi.\n");
		}
		
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