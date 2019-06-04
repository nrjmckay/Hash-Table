/**
 * @author Nolan
 * Map element class
 * Each HashPair will have a key(randomly generated long) and a value of type String
 * Keys are assumed to all be unique and >0
 */
public class HashPair{

	/**
	 * Key will be put through hashfuntion to calculate an index to be inserted to
	 */
	private long key;
	private String value;

	/**
	 * A random long is generated between 1 and 2^60(1152921504606846975) for the key 
	 * @param value of the map element
	 */
	public HashPair(String value){
		//Generate a random long for the key
		long ln = 1 + (long) (Math.random() * (Long.MAX_VALUE/8));  //1152921504606846975, 2^60
		this.key = ln;
		this.value = value;
	}

	/**
	 * Constructor
	 * If key =-1 then the hashpair is used in place of removed items. 
	 * Key set to 0 if negative number != -1 is called
	 * @param value of the map element
	 * @param key 
	 */
	public HashPair(long key,String value){
		try{
			if(key<-1 || key ==0){
				this.key = 0;
				throw new Exception("Error: Keys must be positive.");}
		}catch(Exception e){
			System.out.println(e.getMessage());
			return;}
		this.value = value;
		this.key = key;
	}


	/**
	 * Sum component of the long key, add the first and last 30 bits of the long to an int
	 * @return int after computing the sum component 
	 * (Note: the hashcode has not been modded by the size of the table) 
	 */
	public int hashCode(){
		String s = Long.toBinaryString(this.key);
		if(s.length() <31) {
			return Integer.parseInt(s, 2);
		}
		//Split the long into 30 bit strings, 
		String first = s.substring(0,(int)s.length()-30);//first digits,
		String last = s.substring((int)s.length()-30); //get the last 30 digits
		int fir = Integer.parseInt(first, 2);
		int las = Integer.parseInt(last, 2);
		return (fir+las);
	}

	/**
	 * getter
	 * @return the String value to this hashpair
	 */
	public String getValue(){ 
		return this.value;
	}

	/**
	 * getter
	 * @return the long key to this hashpair
	 */
	public long getKey(){
		return this.key;
	}
}