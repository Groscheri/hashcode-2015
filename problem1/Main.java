import java.util.*;
import java.io.*;
import java.math.*;
class Main {
    public static int R, S, U, P, M;
    public static Map<String,Boolean> unavailable = new HashMap<String,Boolean>();
    public static ArrayList<Server> servers = new ArrayList<Server>();
    
    public static GameState rootGameState = new GameState();
    
    public static class GameState{
            public ArrayList<Rangee> rangees = new ArrayList<Rangee>();
            public static ArrayList<Server> servers = new ArrayList<Server>();        // remaining servers

            // avoir map des scores pour chaque groupe
            public Map<Group, Integer> getScore(ArrayList<Group> groups) {
                Map<Group, Integer> scores = new Map<Group, Integer>();

                for (Group g : groups) {
                    scores.put(g, g.getScore());
                }

                return scores;
            }
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
        public Rangee r; // rangée où il est placé [null si non placé]
        public int s; // [slot] emplacement dans la rangée 
        Server(int k, int l){
            r = null;
            s = -1;
            z = k;
            c = l;
        }

        public void put(Rangee r, int s) {
            this.r = r;
            this.s = s;
        }
    }

    public static class Group{
        public ArrayList<Server> servers = new ArrayList<Server>();
        Group() {

        }

        public int getScore() {
            Map<Rangee, Integer> scores = new Map<Rangee, Integer>();
            for (Server s : servers) {
                if (s.r != null) {
                    // serveur placé

                    int current_score = scores.get(s.r);
                    if (current_score == null ) {
                        scores.put(s.r, 0);
                        current_score = 0;
                    }
                    scores.put(current_score + s.z);
                }
            }
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
        
        for(int i=0;i<R;i++){
			rootGameState.rangees.add(new Rangee(i));
		}
        
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
                
                Server k = new Server(z,c);
                servers.add(k);
                rootGameState.servers.add(k);
        }
        //----------------- Logic
        
        //------------------
        
        System.exit(0);
    }
}
