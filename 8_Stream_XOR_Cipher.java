package cipher;

public class Stream_XOR_Cipher {
	
	static String keyBin;
	
	public static String charToBin(char ch){
		int charToInt = (int)ch;
		StringBuilder str = new StringBuilder("00000000");
		int pos = 7;
		
		while(charToInt > 0){
			if(charToInt % 2 == 0){
				str.setCharAt(pos, '0');
			}
			else{
				str.setCharAt(pos, '1');
			}
			charToInt = charToInt / 2;
			pos -= 1;
		}
		String bin = str.toString();
		return bin;
	}
	
	public static String strToBin(String pt){
		String bin = "";
		for(int i=0; i<pt.length(); i++){
			bin += charToBin(pt.charAt(i));
		}	
		return bin;
	}
	
	public static String _XORWithKey(String binValue, String key){                     
		String cipher = "";
		for(int i=0; i<key.length(); i++){
			if(binValue.charAt(i) == key.charAt(i)){
				cipher += '0';
			}
			else{
				cipher += '1';
			}
		}
		return cipher;
	}
	
	public static String encryption(String pt, String keyStream){
		String ptBin = strToBin(pt);
		System.out.println("PlainText-Binary: " + ptBin);
		keyBin = strToBin(keyStream);
		System.out.println("KeyStream-Binary: " + keyBin);
			
		return _XORWithKey(ptBin, keyBin);
	}
	
	public static String decryption(String ct, String keyBin){
		
		return _XORWithKey(ct, keyBin);
	}
	
	public static void main(String[] args){
		String plainText = "Wiki";
		plainText = plainText.replaceAll("\\s", "");
		String keyStream = "ABCD";
		System.out.println("Plain-Text: " + plainText);
		System.out.println("Key: " + keyStream);
		
		System.out.println("----------------------------------------------------");
		String cipherText = encryption(plainText, keyStream);
		System.out.println("Cipher-Text: " + cipherText);
		
		System.out.println("----------------------------------------------------");
		plainText = decryption(cipherText, keyBin);
		System.out.println("Plain-Text after decryption: " + plainText);
	}

}
