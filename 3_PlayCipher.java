package cipher;

public class PlayCipher {
	
	static char[] ch = new char[2];
	
	public static String addBogusCharacter(String pt){
		char bogusChar = 'x';
		StringBuilder str = new StringBuilder(pt);

		for(int i=0; i<(pt.length()-1); i++){
			if(str.charAt(i) == str.charAt(i+1)){
				str.insert(i+1, bogusChar);
			}
		}
		if((str.length() % 2) != 0){
			str = str.append(bogusChar);
		}
		pt = str.toString();
		return pt;
	}
	
	public static String _searchKeyToEncrypt(String pt, char[][] key){
		ch[0] = pt.charAt(0);
		ch[1] = pt.charAt(1);
		int row_char1=0, col_char1=0, row_char2=0, col_char2= 0;
		
		
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				if(key[i][j] == ch[0]){
					row_char1 = i;
					col_char1 = j;
				}
				if(key[i][j] == ch[1]){
					row_char2 = i;
					col_char2 = j;
				}
			}
		}
		//if both the characters lies in the same row;
		if(row_char1 == row_char2){
			ch[0] = key[row_char1][col_char1 + 1];
			ch[1] = key[row_char2][col_char2 + 1];
		}
		//if both the characters lies in the same column;
		else if(col_char1 == col_char2){
			ch[0] = key[row_char1 + 1][col_char1];
			ch[1] = key[row_char2 + 1][col_char1];
		}
		//otherwise
		else{
			ch[0] = key[row_char1][col_char2];
			ch[1] = key[row_char2][col_char1];
		}
		
		pt = String.valueOf(ch);
		return pt;
	}
	
	public static String _applyKeyToEncrypt(String pt, char[][] key){
		String subString = "";
		for(int i=0; i<pt.length()/2; i++){
			subString += _searchKeyToEncrypt(pt,key);
		}
		return subString;
	}
	
	public static String _encrypt(String pt, char[][] key){
		String ct= "";
		pt = addBogusCharacter(pt);
		for(int i=0; i<pt.length(); i+=2){
			ct += _applyKeyToEncrypt(pt.substring(i, i+2), key);
		}
		return ct;
	}
	
	public static String _searchKeyToDecrypt(String ct, char[][] key){
		ch[0] = ct.charAt(0);
		ch[1] = ct.charAt(1);
		int row_char1=0, col_char1=0, row_char2=0, col_char2= 0;
		
		
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				if(key[i][j] == ch[0]){
					row_char1 = i;
					col_char1 = j;
				}
				if(key[i][j] == ch[1]){
					row_char2 = i;
					col_char2 = j;
				}
			}
		}
		//if both the characters lies in the same row;
		if(row_char1 == row_char2){
			ch[0] = key[row_char1][col_char1 - 1];
			ch[1] = key[row_char2][col_char2 - 1];
		}
		//if both the characters lies in the same column;
		else if(col_char1 == col_char2){
			ch[0] = key[row_char1 - 1][col_char1];
			ch[1] = key[row_char2 - 1][col_char1];
		}
		//otherwise
		else{
			ch[0] = key[row_char1][col_char2];
			ch[1] = key[row_char2][col_char1];
		}
		ch[0] = ch[0];
		ch[1] = ch[1];
		ct = String.valueOf(ch);
		return ct;
	}
	
	public static String _applyKeyToDecrypt(String ct, char[][] key){
		String subString = "";
		for(int i=0; i<ct.length()/2; i++){
			subString += _searchKeyToDecrypt(ct,key);
		}
		return subString;
	} 
	
	public static String _decrypt(String ct, char[][] key){
		String pt = "";
		for(int i=0; i<ct.length(); i+=2){
			pt += _applyKeyToDecrypt(ct.substring(i, i+2), key);
		}
		return pt;
	}
	
	public static void main(String[] args){
		String plainText = "anupama";
		char[][] key = {
				{'l','g','d','b','a'},
				{'q','m','h','e','c'},
				{'u','r','n','i','f'},
				{'x','v','s','o','k'},
				{'z','y','w','t','p'}
		};
		plainText = plainText.replaceAll("\\s", "");
		System.out.println("Plain-Text: " + plainText);
		
		String cipherText = _encrypt(plainText,key);
		System.out.println("Cipher-Text: " + cipherText);
		
		plainText = _decrypt(cipherText, key);
		System.out.println("Plain-Text after Decryption: " + plainText);
	}
}

