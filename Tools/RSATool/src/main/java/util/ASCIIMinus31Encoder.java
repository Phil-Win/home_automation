package main.java.util;

import java.math.BigInteger;

public class ASCIIMinus31Encoder {
	private String message;
	private BigInteger messageAsBigIntASCIIMinus31;

	public ASCIIMinus31Encoder(String inputMessage) {
		this.message = inputMessage;
		int charAsInt;
		String newMessage = "";
		for (char i: message.toCharArray()) {
			charAsInt = ((byte) i) - 31;
			if (charAsInt<0 && charAsInt>94) {
				this.messageAsBigIntASCIIMinus31 = BigInteger.valueOf(0);
				break;
			}
			newMessage += (charAsInt<10) ? "0"+String.valueOf(charAsInt) : String.valueOf(charAsInt);
		}
		this.messageAsBigIntASCIIMinus31 = new BigInteger(newMessage);
	}

	public ASCIIMinus31Encoder(BigInteger inputMessage) {
		this.messageAsBigIntASCIIMinus31 = inputMessage;
		String inputAsString = inputMessage.toString();
		String newMessage = "";
		
		while(inputAsString.length() > 0) {
			if (inputAsString.length()%2==1) {
				newMessage+=(char)(Integer.parseInt((String) inputAsString.subSequence(0, 1))+31);
				inputAsString = inputAsString.substring(1);
			} else {
				newMessage+=(char)(Integer.parseInt((String) inputAsString.subSequence(0, 2))+31);
				inputAsString = inputAsString.substring(2);
			}
		}
		
		this.message = newMessage;
	}
	
	public String getMessage() {
		return message;
	}

	public BigInteger messageAsBigIntASCIIMinus31() {
		return messageAsBigIntASCIIMinus31;
	}
	
	
}
