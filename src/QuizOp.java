import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class QuizOp implements FileOp{
	
	static Scanner input = new Scanner(System.in);
	
	public enum Type{
		TF,MC
	}
	public enum Difficulty{
		Hard,Medium,Easy
	}
	public static void add(int counter, String question, String answer, int points, String subject, Difficulty dif, Type type) {

		String fileName = subject + ".txt";
		
		try{
            FileWriter printer = new FileWriter(fileName,true);
            printer.write( counter +"#"+ question +"#"+ answer +"#"+ points +"#"+ dif +"#"+  type +"*");
            System.out.println("Soru eklendi.\n");
            printer.close();
        }
       catch(IOException e){
           System.out.println("Soru eklenemedi.\n");
       }
		
	}
	public static void change(String subject,String quizName,int questionNo, String target, String newTarget) {
	
		if(target == "answer") {
			File quiz = new File(subject+".txt");
			File newQuiz= new File("temp.txt");
		
			try (BufferedReader br = new BufferedReader(new FileReader(quiz)); BufferedWriter bw = new BufferedWriter(new FileWriter(newQuiz))){
				String line;
				while ((line = br.readLine()) != null) {
					//hoca ismi | quiz ismi | soru
					String[] splits = line.split("|");
        		 
        			 
						if(splits[1].equals(quizName)) {
							
							bw.write( splits[0] +"|"+ splits[1] +"|");
							String questionLine = splits[2];
							String[] questionSplits = questionLine.split("*");
							String questionNoString = Integer.toString(questionNo);
							String questionLine2 = questionSplits[questionNo];
							String[] questionSplits2 = questionLine2.split("#");
 						
 						
							if(questionSplits2[0].equals(questionNoString)) {
 							
								bw.write( questionSplits2[0] +"#"+ questionSplits2[1] +"#"+ newTarget  +"#"+ questionSplits2[3] +"#"+ questionSplits2[4] +"#"+ questionSplits2[5] +"*");
								System.out.println("Başarıyla değiştirildi.");
								break;
							}
							else {
								bw.write( questionSplits2[0] +"#"+ questionSplits2[1] +"#"+ questionSplits2[2] +"#"+ questionSplits2[3] +"#"+ questionSplits2[4] +"#"+ questionSplits2[5] +"*");
							}
						}
						else {
							bw.write( splits[0] +"|"+ splits[1] +"|"+ splits[2]);
							bw.newLine();
						}
 					}
			}
        
			catch (IOException e) {
				System.out.println("Hata: " + e.getMessage());
			}
			quiz.delete();
            newQuiz.renameTo(quiz);

		}
		else {
			File quiz = new File(subject+".txt");
			File newQuiz= new File("temp.txt");
		
			try (BufferedReader br = new BufferedReader(new FileReader(quiz)); BufferedWriter bw = new BufferedWriter(new FileWriter(newQuiz))){
				String line;
				while ((line = br.readLine()) != null) {
					//hoca ismi | quiz ismi | soru
					String[] splits = line.split("|");
        		 
        			 
						if(splits[1].equals(quizName)) {
							bw.write( splits[0] +"|"+ splits[1] +"|");
				
							String allQuestionLine = splits[2];
	 						String[] oneQuestionSplits = allQuestionLine.split("*");
	 						
							String questionNoString = Integer.toString(questionNo);
	 						int i=1;
	 					
	 					while(i<=oneQuestionSplits.length) {
	 								
							String questionLine2 = oneQuestionSplits[i-1];
							String[] questionSplits2 = questionLine2.split("#");
 						
 						
							if(questionSplits2[0].equals(questionNoString)) {
								
								if(questionSplits2[5] == "MC") {
									
									String[] choices = newTarget.split("¿");//soru¿şık1¿şık2... diye geliyor
								
									bw.write(questionSplits2[0] +"#");
								
									bw.write( choices[0] +"¡"+ choices[1] +"¡"+ choices[2]  +"¡"+ choices[3] +"¡"+ choices[4] +"#");
								
									bw.write(questionSplits2[2] +"#"+ questionSplits2[3] +"#"+ questionSplits2[4] +"#"+ questionSplits2[5] +"*");
								}
								else {
									
									bw.write(questionSplits2[0] +"#"+ newTarget +"#"+ questionSplits2[2] +"#"+ questionSplits2[3] +"#"+ questionSplits2[4] +"#"+ questionSplits2[5] +"*");
								}
							}
							else {
								bw.write( questionSplits2[0] +"#"+ questionSplits2[1] +"#"+ questionSplits2[2] +"#"+ questionSplits2[3] +"#"+ questionSplits2[4] +"#"+ questionSplits2[5] +"*");
							}
							i++;
						}
	 					bw.newLine();
						}
						else {
							bw.write( splits[0] +"|"+ splits[1] +"|"+ splits[2]);
							bw.newLine();
						}
 					}
			}
			catch (IOException e) {
				System.out.println("Hata: " + e.getMessage());
			}
			quiz.delete();
            newQuiz.renameTo(quiz);

		}
	}	
	public static void change(String subject, String quizName,int questionNo, Difficulty newTarget) {
		
		File quiz = new File(subject+".txt");
        File newQuiz= new File("temp.txt");
		
        try (BufferedReader br = new BufferedReader(new FileReader(quiz)); BufferedWriter bw = new BufferedWriter(new FileWriter(newQuiz))){
        	String line;
        	
        	while ((line = br.readLine()) != null) {
        		 //hoca ismi | quiz ismi | soru
        		 String[] splits = line.split("|");
        		 
        			 
 					if(splits[1].equals(quizName)) {
 						
 						bw.write( splits[0] +"|"+ splits[1] +"|");
 						String allQuestionLine = splits[2];
 						String[] oneQuestionSplits = allQuestionLine.split("*");
 						
 						String questionNoString = Integer.toString(questionNo);
 						int i=1;
 							while(i<=oneQuestionSplits.length) {
 								
 								String questionLine2 = oneQuestionSplits[i-1];
 		 						String[] questionSplits2 = questionLine2.split("#");
 								
 		 						if(questionSplits2[0].equals(questionNoString)) {
 							
 									bw.write( questionSplits2[0] +"#"+ questionSplits2[1] +"#"+ questionSplits2[2] +"#"+ questionSplits2[3] +"#"+ newTarget +"#"+ questionSplits2[5] +"*");
 								}
 								else {
 									bw.write( questionSplits2[0] +"#"+ questionSplits2[1] +"#"+ questionSplits2[2] +"#"+ questionSplits2[3] +"#"+ questionSplits2[4] +"#"+ questionSplits2[5] +"*");
 								}
 								i++;
 							}
 							bw.newLine();
 					}
 					else {
 						bw.write( splits[0] +"|"+ splits[1] +"|"+ splits[2]);
 						bw.newLine();
 					}
 				}
        }
        
        catch (IOException e) {
            System.out.println("Hata: " + e.getMessage());
            }
            quiz.delete();
            newQuiz.renameTo(quiz);
    }    
	public static void change(String subject, String quizName,int questionNo, int newTarget) {
		
		File quiz = new File(subject+".txt");
        File newQuiz= new File("temp.txt");
		
        try (BufferedReader br = new BufferedReader(new FileReader(quiz)); BufferedWriter bw = new BufferedWriter(new FileWriter(newQuiz))){
        	String line;
        	 while ((line = br.readLine()) != null) {
        		 //hoca ismi | quiz ismi | soru
        		 String[] splits = line.split("|");
        		 
        		 if(splits[1].equals(quizName)) {
						
						bw.write( splits[0] +"|"+ splits[1] +"|");
						String allQuestionLine = splits[2];
						String[] oneQuestionSplits = allQuestionLine.split("*");
						
						String questionNoString = Integer.toString(questionNo);
						int i=1;
							while(i<=oneQuestionSplits.length) {
								
								String questionLine2 = oneQuestionSplits[i-1];
		 						String[] questionSplits2 = questionLine2.split("#");
								
		 						if(questionSplits2[0].equals(questionNoString)) {
							
									bw.write( questionSplits2[0] +"#"+ questionSplits2[1] +"#"+ questionSplits2[2] +"#"+ newTarget +"#"+ questionSplits2[4] +"#"+ questionSplits2[5] +"*");
								}
								else {
									bw.write( questionSplits2[0] +"#"+ questionSplits2[1] +"#"+ questionSplits2[2] +"#"+ questionSplits2[3] +"#"+ questionSplits2[4] +"#"+ questionSplits2[5] +"*");
								}
								i++;
							}
							bw.newLine();
        		 }
 				else {
 					bw.write( splits[0] +"|"+ splits[1] +"|"+ splits[2]);
 					bw.newLine();
 					}
 				}
        }
        
        catch (IOException e) {
            System.out.println("Hata: " + e.getMessage());
            }
            quiz.delete();
            newQuiz.renameTo(quiz);
	}
	public static void remove(String subject, String quizName,int questionNo) {
		File quiz = new File(subject+".txt");
        File newQuiz= new File("temp.txt");
		
        try (BufferedReader br = new BufferedReader(new FileReader(quiz)); BufferedWriter bw = new BufferedWriter(new FileWriter(newQuiz))){
        	String line;
        	 while ((line = br.readLine()) != null) {
        		 //hoca ismi | quiz ismi | soru
        		 String[] splits = line.split("|");
        		 
        		 if(splits[1].equals(quizName)) {
						
						bw.write( splits[0] +"|"+ splits[1] +"|");
						String allQuestionLine = splits[2];
						String[] oneQuestionSplits = allQuestionLine.split("*");
						
						String questionNoString = Integer.toString(questionNo);
						int i=1;
						int questionNoCounter = 1;
							while(i<=oneQuestionSplits.length) {
								
								String questionLine2 = oneQuestionSplits[i-1];
		 						String[] questionSplits2 = questionLine2.split("#");
								
		 						if(questionSplits2[0].equals(questionNoString)) {
		 							i++;
		 							continue;
								}
								else {
									bw.write( questionNoCounter +"#"+ questionSplits2[1] +"#"+ questionSplits2[2] +"#"+ questionSplits2[3] +"#"+ questionSplits2[4] +"#"+ questionSplits2[5] +"*");
								}
								i++;
								questionNoCounter++;
							}
							bw.newLine();
        		 }
 				else {
 					bw.write( splits[0] +"|"+ splits[1] +"|"+ splits[2]);
 					bw.newLine();
 					}
 				}
        }
        
        catch (IOException e) {
            System.out.println("Hata: " + e.getMessage());
            }
            quiz.delete();
            newQuiz.renameTo(quiz);
	}
	public static String show(String subject, String quizName,int questionNo, String target) {
//neden leak hatası veriyor ? Yanına parantez ile yazınca ne değişti?		
		File quiz = new File(subject+".txt");
		
		try (Scanner fileInput = new Scanner(quiz)){
	
			while(fileInput.hasNextLine()) {
				
				String line = fileInput.nextLine();
				String[] splits = line.split("|");
				
				
				String questionNoString = Integer.toString(questionNo);
				
				
				if(splits.length==3) {
					
					if(splits[1].equals(quizName)) {
						
						String allQuestionLine = splits[2];
						String[] oneQuestionSplits = allQuestionLine.split("*");
						
						
						String questionLine2 = oneQuestionSplits[questionNo-1];
						String[] questionSplits2 = questionLine2.split("#");
								
						if(questionSplits2[0].equals(questionNoString)) {
							
							switch(target) {
						
							case "question":
								fileInput.close();
								return questionSplits2[1];
			
							case "answer":
								fileInput.close();
								return questionSplits2[2];
						
							case "points":
								fileInput.close();
								return questionSplits2[3];
							
							case "dif":
									fileInput.close();
									return questionSplits2[4];
							
							case "type":
								fileInput.close();
								return questionSplits2[5];
							
							default:
								System.out.println("Hata!");
								fileInput.close();
								return null;
							}
						}
					}
				}
				else {
					System.out.println("Hata! Bozuk dosya yapısı.");
					fileInput.close();
					return null;
				}
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("Hata!");
			return null;
		}
		System.out.println("Hata! Quiz bulunamadı");
		return null;
	}
	public static void removeQuiz(String subject, String quizName, int questionCounter) {
		
		for(int i=1;i<=questionCounter;i++) {  
			QuizOp.remove(subject, quizName, i);
		}
		File quiz = new File(subject+".txt");
        File newQuiz= new File("temp.txt");
		
        try (BufferedReader br = new BufferedReader(new FileReader(quiz)); BufferedWriter bw = new BufferedWriter(new FileWriter(newQuiz))){
        	String line;
        	 while ((line = br.readLine()) != null) {
        		 //hoca ismi | quiz ismi | soru
        		 String[] splits = line.split("|");
        		 
        		 if(splits[1].equals(quizName)) {
						continue;
				}
 				else {
 					bw.write( splits[0] +"|"+ splits[1] +"|"+ splits[2]);
 					bw.newLine();
 				}
 			}
        }
        
        catch (IOException e) {
            System.out.println("Hata: " + e.getMessage());
            }
            quiz.delete();
            newQuiz.renameTo(quiz);

	}
	public static void finishQuiz(String subject, String quizName) {

		String fileName = subject + ".txt";
		
		try{
            FileWriter printer = new FileWriter(fileName,true);
            printer.write("\n");
            printer.close();
        }
       catch(IOException e){
           System.out.println("Quiz tamamlanamadı.\n");
       }
	}
	public static void createQuiz(String subject, String quizName, String userName) {

		String fileName = subject + ".txt";
		
		try{
            FileWriter printer = new FileWriter(fileName,true);
            printer.write(userName +"|"+ quizName +"|");
            printer.close();
        }
       catch(IOException e){
           System.out.println("Quiz oluşturulamadı!\n");
       }
	}
	public static void showAllQuiz(String subject, String quizName, String userName, long password, List<String> answers) {
		
		File quiz = new File(subject+".txt");//quiz boyutu için
		
		try (Scanner fileInput = new Scanner(quiz)){
	
			while(fileInput.hasNextLine()) {
				
				String line = fileInput.nextLine();
				String[] splits = line.split("|");
				
				if(splits.length==3) {
					
					if(splits[1].equals(quizName)) {
					
						System.out.println(splits[1]+"\n\n");
						String allQuestionLine = splits[2];
						String[] oneQuestionSplits = allQuestionLine.split("*");
						
						for(int i=1; i <= oneQuestionSplits.length - 1; i++ ) {
							System.out.println(i+".soru\n");
							
							if(QuizOp.show(subject,quizName,i,"type") == "MC") {
								String questionLine = QuizOp.show(subject,quizName,i,"question");
								String[] questionSplits = questionLine.split("¿");
								System.out.println(questionSplits[0]+"\n");
								System.out.println("A)"+ questionSplits[1]);
								System.out.println("B)"+ questionSplits[2]);
								System.out.println("C)"+ questionSplits[3]);
								System.out.println("D)"+ questionSplits[4] + "\n");
								System.out.println("Cevap: "+ QuizOp.show(subject,quizName,i,"answer"));
								
								if(User.show(userName,password,"role") == "Student")
									System.out.println("Verdiğiniz cevap: "+ answers.get(i-1));
								
							}
							else {
								System.out.println("Cevap: "+ QuizOp.show(subject,quizName,i,"question"));
								String answer = QuizOp.show(subject,quizName,i,"answer");
								System.out.println("A) Doğru\nB) Yanlış\n");
								if (answer == "True") System.out.println("Cevap: Doğru"); 
								else if (answer == "False") System.out.println("Cevap: Yanlış"); 
								if(User.show(userName,password,"role") == "Student")
									System.out.println("Verdiğiniz cevap: "+ answers.get(i-1));
							}
		         		 	
		         			String dif = QuizOp.show(subject,quizName,i,"dif");
		         			if (dif == "EASY") System.out.println("Zorluk türü: Kolay"); 
		             		else if (dif == "MEDİUM") System.out.println("Zorluk türü: Orta"); 
		             		else if (dif == "HARD") System.out.println("Zorluk türü: Zor"); 
		         			System.out.println("Puanı: "+ QuizOp.show(subject,quizName,i,"points"));
					 }
				  }
				}
				else {
					System.out.println("Hata! Bozuk dosya yapısı.");
					fileInput.close();
				}
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("Hata!");
		}
		System.out.println("Hata! Quiz bulunamadı");
	}
	public static List<String> quizList(String userName, String subject) {
		
		List<String> quizes = new ArrayList<>();
		File quiz = new File(subject+".txt");
		
		try (Scanner fileInput = new Scanner(quiz)){
	
			while(fileInput.hasNextLine()) {
				
				String line = fileInput.nextLine();
				String[] splits = line.split("|");
				
				
				if(splits.length==3) {
					
					if(splits[0].equals(userName))
						quizes.add(splits[1]);
						
					}
				else {
					
					System.out.println("Hata! Bozuk dosya yapısı.");
					fileInput.close();
					return null;
				}
			}
			return quizes ;
		}
		catch (FileNotFoundException e) {
			System.out.println("Hata!");
			return null;
		}	
	}
	public static void scoresList(String userName,long password, String name, String sName){
		
		if(Main.isExist(name,sName)) {
			
			if(User.show(userName, password, "role") == "Teacher") {
				File scores = new File("scores.txt");
				
				try (Scanner fileInput = new Scanner(scores)){
			
					while(fileInput.hasNextLine()) {
						
						String line = fileInput.nextLine();
						String[] splits = line.split("|");
												
						if(splits.length==4) {
							
							if(splits[0].equals(userName)) {
								
									String allQuizLine = splits[3];
									String[] oneQuizSplits = allQuizLine.split("*");
									System.out.println(User.show(userName, password, "subject") +"\n\n");
									for(int i=0; i<oneQuizSplits.length; i++) {
										String quizLine2 = oneQuizSplits[i];
										String[] quizSplits2 = quizLine2.split("#");
										
										if(quizSplits2[0].equals(User.show(userName, password,"subject"))) {
											System.out.println("Quiz ismi: "+ quizSplits2[1] +"Puanı:  "+ quizSplits2[2] +"\n");												
										}
									}
							}
						}
						else {
							System.out.println("Hata! Bozuk dosya yapısı.");
							fileInput.close();
						}
				}
			}
			catch (FileNotFoundException e) {
				System.out.println("Hata!");
			}
		System.out.println("Hata! Quiz bulunamadı");
		}
			else if(User.show(userName, password, "role") == "Student"){
				List<String> subjects = new ArrayList<>();
				File scores = new File("scores.txt");
			
				try (Scanner fileInput = new Scanner(scores)){
		
					while(fileInput.hasNextLine()) {
					
						String line = fileInput.nextLine();
						String[] splits = line.split("|");
											
						if(splits.length==4) {
						
							if(splits[1].equals(name) && splits[2].equals(sName)) {
							
								String allQuizLine = splits[3];
								String[] oneQuizSplits = allQuizLine.split("*");
								for(int i=0; i<oneQuizSplits.length-1; i++) {
									String quizLine2 = oneQuizSplits[i];
									String[] quizSplits2 = quizLine2.split("#");
									if(!subjects.contains(quizSplits2[0]))
										subjects.add(quizSplits2[0]);
								}
								for(int j=0;j<subjects.size();j++) {
									System.out.println(subjects.get(j) +"\n\n");
									for(int i=0; i<oneQuizSplits.length-1; i++) {
										String quizLine2 = oneQuizSplits[i];
										String[] quizSplits2 = quizLine2.split("#");
										if(quizSplits2[0].equals(subjects.get(j))) {
											if(quizSplits2[2]!= "-1")
												System.out.println("Quiz ismi: "+ quizSplits2[1] +"Puanı:  "+ quizSplits2[2]);	
											else
												System.out.println("Quiz ismi: "+ quizSplits2[1] +"Puanı:  Girilmedi");
										}
									}
								}
							}
						}
						else {
							System.out.println("Hata! Bozuk dosya yapısı.");
							fileInput.close();
						}
					}
				}
				catch (FileNotFoundException e) {
					System.out.println("Hata!");
				}
			}
			else {
				System.out.println("Hata! Yetkisiz erişim.");
		}
		}
		System.out.println("Hata! Öğrenci bulunamadı.");
	}
	public static List<String> unsolvedQuizes(String userName) {
		List<String> quizes = new ArrayList<>();
		File scores = new File("scores.txt");
		
		try (Scanner fileInput = new Scanner(scores)){

			while(fileInput.hasNextLine()) {
			
				String line = fileInput.nextLine();
				String[] splits = line.split("|");
									
				if(splits.length==4) {
				
					if(splits[0].equals(userName)) {
					
						String allQuizLine = splits[3];
						String[] oneQuizSplits = allQuizLine.split("*");
						for(int i=0; i<oneQuizSplits.length-1; i++) {
							String quizLine2 = oneQuizSplits[i];
							String[] quizSplits2 = quizLine2.split("#");
							if(quizSplits2[2]== "-1")
								quizes.add(quizSplits2[0] +"#"+ quizSplits2[1] );			
						}
						return quizes;
					}
				}
				else {
					System.out.println("Hata! Bozuk dosya yapısı.");
					fileInput.close();
					return null;
				}
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("Hata!");
			return null;
		}
		return null;
	}
	public static void enterQuiz(String subject,String quizName, String userName, long password) {
		
		try {
			List<String> answers = new ArrayList<>();
			File quiz = new File(subject+".txt");//quiz boyutu için
			
			try (Scanner fileInput = new Scanner(quiz)){

				while(fileInput.hasNextLine()) {
					
					String line = fileInput.nextLine();
					String[] splits = line.split("|");
					
					if(splits.length==3) {
						
						if(splits[1].equals(quizName)) {
						
							System.out.println(splits[1]+"\n\n");
							String allQuestionLine = splits[2];
							String[] oneQuestionSplits = allQuestionLine.split("*");
							
							for(int i=1; i <= oneQuestionSplits.length - 1; i++ ) {
								System.out.println(i+".soru\n");
								String dif = QuizOp.show(subject,quizName,i,"dif");
			         			if (dif == "EASY") System.out.println("Zorluk türü: Kolay"); 
			             		else if (dif == "MEDİUM") System.out.println("Zorluk türü: Orta"); 
			             		else if (dif == "HARD") System.out.println("Zorluk türü: Zor"); 
			         			System.out.println("Puanı: "+ QuizOp.show(subject,quizName,i,"points")+"\n");
			         			String answer = null;
								if(QuizOp.show(subject,quizName,i,"type") == "MC") {
									String questionLine = QuizOp.show(subject,quizName,i,"question");
									String[] questionSplits = questionLine.split("¿");
									System.out.println(questionSplits[0]+"\n");
									System.out.println("A)"+ questionSplits[1]);
									System.out.println("B)"+ questionSplits[2]);
									System.out.println("C)"+ questionSplits[3]);
									System.out.println("D)"+ questionSplits[4] + "\n");
									System.out.println("Cevabınızı giriniz.");
									boolean check=true;
									while(check) {
					          			answer = input.nextLine();
					        			switch(answer) {
					        			
					        			case "a","A":
					        				answer = "A";
					        				check = false;
					        				break;
					        				
					        			case "b","B":
					        				answer = "B";
					        				check = false;
							        		break;
							        		
					        			case "c","C":
					        				answer = "C";	
					        				check = false;
					        			
					        			case"d","D":
					        				answer = "D";
					        				check = false;
					        				
					        			default:
					        				System.out.println("Geçersiz değer! Tekrar giriniz.");
					        				break;
					        		 	}
									}
								}
								else {
									System.out.println(QuizOp.show(subject,quizName,i,"question")+"\n");
									System.out.println("A) Doğru\nB) Yanlış\n");
									System.out.println("Cevabınızı giriniz.");
									boolean check=true;
									while(check) {
										answer = input.nextLine();
					        			switch(answer) {
					        			
					        			case "a","A":
					        				answer = "A";
					        				check = false;
					        				break;
					        				
					        			case "b","B":
					        				answer = "B";
					        				check = false;
							        		break;
							        		
					        			default:
					        				System.out.println("Geçersiz değer! Tekrar giriniz.");
					        				break;
					        		 	}
								}		
						 }
						answers.add(answer);		
					  }
							System.out.println("Tüm soruları cevapladınız.\n");
							System.out.println("1)Quizi tamamla");
							System.out.println("2)Cevabı düzenle");
							while(true) {
			          			int choice = input.nextInt();
			        			if(choice==1) {
			        				addScore(userName,password,subject,quizName,calcScore(subject, quizName,answers));
			        				System.out.println("Quiz tamamlandı.");
			        				System.out.println("Puanınız: " + calcScore(subject, quizName,answers));
			        				System.out.println("Quizi görüntülemek ister misiniz?");
			        				System.out.println("1) Evet");
									System.out.println("2) Hayır");
			        				while(true) {
					          			int choice2 = input.nextInt();
					        			if(choice2==1) {
					        				QuizOp.showAllQuiz(subject, quizName, userName, password, answers);
					        				Student.callUI(userName,password);
					        			}
					        			else if(choice2==2) {
					        				Student.callUI(userName,password);
					        			}
					        			else
					        				System.out.println("Geçersiz değer tekrar giriniz.");
			        				}
			        			}
			        			else if (choice==2) {
			        				
			        				System.out.println("Kaçıncı soruyu değiştirmek istersiniz?");
			        				int questionNo = input.nextInt();
			        				System.out.println(questionNo+".soru\n");
									String dif = QuizOp.show(subject,quizName,questionNo,"dif");
				         			if (dif == "EASY") System.out.println("Zorluk türü: Kolay"); 
				             		else if (dif == "MEDİUM") System.out.println("Zorluk türü: Orta"); 
				             		else if (dif == "HARD") System.out.println("Zorluk türü: Zor"); 
				         			System.out.println("Puanı: "+ QuizOp.show(subject,quizName,questionNo,"points")+"\n");
				         			String answer = null;
									if(QuizOp.show(subject,quizName,questionNo,"type") == "MC") {
										String questionLine = QuizOp.show(subject,quizName,questionNo,"question");
										String[] questionSplits = questionLine.split("¿");
										System.out.println(questionSplits[0]+"\n");
										System.out.println("A)"+ questionSplits[1]);
										System.out.println("B)"+ questionSplits[2]);
										System.out.println("C)"+ questionSplits[3]);
										System.out.println("D)"+ questionSplits[4] + "\n");
										System.out.println("Cevabınızı giriniz.");
										boolean check;
										while(check = true) {
						          			answer = input.nextLine();
						        			switch(answer) {
						        			
						        			case "a","A":
						        				answer = "A";
						        				check = false;
						        				break;
						        				
						        			case "b","B":
						        				answer = "B";
						        				check = false;
								        		break;
								        		
						        			case "c","C":
						        				answer = "C";	
						        				check = false;
						        			
						        			case"d","D":
						        				answer = "D";
						        				check = false;
						        				
						        			default:
						        				System.out.println("Geçersiz değer! Tekrar giriniz.");
						        				break;
						        		 	}
										}
									}
									else {
										System.out.println(QuizOp.show(subject,quizName,questionNo,"question")+"\n");
										System.out.println("A) Doğru\nB) Yanlış\n");
										System.out.println("Cevabınızı giriniz.");
										boolean check;
										while(check=true) {
											answer = input.nextLine();
						        			switch(answer) {
						        			
						        			case "a","A":
						        				answer = "A";
						        				check = false;
						        				break;
						        				
						        			case "b","B":
						        				answer = "B";
						        				check = false;
								        		break;
								        		
						        			default:
						        				System.out.println("Geçersiz değer! Tekrar giriniz.");
						        				break;
						        		 	}
									}		
							 }
							answers.set(questionNo-1,answer);
							System.out.println("Başarıyla değiştirildi.");
							System.out.println("1)Quizi tamamla");
							System.out.println("2)Cevabı düzenle");
			        		}
			        			else
			        				System.out.println("Geçersiz değer tekrar giriniz.");
						}
					}
				}
			}
			}
			catch (FileNotFoundException e) {
				System.out.println("Hata!");
			}
			System.out.println("Hata! Quiz bulunamadı");
		} catch (Exception e) {
			System.out.println("Hata! Quizi tekrar çözünüz.");
			enterQuiz(subject, quizName, userName, password);
		}
	}
	private static void addScore(String userName,long password,String subject, String quizName, int score) {
		String name = User.show(userName, password,"name");
		String sName = User.show(userName, password,"sName");
		try{
            FileWriter printer = new FileWriter("scores.txt",true);
            printer.write( userName +"|"+ name +"|"+ sName +"|"+ quizName +"#"+ subject +"#"+ score+ "\n");
            printer.close();
        }
       catch(IOException e){
           System.out.println("Puan eklenemedi.\n");
       }
		
	}
	private static int calcScore(String subject, String quizName, List<String> answers) {
		int score=0;
		for(int i=0;i<answers.size();i++) {
			String pointsString = QuizOp.show(subject, quizName, i+1, "points");
			int points = Integer.parseInt(pointsString);
			if(answers.get(i)== QuizOp.show(subject, quizName, i+1, "answer"))
				score+=points;
		}
		return score;
	}
	}