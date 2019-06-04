
/**
 * HashTable data structure
 * Three implemented collision resolution mechanisms: 
 * (1)separate chaining (LinkedList), 
 * (2) linear probing, and
 * (3) quadratic probing. 
 * 
 * @author Nolan
 */
public abstract class HashTable{
	/**
	 *Each Hashtable has a size(preferably prime), the number of entries and counts the number of collisons
	 */
	protected int size;
	protected int collisions = 0;
	protected int entries = 0;


	/**
	 * @param size of the array
	 */
	public HashTable(int size){
		this.size = size;
	}

	/**
	 * Hashfunction to compute the index for which the hashpair will be put in
	 * Sum component of the long key, add the first and last 30 bits of the long and mod by the size of the hashtable
	 * @param key of the hashpair
	 * @return the int of the index 
	 */
	public int hashFunction(long key){
		String s = Long.toBinaryString(key);
		if(s.length() <31) {
			return Integer.parseInt(s, 2)% this.size;
		}
		//Split the long into 30 bit strings
		String first = s.substring(0,(int)s.length()-30);//first digits
		String last = s.substring((int)s.length()-30); //get the last 30 digits
        int fir = Integer.parseInt(first, 2);
		int las = Integer.parseInt(last, 2);
		return (fir+las)% this.size;
		
	}

	public int size(){
		return this.size;
	}

	public int getCollisions(){	
		return collisions;
	}

	public int getEntries(){
		return this.entries;
	}

	/**
	 * @return if the hashtable is empty
	 */
	public boolean isEmpty(){
		if (this.entries == 0){
			return true;
		}
		return false;
	}

	/**
	 *Print out info about the table
	 * 
	 */
	public void printPut(){
		System.out.println(this.getClass().getSimpleName() + " collision handling Hash Table:\n"+
				"\tSize: " + this.size +
				"\n\tEntries: " + this.entries +
				"\n\tCollisions: " + this.collisions +".\n");
	}

}