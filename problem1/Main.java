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
                Map<Group, Integer> scores = new HashMap<Group, Integer>();

                for (Group g : groups) {
                    scores.put(g, g.getScore());
                }

                return scores;
            }

            public void setGroups(ArrayList<Group> groups){
            	int i,j;
            	i = 0;
            	j = servers.size()-1;
            	Group g;
            	Server s1 = servers.get(i);
            	Server s2 = servers.get(j);
            	int numGroup = 0;
            	g = groups.get(numGroup);
            	while(i < j){
            		s1.group = g;
            		s2.group = g;
            		i++; 
            		j--;
            		numGroup++;
            		if(numGroup == groups.size()){
              			numGroup = 0;
            		}
            		g = groups.get(numGroup);
            	}
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
                        s.put(this, indexOccupied);
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
        public Group group; //group auquel il appartient
        public double ratio;
        Server(int k, int l){
            r = null;
            s = -1;
            z = k;
            c = l;
            ratio = (double) c/ (double)z;
        }

        public void put(Rangee r, int s) {
            this.r = r;
            this.s = s;
        }
    }


	public static class CustomComparator implements Comparator<Server> {
	    public int compare(Server o1, Server o2) {
	        if (o1.ratio > o2.ratio){
	        	return -1;
	        } else if(o1.ratio < o2.ratio) {
	        	return 1;
	        }
	        return 0;
    	}
	}

    public static class Group{
        public ArrayList<Server> servers = new ArrayList<Server>();
        public int id;
        Group(int i) {
        	id = i;
        }

        public int getScore() {
            Map<Rangee, Integer> scores = new HashMap<Rangee, Integer>();
            for (Server s : servers) {
                if (s.r != null) {
                    // serveur placé

                    Integer current_score = scores.get(s.r);
                    if (current_score == null ) {
                        scores.put(s.r, 0);
                        current_score = 0;
                    }
                    scores.put(s.r, current_score + s.z);
                }
            }

            // calculer meilleur score si on enlève la meilleure rangée

            // calculer meilleur rangée

            // calculer score sans elle => score_max - score_meilleure_rangee
            return 0;
        }

        public int getCapacity() {
            int capacity = 0;
            for (Server s : servers) {
                capacity += s.c;
            }
            return capacity;
        }
    }

    public static void main(String args[]) throws FileNotFoundException {

	//------------------

        //----------------- Inputs
        File file = new File("sample.txt");
        Scanner in = new Scanner(file);
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


        ArrayList<Group> list = new ArrayList<Group>();

        for(int i=0;i<P; i++){
        	Group g = new Group(i);
        	list.add(g);
        }

        Collections.sort(rootGameState.servers, new CustomComparator());

        rootGameState.setGroups(list);

        for(Server server : rootGameState.servers){
        	System.out.println(server.ratio);
        }

        //----------------- Logic
        
        //------------------
        
        System.exit(0);

    }
}
