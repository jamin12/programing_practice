package hello.springtx;

public class Seoul implements Clap {
	public Seoul(){

	}
	@Override
	public String do369(int number) {
		String numberString = String.valueOf(number);
		if (numberString.contains("3") || numberString.contains("6") || numberString
			.contains("9")) {
			return "clap";
		}
		return numberString;
	}
}