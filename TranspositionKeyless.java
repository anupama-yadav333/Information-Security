package cipher;

import java.util.ArrayList;
import java.util.List;

public class TranspositionKeyless {
	
	public static List<Character> Encryption(String pt){
		
		//In this cipher, the plaintext is arranged in two lines as a zigzag pattern (which means column by column)
	       //the ciphertext is created reading the pattern row by row.

		List<Character> rowOne = new ArrayList<Character>();
		List<Character> rowTwo = new ArrayList<Character>();
		
		for(int i=0; i<pt.length(); i++){
			if(i%2 == 0){
					rowOne.add(pt.charAt(i));
				}
			if(i%2 != 0){
					rowTwo.add(pt.charAt(i));
				}				
			}
		rowOne.addAll(rowTwo);
		return rowOne;
	}
	
	public static char[] Decryption(List<Character> ct){
		char[] newPt = new char[ct.size()];
		int r1 = 0,r2;
		
		if(ct.size() %2 == 0){
			r2 = ct.size()/2;
		}
		else{
			r2 = ct.size()/2+1;
		}	
		for(int i=0; i<ct.size();i++){
			if(i%2 == 0){
				newPt[i] = ct.get(r1++);
			}
			else{
				newPt[i] = ct.get(r2++);
			}	
		}
		return newPt;
	}
	
	public static void main(String[] args){
		String plainText = "meet me at the park";
		plainText = plainText.replaceAll("\\s", "");
	
		System.out.println("\nEncrypted String: " + Encryption(plainText));
		System.out.print("\nDecrypted String: ");
		char[] originalText = Decryption(Encryption(plainText));
		System.out.print(originalText);
	}
}
