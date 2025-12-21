import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.InputMismatchException;


public class Admin {
    String name,sName,userName;
    long password;
    Scanner input = new Scanner;
    public Admin(String name,String sName,String userName,long password){
        this.name = name;
        this.sName = name;
        this.userName = name;
        this.password = password;
       
         try{
             FileWriter printer = new FileWriter("users.txt",true);
             printer.write( userName +" "+ password +" "+ name +" "+ sName);
             System.out.println("Admin eklendi.\n");
         }
        catch(IOException e){
            System.out.println("Admin eklenemedi.\n");
        }
       
    }
     public void adminUI(){
         System.out.println("          Hoş geldiniz " + this.name + " " + this.sName);
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
              logOut();
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
                    	if(isExist(name,sName)) {
                    		System.out.println("Öğretmenin dersini giriniz.");
                    		String subject = input.nextLine();
                    		System.out.println("Öğretmenin kullanıcı ismini giriniz.");
                    		String userName = input.nextLine();
                    		System.out.println("Öğretmenin şifresini giriniz. (Sadece sayılardan oluşabilir)");
                    		int password = input.nextInt();
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
                   	if(isExist(name,sName)) {
                   		System.out.println("Öğrencinin kullanıcı ismini giriniz.");
                   		String userName = input.nextLine();
                   		System.out.println("Öğrencinin şifresini giriniz. (Sadece sayılardan oluşabilir)");
                   		int password = input.nextInt();
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
                   	if(isExist(name,sName)) {
                   		System.out.println("Adminin kullanıcı ismini giriniz.");
                   		String userName = input.nextLine();
                   		System.out.println("Adminin şifresini giriniz. (Sadece sayılardan oluşabilir)");
                   		int password = input.nextInt();
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
           
             
            
             default:
              System.out.println("Geçerli bir değer giriniz. (1, 2 veya 3)");
              System.out.println("Yapmak istediğin işlemi giriniz.");
              choice = input.nextInt();
          }
         }
      
   }
}