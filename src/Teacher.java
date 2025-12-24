import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Teacher{
	
	String name,sName,userName,subject;
	long password;
	static Scanner input = new Scanner(System.in);
	
	public Teacher(String name, String sName, String userName, long password, String subject) {
		this.name = name;
		this.sName = sName;
		this.userName = userName;
		this.password = password;
		this.subject = subject;
		User.add(name,sName,userName,password,subject,User.Role.Teacher);
		
		}
	
	public static void UI() throws FileNotFoundException {
		
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
	
	public void changeQuestion(String quizName,int questionNo) {
		System.out.println("Değiştirmek istediğiniz veriyi seçiniz.");
		System.out.println("1) Soru metni");
		System.out.println("2) Cevabı");
		System.out.println("3) Puanı");
		System.out.println("4) Zorluk derecesi");
		System.out.println("5) Geri");
    	
			int choice = input.nextInt();
			int choice2;
			boolean check = true;
    	
			while(check){
         
				switch(choice){
          
					case 1:
						System.out.println("Soru metnini giriniz.");
						String newQuestionText = input.nextLine();
						Question.change(quizName,questionNo,"questionText",newQuestionText);
						check = false;
						break;
           
					case 2:
						System.out.println("Cevabı giriniz.");
						String newAnswer = input.nextLine();
						Question.change(quizName,questionNo,"answer",newAnswer);
						check = false;
						break;
       	 
					case 3:
						System.out.println("Puanı giriniz.");
						int newPoints = input.nextInt();
						Question.change(quizName,questionNo,newPoints);
						check = false;
						break;
       	 
					case 4:
						System.out.println("Zorluk derecesini seçiniz.");
						System.out.println("1) Kolay");
						System.out.println("2) Orta");
						System.out.println("3) Zor");
						System.out.println("4) Geri");
						choice2 = input.nextInt();
						Question.Difficulty dif;
						if (choice2==1) { 
							dif = Question.Difficulty.Easy;
							Question.change(quizName,questionNo,dif);
						}
						else if (choice2==2) { 
							dif = Question.Difficulty.Medium;
							Question.change(quizName,questionNo,dif);
						}
						else if (choice2==3) { 
							dif = Question.Difficulty.Hard;
							Question.change(quizName,questionNo,dif);
						}
						else if (choice2==4) { 
							changeQuestion(quizName,questionNo);
						}
						else {
							System.out.println("Geçersiz değer!"); 
							changeQuestion(quizName,questionNo);
						}
						check = false;
						break;
             
					case 5:
						System.out.println("Soru düzenleme iptal edildi.");
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
