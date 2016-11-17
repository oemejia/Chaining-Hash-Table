/*[CS2302 Spring 2015]
 * Lab04
 * Omar Mejia
 * Professor: Dr. Olac Fuentes
 * TA: Jesus Medrano 
 * Last modified March 25, 2015
 * In this lab we created hash table with chaining to store usernames and passwords from
 * a given txt file.  The program is to implement several methods to find the longest path, 
 * empty slots, the load factor and duplicate accounts.  
 */

package textfiles;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Accounts {
	static Scanner input = new Scanner(System.in);
	public static Scanner x;
	static int items=0;
	static String control;
	static String userInput;

	public static void main(String[] args) {
		int tableSize = 11;
		iNode[] H = new iNode[tableSize];
		String name;//This variable stores the user's input name when running the simulation below.
		String passwords;//This variable stores the user's password input when running the simulation.
		System.out.print("Name of input file: ");
		String pathFile = input.next();
		System.out.println("Hash table size: " + tableSize);

		openfile(pathFile);
		readfile(pathFile, H);
		System.out.println(unique(H) + " unique users found");
		System.out.print("Longest list in table has ");
		System.out.print(longestLength(H));
		System.out.print(" elements ");
		System.out.println();
		System.out.println("Load factor is " + loadFactor(H));
		emptySlots(H);
		System.out.println();
		System.out.println("Begin simulation");
		while(true){
			System.out.print("Login: ");
			name = input.next();
			if(name.equals("end")){
				System.out.println("Bye!");
				break;
			}
			System.out.print("Password: ");
			passwords = input.next();
			if(isMember(H, name, passwords))
				System.out.println("Authentication successful");
			if(!isMember(H,name, passwords))
				System.out.println("Authentication failure");
		}
	}

	public static void openfile(String n){
		try{
			x= new Scanner(new File(n));
		}
		catch(Exception e){
			System.out.println("could not find file");
		}
	}
	public static void readfile(String n, iNode[]H){
		int cnt=0;
		System.out.println("Reading file: " + n );
		while(x.hasNext()){
			String names=x.next();
			//System.out.println(names);
			String password=x.next();
			insert(H,names, password);
			cnt++;
			/*System.out.printf("%s %s" ,w,y);*/ /**Debug Code**/
			/*System.out.println();*/ /**Debug Code**/
		}
		/*System.out.print("Number of lines " + cnt);*/ /**Debug Code**/
		/*System.out.println();*/ /**Debug Code**/
	}

	public static void insert(iNode[]H, String name, String password){
		boolean check = false;
		if(!isMember(H, name, password)){
			//boolean check;
			int k = integerValue(name);
			int h = integerValue(name)%H.length;

			for(iNode t = H[h]; t != null; t=t.next){
				int l = integerValue(t.userName);
				if(l == k){
					check = true;
				}
			}
			if(!check){
				H[h] = new iNode(H[h], h, name, password);
			}
		}
		else{
			System.out.println("Duplicate username found: " + name);
		}
	}

	public static int unique(iNode []H){
		int count = 0;
		for(int i = 0; i < H.length; i++)
			for(iNode t = H[i]; t != null; t=t.next)
				count++;
		return count;
	}

	public static boolean isMember(iNode[] H, String n, String p){ 
		int name = integerValue(n);
		int h=name%H.length; 							             
		iNode table=H[h];							  
		while(table!=null){
			if(table.userName.equals(n) && table.passWord.equals(p))
				return true;
			table=table.next;
		}
		return false;
	}

	public static int integerValue(String S){ 
		int temp=0;
		int i = 0;
		while(i<S.length()){
			temp+=(int)S.charAt(i);
			i++;
		}
		return temp;
	}

	public static iNode retrieve(iNode[]H, int k){
		int h = k % H.length;
		iNode t;
		for( t=H[h];t!=null&& t.key!=k;t=t.next);;
		return t;
	}

	public static int longestLength(iNode[]H){//returns 
		int countTwo=0;
		for(int i=0;i<H.length;i++){
			int count =0;
			for(iNode table =H[i]; table!=null;table=table.next)
				count++;
			/*System.out.println("This is count1 "+ count);*/ /**debug code**/
			if(count>countTwo)
				countTwo=count;
		}
		/*print(H);*/ /**debug code**/
		return countTwo;
	}
	
	public static void print(iNode[] H){
		System.out.println();
		for(int i=0;i<H.length;i++){
			for(iNode t= H[i];t!=null;t=t.next){
				System.out.print(t.userName+" ");
			}
			System.out.print("\n");
		}
	}

	public static void emptySlots(iNode[]H){ //prints number of empty slots
		int count =0;
		int i = 0;
		while(i < H.length){
			if(H[i]==null)
				count++;//increments counter when finding a "null" slot.
			i++;
		}
		System.out.println("There are "+ count + " empty lists in the table");
		/*print(H);*/ /**debug to check empty slot**/
	}

	public static double loadFactor(iNode[] H){
		double count = 0;
		for(int i = 0; i < H.length; i++)//Traverses through hash table
			for(iNode t = H[i]; t != null; t = t.next)//Traverses through nodes at H[i]
				count ++;
		return (double)(count/H.length);
	}
	public void closefile(){
		x.close();
	}
}

