package cipher;

import java.util.HashMap;

public class Monoalphabetic {
	
	public static String changeCharAt(int index, char c, String ct){
		char[] charArray = ct.toCharArray();
		charArray[index] = c;
		return new String(charArray);
	}
	
	public static String _encryption_Monoalphabetic(String pt){
		String ct = pt;
		HashMap<Character, Character> hMap = new HashMap<Character, Character>();
		hMap.put('a', 'N');
		hMap.put('b', 'O');
		hMap.put('c', 'A');
		hMap.put('d', 'T');
		hMap.put('e', 'R');
		hMap.put('f', 'B');
		hMap.put('g', 'E');
		hMap.put('h', 'C');
		hMap.put('i', 'F');
		hMap.put('j', 'U');
		hMap.put('k', 'X');
		hMap.put('l', 'D');
		hMap.put('m', 'Q');
		hMap.put('n', 'G');
		hMap.put('o', 'Y');
		hMap.put('p', 'L');
		hMap.put('q', 'K');
		hMap.put('r', 'H');
		hMap.put('s', 'V');
		hMap.put('t', 'I');
		hMap.put('u', 'J');
		hMap.put('v', 'M');
		hMap.put('w', 'P');
		hMap.put('x', 'Z');
		hMap.put('y', 'S');
		hMap.put('z', 'W');
	
		for(int i=0; i<pt.length(); i++){			
			ct = changeCharAt(i,hMap.get(pt.charAt(i)),ct);
		}
		
		return ct;
	}
	
	public static String _decryption_Monoalphabetic(String ct){
		String pt = ct;
		HashMap<Character, Character> hMap = new HashMap<Character, Character>();
		hMap.put('N', 'a');
		hMap.put('O', 'b');
		hMap.put('A', 'c');
		hMap.put('T', 'd');
		hMap.put('R', 'e');
		hMap.put('B', 'f');
		hMap.put('E', 'g');
		hMap.put('C', 'h');
		hMap.put('F', 'i');
		hMap.put('U', 'j');
		hMap.put('X', 'k');
		hMap.put('D', 'l');
		hMap.put('Q', 'm');
		hMap.put('G', 'n');
		hMap.put('Y', 'o');
		hMap.put('L', 'p');
		hMap.put('K', 'q');
		hMap.put('H', 'r');
		hMap.put('V', 's');
		hMap.put('I', 't');
		hMap.put('J', 'u');
		hMap.put('M', 'v');
		hMap.put('P', 'w');
		hMap.put('Z', 'x');
		hMap.put('S', 'y');
		hMap.put('W', 'z');
	
		for(int i=0; i<ct.length(); i++){			
			pt = changeCharAt(i,hMap.get(ct.charAt(i)),pt);
		}
		
		return pt;
	}
	
	public static void main(String[] args){
		String plainText = "this message is easy to encrypt but hard to find the key";
		plainText = plainText.replaceAll("\\s", "");
		System.out.println("Original Plain Text: " + plainText);
		
		System.out.println("---------------------------------------------------------------");
		String cipherText = _encryption_Monoalphabetic(plainText);
		System.out.println("Cipher Text: " + cipherText);
		
		System.out.println("---------------------------------------------------------------");
		String newPlainText = _decryption_Monoalphabetic(cipherText);
		System.out.println("Plain Text: " + newPlainText);
	}

}
