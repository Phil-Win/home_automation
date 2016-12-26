package main.java.driver;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

import main.java.util.ASCIIMinus31Encoder;

public class testingDriver {

	public static void main(String[] args) {

        String message = " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
        ASCIIMinus31Encoder asdf = new ASCIIMinus31Encoder(message);
        System.out.println(asdf.getMessage());
        System.out.println(asdf.messageAsBigIntASCIIMinus31());
        System.out.println("Ok, now reverse reverse!");
        BigInteger messageInt = new BigInteger("102030405060708091011121314151617181920212223242526272829303132333435363738394041424344454647484950515253545556575859606162636465666768697071727374757677787980818283848586878889909192939495");
        ASCIIMinus31Encoder asdf2 = new ASCIIMinus31Encoder(messageInt);
        System.out.println(asdf2.getMessage());
        System.out.println(asdf2.messageAsBigIntASCIIMinus31());
        
        System.out.println("Ok, now other stuff!");
		Random rng = new Random();
		int randomBit = rng.nextInt(1025)+1024;
		Random randomNumber = new SecureRandom();
        BigInteger p = new BigInteger(randomBit,100,randomNumber);

        // will get a number ~ 2^300, with P(prime)= 1-.5^20
        System.out.println(p.toString());
	}

}
