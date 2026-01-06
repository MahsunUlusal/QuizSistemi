package FileOp;
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

import Main.Main;


public class QuizOp extends FileOp{
	
	static Scanner input = new Scanner(System.in);
	
	public enum Type{
		TF,MC
	}
	public enum Difficulty{
		Hard,Medium,Easy
	}
	
	/**
	 * Soruyu dosyaya yazdırma/ekleme fonksiyonu
	 * @param counter = Quizde yaratılmış kaçıncı soru olduğu 
	 * @param question = Soru metni
	 * @param answer = Cevap
	 * @param points = Sorunun puanı
	 * @param subject = Quizin hangi derse ait olduğu. (Dosyayı bulmak için gerekli)
	 * @param dif = Zorluk seviyesi
	 * @param type = Soru türü
	 */
	public void add(int counter, String question, String answer, int points, String subject, Difficulty dif, Type type) {
		// Ders ismindeki txt dosyasını açıp içine soruNo#soruMetni#cevap#puanı#zorluğu#türü şeklinde soruyu yazıyoruz.
		String fileName = "txt/"+ subject + ".txt";
		try(FileWriter printer = new FileWriter(fileName,true)){
			printer.write( counter +"#"+ question +"#"+ answer +"#"+ points +"#"+ dif +"#"+  type +"*");
			System.out.println("Soru eklendi.\n");
		}
		catch(IOException e){
			System.out.println("Soru eklenemedi.\n");
		}
	}

	/**
	 * Dosyadaki soruyu değiştirme fonksiyonlarından biri. Sorunun soru metnini ya da cevabını düzenler. 
	 * @param subject = Quizin hangi derse ait olduğu. (Dosyayı bulmak için gerekli.)
	 * @param quizName = Quiz ismi. Hangi quizden soru değiştirileceği bilmek için gerekli.
	 * @param questionNo = Soru numarası.
	 * @param target = Değiştirilmek istenen veriyi tanımlar.
	 * @param newTarget = Değiştirilmek istenen verinin son halini tanımlar.
	 */
	public void change(String subject,String quizName,int questionNo, String target, String newTarget) {
		
		if(target.equals("answer")) { // Hedef cevap ise
			File quiz = new File( "txt/"+subject+".txt"); //quiz bilgilerini alıcağımız dosya
			File newQuiz= new File("txt/temp.txt"); // bilgileri geçireceğimiz dosya
		
			try (BufferedReader br = new BufferedReader(new FileReader(quiz)); BufferedWriter bw = new BufferedWriter(new FileWriter(newQuiz))){
				String line;  // Ders dosyası içindeki quiz satırlarını alıyoruz
				while ((line = br.readLine()) != null) {
					String[] splits = line.split("\\|"); // Quiz bilgileri dosyada öğretmeninKullanıcıAdı|quizİsmi|soru1*soru2... diye tutuluyor burda parçalarına ayırıyoruz
					
					if(splits.length >= 2 && splits[1].equals(quizName)) { // aradığımız quiz ise
							
							bw.write( splits[0] +"|"+ splits[1] +"|"); // Öğretmen bilgisi ve quiz ismini aynen yazıyoruz
							String allQuestionLine = splits[2]; // soruların olduğu parçayı alıyoruz
							String[] oneQuestionSplits = allQuestionLine.split("\\*");  // soruları ayırıyoruz
							
							String questionNoString = Integer.toString(questionNo);

							for(String q : oneQuestionSplits) {
								String[] questionSplits2 = q.split("#"); // Soruları parçalara ayırıyoruz
								if(questionSplits2[0].equals(questionNoString)) { // Aradığımız soru ise
									bw.write( questionSplits2[0] +"#"+ questionSplits2[1] +"#"+ newTarget +"#"+ questionSplits2[3] +"#"+ questionSplits2[4] +"#"+ questionSplits2[5] +"*");
									System.out.println("Başarıyla değiştirildi.");
									//yeni haliyle tekrar yazıyoruz
								} else {
									bw.write( questionSplits2[0] +"#"+ questionSplits2[1] +"#"+ questionSplits2[2] +"#"+ questionSplits2[3] +"#"+ questionSplits2[4] +"#"+ questionSplits2[5] +"*");
								// aradığımız soru değilse aynen yazıyoruz
								}
							}
					}
					else { // aradığımız quiz değilse aynen yazıyoruz
						if(splits.length >= 3)
							bw.write( splits[0] +"|"+ splits[1] +"|"+ splits[2]);
						else
							bw.write(line); 
						bw.newLine();
					}
				}
			}
			catch (IOException e) {
				System.out.println("Hata: " + e.getMessage());
			}
			quiz.delete(); // önceki quiz dosyasını siliyoruz
			newQuiz.renameTo(quiz); //bilgileri geçirdiğimiz dosyayı yeniden adlandırıyoruz

		}
		else { // hedef soru metni ise
			File quiz = new File( "txt/"+subject+".txt"); 
			File newQuiz= new File("txt/temp.txt");
		
			try (BufferedReader br = new BufferedReader(new FileReader(quiz)); BufferedWriter bw = new BufferedWriter(new FileWriter(newQuiz))){
				String line;
				while ((line = br.readLine()) != null) {
					String[] splits = line.split("\\|");
					
					if(splits.length >= 2 && splits[1].equals(quizName)) {
							bw.write( splits[0] +"|"+ splits[1] +"|");
				
							String allQuestionLine = splits[2];
	 						String[] oneQuestionSplits = allQuestionLine.split("\\*");
	 						
							String questionNoString = Integer.toString(questionNo);
	 						int i=0; 
	 					
		 					while(i < oneQuestionSplits.length) {
		 								
								String questionLine2 = oneQuestionSplits[i];
								String[] questionSplits2 = questionLine2.split("#");
								
								if(questionSplits2[0].equals(questionNoString)) { // aradığımız soru ise
									
									if(questionSplits2[5].equals("MC")) { // eğer soru test ise 
										
										String[] choices = newTarget.split("¿"); // testlerde soru metni soruMetni¿a¿b¿c¿d¿ şeklinde saklanır gelen yeni soru metnini parçalara ayırıyoruz
										bw.write(questionSplits2[0] +"#");
										bw.write( choices[0] +"¿"+ choices[1] +"¿"+ choices[2] +"¿"+ choices[3] +"¿"+ choices[4] +"#");
										bw.write(questionSplits2[2] +"#"+ questionSplits2[3] +"#"+ questionSplits2[4] +"#"+ questionSplits2[5] +"*");
										// yeni haliyle yazıyoruz
									}
									else {
										bw.write(questionSplits2[0] +"#"+ newTarget +"#"+ questionSplits2[2] +"#"+ questionSplits2[3] +"#"+ questionSplits2[4] +"#"+ questionSplits2[5] +"*");
									// soru türü test değilse yani doğru/yanlış ise soru metni olduğu gibi tutulur burda yeni haliyle yazıyoruz
									}
								}
								else {
									bw.write( questionSplits2[0] +"#"+ questionSplits2[1] +"#"+ questionSplits2[2] +"#"+ questionSplits2[3] +"#"+ questionSplits2[4] +"#"+ questionSplits2[5] +"*");
									// aradığımımz soru değilse aynen yazıyoruz
								}
								i++;
							}
		 					bw.newLine();
						}
						else {
							bw.write(line);
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

	/**
	 * Dosyadaki soruyu değiştirme fonksiyonlarından biri. Sorunun sorunun zorluk seviyesini düzenler. Difficulty enum tipinde veri aldığı için target değerinin gönderilmesine gerek yoktu,target verinin zorluk derecesi olduğu belli olur. 
	 * @param subject  = Quizin hangi derse ait olduğu. (Dosyayı bulmak için gerekli)
	 * @param quizName = Quiz ismi. Hangi quizden soru değiştirileceği bilmek için gerekli
	 * @param questionNo = Soru numarası
	 * @param newTarget = Değiştirilmek istenen verinin son halini tanımlar.
	 */
	public void change(String subject, String quizName,int questionNo, Difficulty newTarget) {
		// change fonksiyonuna overload yapıyoruz (aslında daha az değerle çağırıyoruz)
		// işlemler aynı şekilde ilerliyor. Aradığımız quizin aradığımız sorusu ise yeni haliyle değilse aynen yazıyoruz.
		File quiz = new File( "txt/"+subject+".txt");
		File newQuiz= new File("txt/temp.txt");
		
		try (BufferedReader br = new BufferedReader(new FileReader(quiz)); BufferedWriter bw = new BufferedWriter(new FileWriter(newQuiz))){
			String line;
			while ((line = br.readLine()) != null) {
				 String[] splits = line.split("\\|"); 
					
				 if(splits.length >= 2 && splits[1].equals(quizName)) {
						
						bw.write( splits[0] +"|"+ splits[1] +"|");
						String allQuestionLine = splits[2];
						String[] oneQuestionSplits = allQuestionLine.split("\\*"); 
						
						String questionNoString = Integer.toString(questionNo);
						int i=0;
							while(i < oneQuestionSplits.length) {
								
								String questionLine2 = oneQuestionSplits[i];
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
						bw.write(line);
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

	/**
	 * Dosyadaki soruyu değiştirme fonksiyonlarından biri. Sorunun sorunun puanını düzenler. Int tipinde veri aldığı için target değerinin gönderilmesine gerek yoktu, target verinin puan olduğu belli olur. 
	 * @param subject  = Quizin hangi derse ait olduğu. (Dosyayı bulmak için gerekli.)
	 * @param quizName = Quiz ismi. Hangi quizden soru değiştirileceği bilmek için gerekli.
	 * @param questionNo = Soru numarası.
	 * @param newTarget = Değiştirilmek istenen verinin son halini tanımlar.
	 */
	public void change(String subject, String quizName,int questionNo, int newTarget) {
		// change fonksiyonuna overload yapıyoruz (aslında daha az değerle çağırıyoruz) ve bu sefer int değer ile çağırıyoruz
		// işlemler aynı şekilde ilerliyor. Aradığımız quizin aradığımız sorusu ise yeni haliyle değilse aynen yazıyoruz.
		File quiz = new File( "txt/"+subject+".txt");
		File newQuiz= new File("txt/temp.txt");
		
		try (BufferedReader br = new BufferedReader(new FileReader(quiz)); BufferedWriter bw = new BufferedWriter(new FileWriter(newQuiz))){
			String line;
			 while ((line = br.readLine()) != null) {
				 String[] splits = line.split("\\|");
				
				 if(splits.length >= 2 && splits[1].equals(quizName)) {
						bw.write( splits[0] +"|"+ splits[1] +"|");
						String allQuestionLine = splits[2];
						String[] oneQuestionSplits = allQuestionLine.split("\\*");
						
						String questionNoString = Integer.toString(questionNo);
						int i=0;
							while(i < oneQuestionSplits.length) {
								String questionLine2 = oneQuestionSplits[i];
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
					bw.write(line);
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

	/**
	 * Soruyu dosyadan silme/kaldırma fonksiyonu
	 * @param subject = Quizin hangi derse ait olduğu. (Dosyayı bulmak için gerekli.)
	 * @param quizName = Quiz ismi. Hangi quizden soru silineceğini bilmek için gerekli.
	 * @param questionNo = Soru numarası.
	 */
	public void remove(String subject, String quizName,int questionNo) {
		File quiz = new File( "txt/"+subject+".txt"); // bilgileri alıcağımız dosya
		File newQuiz= new File("txt/temp.txt"); // bilgileri geçireceğimiz dosya
		
		try (BufferedReader br = new BufferedReader(new FileReader(quiz)); BufferedWriter bw = new BufferedWriter(new FileWriter(newQuiz))){
			String line;
			 while ((line = br.readLine()) != null) {
				 String[] splits = line.split("\\|");
				
				 if(splits.length >= 2 && splits[1].equals(quizName)) { // aradığımız quiz mi
						
						bw.write( splits[0] +"|"+ splits[1] +"|");
						String allQuestionLine = splits[2];
						String[] oneQuestionSplits = allQuestionLine.split("\\*");
						
						String questionNoString = Integer.toString(questionNo);
						int i=0; // Döngünün toplam soru sayısı kadar çalışması için sayaç
						int questionNoCounter = 1; // Soru numaralarını yeniden düzenlemek için sayaç
							while(i < oneQuestionSplits.length) {
								String questionLine2 = oneQuestionSplits[i];
		 						String[] questionSplits2 = questionLine2.split("#");
								
		 						if(questionSplits2[0].equals(questionNoString)) { // aradağımız soru ise yazmadan geçiyoruz
		 							i++;
		 							// questionNoCounter arttırılmıyor çünkü bu soru artık olmayacak
		 							continue;
								}
								else { // aradığımız soru değilse soru numarası ile aynen yazıyoruz
									bw.write( questionNoCounter +"#"+ questionSplits2[1] +"#"+ questionSplits2[2] +"#"+ questionSplits2[3] +"#"+ questionSplits2[4] +"#"+ questionSplits2[5] +"*");
									questionNoCounter++; 
								}
								i++;
							}
							bw.newLine();
				 }
				else { // aradığımız quiz değilse aynen yazıyoruz
					bw.write(line);
					bw.newLine();
				}
			}
		}
		catch (IOException e) {
			System.out.println("Hata: " + e.getMessage());
		}
		quiz.delete();
		newQuiz.renameTo(quiz); // eski dosyayı silip yenisini tekrar isimlendiriyoruz
	}

	/**
	 * Dosyadaki soruyu gösterme fonksiyonu. Soruyu parça parça gösterir.
	 * @param subject = Quizin hangi derse ait olduğu. (Dosyayı bulmak için gerekli.)
	 * @param quizName = Quiz ismi. Hangi quizden soru görüntüleneceğini bilmek için gerekli.
	 * @param questionNo = Soru numarası.
	 * @param target = Görüntülenmek istenen veriyi tanımlar.
	 * @return Soruyla alakalı istenilen veriyi döndürür.
	 */
	public String show(String subject, String quizName,int questionNo, String target) {
		File quiz = new File( "txt/"+subject+".txt");
		
		try (Scanner fileInput = new Scanner(quiz)){
			while(fileInput.hasNextLine()) {
				String line = fileInput.nextLine();
				String[] splits = line.split("\\|"); 
				
				String questionNoString = Integer.toString(questionNo);
				
				if(splits.length==3) {
					if(splits[1].equals(quizName)) {
						String allQuestionLine = splits[2];
						String[] oneQuestionSplits = allQuestionLine.split("\\*"); 
						
						if(questionNo-1 < oneQuestionSplits.length) {
							String questionLine2 = oneQuestionSplits[questionNo-1];
							String[] questionSplits2 = questionLine2.split("\\#");
									
							if(questionSplits2[0].equals(questionNoString)) { //aradığımız soru ise
								switch(target) { //istenen veriye göre uygun olan veri döndürülüyor
									case "question": return questionSplits2[1];
									case "answer":   return questionSplits2[2];
									case "points":   return questionSplits2[3];
									case "dif":      return questionSplits2[4];
									case "type":     return questionSplits2[5];
									default: 
										System.out.println("Hata!");
										return null;
								}
							}
						}
					}
				}
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("Hata! Dosya yok.");
			return null;
		}
		return null;
	}

	/**
	 * Dosyadaki quizi tamamen kaldırma fonksiyonu.
	 * @param subject = Quizin hangi derse ait olduğu. (Dosyayı bulmak için gerekli.)
	 * @param quizName = Quiz ismi.
	 * @param questionCounter = Soru sayısı. Fonksiyon için önemi olmadığından kaldırılacaktır. 
	 */
	public void removeQuiz(String subject, String quizName, int questionCounter) {
		File quiz = new File( "txt/"+subject+".txt");
		File newQuiz= new File("txt/temp.txt");
		
		try (BufferedReader br = new BufferedReader(new FileReader(quiz)); BufferedWriter bw = new BufferedWriter(new FileWriter(newQuiz))){
			String line;
			 while ((line = br.readLine()) != null) {
				 String[] splits = line.split("\\|");
				
				 if(splits.length >= 2 && splits[1].equals(quizName)) { // aradığımız quiz ise
						continue; // yazmadan geçiyoruz
				}
				else { // değilse aynen yazıyoruz
					bw.write(line);
					bw.newLine();
				}
			}
		}
		catch (IOException e) {
			System.out.println("Hata: " + e.getMessage());
		}
		quiz.delete();  // eski dosyayı silip yenisini tekrar isimlendiriyoruz
		newQuiz.renameTo(quiz);
	}

	/**
	 * Quiz oluşturmayı tamamlama fonksiyonu. Quiz oluşturmayı bitirerek mevcut tüm öğrencilere quizi tanımlar.
	 * @param subject = Quizin hangi derse ait olduğu. (Dosyayı bulmak için gerekli.)
	 * @param quizName = Quiz ismi.
	 */
	public void finishQuiz(String subject, String quizName) {
		String fileName =  "txt/"+subject +".txt";
		try(FileWriter printer = new FileWriter(fileName,true)){
			printer.write("\n");
		}
		//Quiz oluşturma tamamlandığında yeni quiz için alt satıra geçiliyor
		catch(IOException e){
			System.out.println("Quiz tamamlanamadı.\n");
		}
		File usersList = new File("txt/users.txt");
		
        try (Scanner fileInput = new Scanner(usersList);
             FileWriter printer = new FileWriter("txt/scores.txt", true)) {
            
            while(fileInput.hasNextLine()) {
                String line = fileInput.nextLine();
                String[] splits = line.split("\\;");
                
                if(splits.length >= 5) { // tüm kullanıcların olduğu dosyadan öğrenci olanları alıyoruz
                    if(splits[4].equals("Student")) {
                        String userName = splits[0];
                        String name = splits[2];
                        String sName = splits[3];
                        
                        printer.write(userName +"|"+ name +"|"+ sName +"|"+ subject +"#"+ quizName +"#-1\n");
                        //puanların olduğu dosyaya öğrencilerin bu quizden aldığı puan "-1" olarak giriliyor. Böylece quiz öğrencilere tanımlanıyor
                    }
                }
            }
        }
        catch (IOException | NullPointerException e) {
            System.out.println("Hata! Quiz öğrencilere atanamadı.");
        }
		try(FileWriter printer = new FileWriter("txt/quizList.txt",true)){
			printer.write(subject +"#"+ quizName +"\n");
			// quiz bilgilerini quizlerin listesini tutan dosyaya yazdırıyoruz 
		}
		catch(IOException e){
			System.out.println("Quiz listeye eklenemedi!\n");
		}
				
	}

	/**
	 * Quiz oluşturmayı başlatma fonksiyonu. Ders dosyasının içine quiz bilgilerini yazdırır.
	 * @param subject = Quizin hangi derse ait olduğu. (Dosyayı bulmak için gerekli.)
	 * @param quizName = Quiz ismi.
	 * @param userName = Öğretmenin kullanıcı adı. Quizi kimin oluşturduğunu tanımlamak için gerekli.
	 */
	public void createQuiz(String subject, String quizName, String userName) {
		String fileName = "txt/"+subject+ ".txt";
		try(FileWriter printer = new FileWriter(fileName,true)){
			printer.write(userName +"|"+ quizName +"|");
			// quiz bilgilerini dersin dosyasına yazıyoruz 
			// bu aşamadan sonra sorular eklenecek
			// eklenecek sorular bu satıra yazılacak
		}
		catch(IOException e){
			System.out.println("Quiz oluşturulamadı!\n");
		}
	}
	
	/**
	 * Tüm quizi görüntüleme fonksiyonu. Öğretmen için ayrı, öğrenci için ayrı görüntüleme yöntemi vardır.
	 * @param subject = Quizin hangi derse ait olduğu. (Dosyayı bulmak için gerekli.)
	 * @param quizName = Quiz ismi.
	 * @param userName = Kullanıcı adı. Rol bilgisini almak için gerekli.
	 * @param password = Şifre. Rol bilgisini almak için gerekli.
	 * @param answers = Öğrencinin cevaplarını tutan liste.  Öğrencinin cevapları ile görüntüleyebilmesi için gerekli. Öğretmen null gönderir.
	 */
	public void showAllQuiz(String subject, String quizName, String userName, long password, List<String> answers) {
		UserOp userOp = new UserOp();
		File quiz = new File("txt/"+subject+".txt");
		try (Scanner fileInput = new Scanner(quiz)){
			while(fileInput.hasNextLine()) {
				String line = fileInput.nextLine();
				String[] splits = line.split("\\|");
				
				if(splits.length==3) {
					if(splits[1].equals(quizName)) {
						System.out.println(splits[1]+"\n\n");
						String allQuestionLine = splits[2];
						String[] oneQuestionSplits = allQuestionLine.split("\\*"); 
						
						for(int i=1; i <= oneQuestionSplits.length; i++ ) { //soru sayısı kadar döngü
							System.out.println(i+".soru\n");
							
							if("MC".equals(show(subject,quizName,i,"type"))) { // test ise
								String questionLine = show(subject,quizName,i,"question");
								String[] questionSplits = questionLine.split("¿"); //soru metni ve şıkları ayırıyoruz
								System.out.println(questionSplits[0]+"\n");
								if(questionSplits.length > 4) {
									System.out.println("A)"+ questionSplits[1]);
									System.out.println("B)"+ questionSplits[2]);
									System.out.println("C)"+ questionSplits[3]);
									System.out.println("D)"+ questionSplits[4] + "\n");
								} //tüm soruyu yazdırıyoruz
								System.out.println("Cevap: "+ show(subject,quizName,i,"answer"));
								//cevabı yazdırıyoruz
								if("Student".equals(userOp.show(userName,password,"role")))//fonksiyonu çağıran öğrenci ise
									System.out.println("Verdiğiniz cevap: "+ answers.get(i-1));//verdiği cevabı yazdırıyoruz
							}
							else { //doğru/yanlış türünde ise direkt soruyu yazdırıyoruz
								System.out.println("Soru: "+ show(subject,quizName,i,"question"));
								String answer = show(subject,quizName,i,"answer");
								System.out.println("A) Doğru\nB) Yanlış\n");
								if ("True".equals(answer)) System.out.println("Cevap: Doğru"); 
								else if ("False".equals(answer)) System.out.println("Cevap: Yanlış"); 
								
								if("Student".equals(userOp.show(userName,password,"role")))
									System.out.println("Verdiğiniz cevap: "+ answers.get(i-1));
							}
							
							// sorunun geri kalan özelliklerini yazdırıyoruz
							String dif = show(subject,quizName,i,"dif");
							if ("EASY".equals(dif)) System.out.println("Zorluk türü: Kolay"); 
							else if ("MEDİUM".equals(dif)) System.out.println("Zorluk türü: Orta"); 
							else if ("HARD".equals(dif)) System.out.println("Zorluk türü: Zor"); 
							System.out.println("Puanı: "+ show(subject,quizName,i,"points"));
					 }
				  }
				}
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("Hata!");
		}
	}

	/**
	 * Öğretmenin oluşturduğu quizleri döndüren fonksiyon.
	 * @param subject = Öğretmenin girdiği ders. (Dosyayı bulmak için gerekli.)
	 * @param userName = Öğretmenin kullanıcı adı
	 * @return Öğretmenin oluşturduğu quizlerin listesini döndürür.
	 */
	public List<String> quizList(String userName, String subject) {
		List<String> quizes = new ArrayList<>();
		File quiz = new File("txt/"+subject+".txt");
		
		try (Scanner fileInput = new Scanner(quiz)){
			while(fileInput.hasNextLine()) {
				String line = fileInput.nextLine();
				String[] splits = line.split("\\|");
				
				if(splits.length==3) {
					if(splits[0].equals(userName))// eğer quizi oluşturan öğretmen fonksiyonu çağıran öğretmen ise
						quizes.add(splits[1]); //listeye ekle
				}
			}
			return quizes ; // öğretmenin oluşturduğu quizlerden oluşan listeyi döndür
		}
		catch (FileNotFoundException e) {
			System.out.println("Hata!");
			return null;
		}	
	}

	/**
	 * Bir öğrencinin quiz puanlarını yazdıran fonksiyon. Öğretmen çağırırsa öğretmenin girdiği dersin quizleri listelenir, öğrenci çağırırsa tüm quizler listelenir.
	 * @param userName = Kullanıcı adı. Kullanıcı bilgilerini görüntülemek için gerekli.
	 * @param password = Şifre. Kullanıcı bilgilerini görüntülemek için gerekli.
	 * @param name = Puanları görüntülenecek öğrencinin ismi.
	 * @param sName = Puanları görüntülenecek öğrencinin soy ismi.
	 */
	public void scoresList(String userName,long password, String name, String sName){
		UserOp userOp = new UserOp();
		if(Main.isExist(name,sName)) { // öğrenci mevcut mu
			if("Teacher".equals(userOp.show(userName, password, "role"))) { // fonksiyonu çağıran öğretmen ise
				File scores = new File("txt/scores.txt");
				System.out.println("\n"+userOp.show(userName, password, "subject") +"\n\n");
				try (Scanner fileInput = new Scanner(scores)){
					while(fileInput.hasNextLine()) {
						String line = fileInput.nextLine();
						String[] splits = line.split("\\|"); //scores dosyasında veriler öğrencinin kullanıcıAdı|ismi|soyİsmi|quizVerileri şeklinde tutuluyor. Quiz verileri ders#quizİsmi#puan şeklinde tutuluyor.
												
						if(splits.length==4) {
							if(splits[1].equals(name)&&splits[2].equals(sName)) { //öğretmenin istediği öğrenci mi
									String quizLine = splits[3];
									String[] quizSplits = quizLine.split("\\#"); 
									
									if(quizSplits[0].equals(userOp.show(userName, password,"subject"))) //öğretmenin girdiği ders mi
										System.out.println("Quiz ismi: "+ quizSplits[1] +"Puanı:  "+ quizSplits[2] +"\n");																				
								}
							}	
						}
					}
				catch (FileNotFoundException e) {
					System.out.println("Hata!");
				}
			}
			else if("Student".equals(userOp.show(userName, password, "role"))){ // fonksiyonu çağıran öğrenci ise
				File scores = new File("txt/scores.txt"); //puanların tutulduğu dosya
	            List<String> subjects = new ArrayList<>();//ders isimlerini tutucak liste
	            List<String> quizData = new ArrayList<>(); //quiz verilerini içeren satırları tutan liste. Tekrar dosyayı açmamak için burda tutuyoruz.
	            
	            try (Scanner fileInput = new Scanner(scores)){
	        
	                while(fileInput.hasNextLine()) {
	                    String line = fileInput.nextLine();
	                    String[] splits = line.split("\\|"); 
	                                            
	                    if(splits.length==4) {
	                        if(splits[1].equals(name) && splits[2].equals(sName)) { //veri öğrenciye mi ait
	                            quizData.add(line); //quiz verilerini içeren listeye tümüyle ekle
	                            
	                            String quizLine = splits[3];
	                            String[] quizSplits = quizLine.split("\\#"); // quiz verilerini içeren bölümü parçalara ayırır
	                            
	                            if(!subjects.contains(quizSplits[0])) // ders listeye önceki döngülerde eklenmiş mi diye kontrol eder 
	                                subjects.add(quizSplits[0]); // dersi listeye ekler                                                                 
	                        }
	                    }   
	                }
	                
	                for(int i=0; i<subjects.size(); i++) { // burda ders sayısı kadar döngüye girer
	                    System.out.println("\n"+subjects.get(i)+":\n"); // ders ismini yazdırır
	                    
	                    for(int j=0; j<quizData.size(); j++) {
	                        String[] splits = quizData.get(j).split("\\|"); 
	                        String quizLine = splits[3];
	                        String[] quizSplits = quizLine.split("\\#"); 
	                        
	                        if(quizSplits[0].equals(subjects.get(i))) { // yazdırılan derse ait bir quiz ise puanına göre girdi veya girmedi şeklinde yazdırır.
	                            if(quizSplits[2].equals("-1")) {
	                                System.out.println("  " + quizSplits[1]+": Girilmedi"); 
	                            }
	                            else {
	                                System.out.println("  " + quizSplits[1]+": "+quizSplits[2]);
	                            }
	                        }
	                    }
	                }
	            }
	            catch (FileNotFoundException e) { System.out.println("Hata!"); }
	        }
	        else { System.out.println("Hata! Yetkisiz erişim."); } // bu fonksiyona erişen öğrenci ya da öğretmen tek erişebilir
	    } else { System.out.println("Hata! Öğrenci bulunamadı."); }
	}

	/**
	 * Öğrencinin girmediği quizleri ders ismi ile birlikte döndüren fonksiyon.
	 * @param userName = Öğrencinin kullanıcı adı.
	 * @return Öğrencinin girmediği quizleri ders ismi ile birlikte döndürür.
	 */
	public List<String> unsolvedQuizes(String userName) {
		List<String> quizes = new ArrayList<>();
		File scores = new File("txt/scores.txt");
		
		try (Scanner fileInput = new Scanner(scores)){
			while(fileInput.hasNextLine()) {
				String line = fileInput.nextLine();
				String[] splits = line.split("\\|"); 
										
				if(splits.length==4) {
					if(splits[0].equals(userName)) { // fonksiyonu çağıran öğrencinin verisi mi
							String quizLine = splits[3];
							String[] quizSplits = quizLine.split("\\#"); 
							if(quizSplits[2].equals("-1")) // puanı "-1" olarak gözüküyorsa
								quizes.add(quizSplits[0]+"#"+quizSplits[1]); //dersİsmi#quizİsmi şeklinde listeye ekler																			
						}
					}
				else {
					System.out.println("Hatalı dosya yapısı!");
					return null;
				}
			}
			return quizes; //listeyi döndürür
			}
		catch (FileNotFoundException e) {
			System.out.println("Hata!");
			return null;
		}
	}

	/**
	 * Öğrencinin quize girmesini sağlayan fonksiyon. Quize girme arayüzü bu fonksiyon içindedir.
	 * @param subject = Quizin ait olduğu ders. (Dosyayı bulmak için gerekli.)
	 * @param quizName = Quizin ismi.
	 * @param userName = Öğrencinin kullanıcı adı.
	 * @param password = Öğrencinin şifresi.
	 */
	public void enterQuiz(String subject,String quizName, String userName, long password) {
		
		try {
			List<String> answers = new ArrayList<>();//öğrencinin cevaplarının tutulacağı liste
			File quiz = new File("txt/"+subject+".txt");
			
			try (Scanner fileInput = new Scanner(quiz)){

				while(fileInput.hasNextLine()) {
					String line = fileInput.nextLine();
					String[] splits = line.split("\\|"); 
					
					if(splits.length==3) {
						if(splits[1].equals(quizName)) {
						
							System.out.println(splits[1]+"\n\n");
							String allQuestionLine = splits[2];
							String[] oneQuestionSplits = allQuestionLine.split("\\*");
							
							for(int i=1; i <= oneQuestionSplits.length; i++ ) { // quiz verilerini açtıktan sonra soru sayısı kadar döngüyle soruyu çözülmek üzere yazdırır
								System.out.println(i+".soru\n");
								String dif = show(subject,quizName,i,"dif");
								if ("EASY".equals(dif)) System.out.println("Zorluk türü: Kolay"); 
								else if ("MEDİUM".equals(dif)) System.out.println("Zorluk türü: Orta"); 
								else if ("HARD".equals(dif)) System.out.println("Zorluk türü: Zor"); 
								System.out.println("Puanı: "+ show(subject,quizName,i,"points")+"\n");
								String answer = null;
								
								if("MC".equals(show(subject,quizName,i,"type"))) {
									String questionLine = show(subject,quizName,i,"question");
									String[] questionSplits = questionLine.split("¿");
									System.out.println(questionSplits[0]+"\n");
									System.out.println("A)"+ questionSplits[1]);
									System.out.println("B)"+ questionSplits[2]);
									System.out.println("C)"+ questionSplits[3]);
									System.out.println("D)"+ questionSplits[4] + "\n");
									System.out.println("Cevabınızı giriniz.");
									boolean check = true;
									while(check) { //cevapların aynı şekilde tutulması için küçük de girilse büyük de girilse aynı şekilde döndürür
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
												break;
											case "d","D": 
												answer = "D"; 
												check = false; 
												break;
											default: 
												System.out.println("Geçersiz değer! Tekrar giriniz."); break;
										}
									}
								}
								else {
									System.out.println(show(subject,quizName,i,"question")+"\n");
									System.out.println("A) Doğru\nB) Yanlış\n");
									System.out.println("Cevabınızı giriniz.");
									boolean check = true;
									
									while(check) {
										answer = input.nextLine();
										switch(answer) {//cevabın true ya da false şeklinde tutulmasını sağlar
											case "a","A": 
												answer = "True"; 
												check = false; 
												break;
												
											case "b","B": 
												answer = "False"; 
												check = false; 
												break;
												
											default: 
												System.out.println("Geçersiz değer! Tekrar giriniz."); break;
										}
									}		
								 }
								 answers.add(answer);		
							  }//tüm soruları çözdükten sonra cevabın değiştirilmesine olanak sağlar
							System.out.println("Tüm soruları cevapladınız.\n");
							System.out.println("1)Quizi tamamla");
							System.out.println("2)Cevabı düzenle");
							
							boolean menuCheck = true;
							while(menuCheck) {
								int choice = input.nextInt();
								input.nextLine(); 
								
								if(choice==1) {//quiz olma tamamlanırsa
									addScore(userName,password,subject,quizName,calcScore(subject, quizName,answers)); // puanı hesaplayıp dosyaya kaydeder
									System.out.println("Quiz tamamlandı.");
									System.out.println("Puanınız: " + calcScore(subject, quizName,answers));
									System.out.println("Quizi görüntülemek ister misiniz?"); //quizi cevapları ile birlikte görüntüleme seçeneği sunar
									System.out.println("1) Evet");
									System.out.println("2) Hayır");
									while(true) {
										int choice2 = input.nextInt();
										if(choice2==1) {
											showAllQuiz(subject, quizName, userName, password, answers);
											break;
										}
										else if(choice2==2) {
											break;
										}
										else
											System.out.println("Geçersiz değer tekrar giriniz.");
									}
									menuCheck = false;
								}
								else if (choice==2) { // soruyu düzenlemek isterse 
									String newAns = null;
									System.out.println("Kaçıncı soruyu değiştirmek istersiniz?");
									int questionNo = input.nextInt();
									input.nextLine(); 
								if(questionNo-1 < answers.size() && 0<questionNo) { // soru numarası geçerli değerlerde mi kontrolü
									System.out.println(questionNo+".soru\n");
									
									if("MC".equals(show(subject,quizName,questionNo,"type"))) { //soru test ise
										String questionLine = show(subject,quizName,questionNo,"question");
										String[] questionSplits = questionLine.split("¿");
										System.out.println(questionSplits[0]+"\n");
										System.out.println("A)"+ questionSplits[1]);
										System.out.println("B)"+ questionSplits[2]);
										System.out.println("C)"+ questionSplits[3]);
										System.out.println("D)"+ questionSplits[4] + "\n");
										System.out.println("Cevabınızı giriniz.");
										boolean check = true;
										while(check) { //cevapların aynı şekilde tutulması için küçük de girilse büyük de girilse aynı şekilde döndürür
											newAns = input.nextLine();
											switch(newAns) {
												case "a","A": 
													newAns = "A";
													check = false; 
													break;
												case "b","B": 
													newAns = "B"; 
													check = false; 
													break;
												case "c","C": 
													newAns = "C"; 
													check = false; 
													break;
												case "d","D": 
													 newAns = "D"; 
													check = false; 
													break;
												default: 
													System.out.println("Geçersiz değer! Tekrar giriniz."); break;
											}
										}
									}
									else { // doğru/yanlış ise
										System.out.println(show(subject,quizName,questionNo,"question")+"\n");
										System.out.println("A) Doğru\nB) Yanlış\n");
										System.out.println("Cevabınızı giriniz.");										
										boolean check = true;
										while(check) {
										newAns = input.nextLine();
										if(newAns.equalsIgnoreCase("a")) { 
											newAns="True"; 
											check = false; 
										}
										else if(newAns.equalsIgnoreCase("False")) {
											newAns="B";
											check = false; 
										}
										else System.out.println("Geçerli bir değer giriniz.");
										}
									} 
										
									
									
										answers.set(questionNo-1, newAns);//cevaplar listesindeki cevabı değiştirir
										System.out.println("Başarıyla değiştirildi.");
									}
									
									System.out.println("1)Quizi tamamla");
									System.out.println("2)Cevabı düzenle");
								}
								else
									System.out.println("Geçersiz değer tekrar giriniz.");
							}
							return; 
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Hata! Quizi tekrar çözünüz: " + e.getMessage());
		}
	}

	/**
	 * Öğrencinin quizden aldığı puanın belgeye eklenmesini sağlayan fonksiyon. "-1" olan değeri alınan puan ile değiştirir.
	 * @param subject = Quizin ait olduğu ders.
	 * @param quizName = Quizin ismi.
	 * @param userName = Öğrencinin kullanıcı adı.
	 * @param password = Öğrencinin şifresi.
	 * @param score = Quizden alınan puan.
	 */
	private void addScore(String userName,long password,String subject, String quizName, int score) {
		File scores = new File("txt/scores.txt");
		File newScores= new File("txt/temp.txt");
		
		try (BufferedReader br = new BufferedReader(new FileReader(scores)); BufferedWriter bw = new BufferedWriter(new FileWriter(newScores))){
			String line;
			 while ((line = br.readLine()) != null) {
				 String[] splits = line.split("\\|"); 
				
				 if(splits.length == 4 && splits[0].equals(userName)) { //kullanıcıya ait quiz verisi mi
						String allScoreLine = splits[3];
						String[] scoreSplits = allScoreLine.split("\\#");
							if(scoreSplits[0].equals(subject) && scoreSplits[1].equals(quizName)) {// quiz ismi ve ders ismi kontrolü
						
		 						if(scoreSplits[2].equals("-1")) { // önceden quize girilmiş mi kontrolü
		 							bw.write(splits[0] +"|"+ splits[1] +"|"+ splits[2] +"|");
		                            bw.write(subject +"#"+ quizName +"#"+ score);
		                            bw.newLine();
		                            // satırın yeni haliyle tekrar yazılması
								}
								else {
		                            bw.write(line);
		                            bw.newLine();
		                            System.out.println("Hata! Bu quize zaten girilmiş.");
							}
							}
							else { //aranan quiz değilse aynen yaz
		                        bw.write(line);
		                        bw.newLine();
							}
				 }
				else { // başka kullanıcının verisi ise aynen yaz
					bw.write(line);
					bw.newLine();
				}
			 }
		}
		catch (IOException e) {
			System.out.println("Hata: " + e.getMessage());
		}
		scores.delete(); //eski dosyayı sil
		newScores.renameTo(scores); // yeni dosyayı yeniden adlandır

	}

	/**
	 * Öğrencinin quizden aldığı puanı hesaplamaya yarayan fonksiyon.
	 * @param subject = Quizin ait olduğu ders.
	 * @param quizName = Quizin ismi.
	 * @param answers = Öğrencinin verdiği cevapları tutan liste.
	 * @return Alınan puanı döndürür.
	 */
	public int calcScore(String subject, String quizName, List<String> answers) {
		int score=0;
		for(int i=0;i<answers.size();i++) { //soru sayısı kadar döngü
			String pointsString = show(subject, quizName, i+1, "points"); //puan verisini alma
			if(pointsString != null) {
				int points = Integer.parseInt(pointsString); //veriyi integer tipine çevirme
				
				if(answers.get(i).equals(show(subject, quizName, i+1, "answer"))) //cevap kontrolü
					score+=points; //puanın eklenmesi
			}
		}
		return score;
	}
	
	/**
	 * Yeni öğrenci eklendiğinde mevcut quizlerin öğrenciye tanımlanmasını sağlayan fonskiyon.
	 * @param userName = Öğrencinin kullanıcı ismi.
	 * @param name = Öğrencinin ismi.
	 * @param sName = Öğrencinin soy ismi.
	 */
	public void scoreListForNewStd(String userName, String name, String sName){
		File quizList = new File("txt/quizList.txt"); //öğrenciye tüm quizleri atayabilmek için quiz listesi
		List<String> quizes = new ArrayList<>(); //quiz isimlerini tutan liste
		List<String> subjects = new ArrayList<>(); //ders isimlerini tutan liste
		
		try (Scanner fileInput = new Scanner(quizList)){
			while(fileInput.hasNextLine()) {
				String line = fileInput.nextLine();
				String[] splits = line.split("\\#"); //dosyada veri dersİsmi#quizİsmi şeklinde tutuluyor
				
				if(splits.length==2) {
					subjects.add(splits[0]); //dersi listeye ekle
					quizes.add(splits[1]); //quizi listeye ekle
				}
				//burda ders tekrar tekrar eklenebilir. Amaç 2 farklı listede aynı indexte aynı quizin verisi olması
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("Hata! Quiz listesi bulunamadı.");
		}	
		try(FileWriter printer = new FileWriter("txt/scores.txt", true)){
            for(int i=0; i<subjects.size(); i++) {// Ders sayısı kadar. Quiz sayısı ve ders sayısı bu durumda eşit olacaktır
                printer.write(userName +"|"+ name +"|"+ sName +"|"+ subjects.get(i) +"#"+ quizes.get(i) +"#-1\n"); //"-1" puan şeklinde quizi scores dosyasına yazdırma
            }
        }
        catch(IOException e){
            System.out.println("Hata! Öğrenci quiz listesine eklenemedi.\n");
        }
}
}