package textfiles;

public class iNode {
	public iNode next;
	public String userName;
	public String passWord;
	public int key;



	public iNode(){
		userName = null;
		passWord = null;
		next = null;
		key = 0;
	}
	
	 iNode(iNode H,  String n, String pass){
		next =H;
		userName = n;
		passWord = pass;
	}
	 
	 iNode(iNode H, int k, String n, String pass){
		 passWord = pass;
		 next = H;
		 userName = n;
		 //userName = n;
		 
		 key = k;
	 }
}




