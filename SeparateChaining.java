/**
 * Open Addressing, All keys that map to the same index are stored as a linked list
 * @author Nolan
 */
public class SeparateChaining extends HashTable{
	private Node[] array;

	/**
	 * @param size is the size of the array
	 */
	public SeparateChaining(int size){
		super(size);
		this.array = new Node[size];
	}

	/**
	 * If there is no entry at index then make a linkedlist for the index 
	 * else add the entry to the start
	 * @param hp is the hashpair being added
	 */
	public void add(HashPair hp){
		int index = hashFunction(hp.hashCode());	
		Node n = new Node(hp,null);

		if(array[index] == null){
			array[index] = n;
			this.entries ++;
		}
		else{
			System.out.println("Collision at " + index);
			this.collisions += 1;
			n.setNext(array[index]);
			array[index] = n;
			this.entries ++;
		}
		System.out.println("Added value " + array[index].getHPValue() + " to index " + index+ ".");
	}



	/**
	 * Remove a node with the key
	 * @param key the key to the item being deleted
	 * @return the value from the deleted key
	 */
	public String remove(int key){
		if(key<1) 
			return null;
		int b = 0;
		int index = hashFunction(key);
		if(array[index] == null){
			System.out.println("No Item to be removed");
			return null;
		}

		else{
			Node current = array[index];
			/*If the head is to be removed */
			if(current.getHPKey() == key) {
				System.out.println("Removed from position " + b + " in index " + index + ".");
				array[index] = current.getNext();
				entries --;
				return current.getHPValue();
			}
			Node prev;
			prev = current;
			current = current.next;
			while(current != null){
				b++;
				if(current.getHPKey() == key) {
					System.out.println("Removed from position " + b + " in index " + index+ ".");
					prev.setNext(current.getNext());
					this.entries --;
					return current.getHPValue();
				}
				prev = current;
				current = current.next;
			}
			System.out.println("No Item with the key " + key + " was found to be removed.");
			return null;
		}
	}


	/**
	 * Get the value with the associated key
	 * @param key to the value wanted
	 * @return the value of the hashpair
	 */
	public String get(long key){
		if(key<1) 
			return null;
		int index = hashFunction(key);
		if(this.array[index] == null){
			System.out.println("No item at location.");
			return null;
		}
		else{
			Node current = array[index];
			while(current != null){
				if(current.getHPKey() == key) {
					//System.out.println("Found at position " + b + " in index " + index);
					return current.getHPValue();
				}
				current = current.next;
			}					
			System.out.println("No Item with the key " + key + " was found.");
			return null;
		}
	}


	/**
	 * Method to print out the contents of an index
	 */
	public void print(){
		if(this.entries == 0) 
			System.out.println("Table is currently empty.\n");
		else {
			System.out.print("Contents of " + this.getClass().getSimpleName() + " table:\n");
			for(int i = 0; i <this.size; i++) {
				if(array[i] == null) {
					System.out.println("Index: " + i + " contains: ");
				}
				else {
					Node current = array[i];
					System.out.print("Index: " + i + " contains:| ");
					while(current != null){
						System.out.print(current.getHashPair().getValue() + " | ");
						current = current.next;
					}
					System.out.print("\n");
				}
			}System.out.print("\n");
		}
	}

	public void display(){
		if(this.entries == 0) 
			System.out.println("Table is currently empty.\n");
		else {
			System.out.print("Contents of " + this.getClass().getSimpleName() + " table:");
			for(int i = 0; i<this.size(); i ++) {
				if(i%5 == 0) 
					System.out.print("\n| ");
				if(array[i] != null) {
					Node current = array[i];
					System.out.print(current.getHPValue());
					if(current.next == null)
						System.out.print(" | ");
					else {
						while(current.next != null){
							System.out.print(" -> ");
							current = current.next;
							System.out.print(current.getHPValue());
						}System.out.print(" | ");
					}
				}
				else {	System.out.print(" | ");
				}
			}System.out.println("\n");
		}
	}


	//Nodes inner class
	/**
	 * @author Nolan
	 *inner class for the nodes of the linked list
	 */
	public class Node{
		private HashPair hp;
		private Node next;

		/**
		 * default constructor 
		 */
		Node(){
			this.hp = null;
			this.next = null;
		}

		/**
		 * parameterized constructor 
		 * @param h is the HashPair for the node
		 * @param next is the next node
		 */
		Node(HashPair h, Node next){
			this.hp = h;
			this.next = next;
		}

		/**
		 * setter
		 * @param n set the next node to n
		 */
		public void setNext(Node n) {
			this.next = n;
		}

		/**
		 * getter for the next node
		 * @return the next node
		 */
		public Node getNext() {
			return this.next;
		}

		/**
		 * getter
		 * @return the HashPair at this node
		 */
		public HashPair getHashPair() {
			return this.hp;
		}

		/**
		 * 
		 * @return the key from the hashPair
		 */
		public long getHPKey() {
			return this.hp.getKey();
		}
		public String getHPValue() {
			return this.hp.getValue();
		}
	} //end of inner class

}