package cipher;

public class HillCipher {
	
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
	
	public static int _convertCharToInt(char ch){
		int chValue = (int)ch;
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
	
	public static String _convertIntToChar(int[][] cipherMatrix, int row, int col){
		String ct= "";
		int val;
		for(int i=0; i<row; i++){
			for(int j=0; j<col; j++){
				val = cipherMatrix[i][j] + 65;
				ct += (char)val;
			}
		}
		return ct;
	}
	
	public static String _multiplyMatrices(int[][] p, int[][] key, int row, int col){
		int[][] cipherMatrix = new int[row][col];
		for(int i=0; i<row; i++){
			for(int j=0; j<col; j++){
				cipherMatrix[i][j] = 0;
				for(int k=0; k<col; k++){
					cipherMatrix[i][j] += p[i][k]*key[k][j]; 
				}
				cipherMatrix[i][j] %= 26;
			}
		}
		String cipher = _convertIntToChar(cipherMatrix, row, col);
		return cipher;
	}
	
	public static String _encrypt(String pt, int col, int[][] key){
		pt = insertBogus(pt, col);
		int row = pt.length()/col;
		int[][] plainTextMatrix = new int[pt.length()/col][col];
		int pos= 0;
		for(int i=0; i<pt.length()/col; i++){                                  //creating matrix of plain-Text;
			for(int j=0; j<col; j++){
				plainTextMatrix[i][j] = _convertCharToInt(pt.charAt(pos++));
			}
		}
		String cipher = _multiplyMatrices(plainTextMatrix, key, row, col);
		return cipher;
	}
	
	public static String _decrypt(String ct, int col, int[][] keyInverse){
		String pt = _encrypt(ct, col, keyInverse);
		return pt;
	}
	
	public static void main(String[] args){
		String plainText = "code is ready";
		plainText = plainText.replaceAll("\\s", "");
		System.out.println("Plain-Text: " + plainText);
		
		int[][] key = {
				{9,7,11,13},
				{4,7,5,6},
				{2,21,14,9},
				{3,23,21,8}
		};
		int[][] keyInverse = {
				{2,15,22,3},
				{15,0,19,3},
				{9,9,3,11},
				{17,0,4,7}
		};
		String cipherText = _encrypt(plainText, 4, key);
		System.out.println("Cipher-Text: " + cipherText);
		
		plainText = _decrypt(cipherText, 4, keyInverse);
		System.out.println("Plain-Text after decryption: " + plainText);
	}
}
