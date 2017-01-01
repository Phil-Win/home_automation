This tool will have 3 drivers... to generate keys, encrypt and decrypt

java -cp RSATool.jar main.java.driver.DecryptDriver <Location of the private key> <String to decrypt>
java -cp RSATool.jar main.java.driver.EncryptDriver <location of the public key> <String to encrypt>

java -cp RSATool.jar main.java.driver.KeyGenDriver <will create a public/private key that'll range from 2048 bits to 4098 bits>