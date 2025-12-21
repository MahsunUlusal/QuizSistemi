import java.io.FileWriter;
import java.io.IOException;

public class TrueFalse {
String question,answer,subject;
int points;

public TrueFalse(String question, String answer, String subject, int points) {
	this.question = question;
	this.answer = answer;
	this.subject = subject;
	this.points = points;
}

public void Write() {
	
	String fileName=subject+".txt";
	
	try { 
	FileWriter printer = new FileWriter(fileName, true); 
	printer.write(question +";"+ answer +";"+ points +";"+ "TF" + ":"); 
	printer.close(); 
	System.out.println("Soru eklendi.\n"); 
	} 
	catch (IOException e) { 
		System.out.println("Soru eklenemedi.\n"); 
	} 

  }



}
