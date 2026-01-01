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
	UserOp userOp = new UserOp();
	
	static Scanner input = new Scanner(System.in);
	
	public enum Type{
		TF,MC
	}
	public enum Difficulty{
		Hard,Medium,Easy
	}
	
	public void add(int counter, String question, String answer, int points, String subject, Difficulty dif, Type type) {
		String fileName = "txt/"+ subject + ".txt";
		try(FileWriter printer = new FileWriter(fileName,true)){
			printer.write( counter +"#"+ question +"#"+ answer +"#"+ points +"#"+ dif +"#"+  type +"*");
			System.out.println("Soru eklendi.\n");
		}
		catch(IOException e){
			System.out.println("Soru eklenemedi.\n");
		}
	}

	public void change(String subject,String quizName,int questionNo, String target, String newTarget) {
		if(target.equals("answer")) {
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

							for(String q : oneQuestionSplits) {
								String[] questionSplits2 = q.split("#");
								if(questionSplits2[0].equals(questionNoString)) {
									bw.write( questionSplits2[0] +"#"+ questionSplits2[1] +"#"+ newTarget +"#"+ questionSplits2[3] +"#"+ questionSplits2[4] +"#"+ questionSplits2[5] +"*");
									System.out.println("Başarıyla değiştirildi.");
								} else {
									bw.write( questionSplits2[0] +"#"+ questionSplits2[1] +"#"+ questionSplits2[2] +"#"+ questionSplits2[3] +"#"+ questionSplits2[4] +"#"+ questionSplits2[5] +"*");
								}
							}
					}
					else {
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
			quiz.delete();
			newQuiz.renameTo(quiz);

		}
		else {
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
									
									if(questionSplits2[5].equals("MC")) {
										
										String[] choices = newTarget.split("¿");
										bw.write(questionSplits2[0] +"#");
										bw.write( choices[0] +"¿"+ choices[1] +"¿"+ choices[2] +"¿"+ choices[3] +"¿"+ choices[4] +"#");
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

	public void change(String subject, String quizName,int questionNo, Difficulty newTarget) {
		
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

	public void change(String subject, String quizName,int questionNo, int newTarget) {
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

	public void remove(String subject, String quizName,int questionNo) {
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
						int questionNoCounter = 1;
							while(i < oneQuestionSplits.length) {
								String questionLine2 = oneQuestionSplits[i];
		 						String[] questionSplits2 = questionLine2.split("#");
								
		 						if(questionSplits2[0].equals(questionNoString)) {
		 							i++;
		 							continue;
								}
								else {
									bw.write( questionNoCounter +"#"+ questionSplits2[1] +"#"+ questionSplits2[2] +"#"+ questionSplits2[3] +"#"+ questionSplits2[4] +"#"+ questionSplits2[5] +"*");
									questionNoCounter++; 
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
									
							if(questionSplits2[0].equals(questionNoString)) {
								switch(target) {
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

	public void removeQuiz(String subject, String quizName, int questionCounter) {
		File quiz = new File( "txt/"+subject+".txt");
		File newQuiz= new File("txt/temp.txt");
		
		try (BufferedReader br = new BufferedReader(new FileReader(quiz)); BufferedWriter bw = new BufferedWriter(new FileWriter(newQuiz))){
			String line;
			 while ((line = br.readLine()) != null) {
				 String[] splits = line.split("\\|");
				
				 if(splits.length >= 2 && splits[1].equals(quizName)) {
						continue;
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

	public void finishQuiz(String subject, String quizName) {
		String fileName =  "txt/"+subject +".txt";
		try(FileWriter printer = new FileWriter(fileName,true)){
			printer.write("\n");
		}
		catch(IOException e){
			System.out.println("Quiz tamamlanamadı.\n");
		}
		File usersList = new File("txt/users.txt");
		
        try (Scanner fileInput = new Scanner(usersList);
             FileWriter printer = new FileWriter("txt/scores.txt", true)) {
            
            while(fileInput.hasNextLine()) {
                String line = fileInput.nextLine();
                String[] splits = line.split("\\;");
                
                if(splits.length >= 5) {
                    if(splits[4].equals("Student")) {
                        String userName = splits[0];
                        String name = splits[2];
                        String sName = splits[3];
                        
                        printer.write(userName +"|"+ name +"|"+ sName +"|"+ subject +"#"+ quizName +"#-1\n");
                    }
                }
            }
        }
        catch (IOException | NullPointerException e) {
            System.out.println("Hata! Quiz öğrencilere atanamadı.");
        }
		try(FileWriter printer = new FileWriter("txt/quizList.txt",true)){
			printer.write(subject +"#"+ quizName +"\n");
		}
		catch(IOException e){
			System.out.println("Quiz listeye eklenemedi!\n");
		}
				
	}

	public void createQuiz(String subject, String quizName, String userName) {
		String fileName = "txt/"+subject+ ".txt";
		try(FileWriter printer = new FileWriter(fileName,true)){
			printer.write(userName +"|"+ quizName +"|");
		}
		catch(IOException e){
			System.out.println("Quiz oluşturulamadı!\n");
		}
	}

	public void showAllQuiz(String subject, String quizName, String userName, long password, List<String> answers) {
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
						
						for(int i=1; i <= oneQuestionSplits.length; i++ ) { 
							System.out.println(i+".soru\n");
							
							// DÜZELTME: == yerine .equals
							if("MC".equals(show(subject,quizName,i,"type"))) {
								String questionLine = show(subject,quizName,i,"question");
								String[] questionSplits = questionLine.split("¿");
								System.out.println(questionSplits[0]+"\n");
								if(questionSplits.length > 4) {
									System.out.println("A)"+ questionSplits[1]);
									System.out.println("B)"+ questionSplits[2]);
									System.out.println("C)"+ questionSplits[3]);
									System.out.println("D)"+ questionSplits[4] + "\n");
								}
								System.out.println("Cevap: "+ show(subject,quizName,i,"answer"));
								
								if("Student".equals(userOp.show(userName,password,"role")))
									System.out.println("Verdiğiniz cevap: "+ answers.get(i-1));
							}
							else {
								System.out.println("Cevap: "+ show(subject,quizName,i,"question"));
								String answer = show(subject,quizName,i,"answer");
								System.out.println("A) Doğru\nB) Yanlış\n");
								if ("True".equals(answer)) System.out.println("Cevap: Doğru"); 
								else if ("False".equals(answer)) System.out.println("Cevap: Yanlış"); 
								
								if("Student".equals(userOp.show(userName,password,"role")))
									System.out.println("Verdiğiniz cevap: "+ answers.get(i-1));
							}
							
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

	public List<String> quizList(String userName, String subject) {
		List<String> quizes = new ArrayList<>();
		File quiz = new File("txt/"+subject+".txt");
		
		try (Scanner fileInput = new Scanner(quiz)){
			while(fileInput.hasNextLine()) {
				String line = fileInput.nextLine();
				String[] splits = line.split("\\|");
				
				if(splits.length==3) {
					if(splits[0].equals(userName))
						quizes.add(splits[1]);
				}
			}
			return quizes ;
		}
		catch (FileNotFoundException e) {
			System.out.println("Hata!");
			return null;
		}	
	}

	public void scoresList(String userName,long password, String name, String sName){
		if(Main.isExist(name,sName)) {
			if("Teacher".equals(userOp.show(userName, password, "role"))) { 
				File scores = new File("txt/scores.txt");
				System.out.println(userOp.show(userName, password, "subject") +"\n\n");
				try (Scanner fileInput = new Scanner(scores)){
					while(fileInput.hasNextLine()) {
						String line = fileInput.nextLine();
						String[] splits = line.split("\\|"); 
												
						if(splits.length==4) {
							if(splits[1].equals(name)&&splits[2].equals(sName)) {
									String quizLine = splits[3];
									String[] quizSplits = quizLine.split("\\#"); 
									
									if(quizSplits[0].equals(userOp.show(userName, password,"subject"))) 
										System.out.println("Quiz ismi: "+ quizSplits[1] +"Puanı:  "+ quizSplits[2] +"\n");																				
								}
							}	
						}
					}
				catch (FileNotFoundException e) {
					System.out.println("Hata!");
				}
			}
			else if("Student".equals(userOp.show(userName, password, "role"))){
				File scores = new File("txt/scores.txt");
	            List<String> subjects = new ArrayList<>();
	            List<String> quizData = new ArrayList<>(); 
	            
	            try (Scanner fileInput = new Scanner(scores)){
	        
	                while(fileInput.hasNextLine()) {
	                    String line = fileInput.nextLine();
	                    String[] splits = line.split("\\|"); 
	                                            
	                    if(splits.length==4) {
	                        if(splits[1].equals(name) && splits[2].equals(sName)) {
	                            quizData.add(line); 
	                            
	                            String quizLine = splits[3];
	                            String[] quizSplits = quizLine.split("\\#"); 
	                            
	                            if(!subjects.contains(quizSplits[0]))
	                                subjects.add(quizSplits[0]);                                                                        
	                        }
	                    }   
	                }
	                
	                for(int i=0; i<subjects.size(); i++) {
	                    System.out.println(subjects.get(i)+":\n");
	                    
	                    for(int j=0; j<quizData.size(); j++) {
	                        String[] splits = quizData.get(j).split("\\|"); 
	                        String quizLine = splits[3];
	                        String[] quizSplits = quizLine.split("\\#"); 
	                        
	                        if(quizSplits[0].equals(subjects.get(i))) {
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
	        else { System.out.println("Hata! Yetkisiz erişim."); }
	    } else { System.out.println("Hata! Öğrenci bulunamadı."); }
	}

	public List<String> unsolvedQuizes(String userName) {
		List<String> quizes = new ArrayList<>();
		File scores = new File("txt/scores.txt");
		
		try (Scanner fileInput = new Scanner(scores)){
			while(fileInput.hasNextLine()) {
				String line = fileInput.nextLine();
				String[] splits = line.split("\\|"); 
										
				if(splits.length==4) {
					if(splits[0].equals(userName)) {
							String quizLine = splits[3];
							String[] quizSplits = quizLine.split("\\#"); 
							if(quizSplits[2].equals("-1"))
								quizes.add(quizSplits[0]+"#"+quizSplits[1]);																			
						}
					}
				else {
					System.out.println("Hatalı dosya yapısı!");
					return null;
				}
			}
			return quizes;
			}
		catch (FileNotFoundException e) {
			System.out.println("Hata!");
			return null;
		}
	}

	public void enterQuiz(String subject,String quizName, String userName, long password) {
		
		try {
			List<String> answers = new ArrayList<>();
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
							
							for(int i=1; i <= oneQuestionSplits.length; i++ ) {
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
												System.out.println("Geçersiz değer! Tekrar giriniz."); break;
										}
									}		
								 }
								 answers.add(answer);		
							  }
							System.out.println("Tüm soruları cevapladınız.\n");
							System.out.println("1)Quizi tamamla");
							System.out.println("2)Cevabı düzenle");
							
							boolean menuCheck = true;
							while(menuCheck) {
								int choice = input.nextInt();
								input.nextLine(); 
								
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
								else if (choice==2) {
									System.out.println("Kaçıncı soruyu değiştirmek istersiniz?");
									int questionNo = input.nextInt();
									input.nextLine(); 
									
									System.out.println(questionNo+".soru\n");									
									System.out.println("Yeni cevabınızı giriniz:");
									String newAns = input.nextLine(); 
									if(newAns.equalsIgnoreCase("a")) newAns="A"; 
									if(newAns.equalsIgnoreCase("b")) newAns="B";
									
									if(questionNo-1 < answers.size()) {
										answers.set(questionNo-1, newAns.toUpperCase());
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

	private void addScore(String userName,long password,String subject, String quizName, int score) {
		File scores = new File("txt/scores.txt");
		File newScores= new File("txt/temp.txt");
		
		try (BufferedReader br = new BufferedReader(new FileReader(scores)); BufferedWriter bw = new BufferedWriter(new FileWriter(newScores))){
			String line;
			 while ((line = br.readLine()) != null) {
				 String[] splits = line.split("\\|"); 
				
				 if(splits.length == 4 && splits[0].equals(userName)) {
						String allScoreLine = splits[3];
						String[] scoreSplits = allScoreLine.split("\\#");
							if(scoreSplits[0].equals(subject) && scoreSplits[1].equals(quizName)) {
						
		 						if(scoreSplits[2].equals("-1")) {
		 							bw.write(splits[0] +"|"+ splits[1] +"|"+ splits[2] +"|");
		                            bw.write(subject +"#"+ quizName +"#"+ score);
		                            bw.newLine();
								}
								else {
		                            bw.write(line);
		                            bw.newLine();
		                            System.out.println("Hata! Bu quize zaten girilmiş.");
							}
							bw.newLine();
							}
							else {
		                        bw.write(line);
		                        bw.newLine();
							}
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
		scores.delete();
		newScores.renameTo(scores);

	}

	private int calcScore(String subject, String quizName, List<String> answers) {
		int score=0;
		for(int i=0;i<answers.size();i++) {
			String pointsString = show(subject, quizName, i+1, "points");
			if(pointsString != null) {
				int points = Integer.parseInt(pointsString);
				
				if(answers.get(i).equals(show(subject, quizName, i+1, "answer")))
					score+=points;
			}
		}
		return score;
	}
	public void scoreListForNewStd(String userName, String name, String sName){
		File quizList = new File("txt/quizList.txt");
		List<String> quizes = new ArrayList<>();
		List<String> subjects = new ArrayList<>();
		
		try (Scanner fileInput = new Scanner(quizList)){
			while(fileInput.hasNextLine()) {
				String line = fileInput.nextLine();
				String[] splits = line.split("\\#");
				
				if(splits.length==2) {
					subjects.add(splits[0]);
					quizes.add(splits[1]);
				}
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("Hata! Quiz listesi bulunamadı.");
		}	
		try(FileWriter printer = new FileWriter("txt/scores.txt", true)){
            for(int i=0; i<subjects.size(); i++) {
                printer.write(userName +"|"+ name +"|"+ sName +"|"+ subjects.get(i) +"#"+ quizes.get(i) +"#-1\n");
            }
        }
        catch(IOException e){
            System.out.println("Hata! Öğrenci quiz listesine eklenemedi.\n");
        }
}
}