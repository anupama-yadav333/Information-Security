package cipher;

public class VigenereCipher {
	static int chValue;
	
	public static int PSValue(char ch){
		chValue = (int)ch;
		try{
			if(chValue >= 65 && chValue < 91){
				chValue = chValue % 65;
			}
			else if(chValue >= 97 && chValue < 123){
				chValue = chValue % 97;
			}
		}
		catch(Exception e){
			System.out.println("Mesaage should only contain characters");
		}		
		return chValue;
	}
	
	public static int keyStreamValue(char key){
		chValue = PSValue(key);
		return chValue;
	}
	
	public static char convertIntToChar(int val){
		char ch = (char)(val+65);
		return ch;
	}
	
	public static String _encrypt(String plainText, String keyStream){
		int charValue,keyValue = 0,cipherCharValue;
		char[] cipherText = new char[plainText.length()];
		
		int j=0;		
		for(int i=0; i<plainText.length(); i++){
			charValue = PSValue(plainText.charAt(i));			
			if(j>=keyStream.length()){                             //this is to form a circulation between PASCAL key
				j=0;
			}
			keyValue = keyStreamValue(keyStream.charAt(j));	
			
			cipherCharValue = charValue + keyValue;
			if(cipherCharValue > 26){
				cipherCharValue %= 26;
			}			
			cipherText[i] = convertIntToChar(cipherCharValue);
			j++;
		}
		
		String cipher = String.valueOf(cipherText);
		return cipher;
	}
	
	public static String _decrypt(String ct, String key){
		int charValue,keyValue,plainCharValue = 0;
		char[] plainText = new char[ct.length()];
		int j=0;
		for(int i=0; i<ct.length(); i++){
			charValue = PSValue(ct.charAt(i));			
			if(j >= key.length()){                             //this is to form a circulation between PASCAL key
				j=0;
			}
			keyValue = keyStreamValue(key.charAt(j));
			
			charValue += 26;
			plainCharValue = charValue - keyValue;
			if(plainCharValue >= 26){
				plainCharValue %= 26;
			}
			plainText[i] = convertIntToChar(plainCharValue);
			j++;
		}
		
		String decipher = String.valueOf(plainText);
		return decipher;		
	}
	
	public static void main(String[] args){
		
		String plainText = "attackatdawn";
		plainText = plainText.replaceAll("\\s", "");
		String keyStream = "LEMON";
		
		System.out.println("--------------------------------------");
		String cipherText = _encrypt(plainText, keyStream);
		System.out.println("Encrypted Text: " + cipherText);
		System.out.println("--------------------------------------");
		plainText = _decrypt(cipherText, keyStream);
		System.out.println("Decrypted Text: " + plainText);
	}
}