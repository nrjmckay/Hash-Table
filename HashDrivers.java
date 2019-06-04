/**
 * Driver method to test the hashtables
 * @author Nolan
 */
public class HashDrivers{
	public static void main(String[] args){
		int size = 6;
		LinearProbing s1 = new LinearProbing(size);
		QuadraticProbing s2 = new QuadraticProbing(size);
		SeparateChaining s = new SeparateChaining(size);
		HashPair a = new HashPair("a"); 
		HashPair b = new HashPair("b"); 
		HashPair c = new HashPair("c"); 
		HashPair d = new HashPair(9,"d");
		//HashPair e = new HashPair(0,"d");
		//HashPair f = new HashPair(-2,"f");
		
		for(int i =0; i <7; i++) {
			s.add(new HashPair(i+1,String.valueOf((char)(97+i))));
			s1.add(new HashPair(i+1,String.valueOf((char)(97+i))));	
			}
		s.print();
		s.remove(4);
		s1.remove(4);
		
		s.display();
		s1.display();
		/*for(int i =0; i <26; i++) {
			s1.add(new HashPair(String.valueOf((char)(97+i))));
		}
		
		for(int i =0; i <26; i++) {
			s2.add(new HashPair(String.valueOf((char)(97+i))));
		}
		s1.display();
		s2.display();
		s1.printPut();
		s2.printPut();
		s1.add(a);
		s2.add(a);
		s1.add(b);
		s2.add(b);
		s1.add(c);
		s2.add(c);

		s1.remove(b.getKey());
		s2.remove(b.getKey());
		s1.print();
		s2.print();
		s1.display();
		s2.display();
		s1.printPut();
		s2.printPut();*/
	
	}
}