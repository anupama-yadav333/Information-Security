package cipher;

import java.util.HashMap;

public class Monoalphabetic {
	
	public static String changeCharAt(int index, char c, String ct){
		char[] charArray = ct.toCharArray();
		charArray[index] = c;
		return new String(charArray);
	}
	
	public static String monoalphabeticSubstituionCipher(String pt){
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
	public static void main(String[] args){
		String plainText = "this message is easy to encrypt but hard to find the key";
		plainText = plainText.replaceAll("\\s", "");
		System.out.println(plainText);
		String cipherText = monoalphabeticSubstituionCipher(plainText);
		System.out.println(cipherText);
	
	}

}
