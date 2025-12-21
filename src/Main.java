//Bilgilerimi görüntüle eklenebilir
//Her adımda geri gitme ekle

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
	
	}
	public static void logIO() {
		String userName;
		long password;
		Scanner input = new Scanner(System.in);
        System.out.println("Kullanıcı adınızı giriniz.");
        userName = input.nextLine();
        System.out.println("Şifrenizi giriniz.");
        password = input.nextLong();
        
        if(isExist(userName,password)) {
        	
        }
        else {
        	System.out.println("\n\nKullanıcı bulunamadı! Kullanıcı adınızı ya da şifreniz yanlış olablir.\n\n");
        	logIO();
        }
	}

}
