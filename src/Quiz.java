import java.io.FileNotFoundException;
import java.util.Scanner;

public class Quiz {
String quizName,subject;
int pointsCounter = 0;

	public Quiz(String quizName, String subject) {
		this.quizName = quizName;
		this.subject = subject;
		this.UI();
	}
	
	static Scanner input = new Scanner(System.in);
	public void UI() throws FileNotFoundException {
		
        System.out.println("1) Soru ekle.");
        System.out.println("2) Girilen soruları görüntüle.");
        System.out.println("3) Girilen soruları düzenle.");
        System.out.println("4) Girilen soruyu sil.");
        System.out.println("5) Quiz oluşturmayı iptal et.");
        System.out.println("6) Quiz oluşturmayı bitir.");
        

        int choice = input.nextInt();
        
        boolean check = true;
        while(check){
           
         switch(choice){
            
         	 case 1:
         		System.out.println("Soru türünü seçiniz.");
         		System.out.println("1) Test");
         		System.out.println("2) Doğru-Yanlış");
         		int type = input.nextInt();
         		
         		System.out.println("Soru metnini giriniz.");
         		String question = input.nextLine();
         		
         		if(type == 1) {
         			System.out.println("A şıkkını giriniz.");
         			String choicesA = input.nextLine();
         			System.out.println("A şıkkını giriniz.");
         			String choicesB = input.nextLine();
         			System.out.println("A şıkkını giriniz.");
         			String choicesC = input.nextLine();
         			System.out.println("A şıkkını giriniz.");
         			String choicesD = input.nextLine();
         			 question += "¿"+ choicesA +"¿"+ choicesB +"¿"+ choicesC +"¿"+ choicesD;
         		}
         		else if(type == 2) {
         			System.out.println("Sorunun cevabını seçiniz.");
         			System.out.println("1) Doğru");
             		System.out.println("2) Yanlış");
             		int answer = input.nextInt();
         		}
         		else {
         			System.out.println("Geçerli bir değer giriniz.");
         			UI();
         		}
         		
         		System.out.println("Sorunun puanını giriniz.");
         		int points = input.nextInt();
         		pointsCounter += points;
         		while(true) {
         			if(pointsCounter>100) {
         				System.out.println("Toplam puan 100'ü geçiyor!.");
         				pointsCounter -= points;
         				System.out.println("Sorunun puanını tekrar giriniz. ( Güncel: " + pointsCounter +")");	
         				points = input.nextInt();
                 		pointsCounter += points;
         			}
         			else if(pointsCounter==100) {
         				System.out.println("Toplam puan 100! Başka soru eklemek istiyorsanız menüden soruların puanını değiştiriniz.");
         				break;
         			}
         			else break;
         		}
         		
         		System.out.println("Sorunun zorluğunu seçiniz.");
         		System.out.println("1) Kolay");
         		System.out.println("2) Orta");
         		System.out.println("3) Zor");
         		int difNo = input.nextInt();
         		Question.Difficulty dif;
         		if (difNo == 1) dif = Question.Difficulty.Easy;
         		else if (difNo == 2) dif = Question.Difficulty.Medium;
         		else if (difNo == 3) dif = Question.Difficulty.Hard;
         		else {
         			System.out.println("Geçerli bir değer giriniz.");
         			UI();
         		}
         		
         		
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
                     
                check = false;
                break;
                  
          	 case 6:
                    
                 check = false;
                 break;
             	 		
            default:
             System.out.println("Geçerli bir değer giriniz. (1, 2, 3, 4, 5 veya 6)");
             System.out.println("Yapmak istediğin işlemi giriniz.");
             choice = input.nextInt();
         }
        }      

	}
	public void changeQuestion(String subject,String quizName,int questionNo) {
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
						Question.change(subject,quizName,questionNo,"questionText",newQuestionText);
						check = false;
						break;
           
					case 2:
						System.out.println("Cevabı giriniz.");
						String newAnswer = input.nextLine();
						Question.change(subject,quizName,questionNo,"answer",newAnswer);
						check = false;
						break;
       	 
					case 3:
						System.out.println("Puanı giriniz.");
						int newPoints = input.nextInt();
						Question.change(subject,quizName,questionNo,newPoints);
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
							Question.change(subject,quizName,questionNo,dif);
						}
						else if (choice2==2) { 
							dif = Question.Difficulty.Medium;
							Question.change(subject,quizName,questionNo,dif);
						}
						else if (choice2==3) { 
							dif = Question.Difficulty.Hard;
							Question.change(subject,quizName,questionNo,dif);
						}
						else if (choice2==4) { 
							changeQuestion(subject,quizName,questionNo);
						}
						else {
							System.out.println("Geçersiz değer!"); 
							changeQuestion(subject,quizName,questionNo);
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
