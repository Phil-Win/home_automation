package main.java.driver;

import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Random;

public class KeyGenDriver {

	public static void main(String[] args) throws IOException {
    Random rng = new Random();
    int randomBit;
    Random randomNumber = new SecureRandom();
    
    randomBit = rng.nextInt(1025)+1024;
    BigInteger p1 = new BigInteger(randomBit,100,randomNumber);
    randomBit = rng.nextInt(1025)+1024;
    BigInteger p2 = new BigInteger(randomBit,100,randomNumber);
    BigInteger publicKeyN = p1.multiply(p2);
    BigInteger totientNumber = (p1.subtract(BigInteger.ONE)).multiply(p2.subtract(BigInteger.ONE));
    BigInteger publicKeyE = BigInteger.valueOf(65537);
    while (!totientNumber.gcd(publicKeyE).equals(BigInteger.ONE)) {
      publicKeyE = new BigInteger(18,100,randomNumber);
    }
    BigInteger privateKeyD = publicKeyE.modInverse(totientNumber);
        
    String pubFile1 = "RSAPublicKey.txt";
    System.out.println("Writing to file: " + pubFile1);
    try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(pubFile1))) {
        writer.write("PublicKeyN="+publicKeyN.toString());
        writer.write("\nPublicKeyE="+publicKeyE.toString());
    }

    String ppkFile1 = "RSAPubPriKey.txt";
    System.out.println("Writing to file: " + ppkFile1);
    try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(ppkFile1))) {
        writer.write("PublicKeyN="+publicKeyN.toString());
        writer.write("\nPublicKeyE="+publicKeyE.toString());
        writer.write("\nPrivateKeyD="+privateKeyD.toString());
    }

	}


}
