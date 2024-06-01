package practice.datadriventesting;

public class AlphaNumericRandomString {

	public static void main(String[] args) {
		int n = 20; // length of the output string
		// choose a character random from String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
		// Create String Buffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; i++)
		// generate a random between o to alphanumeric
		{
			int index = (int) (AlphaNumericString.length() * Math.random());
			// add character one by one of sb
			sb.append(AlphaNumericString.charAt(index));
			
		}
		System.out.println(sb);
	}
}