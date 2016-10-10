package cipher;

import java.util.Random;

public class OneTimePad {
	
	static String thisKey;
	static boolean decrypt= false;
	
	public static char _randomKeyGenerator(){
		String keySpace = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random random = new Random();
		int index = random.nextInt(keySpace.length());
		
		return keySpace.charAt(index);
	}
	
	public static int _convertCharToInt(char ch1, char ch2){
		int ptCharValue = (int)ch1;
		int keyCharValue = (int)ch2;
		int newCharValue = 0;
		try{
			if(ptCharValue >= 65 && ptCharValue < 91){
				ptCharValue = ptCharValue % 65;
			}
			else if(ptCharValue >= 97 && ptCharValue < 123){
				ptCharValue = ptCharValue % 97;
			}
			if(keyCharValue >= 65 && keyCharValue < 91){
				keyCharValue = keyCharValue % 65;
			}
			else if(keyCharValue >= 97 && keyCharValue < 123){
				keyCharValue = keyCharValue % 97;
			}
			
			if(decrypt == true){
				newCharValue = ptCharValue - keyCharValue;
				if(newCharValue < 0){
					newCharValue = 26 + newCharValue;
				}
				else{
					newCharValue %= 26;
				}		
			}
			else{
				newCharValue = (ptCharValue + keyCharValue) % 26;
			}
	
		}
		catch(Exception e){
			System.out.println("Mesaage should only contain characters");
		}		
		return newCharValue;
	}
	
	public static String _calculateOTP(String pt, String key){
		int val;
		String ct = "";
		for(int i=0; i<pt.length(); i++){
			val = _convertCharToInt(pt.charAt(i), key.charAt(i));
			ct += (char)(val + 65);
		}
		return ct;
	}
	
	public static String _encryption(String pt){
		 String cipher = pt;
		 String key = "";
		 for(int i=0; i<pt.length(); i++){
			 key += _randomKeyGenerator();
		 }
		 thisKey = key;
		 System.out.println("key: " + key);
		 System.out.println("------------------------------------------");
		 cipher = _calculateOTP(pt, key);
		 return cipher;
	}
	
	public static String _decryption(String ct, String k){
		String pt= "";
		decrypt = true;
		pt = _calculateOTP(ct, k);		
		return pt;
	}
	
	public static void main(String[] args){
		String plainText = "hello";
		plainText = plainText.replaceAll("\\s", "");
		System.out.println("Plain-Text: " + plainText);
		
		System.out.println("------------------------------------------");
		String cipherText = _encryption(plainText);
		System.out.println("Cipher-Text: " + cipherText);
		
		System.out.println("------------------------------------------");
		plainText = _decryption(cipherText, thisKey);
		System.out.println("Plain-Text after Decryption: " + plainText);
	}

}
