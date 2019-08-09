public class User {
	String nickName;
	String id;
	int rNum = 0;	
	public User(String nickName, String id, int rNum) {
		this.nickName = nickName;
		this.id = id;
		this.rNum = rNum;
	}
	public User(String nickName, String id) {
		this.nickName = nickName;
		this.id = id;
	}
	public void userprint() {
		System.out.println("Id : "+ id +"닉네임 : " + nickName );
	}
}
