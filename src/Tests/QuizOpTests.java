package Tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import FileOp.QuizOp;
import FileOp.QuizOp.Difficulty;
import FileOp.QuizOp.Type;

public class QuizOpTests {
	
	QuizOp quizOp = new QuizOp();
	
	String testSubject = "testSubject"; 
	String testQuizName = "testQuizName";
	String testTeacher = "testTeacher";
	
	File quizFile = new File("txt/"+ testSubject +".txt");
	
	@DisplayName("Quiz oluşturma ve soru ekleme")
	@BeforeEach
    void setUp() {
		quizOp.createQuiz(testSubject, testQuizName, testTeacher);
		assertTrue(quizFile.exists(), "Ders dosyası oluşturulamadı!");
		assertTrue(quizFile.length() > 0, "Quiz oluşturulamadı!");
		
		long firstSize = quizFile.length();
		quizOp.add(1, "Java hangisidir?¿Dil¿Ressam¿Oyun Adi¿Araba Markasi", "A", 10, testSubject, Difficulty.Easy, Type.MC);
		assertTrue(quizFile.length() > firstSize, "Soru eklenemedi");
		long firstSize2 = quizFile.length();
		quizOp.add(2, "Java bir dildir.", "True", 20, testSubject, Difficulty.Hard, Type.TF);
		assertTrue(quizFile.length() > firstSize2, "Soru eklenemedi.");	
		
    }
	
	@DisplayName("Quiz ve soru silme")
	@AfterEach
    void tearDown() {
		quizOp.remove(testSubject, testQuizName, 1);
		quizOp.remove(testSubject, testQuizName, 2);

		if(quizFile.exists()) {
			quizOp.removeQuiz( testSubject, testQuizName, 2);
			quizFile.delete();
		}
	}
	
	@DisplayName("Soru gösterme")
	@Test
    void testShowQuestion() { 
		
		assertEquals(quizOp.show(testSubject, testQuizName,1, "question"), "Java hangisidir?¿Dil¿Ressam¿Oyun Adi¿Araba Markasi");
		assertEquals(quizOp.show(testSubject, testQuizName,1, "answer"), "A");
		assertEquals(quizOp.show(testSubject, testQuizName,1, "points"), "10");
		assertEquals(quizOp.show(testSubject, testQuizName,1, "dif"), "Easy");
		assertEquals(quizOp.show(testSubject, testQuizName,1, "type"), "MC");
		
		assertEquals(quizOp.show(testSubject, testQuizName,2, "question"), "Java bir dildir.");
		assertEquals(quizOp.show(testSubject, testQuizName,2, "answer"), "True");
		assertEquals(quizOp.show(testSubject, testQuizName,2, "points"), "20");
		assertEquals(quizOp.show(testSubject, testQuizName,2, "dif"), "Hard");
		assertEquals(quizOp.show(testSubject, testQuizName,2, "type"), "TF");

    }
	
	@DisplayName("Soru değiştirme")
	@Test
    void testChange() {
		quizOp.change(testSubject,testQuizName, 2, "question", "Eclipse bir dildir.");
		quizOp.change(testSubject,testQuizName, 2, "answer", "False");
		quizOp.change(testSubject,testQuizName, 2, 25);
		quizOp.change(testSubject,testQuizName, 2, Difficulty.Medium);
		assertEquals(quizOp.show(testSubject, testQuizName,2, "question"), "Eclipse bir dildir.");
		assertEquals(quizOp.show(testSubject, testQuizName,2, "answer"), "False");
		assertEquals(quizOp.show(testSubject, testQuizName,2, "points"), "25");
		assertEquals(quizOp.show(testSubject, testQuizName,2, "dif"), "Medium");

	}
	
	@DisplayName("Puan hesaplama")
	@Test
    void testCalScore() {
		List<String> answers = new ArrayList<>();
		answers.add("A");
		answers.add("True");
		int score = quizOp.calcScore(testSubject, testQuizName,answers);
		assertEquals(score, 30);
	}
	
}