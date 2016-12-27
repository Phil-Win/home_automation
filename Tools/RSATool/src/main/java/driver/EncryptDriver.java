package main.java.driver;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Properties;

import main.java.util.ASCIIMinus31Encoder;

public class EncryptDriver {
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        BigInteger PublicKeyN;
        BigInteger PublicKeyE;
        BigInteger encryptedMessage;
        String message;
        ASCIIMinus31Encoder encodedMessage;
        Properties prop = new Properties();
        InputStream input = null;
        
        if (args.length != 2) {
            System.out.println("Sorry, you must enter 2 parameters, 1st=public key file 2nd=message to encrypt");
            System.exit(0);
        }
        
        try {
            input = new FileInputStream(args[0]);
            prop.load(input);
            PublicKeyN = new BigInteger(prop.getProperty("PublicKeyN"));
            PublicKeyE = new BigInteger(prop.getProperty("PublicKeyE"));
            message = args[1];
            
            encodedMessage = new ASCIIMinus31Encoder(message);
            
            if (encodedMessage.getMessageAsBigIntASCIIMinus31().compareTo(PublicKeyN) == 1) {
                System.out.println("Sorry, your message is too big");
                System.exit(0);
            }
            
            encryptedMessage = encodedMessage.getMessageAsBigIntASCIIMinus31().modPow(PublicKeyE, PublicKeyN);
            System.out.println(encryptedMessage.toString());
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
