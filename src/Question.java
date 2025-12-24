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

	public static void change(String quizName,int questionNo, String target, String newTarget) {

	}
	
	public static void change(String quizName,int questionNo, Difficulty newTarget) {

	}
	
	public static void change(String quizName,int questionNo, Type newTarget) {

	}
	public static void change(String quizName,int questionNo, int newTarget) {

	}

	public void remove() {


	}

	public String show() {

		return null;
	}

}
