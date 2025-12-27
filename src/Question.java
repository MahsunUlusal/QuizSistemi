import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public abstract class Question implements FileOp{

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
						else {
						System.out.println("Hata!");
						fileInput.close();
						return null;
						}
					}
					else {
						System.out.println("Hata!");
						fileInput.close();
						return null;
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
			return null;
		}
		System.out.println("Hata!");
		return null;
	}
	public static void removeQuiz(String subject, String quizName, int questionCounter) {
		
		for(int i=1;i<=questionCounter;i++) {  
			Question.remove(subject, quizName, i);
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

}