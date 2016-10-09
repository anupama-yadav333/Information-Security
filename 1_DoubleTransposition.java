package cipher;

public class DoubleTransposition {
	
	public static String insertBogus(String pt, int col){
		
		int bogus_char;
		if(pt.length() % col== 0){
			return pt;
		}
		else{
			bogus_char = col - (pt.length() % col);
			for(int i=0; i<bogus_char; i++){
				pt = pt + "z";
			}
			return pt;
		}
	}
	
	public static String _encrypt_permuteColumns(String substr,String encrypt_key, int col){
		char[] row = new char[col];
		int index;
		for(int i=0; i<col; i++){
			index = encrypt_key.charAt(i);           //ASCII value of 2 is returned by encrypt_key.charAt because encrypt_key is of String type
			index -= 48;
			row[i] = substr.charAt(index);
		}
		String permutedText = String.valueOf(row);
 		return permutedText;
	}
	
	public static String _decrypt_permuteColumns(String substr,String decrypt_key, int col){
		char[] row = new char[col];
		int index;
		for(int i=0; i<col; i++){
			index = decrypt_key.charAt(i);           //ASCII value of 2 is returned by decrypt_key.charAt because encrypt_key is of String type;
			index -= 48;
			row[i] = substr.charAt(index);
		}
		String permutedText = String.valueOf(row);
 		return permutedText;
	}
	
	public static String _readColumnsWise(String mt, int col){
		char[] row = new char[mt.length()];
		int pos=0;
		for(int i=0; i<col; i++){
			for(int j=i; j<mt.length(); j+=5){
				row[pos++] = mt.charAt(j);
			}
		}
		String newString = String.valueOf(row);
		return newString;
	}
	
	public static String _writeColumnWise(String ct, int col){
		char[] row = new char[ct.length()];
		int pos = 0;
		for(int i=0; i<ct.length()/col; i++){
			for(int j=i; j<ct.length(); j+=4){
				row[pos++] = ct.charAt(j);
			}
		}
		String newString = String.valueOf(row);
		return newString;
	}
	
	public static String encryption(String pt,String encrypt_key, int col){
		String middleString = "";
		pt = insertBogus(pt,col);
		for(int i=0; i<pt.length(); i+=col){
			middleString += _encrypt_permuteColumns(pt.substring(i, i+(col)),encrypt_key, col);
		}
		middleString = _readColumnsWise(middleString, col);
		return middleString;
	}
	
	public static String decryption(String ct,String decrypt_key, int col){
		String middleString = "";
		ct = _writeColumnWise(ct, col);
		for(int i=0; i<ct.length(); i+=col){
			middleString += _decrypt_permuteColumns(ct.substring(i, i+(col)),decrypt_key, col);
		}
		return middleString;
	}
	
	public static void main(String[] args){
		int column = 5;
		String plainText = "enemy attacks tonight";
		plainText = plainText.replaceAll("\\s", "");
		System.out.println("Original Plain-Text: " + plainText);
		
		System.out.println("--------------------------------");
		String plaintToCipherMiddle = encryption(plainText,"20341", column);
		System.out.println("Plain-Text to Cipher-Text(Middle-Text): " + plaintToCipherMiddle);
		
		System.out.println("--------------------------------");
		String cipherText = encryption(plaintToCipherMiddle,"20341", column);
		System.out.println("Cipher Text: " + cipherText);
		System.out.println("--------------------------------");
		
		String cipherToPlainMiddle = decryption(cipherText,"14023", column);
		System.out.println("Cipher-Text to Plain-Text(Middle-Text): " + cipherToPlainMiddle);
		
		System.out.println("--------------------------------");
		String newPlainText = decryption(cipherToPlainMiddle,"14023", column);
		System.out.println("Plain Text: " + newPlainText);		
	}

}
