package Users;
import java.util.List;
import java.util.Scanner;
import FileOp.QuizOp;
import FileOp.UserOp;
import Main.Main;

public class Student {
    
    private String name, sName, userName;
    private long password;
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getPassword() {
		return password;
	}

	public void setPassword(long password) {
		this.password = password;
	}

	static Scanner input = new Scanner(System.in);
    QuizOp quizOp = new QuizOp();
    UserOp userOp = new UserOp();
    public Student (String name, String sName, String userName, long password ) throws Exception {
        this.name = name;
        this.sName = sName;
        this.userName = userName;
        this.password = password;
    }
    
    public void UI() {
        try {
            boolean check = true;
             
            while(check) {
                System.out.println("\nYapmak istediğiniz işlemi seçiniz.\n\n");
                System.out.println("1) Quizlerimi görüntüle.");
                System.out.println("2) Quize gir.");
                System.out.println("3) Çıkış yap.");
            
                int choice = -1;
                try {
                    choice = input.nextInt();
                    input.nextLine(); 
                } catch (Exception e) {
                    input.nextLine();
                }
             
                switch(choice) {
                     
                    case 1:
                        quizOp.scoresList(userName, password, name, sName);
                        break;
                      
                    case 2:
                        List<String> quizesWsubject = quizOp.unsolvedQuizes(userName);
                        
                        if (quizesWsubject != null && !quizesWsubject.isEmpty()) {
                            System.out.println("Girmek istediğiniz quizi seçiniz.\n");
                            System.out.println("Quizleriniz: \n");
                            
                            for(int i=0; i < quizesWsubject.size(); i++) {
                                String quizesWsubjectLine = quizesWsubject.get(i);
                                String quizes[] = quizesWsubjectLine.split("\\#"); 
                                if(quizes.length > 1) {
                                    System.out.println((i+1) + ") Ders: " + quizes[0]+ "Quiz ismi: "+quizes[1]);
                                } else {
                                    System.out.println(" Hatalı dosya yapısı!");
                                }
                            }
                            
                            int quizChoice;
                            while(true) {
                                try {
                                    quizChoice = input.nextInt();
                                    input.nextLine();
                                    if(quizChoice <= 0 || quizChoice > quizesWsubject.size())
                                        System.out.println("Geçersiz değer! Tekrar giriniz.");
                                    else
                                        break;
                                } catch(Exception e) {
                                    System.out.println("Lütfen sayı giriniz.");
                                    input.nextLine();
                                }
                            }
                            
                            String quizesWsubjectLine = quizesWsubject.get(quizChoice-1);
                            String[] quiz = quizesWsubjectLine.split("\\#");
                            String subject = quiz[0];
                            String quizName = quiz[1];
                            quizOp.enterQuiz(subject, quizName, userName, password);
                            UI();
                        } else {
                            System.out.println("Çözülecek aktif quiz bulunamadı.");
                        }
                        
                        break;
                     
                    case 3:
                        Main.logIO();
                        check = false;
                        break;
                    
                    default:
                        System.out.println("Geçerli bir değer giriniz. (1, 2 veya 3)");
                }
            }
        } catch (Exception e) {
             System.out.println("Hata! " + e.getMessage());
        }
    }
}