
abstract class User {
	String name,sName,userName;
	long password;
	
	enum Role{
		Admin,Teacher,Student
	}
	
	Role role;
	
	public User (String name, String sName, String userName, long password, Role role){
		this.name = name;
		this.sName = sName;
		this.userName = userName;
		this.password = password;
		this.role = role;
	}
	public static void UI() {
	}
	
	
}
