import java.util.*;
import java.io.*;
import java.math.*;

class Main {
	public static int R, S, U, P, M;

	public static Map<String,Boolean> unavailable = new HashMap<String,Boolean>();
	
	public static GameState rootGameState = new GameState();
	
	public static class GameState{
		public ArrayList<Rangee> rangees =  ArrayList<Rangee>();
		public static ArrayList<Server> servers = new ArrayList<Server>();	// remaining servers
	}
	
	public static class Rangee{
		public ArrayList<Server> list = new ArrayList<Server>();
	}

	public static class Server{
		public int z,c;
		Server(int k, int l){
			z = k;
			c = l;
		}
	}

    public static class Group{
        public ArrayList<Server> servers = new ArrayList<Server>();
        Group() {

        }

        public int getCapacity() {
            int capacity = 0;
            for (Server s : servers) {
                capacity += s.c;
            }
            return capacity;
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
		
		rootGameState.servers.add(new Server(z,c));
	}
	//----------------- Logic
	
	//------------------
    }
}
