import java.util.*;
import java.io.*;
import java.math.*;
class Main {

        public static int R, S, U, P, M;
        public static Map<String,Boolean> unavailable = new HashMap<String,Boolean>();
        
        public static GameState rootGameState = new GameState();
        
        public static class GameState{
                public ArrayList<Rangee> rangees = new ArrayList<Rangee>();
                public static ArrayList<Server> servers = new ArrayList<Server>();        // remaining servers
        }
        
        public static class Rangee{
                int indexOccupied = 0;
                int index;
                public Map<Integer, Server> rang = new HashMap<Integer, Server>();
                public boolean appendServerFF(Server s){
                        boolean placed = false;
                        // First fit
                        Server o;
                        while((o = rang.get(indexOccupied)) != null){
                                indexOccupied+=o.z;
                        }
                        while(unavailable.get(""+index+"-"+indexOccupied) != null){
                            indexOccupied++;
                        }
                        if(indexOccupied + s.z < S){
                            rang.put(indexOccupied, s);
                            placed = true;
                        }
                        
                        return placed;
                }
                
                Rangee(int idx){
                    index = idx;
                }
        }
	    public static class Server{
			public int z,c;
			public double ratio;
			Server(int k, int l){
				z = k;
				c = l;
				ratio = (double)c / z;
			}
        }


	public static class CustomComparator implements Comparator<Server> {
	    @Override
	    public int compare(Server o1, Server o2) {
	        if (o1.ratio < o2.ratio){
	        	return -1;
	        } else if(o1.ratio > o2.ratio) {
	        	return 1;
	        }
	        return 0;
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

	//------------------

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

        Collections.sort(rootGameState.servers, new CustomComparator());
        //----------------- Logic
        
        //------------------

    }
}
