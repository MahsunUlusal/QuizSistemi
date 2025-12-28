import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class Teacher{
	
	String name,sName,userName,subject;
	long password;
	static Scanner input = new Scanner(System.in);
	List<String> quizes = new ArrayList<>();
	
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
             
          	 case 1:{
          		 System.out.println("Görüntülemek istediğniz quizi seçiniz.\n");
          		 quizes = QuizOp.quizList(userName,subject);
          		 System.out.println("Quizleriniz: \n");
          		 for(int i=0; i<quizes.size() ; i++)
          		 System.out.println((i+1)+")"+quizes.get(i));
          		int quizChoice;
          		 while(true) {
          			quizChoice = input.nextInt();
        			if(quizChoice<0 || quizes.size()<quizChoice)
        				System.out.println("Geçersiz değer! Tekrar giriniz.");
        			else
        		 		break;
        		 	}
          		String quizName = quizes.get(quizChoice-1);
          		QuizOp.showAllQuiz(subject,quizName,userName,password,null);
          		UI();
          		 check = false;
          		 break;
          	 } 
          	 case 2:{
          		System.out.println("Quizin ismini giriniz.");
         		String quizName = input.nextLine();
         		Quiz quiz = new Quiz (quizName,subject,userName);
         		UI();
                check = false;
                break;
          	 }
          	 case 3:{
          		System.out.println("Görüntülemek istediğniz öğrencinin ismini giriniz.\n");
          		String stName = input.nextLine();
          		System.out.println("Öğrencinin soy ismini giriniz.\n");
          		String stSName = input.nextLine();
         		QuizOp.scoresList(userName,password,stName,stSName);
         		UI();
                check = false;
                break;
          	 }
          	 case 4:{
          		Main.logIO();
                check = false;
                break;
          	 }
             default:
              System.out.println("Geçerli bir değer giriniz. (1, 2, 3, 4 veya 5)");
              System.out.println("Yapmak istediğin işlemi giriniz.");
              choice = input.nextInt();
          }
        }      
	}
	
	
}
