/**
 * Open Addressing,
 * Linear Probing collision resolution 
 * If a collision happens then try the next index 
 * @author Nolan
 */

public class LinearProbing extends HashTable{
	private HashPair[] array;

	/**
	 * @param size initialize the array 
	 */
	public LinearProbing(int size){
		super(size);
		this.array = new HashPair[size];
	}


	/**
	 * Method to add in entries, if the table is full throw an exception. Will not be added if the key exists in the table already
	 * If the index is already full try the next index
	 * @param hp the hashpair to be added
	 */
	public void add(HashPair hp){
		try{
			if(this.entries >= this.size){
				throw new Exception("Error: The hashtable is full, you cannot add anymore items.");}
			else if(hp.getKey() <=0){
				throw new Exception("Error: Key could not be added to table.");}
		}catch(Exception e){
			System.out.println(e.getMessage());
			return;}

		//get hashcode and compress it to it the table
		int index = hashFunction(hp.getKey());
		while(array[index] != null && array[index].getKey() != -1){
			//Check to see if the key was already inserted
			if(hp.getKey() == array[index].getKey())
				return;
			System.out.println("Collision at index " + index + ".");
			this.collisions ++;
			index ++;
			if(index >= this.size()){
				index = 0;
			}
		}	
		array[index] = hp;
		System.out.println("Added value " + array[index].getValue() + " to slot " + index+ ".");
		this.entries++;
	}


	/**
	 * Method to remove entries using the key and returns the value of the key that was just removed
	 * When removing an item, add a special value to removed slot with key =-1 so we skip it when searching for another item
	 * If the key is not found return null
	 * @param key is the key from the hashpair
	 * @return the value associated with the key
	 */
	public String remove(long key){
		if(key<1) 
			return null;
		int index = hashFunction(key);
		int check = 0;
		while(check < this.size() && array[index] !=null) {
			if(array[index].getKey() == key){
				String cValue = array[index].getValue();
				//create a special value to take the place of the removed value, no other hashpair can have the key of -1
				array[index] = new HashPair(-1," ");
				System.out.println("Removed value " + cValue + " from slot " + index+ ".");
				this.entries --;
				return cValue;
			}
			index ++;
			check ++;
			if(index >= this.size())
				index = 0;
		}
		System.out.println("No item found with key " + key + " to be removed.");
		return null;
	}//22


	/**
	 * Gets the value from the key
	 * If the key is not found return null
	 * @param key is the key from the hashpair
	 * @return the value associated with the key
	 */
	public String get(long key){
		if(key<1) 
			return null;
		int index = hashFunction(key);
		int check = 0;
		while(check < this.size() && array[index] !=null) {
			if(array[index].getKey() == key){
				return array[index].getValue();
			}
			index ++;
			if(index >= this.size())
				index = 0;
			check ++;
		}
		System.out.println("No item found with key " + key + ".");
		return null;
	}


	/**
	 * returns the value at a given index
	 * @param index used
	 * @return the string value at index
	 */
	public String getValue(int index) {
		if(index<0) 
			return null;
		else if(array[index] !=null && array[index].getKey() != -1 ) 
			return array[index].getValue();
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
		System.out.print("Contents of " + this.getClass().getSimpleName() + " table:\n");
		for(int i = 0; i<this.size(); i ++) {
			if(i%5 == 0)
				System.out.print("\n| ");
			if(array[i] != null) {
				System.out.print(array[i].getValue()+ " | ");
			}
			else {	System.out.print("  | ");
			}
		}System.out.print("\n");
	}

}