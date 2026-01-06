package Users;
import java.util.InputMismatchException;
import java.util.Scanner;
import FileOp.QuizOp;
import FileOp.UserOp;
import Main.Main;


public class Admin implements User{
	
    static Scanner input = new Scanner(System.in);
    private String name,sName,userName;
	private long password;
	QuizOp quizOp = new QuizOp();
	UserOp userOp = new UserOp();
	
	// getter ve setter methodları
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public long getPassword() {
		return password;
	}
	public void setPassword(long password) {
		this.password = password;
	}
	
	/**
	 * Admin nesnesi oluşturmaya yarar.
	 * @param name = Adminin ismi.
	 * @param sName = Adminin soy ismi.
	 * @param userName = Adminin kullanıcı adı.
	 * @param password = Adminin şifresi.
	 */
	public Admin(String name,String sName,String userName,long password) throws Exception{//admin consructer
    	this.name = name;
		this.sName = sName;
		this.userName = userName;
		this.password = password;
   }
	
	 /**
	 * Adminin işlemlerini yapabilmesini sağlayan fonksiyon/arayüz. Admin giriş yaptıktan sonra bu fonksiyon çağırılır.
	 */

   public void UI() {
	 //admin için arayüz sağlar
	 //seçimlere göre diğer methodları çağırır
	   try {
         System.out.println("Yapmak istediğiniz işlemi seçiniz.\n\n");
         System.out.println("1) Kullanıcı ekle.");
         System.out.println("2) Kullanıcı çıkar.");
         System.out.println("3) Varolan kullanıcıyı düzenle.");
         System.out.println("4) Varolan kullanıcıyı görüntüle.");
         System.out.println("5) Çıkış yap.");
        
         int choice = input.nextInt();
         input.nextLine();

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
            	 System.out.println("Geçerli bir değer giriniz.");
            	 System.out.println("Yapmak istediğin işlemi giriniz.");
            	 choice = input.nextInt();
          }
         }  
	   }
	   catch(Exception e) {
		   System.out.println("Hata!");
		   input.next(); 
		   UI();
	   }
   }
   
   /**
    * Kullanıcı silinmeden önce verisi silinecek kullanıcının bilgilerini alan fonksiyon.
    * User sınıfındaki remove fonksiyonu burdan çağrılır.
    */

   private void removeUser() {
	   //silinecek kullanıcının verilerini alır ve asıl silme metodunu çağırır
	   try {
		System.out.println("Silmek istediğiniz kullanıcının kullanıcı adını giriniz.");
		   String userName = input.nextLine();
		   System.out.println("Silmek istediğiniz kullanıcının şifresini giriniz.");
		   long password = input.nextLong();
		   
		   if(Main.isExist(userName,password)) {
			   System.out.println("Silmek istediğinize emin misiniz?");
			   System.out.println("1) Evet ileri     2) Hayır geri");
			   int choice = input.nextInt();

			   if(choice == 1) { 
				   userOp.remove(userName,password);
				   UI();
				   }
			   else if(choice == 2) UI();
			   else UI();
		   }
		   else {
			   System.out.println("Kullanıcı bulunamadı!");
			   UI();
		   }
	   } catch (Exception e) {
		   input.next();
		   System.out.println("Hata!");
		   UI();
	   }
   }
   
   /**
    * Kullanıcı ile alakalı veriler gösterilmeden önce verisi gösterilecek kullanıcının bilgilerini ve gösterilmesi istenen veri bilgisini alan fonksiyon.
    * User sınıfındaki show fonksiyonu burdan çağrılır.
    */
   private void showUser(){
	   // verileri gösterilecek kullanıcının kullanıcı adı ve şifresini alır, hangi veri gösterileceği bilgisini alır asıl gösterme metodunu çağırır
	   try {
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
		   		if(userOp.show(userName,password,"role").equals("Teacher")) {
		   			System.out.println("5) Girdiği ders");
		   			System.out.println("6) Geri");
		   		}
		   		else
		   			System.out.println("5) Geri");
		   	
		   		int choice = input.nextInt();
		        input.nextLine();

		   		boolean check = true;
		    
		   		while(check){
		        
		   			switch(choice){
		         
		   			case 1:
		   				System.out.println(userOp.show(userName,password,"name"));
		   				check = false;
		   				break;
		          
		   			case 2:
		      		 System.out.println(userOp.show(userName,password,"sName"));
		      		 check = false;
		      		 break;
		      	 
		   			case 3:
		   				System.out.println(userName);
		   				check = false;
		   				break;
		      	 
		   			case 4:
		   				System.out.println(password);
		   				check = false;
		   				break;
		            
		   			case 5:
		   				if(userOp.show(userName,password,"role").equals("Teacher")) {
		   					System.out.println(userOp.show(userName,password,"subject"));
		   				}
			       		else UI();	
		   				check = false;
		   				break;
		          
		   			case 6:
		   				if(userOp.show(userName,password,"role").equals("Teacher")) {
		   					UI();
			                check = false;
			                break;
		   				}
		   				else
		   					System.out.println("Geçerli bir değer giriniz.");
		   					System.out.println("Yapmak istediğin işlemi giriniz.");
		   					choice = input.nextInt();
		        
		   			default:
		   				System.out.println("Geçerli bir değer giriniz.");
		   				System.out.println("Yapmak istediğin işlemi giriniz.");
		   				choice = input.nextInt();
		   			}
		   	UI();
		   			
		   		}
		   }
		   else {
			   System.out.println("Kullanıcı bulunamadı!");
			   UI();
		   }
	   } catch (Exception e) {
		   System.out.println("Hata!");
		   input.nextLine();
		   UI();
	   }
   }
   
   /**
    * Kullanıcı eklenmeden önce eklenecek kullanıcının bilgilerini alan fonksiyon.
    * User sınıfındaki add fonksiyonları burdan çağrılır.
    */
   private void addUser(){
	   //kullanıcı eklemek için admine arayüz sağlar. Eklenecek kullanıcının verilerini alır
	   //asıl kullanıcı ekleme metodunu çağırır
       try {
		System.out.println("Hangi tür kullanıcı eklemek istersiniz?");
		   System.out.println("1) Öğretmen");
		   System.out.println("2) Öğrenci");
		   System.out.println("3) Admin");
		   System.out.println("4) Geri");
		   int choice = input.nextInt();
		   input.nextLine();
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
		                		userOp.add(name,sName,userName,password,subject,UserOp.Role.Teacher);
		                	}
		                	else {
		                		System.out.println("Öğretmen halihazırda mevcut.");
		                		check = false;
		                		break;
		                	}
		              }
		              catch (InputMismatchException e){
		                  System.out.println("Lütfen geçerli bir veri tipi giriniz!");
		                  UI();
		              }                
		              UI();
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
		               		userOp.add(name,sName,userName,password,UserOp.Role.Student);
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
		        UI();
		             check = false;
		             break;
		         
		         case 3:
		        	 while(true) {
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
		                    userOp.add(name,sName,userName,password,UserOp.Role.Admin);
		                    UI();
		               	}
		               	else {
		               		System.out.println("Admin halihazırda mevcut.");
		               		UI();
		               	}
		             }
		             catch (InputMismatchException e){
		                 System.out.println("Lütfen geçerli bir veri tipi giriniz!");
		                 UI();
		             }     
		        	 }
		             
		         case 4: 
		        	 UI();
		        	 
		        
		         default:
		        	 
		          System.out.println("Geçerli bir değer giriniz. (1, 2, 3 veya 4)");
		          System.out.println("Yapmak istediğin işlemi giriniz.");
		          choice = input.nextInt();
		          input.nextLine();

		      }
		        	 
		     }
	   } catch (Exception e) {
		   System.out.println("Hata!");
		   input.next();
		   UI();
	   }
      
   }
   
   /**
    * Kullanıcı ile alakalı veriler değiştirilmeden önce verisi değiştirilecek kullanıcının bilgilerini ve değiştirilecek veri bilgisini alan fonksiyon.
    * User sınıfındaki change fonksiyonu burdan çağrılır.
    */
   private void changeUser() { 
	   //değiştirelecek kullanıcının verilerini ve hangi verinin değiştirileceğini alarak asıl değiştirme metodunu çağırır
	  
	   try {
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
		   		if(userOp.show(userName,password,"role").equals("Teacher")) {//verisi değiştirilecek kişi öğretmen ise girdiği ders seçeneği de sunulur
		   			System.out.println("5) Girdiği ders");
		   			System.out.println("6) Geri");
		   		}
		   		else
		   			System.out.println("5) Geri");
		   	
		   		int choice = input.nextInt();
		        input.nextLine();

		   		boolean check = true;
		    
		   		while(check){
		        
		   			switch(choice){
		         
		   			case 1:
		   				System.out.println("Kullanıcının ismini giriniz.");
		   				String newName = input.nextLine();
		   				userOp.change(userName,password,"name",newName);
		   				UI();
		   				check = false;
		   				break;
		          
		   			case 2:
		      		 System.out.println("Kullanıcının soy ismini giriniz.");
		      		 String newSName = input.nextLine();
		      		 userOp.change(userName,password,"sName",newSName);
		      		 UI();
		      		 check = false;
		      		 break;
		      	 
		   			case 3:
		   				System.out.println("Kullanıcının kullanıcı adını giriniz.");
		   				String newUserName = input.nextLine();
		   				userOp.change(userName,password,"userName",newUserName);
		   				UI();
		   				check = false;
		   				break;
		      	 
		   			case 4:
		   				System.out.println("Kullanıcının şifresini giriniz.");
		   				long newPassword = input.nextLong();
		   				String passwordString = Long.toString(newPassword);
		   				userOp.change(userName,password,"password",passwordString);
		   				UI();
		   				check = false;
		   				break;
		            
		   			case 5:
		   				if(userOp.show(userName,password,"role").equals("Teacher")) {
		   					System.out.println("Ders ismini giriniz.");
		   					String newSubject;
		   					newSubject = input.nextLine();
		   					userOp.change(userName,password,"subject",newSubject);
		   					UI();
		   				}
			       		else UI();	
		   				check = false;
		   				break;
		          
		   			case 6:
		        	 	UI();
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
	   } catch (Exception e) {
		   System.out.println("Hata!"+e.toString());
		   e.getMessage();
		   input.next();
		   UI();
	   }
   }
   
}