package cipher;

public class RabinCipher {
	
	public static int mod(int i, int j) {
	    if (i % j < 0) {
	        return i+j;
	    }
	    return i % j;
	}
	
	// GCD is greatest common divisor function
	public static int GCD(int r1, int r2){
		int q,r;
		while(r2 > 0){
			q = r1/r2;
			r = r1-(q*r2);
			r1 = r2;
			r2 = r;
		}
		return r1;
	}
	
	public static int multiplicativeInverse(int r1, int r2){			// r1 = zn := inverse of r2 in Zn
		int t1= 0, t2= 1, q, r, t;
		int zn = r1;
		try{
			if(GCD(r1, r2) == 1){			//check if GCD exists then enter to while to find multiplicative inverse
				while(r2 > 0){
					q = r1/r2;
					r = r1 - q*r2;
					r1 = r2;
					r2 = r;
					
					t = t1 - q*t2;
					t1 = t2;
					t2 = t;
				}
				if(t1 < 0){
					t1 = mod(t1, zn);
				}
			}
		}catch(Exception e){
			System.out.println("GCD of two numbers is not equal to 1 so inverse doeesn't exists");
			t1= -1;			//unsuccessful return
		}
		return t1;
	}
	
	// Chinese Remainder Theorem will give four solutions
	public static int CRT(int a, int b, int p, int q){
		
		if(GCD(p,q) == 1){
			int M = p*q;
			int p_inv = multiplicativeInverse(q,p);
			int q_inv = multiplicativeInverse(p,q);
			
			return mod((a*q*q_inv + b*p*p_inv), M);
		}
		return -1;
	}
	
	// n here is public key (n = p* q) and pt is plainText
	public static int _encrypt(int n, int pt){
		if(GCD(n,pt) == 1){
			return mod((pt*pt), n);
		}
		return -1;
	}
	
	// p and q are two large distinct prime numbers and ct is cipher number
	public static void _decrypt(int p, int q, int ct){
		int a1 = mod(((int)Math.pow(ct, (p+1)/4)) , p);
		int a2 = mod(-((int)Math.pow(ct, (p+1)/4)) , p);
		int b1 = mod(((int)Math.pow(ct, (q+1)/4)) , q);
		int b2 = mod(-((int)Math.pow(ct, (q+1)/4)) , q);
		
		System.out.println(" P1: " + CRT(a1,b1,p,q));
		System.out.println(" P2: " + CRT(a1,b2,p,q));
		System.out.println(" P3: " + CRT(a2,b1,p,q));
		System.out.println(" P4: " + CRT(a2,b2,p,q));
	}
	
	public static void main(String[] args){
		System.out.println("Encrypted number: " + _encrypt(77,10));			// encryption is ct = p^2 mod n
		_decrypt(7, 11, 23);
	}

}
