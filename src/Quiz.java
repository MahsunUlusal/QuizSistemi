import java.util.Scanner;

public class Quiz {
String quizName,subject;
int pointsCounter, questionCounter;
static Scanner input = new Scanner(System.in);

	public Quiz(String quizName, String subject, String userName) throws Exception {
		this.quizName = quizName;
		this.subject = subject;
		QuizOp.createQuiz(subject,quizName,userName);
		this.pointsCounter=0;
		this.questionCounter=0;
		UI();
	}
	private void UI() throws Exception {
		
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
            
         	 case 1:{
         		 
         		System.out.println("Soru türünü seçiniz.");
         		System.out.println("1) Test");
         		System.out.println("2) Doğru-Yanlış");
         		int typeChoice = input.nextInt();
         		QuizOp.Type type = null;
         		switch(typeChoice) {
     			
     			case 1:
     				type = QuizOp.Type.MC;
     				break;
     			
     			case 2:
     				type = QuizOp.Type.TF;
     				break;
     			}
         		
         		String answer = null;
         		System.out.println("Soru metnini giriniz.");
         		String question = input.nextLine();
         		
         		if(typeChoice == 1) {
         			System.out.println("A şıkkını giriniz.");
         			String choicesA = input.nextLine();
         			System.out.println("B şıkkını giriniz.");
         			String choicesB = input.nextLine();
         			System.out.println("C şıkkını giriniz.");
         			String choicesC = input.nextLine();
         			System.out.println("D şıkkını giriniz.");
         			String choicesD = input.nextLine();
         			 question += "¿"+ choicesA +"¿"+ choicesB +"¿"+ choicesC +"¿"+ choicesD;
         			System.out.println("Sorunun cevabını seçiniz.");
         			System.out.println("1) A");
             		System.out.println("2) B");
             		System.out.println("3) C");
             		System.out.println("4) D");
             		int answerChoice = input.nextInt();
             		if(answerChoice != 1 | answerChoice != 2| answerChoice != 3| answerChoice != 4) {
             			System.out.println("Geçerli bir değer giriniz.(1,2,3 veya 4");
             			UI();
             			
             			switch(answerChoice) {
                 		case 1:
                 			answer = "A";
                 			break;
                 			
                 		case 2:
                 			answer = "B";
                 			break;
                 			
                 		case 3:
                 			 answer = "C";
                 			break;
                 			
                 		case 4:
                 			 answer = "D";
                 			break;
                 		}
             		}
         		}
         		else if(typeChoice == 2) {
         			System.out.println("Sorunun cevabını seçiniz.");
         			System.out.println("1) Doğru");
             		System.out.println("2) Yanlış");
             		int answerChoice = input.nextInt();
             		if(answerChoice != 1 | answerChoice != 2) {
             			System.out.println("Geçerli bir değer giriniz.");
             			System.out.println("Geçerli bir değer giriniz.(1 veya 2");
             			UI();
             			switch(answerChoice) {
             			
             			case 1:
             				answer = "True";
             				break;
             			
             			case 2:
             				answer = "False";
             				break;
             			}
             		}
         		}
         		else {
         			System.out.println("Geçerli bir değer giriniz.");
         			UI();
         		}
         		
         		System.out.print("Sorunun puanını giriniz. ( Güncel puan: "+ pointsCounter+")");
         		int points = input.nextInt();
         		this.pointsCounter += points;
         		while(true) {
         			if(this.pointsCounter>100) {
         				System.out.println("Toplam puan 100'ü geçiyor!.");
         				this.pointsCounter -= points;
         				System.out.println("Sorunun puanını tekrar giriniz. ( Güncel: " + pointsCounter +")");	
         				points = input.nextInt();
                 		this.pointsCounter += points;
         			}
         			else if(this.pointsCounter==100) {
         				System.out.println("Toplam puan 100! Başka soru eklemek istiyorsanız menüden soruların puanını değiştiriniz.");
         				break;
         			}
         			else{ 
         				System.out.println("Güncel puan: " + pointsCounter);	
         				break;
         				}
         		}
         		
         		System.out.println("Sorunun zorluğunu seçiniz.");
         		System.out.println("1) Kolay");
         		System.out.println("2) Orta");
         		System.out.println("3) Zor");
         		int difNo = input.nextInt();
         		QuizOp.Difficulty dif = null;
         		if (difNo == 1) dif = QuizOp.Difficulty.Easy;
         		else if (difNo == 2) dif = QuizOp.Difficulty.Medium;
         		else if (difNo == 3) dif = QuizOp.Difficulty.Hard;
         		else {
         			System.out.println("Geçerli bir değer giriniz.");
         			UI();
         		}
         		QuizOp.add(questionCounter++,question,answer,points,subject,dif,type);
         		System.out.println("Soru başarıyla eklendi.");
         		UI();
         	 }

         	 case 2:{
         		int questionNo ;
         		 while(true) {
         			 System.out.println("Kaçıncı soruyu görüntülemek istersiniz.");
         			 questionNo = input.nextInt();
         			if(questionCounter<questionNo || questionNo<0)
         				System.out.println("Geçersiz değer! Tekrar giriniz.");
         			else
         		 		break;
         		 	}
         		 System.out.println(questionNo+".soru\n");
         		 if(QuizOp.show(subject,quizName,questionNo,"type") == "MC") {
         			String questionLine = QuizOp.show(subject,quizName,questionNo,"question");
         			String[] questionSplits = questionLine.split("¿");
         			System.out.println(questionSplits[0]+"\n");
         			System.out.println("A)"+ questionSplits[1]);
         			System.out.println("B)"+ questionSplits[2]);
         			System.out.println("C)"+ questionSplits[3]);
         			System.out.println("D)"+ questionSplits[4] + "\n");
         			System.out.println("Cevap: "+ QuizOp.show(subject,quizName,questionNo,"answer"));
         		 }
         		 else {
         			System.out.println("Cevap: "+ QuizOp.show(subject,quizName,questionNo,"question"));
         			String answer = QuizOp.show(subject,quizName,questionNo,"answer");
         			System.out.println("A) Doğru\nB) Yanlış");
         			if (answer == "True") System.out.println("Cevabı: Doğru"); 
             		else if (answer == "False") System.out.println("Cevabı: Yanlış"); 
         		 }
         		 	
         			String dif = QuizOp.show(subject,quizName,questionNo,"dif");
         			if (dif == "EASY") System.out.println("Zorluk türü: Kolay"); 
             		else if (dif == "MEDİUM") System.out.println("Zorluk türü: Orta"); 
             		else if (dif == "HARD") System.out.println("Zorluk türü: Zor"); 
         			System.out.println("Puanı: "+ QuizOp.show(subject,quizName,questionNo,"points"));
         			UI();
         	 }
         	 
         	 case 3:{
         		int questionNo ;
        		 while(true) {
        			 System.out.println("Kaçıncı soruyu değiştirmek istersiniz.");
        			 questionNo = input.nextInt();
        			if(questionCounter<questionNo || questionNo<0)
        				System.out.println("Geçersiz değer! Tekrar giriniz.");
        			else
        		 		break;
        		 	}
        		 System.out.println("Neyi değiştirmek istediğinizi seçiniz.");
        		 System.out.println("1) Soru metnini (Tüm soruyu)");
          		 System.out.println("2) Cevabı");
          		 System.out.println("3) Puanı");
          		 System.out.println("4) Zorluk derecesini");
          		 int choice2 = input.nextInt();
          		 
          		if(choice2 == 1) {
          			
          			String newAnswer = null;
          			System.out.println("Güncel soru metni ve cevabı: \n");
          			if(QuizOp.show(subject,quizName,questionNo,"type") == "MC") {
             			String questionLine = QuizOp.show(subject,quizName,questionNo,"question");
             			String[] questionSplits = questionLine.split("¿");
             			System.out.println(questionSplits[0]+"\n");
             			System.out.println("A)"+ questionSplits[1]);
             			System.out.println("B)"+ questionSplits[2]);
             			System.out.println("C)"+ questionSplits[3]);
             			System.out.println("D)"+ questionSplits[4] + "\n");
             			System.out.println("Cevap: "+ QuizOp.show(subject,quizName,questionNo,"answer"));
             		 }
             		 else {
             			System.out.println("Cevap: "+ QuizOp.show(subject,quizName,questionNo,"question"));
             			String answer = QuizOp.show(subject,quizName,questionNo,"answer");
             			System.out.println("A) Doğru\nB) Yanlış");
             			if (answer == "True") System.out.println("Cevabı: Doğru"); 
                 		else if (answer == "False") System.out.println("Cevabı: Yanlış"); 
             		 }
          			System.out.println("Yeni soru metnini giriniz.");
          			String question = input.nextLine();
          			
          			if(QuizOp.show(subject,quizName,questionNo,"type") == "MC") {
             			System.out.println("A şıkkını giriniz.");
             			String choicesA = input.nextLine();
             			System.out.println("B şıkkını giriniz.");
             			String choicesB = input.nextLine();
             			System.out.println("C şıkkını giriniz.");
             			String choicesC = input.nextLine();
             			System.out.println("D şıkkını giriniz.");
             			String choicesD = input.nextLine();
             			 question += "¿"+ choicesA +"¿"+ choicesB +"¿"+ choicesC +"¿"+ choicesD;
             			System.out.println("Sorunun cevabını seçiniz.");
             			System.out.println("1) A");
                 		System.out.println("2) B");
                 		System.out.println("3) C");
                 		System.out.println("4) D");
                 		int answerChoice = input.nextInt();
                 		if(answerChoice != 1 | answerChoice != 2| answerChoice != 3| answerChoice != 4) {
                 			System.out.println("Geçerli bir değer giriniz.(1,2,3 veya 4");
                 			UI();
                 			
                 			switch(answerChoice) {
                     		case 1:
                     			newAnswer = "A";
                     			break;
                     			
                     		case 2:
                     			newAnswer = "B";
                     			break;
                     			
                     		case 3:
                     			 newAnswer = "C";
                     			break;
                     			
                     		case 4:
                     			 newAnswer = "D";
                     			break;
                     		}
                 		}
             		}
             		else if(QuizOp.show(subject,quizName,questionNo,"type") == "TF") {
             			System.out.println("Sorunun cevabını seçiniz.");
             			System.out.println("1) Doğru");
                 		System.out.println("2) Yanlış");
                 		int answerChoice = input.nextInt();
                 		if(answerChoice != 1 | answerChoice != 2) {
                 			System.out.println("Geçerli bir değer giriniz.");
                 			System.out.println("Geçerli bir değer giriniz.(1 veya 2");
                 			UI();
                 			switch(answerChoice) {
                 			
                 			case 1:
                 				newAnswer = "True";
                 				break;
                 			
                 			case 2:
                 				newAnswer = "False";
                 				break;
                 			}
                 		}
             		}
          			System.out.println("Quizin güncel toplam puanı: "+ pointsCounter);
          			System.out.println("Sorunun güncel puanı: "+ QuizOp.show(subject,quizName,questionNo,"points"));
          			System.out.print("Sorunun puanını giriniz.");
             		int points = input.nextInt();
             		this.pointsCounter += points;
             		while(true) {
             			if(this.pointsCounter>100) {
             				System.out.println("Toplam puan 100'ü geçiyor!.");
             				this.pointsCounter -= points;
             				System.out.println("Sorunun puanını tekrar giriniz. ( Güncel: " + pointsCounter +")");	
             				points = input.nextInt();
                     		this.pointsCounter += points;
             			}
             			else if(this.pointsCounter==100) {
             				System.out.println("Toplam puan 100! Başka soru eklemek istiyorsanız menüden soruların puanını değiştiriniz.");
             				break;
             			}
             			else { 
             				System.out.println("Güncel puan: " + pointsCounter);	
             				break;
             				}
             		}
             		
             		System.out.println("Sorunun zorluğunu seçiniz.");
             		System.out.println("1) Kolay");
             		System.out.println("2) Orta");
             		System.out.println("3) Zor");
             		int difNo = input.nextInt();
             		QuizOp.Difficulty dif = null;
             		if (difNo == 1) dif = QuizOp.Difficulty.Easy;
             		else if (difNo == 2) dif = QuizOp.Difficulty.Medium;
             		else if (difNo == 3) dif = QuizOp.Difficulty.Hard;
             		else {
             			System.out.println("Geçerli bir değer giriniz.");
             			UI();
             		}
             		QuizOp.change(this.subject,quizName,questionNo,"question",question);
             		QuizOp.change(this.subject,quizName,questionNo,"answer",newAnswer);
             		QuizOp.change(this.subject,quizName,questionNo,dif);
             		QuizOp.change(this.subject,quizName,questionNo,points);
             		System.out.println("Soru başarıyla değiştirildi.");
             		UI();
          		}
          		
          		else if(choice2 == 2) {
          			
          			String newAnswer = null;          			
          			System.out.println("Güncel soru metni ve cevabı: \n");
          			if(QuizOp.show(subject,quizName,questionNo,"type") == "MC") {
             			String questionLine = QuizOp.show(subject,quizName,questionNo,"question");
             			String[] questionSplits = questionLine.split("¿");
             			System.out.println(questionSplits[0]+"\n");
             			System.out.println("A)"+ questionSplits[1]);
             			System.out.println("B)"+ questionSplits[2]);
             			System.out.println("C)"+ questionSplits[3]);
             			System.out.println("D)"+ questionSplits[4] + "\n");
             			System.out.println("Cevap: "+ QuizOp.show(subject,quizName,questionNo,"answer"));
             		 }
             		 else {
             			System.out.println("Cevap: "+ QuizOp.show(subject,quizName,questionNo,"question"));
             			String answer = QuizOp.show(subject,quizName,questionNo,"answer");
             			System.out.println("A) Doğru\nB) Yanlış");
             			if (answer == "True") System.out.println("Cevabı: Doğru"); 
                 		else if (answer == "False") System.out.println("Cevabı: Yanlış"); 
             		 }
          			if(QuizOp.show(subject,quizName,questionNo,"type") == "MC") {
             			System.out.println("Sorunun cevabını seçiniz.");
             			System.out.println("1) A");
                 		System.out.println("2) B");
                 		System.out.println("3) C");
                 		System.out.println("4) D");
                 		int answerChoice = input.nextInt();
                 		if(answerChoice != 1 | answerChoice != 2| answerChoice != 3| answerChoice != 4) {
                 			System.out.println("Geçerli bir değer giriniz.(1,2,3 veya 4");
                 			UI();
                 			
                 			switch(answerChoice) {
                     		case 1:
                     			newAnswer = "A";
                     			break;
                     			
                     		case 2:
                     			newAnswer = "B";
                     			break;
                     			
                     		case 3:
                     			 newAnswer = "C";
                     			break;
                     			
                     		case 4:
                     			 newAnswer = "D";
                     			break;
                     		}
                 		}
             		}
             		else{
             			System.out.println("Sorunun cevabını seçiniz.");
             			System.out.println("1) Doğru");
                 		System.out.println("2) Yanlış");
                 		int answerChoice = input.nextInt();
                 		if(answerChoice != 1 | answerChoice != 2) {
                 			System.out.println("Geçerli bir değer giriniz.");
                 			System.out.println("Geçerli bir değer giriniz.(1 veya 2");
                 			UI();
                 			switch(answerChoice) {
                 			
                 			case 1:
                 				newAnswer = "True";
                 				break;
                 			
                 			case 2:
                 				newAnswer = "False";
                 				break;
                 			}
                 		}
             		}
          			QuizOp.change(this.subject,quizName,questionNo,"answer",newAnswer);
          			System.out.println("Cevap başarıyla değiştirildi.");
          		}
          		
          		else if(choice2 == 3) {
          			System.out.println("Quizin güncel toplam puanı: "+ pointsCounter);
          			System.out.println("Sorunun güncel puanı: "+ QuizOp.show(subject,quizName,questionNo,"points"));
          			System.out.println("Sorunun puanını giriniz.");
             		int points = input.nextInt();
             		this.pointsCounter += points;
             		while(true) {
             			if(this.pointsCounter>100) {
             				System.out.println("Toplam puan 100'ü geçiyor!.");
             				this.pointsCounter -= points;
             				System.out.println("Sorunun puanını tekrar giriniz. ( Güncel: " + pointsCounter +")");	
             				points = input.nextInt();
                     		this.pointsCounter += points;
             			}
             			else if(this.pointsCounter==100) {
             				System.out.println("Toplam puan 100! Başka soru eklemek istiyorsanız menüden soruların puanını değiştiriniz.");
             				break;
             			}
             			else { 
             				System.out.println("Güncel puan: " + pointsCounter);	
             				break;
             				}
             		}
          		}
          		
          		else if(choice2 == 4) {
          			String dif = QuizOp.show(subject,quizName,questionNo,"dif");
         			if (dif == "EASY") System.out.println("Güncel zorluk türü: Kolay"); 
             		else if (dif == "MEDİUM") System.out.println("Güncel zorluk türü: Orta"); 
             		else if (dif == "HARD") System.out.println("Güncel zorluk türü: Zor"); 
         			System.out.println("\nSorunun yeni zorluğunu seçiniz.");
             		System.out.println("1) Kolay");
             		System.out.println("2) Orta");
             		System.out.println("3) Zor");
             		int difNo = input.nextInt();
             		QuizOp.Difficulty newDif = null;
             		if (difNo == 1) newDif = QuizOp.Difficulty.Easy;
             		else if (difNo == 2) newDif = QuizOp.Difficulty.Medium;
             		else if (difNo == 3) newDif = QuizOp.Difficulty.Hard;
             		else {
             			System.out.println("Geçerli bir değer giriniz.");
             			UI();
             		}
             		QuizOp.change(this.subject,quizName,questionNo,newDif);
             		System.out.println("Soru başarıyla değiştirildi.");
          		}
          		else {
          			System.out.println("Geçerli bir değer giriniz.");
         			UI();
          		}    		
         	 }
         	 
         	 case 4:{
         		int questionNo ;
        		 while(true) {
        			 System.out.println("Kaçıncı soruyu silmek istersiniz?");
        			 questionNo = input.nextInt();
        			if(questionCounter<questionNo || questionNo<0)
        				System.out.println("Geçersiz değer! Tekrar giriniz.");
        			else {
        				System.out.println("Soruyu silmek istediğinize emin misiniz?\nBu işlem geri alınamaz ve soru hakkında her şey silinerek ondan sonraki bütün soruların numarası 1 tane geriye kaydırılır.");
        				System.out.println("1) Evet\n2) Hayır");
        				int choice2 = input.nextInt();
        				if(choice2 == 1) {
        					QuizOp.remove(subject, quizName, questionNo);
        							questionCounter--;
        							System.out.println("Soru başarıyla silindi.");
        							UI();
        				}
        				else if(choice2 == 2) {
        					System.out.println("Soru silme iptal edildi.");
							UI();
        				}
        			}
        		 } 
         	 }
         	 
             case 5:{
            	System.out.println("Quizi iptal etmek istediğinize emin misiniz?\nBu işlem geri alınamaz ve quiz hakkında her şey silinir.");
 				System.out.println("1) Evet\n2) Hayır");
 				int choice2 = input.nextInt();
 				if(choice2 == 1) {
 					QuizOp.removeQuiz(subject, quizName, questionCounter);
 							System.out.println("Soru başarıyla silindi.");
 							UI();
 				}
 				else if(choice2 == 2) {
 					System.out.println("Quiz silme iptal edildi.");
						UI();
 				}
             }
             
          	 case 6:{
          		if(pointsCounter == 100) {
          			System.out.println("Quizi bitirmek istediğinize emin misiniz?\nBu işlem geri alınamaz ve quiz üzerinde bir daha değişiklik yapamazsınız.");
     				System.out.println("1) Evet\n2) Hayır");
     				int choice2 = input.nextInt();
     				
     				if(choice2 == 1) {
     					QuizOp.finishQuiz(subject, quizName);
     							System.out.println("Quiz başarıyla oluşturuldu.");
     							UI();
     				}
     				else if(choice2 == 2) {
     					System.out.println("Quiz oluşturulmaya devam ediliyor.");
    						UI();
     				}
          		}
          		else {
          			System.out.println("Toplam puan 100'e ulaşmadı! Quizi bitirebilmek için mevcut soruların puanlarını düzenleyiniz ya da yeni soru ekleyiniz.");
          			System.out.println("Quizin güncel toplam puanı: "+ pointsCounter);
          			UI();
          		}
          	 }
             	 		
            default:
             System.out.println("Geçerli bir değer giriniz. (1, 2, 3, 4, 5 veya 6)");
             System.out.println("Yapmak istediğin işlemi giriniz.");
             choice = input.nextInt();
         }
        }      

	}
	
}
