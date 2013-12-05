
public class MyFirstClass {
	
	public static void main(String[] args){
		
		String s1 = getGreeting();
		String s2 = makeMoreExciting(s1);
		String s3 = makeMoreExciting(s2);
		String s4 = makeMoreExciting(s3);
		System.out.println(s4);
		
	}
	public static String getGreeting(){
		return "Greetings";
	}
	public static String makeMoreExciting(String s) {
		String s2 = s + "!";
		return s2;	
	}
	public static String makeMoreExciting1(String s) {
		String s2 = null;
		String s3 = s2 + "!";
		return s3;	
	}
	public static String makeMoreExciting2(String s) {
		String s3 = null;
		String s4 = s3 + "!";
		return s4;
	}

}
