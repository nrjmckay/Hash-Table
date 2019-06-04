/**
 * Open Addressing,
 * Quadratic Probing collision resolution. 
 * A quadratic polynomial is used to resolve collisions. Less likely to encounter primary clustering than Linear Probing
 * The polynomial used is h(k) + 1^2, h(k) + 2^2, h(k) + 3^2,..., h(k) + n^2 where n is the number of probings
 * QuadraticProbing may fail to compute at every index in the hashtable
 * @author Nolan
 */
public class QuadraticProbing extends HashTable{
	private HashPair[] array;


	/**
	 * @param size initialize the array 
	 */
	public QuadraticProbing(int size){
		super(size);
		this.array = new HashPair[size];
	}


	/**
	 * Method to add in entries, if the table is full throw an exception. Will not be added if the key exists in the table already
	 * In the case of a collision, a quadratic poly is used to compute the probe position
	 * In the case that no empty slot is found, return. 
	 * @param hp the hashpair added to the table
	 */
	public void add(HashPair hp){
		try{
			if(this.entries >= this.size){throw new Exception("Error: The hashtable is full, you cannot add anymore items.");}
			else if(hp ==null){throw new Exception("Error: Could not be added to table.");}
			else if(hp.getKey() <= 0){throw new Exception("Error: Could not be added to table.");}
		}catch(Exception e){
			System.out.println(e.getMessage());
			return;}

		long k = hp.getKey();
		//get hashcode and compress it to it the table
		int hf = hashFunction(k);
		int index = hf;
		int i = 0;
		while(array[index] != null && array[index].getKey() != -1 && i < this.size){
			//no two items can have the same key
			if(k == array[index].getKey())
				return;
			System.out.println("Collision at index " + index + ".");
			i++;
			index = (hf + i*i)%this.size;
			collisions++;
		}	
		if(i >= this.size) {
			System.out.println("No spot found to insert.");
			//this.collisions -= i;
			return;
		}
		array[index] = hp;
		this.entries++;
		System.out.println("Added value " + array[index].getValue() + " to slot " + index+ ".");
	}//14


	/**
	 * Method to remove entries using the key and returns the value of the key that was just removed
	 * When removing an item, add a special value to removed slot with key =-1 so we skip it when searching for another item
	 * @param key is the key from the hashpair
	 * @return the value associated with the key
	 */
	public String remove(long key){
		if(key<1) 
			return null;
		int hf = hashFunction(key);
		int index = hf;
		int i = 0;
		while(i < this.size() && array[index] !=null) {
			if(array[index].getKey() == key){
				String cValue = array[index].getValue();
				//create a special value to take the place of the removed value, no hashpair can have the key of -1
				array[index] = new HashPair(-1," ");
				System.out.println("Removed value " + cValue + " from slot " + index+ ".");
				this.entries --;
				return cValue;
			}
			i++;
			index = (hf + i*i)%this.size;
		}
		System.out.println("No item found with key " + key + " to be removed.");
		return null;
	}//21


	/**
	 * gets the value from the key
	 * @param key is the key from the hashpair
	 * @return the value associated with the key
	 */
	public String get(long key){
		if(key<1) 
			return null;
		int hf = hashFunction(key);
		int index = hf;
		int i = 0;
		while(i < this.size() && array[index] !=null) {
			if(array[index].getKey() == key){
				return array[index].getValue();
			}
			i++;
			index = (hf + i*i)%this.size;
		}
		System.out.println("No item found with key " + key + ".");
		return null;
	}


	/**
	 * returns the value at a given index 
	 * @param index is the index that the value is wanted
	 * @return the string value at index
	 */
	public String getValue(int index) {
		if(index <0) 
			return null;
		else if(array[index] !=null) 
			return array[index].getValue();
		System.out.println("The index " + index + " is empty.");
		return null;
	}

	/**
	 * Printout method to show the full indices
	 */
	public void print() {
		if(this.entries == 0) 
			System.out.println("Table is currently empty.\n");

		else{
			System.out.print("Contents of " + this.getClass().getSimpleName() + " table:\n");
			for(int i = 0; i<this.size(); i ++) {
				if(array[i] != null ) {
					if(array[i].getKey() != -1)
						System.out.println("Index: " + i + " contains: " + array[i].getValue());
				}
			}System.out.print("\n");
		}
	}

	/**
	 * Display the hashtable
	 */
	public void display() {
		if(this.entries == 0) 
			System.out.println("Table is currently empty.\n");
		else {
			System.out.print("Contents of " + this.getClass().getSimpleName() + " table:\n");
			for(int i = 0; i<this.size(); i ++) {
				if(i%5 == 0)
					System.out.print("\n| ");
				if(array[i] != null) {
					System.out.print(array[i].getValue()+ " | ");
				}
				else {	System.out.print("  | ");
				}
			}System.out.println("\n");
		}
	}
}