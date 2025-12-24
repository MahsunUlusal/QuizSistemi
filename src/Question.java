import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Question implements FileOp {

	public enum Type{
		TF,MC
	}
	public enum Difficulty{
		Hard,Medium,Easy
	}
	
	public void add (int counter, String question, String answer, int points, String subject, Difficulty dif, Type type) {

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
							String questionLine2 = questionSplits[questionNo-1];
							String[] questionSplits2 = questionLine2.split("#");
 						
 						
							if(questionSplits2[0].equals(questionNo-1)) {
 							
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
							String questionLine = splits[2];
							String[] questionSplits = questionLine.split("*");
							String questionLine2 = questionSplits[questionNo-1];
							String[] questionSplits2 = questionLine2.split("#");
 						
 						
							if(questionSplits2[0].equals(questionNo-1)) {
								
								String questionLine3 = questionSplits2[1];
								String[] questionSplits3 = questionLine3.split("¡");
								
								String[] choices = newTarget.split("¿");
								
								bw.write(questionSplits2[0] +"#"+ questionSplits2[1] +"#");
								
								bw.write( choices[0] +"¡"+ choices[1] +"¡"+ choices[2]  +"¡"+ choices[3] +"#");
								
								bw.write(questionSplits2[3] +"#"+ questionSplits2[4] +"#"+ questionSplits2[5] +"*");
								System.out.println("Başarıyla değiştirildi.");
								break;
							}
							else {
								bw.write( questionSplits2[0] +"#"+ questionSplits2[1] +"#"+ questionSplits2[2] +"#"+ questionSplits2[3] +"#"+ questionSplits2[4] +"#"+ questionSplits2[5] +"*");
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
 						String questionLine = splits[2];
 						String[] questionSplits = questionLine.split("*");
 						String questionLine2 = questionSplits[questionNo-1];
 						String[] questionSplits2 = questionLine2.split("#");
 						
 						
 						if(questionSplits2[0].equals(questionNo-1)) {
 							
 							bw.write( questionSplits2[0] +"#"+ questionSplits2[1] +"#"+ questionSplits2[2] +"#"+ questionSplits2[3] +"#"+ newTarget +"#"+ questionSplits2[5] +"*");
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
 						String questionLine = splits[2];
 						String[] questionSplits = questionLine.split("*");
 						String questionLine2 = questionSplits[questionNo-1];
 						String[] questionSplits2 = questionLine2.split("#");
 						
 						
 						if(questionSplits2[0].equals(questionNo-1)) {
 							
 							bw.write( questionSplits2[0] +"#"+ questionSplits2[1] +"#"+ questionSplits2[2] +"#"+ newTarget +"#"+ questionSplits2[4]+"#"+ questionSplits2[5] +"*");
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

	public void remove() {


	}

	public String show() {

		return null;
	}

}
