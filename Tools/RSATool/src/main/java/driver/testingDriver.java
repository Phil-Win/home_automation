package main.java.driver;

import java.math.BigInteger;

import main.java.util.ASCIIMinus31Encoder;

public class testingDriver {

	public static void main(String[] args) {
		char letter;
		// TODO Auto-generated method stub
        for(int i =33; i<126; i++)
        {
        	letter = (char) i;
            System.out.println( i + ". " + letter);
            System.out.println((byte) letter); 
        }
        String message = " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
        ASCIIMinus31Encoder asdf = new ASCIIMinus31Encoder(message);
        System.out.println(asdf.getMessage());
        System.out.println(asdf.messageAsBigIntASCIIMinus31());
        System.out.println("Ok, now reverse reverse!");
        BigInteger messageInt = new BigInteger("102030405060708091011121314151617181920212223242526272829303132333435363738394041424344454647484950515253545556575859606162636465666768697071727374757677787980818283848586878889909192939495");
        ASCIIMinus31Encoder asdf2 = new ASCIIMinus31Encoder(messageInt);
        System.out.println(asdf2.getMessage());
        System.out.println(asdf2.messageAsBigIntASCIIMinus31());
        
        
	}

}
