package Users;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import FileOp.QuizOp;
import FileOp.UserOp;
import Main.Main;
import Quiz.Quiz;

public class Teacher implements User{
	
	private String name,sName,userName,subject;
	private long password;
	private static Scanner input = new Scanner(System.in);
	
	//getter ve setter methodları
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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public long getPassword() {
		return password;
	}

	public void setPassword(long password) {
		this.password = password;
	}

	List<String> quizes = new ArrayList<>();
	QuizOp quizOp = new QuizOp();
	UserOp userOp = new UserOp();
	
	/**
	 * Öğretmen nesnesi oluşturmaya yarar.
	 * @param name = Öğretmenin ismi.
	 * @param sName = Öğretmenin soy ismi.
	 * @param userName = Öğretmenin kullanıcı adı.
	 * @param password = Öğretmenin şifresi.
	 * @param subject = Öğretmenin girdiği ders.
	 */
	public Teacher(String name, String sName, String userName, long password, String subject){ // öğretmen consructer
		this.name = name;
		this.sName = sName;
		this.userName = userName;
		this.password = password;
		this.subject = subject;
	}
	
	/**
	 * Öğretmenin işlemlerini yapabilmesini sağlayan fonksiyon/arayüz. Öğretmen giriş yaptıktan sonra bu fonksiyon çağırılır.
	 */
	public void UI(){
		//öğretmen için arayüz sağlar
    	//seçimlere göre diğer methodları çağırır
		 try {
			boolean check = true;
			
			while(check){
				System.out.println("\n\nYapmak istediğiniz işlemi seçiniz.\n\n");
				System.out.println("1) Quizleri görüntüle.");
				System.out.println("2) Quiz oluştur.");
				System.out.println("3) Öğrenci puanlarını görüntüle.");
				System.out.println("4) Çıkış yap.");
			
				int choice = input.nextInt();
				input.nextLine();
			    
				switch(choice){
			     
			  	 case 1:{
			  		 System.out.println("Görüntülemek istediğniz quizi seçiniz.\n");
			  		 quizes = quizOp.quizList(userName,subject);
			  		 
			  		 if(quizes != null && !quizes.isEmpty()) {
				  		 System.out.println("Quizleriniz: \n");
				  		 for(int i=0; i<quizes.size() ; i++)
				  			 System.out.println((i+1)+")"+quizes.get(i));
				  		 
				  		 int quizChoice;
				  		 while(true) {
				  			quizChoice = input.nextInt();
				  			input.nextLine(); // Buffer
							if(quizChoice<=0 || quizes.size()<quizChoice)
								System.out.println("Geçersiz değer! Tekrar giriniz.");
							else
						 		break;
						 }
				  		String quizName = quizes.get(quizChoice-1);
				  		quizOp.showAllQuiz(subject,quizName,userName,password,null);
			  		 } else {
			  			 System.out.println("Hiç quiz bulunamadı.");
			  		 }
			  		 break;
			  	 } 
			  	 
			  	 case 2:{
			  		System.out.println("Quizin ismini giriniz.");
			 		String quizName = input.nextLine();
			 		Quiz quiz = new Quiz (quizName,subject,userName);
			 		quiz.UI();
			        break;
			  	 }
			  	 
			  	 case 3:{
			  		System.out.println("Görüntülemek istediğniz öğrencinin ismini giriniz.\n");
			  		String stName = input.nextLine();
			  		System.out.println("Öğrencinin soy ismini giriniz.\n");
			  		String stSName = input.nextLine();
			 		quizOp.scoresList(userName,password,stName,stSName);
			        break;
			  	 }
			  	 
			  	 case 4:{
			  		Main.logIO();
			        check = false;
			        break;
			  	 }
			  	 
			     default:
			      System.out.println("Geçerli bir değer giriniz. (1, 2, 3, 4)");
			  }
			}
		 } catch (Exception e) {
			System.out.println("Hata!");
			input.nextLine();
			UI();
		 }      
	}
}