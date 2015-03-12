import java.util.*;
import java.io.*;
import java.math.*;

class Main {
	public static int R, S, U, P, M;

	public static Map<String,Boolean> unavailable = new HashMap<String,Boolean>();
	public static ArrayList<Server> servers = new ArrayList<Server>();
	

	public static class Server{
		public int z,c;
		Server(int k, int l){
			z = k;
			c = l;
		}
	}

    public static void main(String args[]) {
	//----------------- Inputs
	Scanner in = new Scanner(System.in);
	R = in.nextInt();
	S = in.nextInt();
	U = in.nextInt();
	P = in.nextInt();
	M = in.nextInt();
	in.nextLine();
	
	for(int i=0;i<U; i++){
		String[] s = in.nextLine().split(" ");
		int r = Integer.parseInt(s[0]);
		int ss = Integer.parseInt(s[1]);
		
		unavailable.put(""+r+"-"+ss,true);
	}
	for(int i=0;i<M; i++){
		String[] s = in.nextLine().split(" ");
		
		int z = Integer.parseInt(s[0]);
		int c = Integer.parseInt(s[1]);
		
		servers.add(new Server(z,c));
	}
	//----------------- Logic
	
	//------------------
    }
}
