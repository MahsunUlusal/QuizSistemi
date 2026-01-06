package FileOp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
	
 public class UserOp extends FileOp{
		
		Scanner input = new Scanner(System.in);
		
		public enum Role{
			Admin,Teacher,Student
		}
		
		Role role;
		
		
		/**
		 * Belgeye kullanıcı eklemeye yarayan fonksiyonlardan biri.
		 * @param name = Eklencek kullanıcının ismi.
		 * @param sName = Eklencek kullanıcının soy ismi.
		 * @param userName = Eklencek kullanıcının kullanıcı adı.
		 * @param password = Eklencek kullanıcının şifresi.
		 * @param role = Eklenecek kullanıcının rolü.
		 */
		public void add(String name, String sName, String userName, long password, Role role ) {
			String roleTr;
			if(role==Role.Student) roleTr="Öğrenci";
			if(role==Role.Teacher) roleTr="Öğretmen";
			else roleTr="Admin";
			try{
	            FileWriter printer = new FileWriter("txt/users.txt",true);
	            printer.write( userName +";"+ password +";"+ name +";"+ sName +";"+ role +"\n");//kullanıcı bilgileri sistemde kullanıcıAdı;şifre;isim;soyisim;role olarak kaydedilir.(Öğretmen hariç)
	            System.out.println(roleTr + " eklendi.\n");
	            printer.close();
	        }
	       catch(IOException e){
	           System.out.println(roleTr + " eklenemedi.\n");
	       }
			if(role == Role.Student) {
				QuizOp quizOp = new QuizOp();
				quizOp.scoreListForNewStd(userName,name,sName);
			}
			
		}
		
		/**
		 * Belgeye kullanıcı eklemeye yarayan fonksiyonlardan biri. Ders verisi de alarak önceki add fonksiyonunu overload eder.
		 * @param name = Eklencek öğretmenin ismi.
		 * @param sName = Eklencek öğretmenin soy ismi.
		 * @param userName = Eklencek öğretmenin kullanıcı adı.
		 * @param password = Eklencek öğretmenin şifresi.
		 * @param subject = Eklenecek öğretmenin dersi.
		 * @param role = Eklenecek kullanıcının rolü. (Teacher rolü gönderilmeli.)
		 */

		public void add(String name, String sName, String userName, long password, String subject, Role role ) {// eğer öğretmen eklenicekse bu method overload edilir
			
			try{
	            FileWriter printer = new FileWriter("txt/users.txt",true);
	            printer.write( userName +";"+ password +";"+ name +";"+ sName +";"+ subject +";"+ role +"\n");// soy isim ve rol arasına ders verisi de eklenir
	            System.out.println(role + " eklendi.\n");
	            printer.close();
	        }
	       catch(IOException e){
	           System.out.println(role + " eklenemedi.\n");
	       }
			
		}
		
		/**
		 * Belgeden kullanıcı ile alaklı verileri değiştirmeyi sağlayan fonksiyon.
		 * @param userName = Değiştirilecek kullanıcının kullanıcı adı.
		 * @param password = Değiştirilecek kullanıcının şifresi.
		 * @param target = Değiştirilmesi istenen veriyi belirtir.
		 * @param newTarget = Değiştirilmesi istenen verinin son halini belirtir.
		 */
		public void change(String userName, long password, String target, String newTarget ) {
				
				File users = new File("txt/users.txt"); //verileri almak için gereken dosya
		        File newUsers = new File("txt/temp.txt"); //verileri yazacağımız dosya
				
		        try (BufferedReader br = new BufferedReader(new FileReader(users)); BufferedWriter bw = new BufferedWriter(new FileWriter(newUsers))){
		        	String line;
		        	String passwordString = Long.toString(password);
		        	
		        	 while ((line = br.readLine()) != null) {
		        		 String[] splits = line.split(";"); // kullanıcı verilerini satır satır alıp bölüyoruz
		        		 
		        		 if(splits.length >=5) {
		        			 
		 					if(splits[0].equals(userName) && splits[1].equals(passwordString)) {// değiştireceğimiz kullanıcıya ait veriler ise
		 						
		 						if(splits.length == 6 && splits[5].equals("Teacher")) {// değiştirilecek kullanıcı öğretmen ise
		 							
		 							switch(target) { //hedef veriye göre yeni veriyi yazar
		 						
		 							case "name":
		 								bw.write( splits[0] +";"+ splits[1] +";"+ newTarget +";"+ splits[3] +";"+ splits[4] +";"+ splits[5]);
		 								System.out.println("Başarıyla değiştirildi.");
		 								break;
		 							
		 							case "sName":
		 								bw.write( splits[0] +";"+ splits[1] +";"+ splits[2] +";"+ newTarget +";"+ splits[4] +";"+ splits[5]);
		 								System.out.println("Başarıyla değiştirildi.");
		 								break;
		 								
		 							case "userName":
		 								bw.write( newTarget +";"+ splits[1] +";"+ splits[2] +";"+ splits[3] +";"+ splits[4] +";"+ splits[5]);
		 								System.out.println("Başarıyla değiştirildi.");
		 								break;
		 								
		 							case "password":
		 								bw.write(splits[0] +";"+ newTarget +";"+ splits[2] +";"+ splits[3] +";"+ splits[4] +";"+ splits[5]);
		 								System.out.println("Başarıyla değiştirildi.");
		 								break;
		 								
		 							case "subject":
		 								bw.write(splits[0] +";"+ splits[1]  +";"+ splits[2] +";"+ splits[3] +";"+ newTarget +";"+ splits[5]  );
		 								System.out.println("Başarıyla değiştirildi.");
		 								break;
		 								
		 							}
		 							bw.newLine();//alt satıra geçer
		 						}
		 						else {// ddeğiştirilecek kullanıcı öğretmen değil ise
		 							switch(target) {
			 						
		 							case "name":
		 								bw.write( splits[0] +";"+ splits[1] +";"+ newTarget +";"+ splits[3] +";"+ splits[4]);
		 								System.out.println("Başarıyla değiştirildi.");
		 								break;
		 							
		 							case "sName":
		 								bw.write( splits[0] +";"+ splits[1] +";"+ splits[2] +";"+ newTarget +";"+ splits[4]);
		 								System.out.println("Başarıyla değiştirildi.");
		 								break;
		 								
		 							case "userName":
		 								bw.write( newTarget +";"+ splits[1] +";"+ splits[2] +";"+ splits[3] +";"+ splits[4]);
		 								System.out.println("Başarıyla değiştirildi.");
		 								break;
		 								
		 							case "password":
		 								bw.write(splits[0] +";"+ newTarget +";"+ splits[2] +";"+ splits[3] +";"+ splits[4]);
		 								System.out.println("Başarıyla değiştirildi.");
		 								break;
		 								
		 							}
		 							bw.newLine();
		 						}		
		 				}

		 					else {// değiştireceğimiz kullanıcıya ait veriler değil ise aynen yazar
		 						bw.write(line);
		 						bw.newLine();
		 					}
		        	 }
		        }   	 
			}
		        
		        catch (IOException e) {
	            System.out.println("Hata: " + e.getMessage());
		        }
		        users.delete(); //eski dosyayı siler
		        newUsers.renameTo(users); // yeni dosyayı yeniden adlandırır
		       
		        
		        
		}	
		
		
		/**
		 * Belgeden kullanıcı ile alaklı verileri görüntülemeyi sağlayan fonksiyon.
		 * @param userName = Görüntülenecek kullanıcının kullanıcı adı.
		 * @param password = Görüntülenecek kullanıcının şifresi.
		 * @param target = Görüntülenmek istenen veriyi belirtir.
		 */
		public String show (String userName, long password, String target) {
				
			File users = new File("txt/users.txt");
			
				try(Scanner fileInput = new Scanner(users)) {
					
					while(fileInput.hasNextLine()) {
						String line = fileInput.nextLine();
						String[] splits = line.split(";");
				
						String passwordString = Long.toString(password);
				
						if(splits.length==5 || splits.length==6) {
							if(splits[0].equals(userName) && splits[1].equals(passwordString)) { //aradığmız kullanıcı mı
								
								switch(target) {//hedef verinin ait olduğu parça geri döndürülür
								
								case "subject": // eğer ders ismi verisi isteniyor ise kullanıcı öğretmen mi diye kontrol edilir
									if(splits.length==6) {
										fileInput.close();
										return splits[4];
									}
									else {
										System.out.println("Kullanıcı öğretmen değil!");
										fileInput.close();
										return null;
									}
					
								case "name":
									fileInput.close();
									return splits[2];
								
								case "sName":
									fileInput.close();
									return splits[3];
									
								case "role":
									if(splits.length==6) {
										fileInput.close();
										return splits[5];
									}
									else
										return splits[4];
								default:
									System.out.println("Hata!");
									fileInput.close();
									return null;
								}
								}
						}
					}
				}
				catch (FileNotFoundException e) {
					return null;
				}
				System.out.println("Hata!");
				return null;
		}	
		
		/**
		 * Belgeden kullanıcı silmeyi/kaldırmayı sağlayan fonksiyon.
		 * @param userName = Silinecek kullanıcının kullanıcı adı.
		 * @param password = Silinecek kullanıcının şifresi.
		 */
		public void remove(String userName,long password) {
			
			File users = new File("txt/users.txt");//verileri almak için gereken dosya
	        File newUsers = new File("txt/temp.txt");//verileri yazıcağımız dosya
			
	        try (BufferedReader br = new BufferedReader(new FileReader(users)); BufferedWriter bw = new BufferedWriter(new FileWriter(newUsers))){
	        	String line;
	        	String passwordString = Long.toString(password);
	        	
	        	 while ((line = br.readLine()) != null) {
	        		 String[] splits = line.split(";");
	        		 
	        		 if(splits.length >=5) {
	        			 
	 					if(splits[0].equals(userName) && splits[1].equals(passwordString)) { //eğer sileceğimiz kullanıcı ise bir şey yazmadan devam eder
	 						continue;
	 						}

	 					else {//eğer sileceğimiz kullanıcı değil ise aynen yazar
	 						bw.write(line);
	 						bw.newLine();
	 						
	 					}
	        		 }
	        		 else {
	        			 System.out.println("Hata: Bozuk dosya yapısı.");
	        		 }
	        	 }   
	        }
	        catch (IOException e) {
	        System.out.println("Hata: " + e.getMessage());
	        }
	        if (users.delete()) {
	            if(newUsers.renameTo(users)) //eski dosya silinerek yeni dosya yeniden adlandırılır.
	                 System.out.println("Başarıyla silindi.");
	            
	            }
	         else 
	            System.out.println("Eski dosya silinemedi (Dosya açık olabilir).");
	        
	    }
	}
		
