import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student{
	
	String name,sName,userName;
	long password;
	static Scanner input = new Scanner(System.in);
	
	public Student (String name, String sName, String userName, long password ) throws Exception{
		this.name = name;
		this.sName = sName;
		this.userName = userName;
		this.password = password;	
		UI();
	}
	private void UI() throws Exception {
		
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
              QuizOp.scoresList(userName, password, name, sName);
              check = false;
              break;
              
          	 case 2:
          		List<String> quizesWsubject = new ArrayList<>();
          		System.out.println("Girmek istediğiniz quizi seçiniz.\n");
          		quizesWsubject = QuizOp.unsolvedQuizes(userName);
          		System.out.println("Quizleriniz: \n");
          		
         		for(int i=0; i<quizesWsubject.size() ; i++) {
         			String quizesWsubjectLine = quizesWsubject.get(i);
         			String quizes[] = quizesWsubjectLine.split("#");
         			System.out.println((i+1)+")"+ quizes[1]);
         		}
         			int quizChoice;
         			while(true) {
         				quizChoice = input.nextInt();
         				if(quizChoice<0 || quizesWsubject.size()<quizChoice)
         					System.out.println("Geçersiz değer! Tekrar giriniz.");
         				else
         					break;
        		 	}
         		String quizesWsubjectLine = quizesWsubject.get(quizChoice-1);
         		String[] quiz = quizesWsubjectLine.split("#");
         		String subject = quiz[0];
         		String quizName = quiz[1];
         		QuizOp.enterQuiz(subject,quizName,userName,password);
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
         }
	public static void callUI(String userName, long password) throws Exception {
		String name = User.show(userName, password,"name");
		String sName = User.show(userName, password,"sName");
		Student newstudent = new Student(name,sName,userName,password);
		newstudent.UI();
	}
	}