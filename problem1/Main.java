import java.util.*;
import java.io.*;
import java.math.*;
class Main {

        public static int R, S, U, P, M;
        public static Map<String,Boolean> unavailable = new HashMap<String,Boolean>();
        public static ArrayList<Server> servers = new ArrayList<Server>();
        public static ArrayList<Rangee> rangees = new ArrayList<Rangee>();
        //public static ArrayList < Group > groups = new ArrayList < Group > ();
        
        public static GameState bestSoFar=null;
        public static GameState rootGameState;
        
        public static ArrayList<GameState> toProcess = new ArrayList<GameState>();
        
        
        
        public static class GameState implements Serializable{
			public Server cServ;
			public Rangee cRang;
			public int score=0;
			public ArrayList<Rangee> rangees = new ArrayList<Rangee>();
			public ArrayList<Server> servers = new ArrayList<Server>();        // remaining servers
			public ArrayList < Group > groups = new ArrayList < Group > ();
			
			GameState(){
				
			}
			
			public void process(){
				if(cServ == null || cRang.appendServerFF(cServ)){
					servers.remove(cServ);
					// Compute score
					score = getScore(groups);
					if(score > bestSoFar.score){
						bestSoFar = this;
					}
					// ----
					for(int i=0;i<rangees.size();i++){
						for(int j=0;j<servers.size();j++){
							
							GameState gs = gameStateDeepCopy(this);
							gs.cRang = gs.rangees.get(i);
							gs.cServ = gs.servers.get(j);
							toProcess.add(gs);
						}
					}
				}
			}
			
			public String toString(){
				return "GS: "+servers.size();
			}
			
			// avoir map des scores pour chaque groupe
		public Map < Group, Integer > getScore(ArrayList < Group > groups) {
			Map < Group, Integer > scores = new HashMap < Group, Integer > ();
			for (Group g: groups) {
				scores.put(g, g.getScore());
			}
			return scores;
		}
		public void setGroups() {
			int i, j;
			i = 0;
			j = servers.size() - 1;
			Group g;
			Server s1;
			Server s2;
			int numGroup = 0;
			g = groups.get(numGroup);
			while (i < j) {
				s1 = servers.get(i);
				s2 = servers.get(j);
				System.out.println(i + ":" + j);
				s1.group = g;
				s2.group = g;
				i++;
				j--;
				numGroup++;
				if (numGroup == groups.size()) {
					numGroup = 0;
				}
				g = groups.get(numGroup);
			}
			if (i == j) {
				s1 = servers.get(i);
				s1.group = g;
			}
		}
        }
        
        public static class Rangee implements Serializable{
                int indexOccupied = 0;
                int index;
                public Map<Integer, Server> rang = new HashMap<Integer, Server>();
                public boolean appendServerFF(Server s){
                        boolean placed = false;
                        // First fit
                        Server o;
                        while((o = rang.get(indexOccupied)) != null){
							indexOccupied+=o.z;
							while(unavailable.get(""+index+"-"+indexOccupied) != null){
								indexOccupied++;
							}

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
        
	    public static class Server implements Serializable{
			public int z, c;
			public Rangee r; // rangée où il est placé [null si non placé]
			public int s; // [slot] emplacement dans la rangée
			public Group group; //group auquel il appartient
			public double ratio;
			public int id;
			Server(int k, int l, int id) {
				r = null;
				s = -1;
				z = k;
				c = l;
				this.id = id;
				ratio = (double) c / (double) z;
			}
			public void put(Rangee r, int s) {
				this.r = r;
				this.s = s;
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

    public static class Group implements Serializable{
		public ArrayList < Server > servers = new ArrayList < Server > ();
		public int id;
		Group(int i) {
			id = i;
		}
		private int capacity_score = 0;
		private Rangee best = null;
		public int getScore() {
			Map < Rangee, Integer > scores = new HashMap < Rangee, Integer > ();
			for (Server s: servers) {
				if (s.r != null) {
					// serveur placé
					Integer current_score = scores.get(s.r);
					if (current_score == null) {
						scores.put(s.r, 0);
						current_score = 0;
					}
					scores.put(s.r, current_score + s.z);
				}
			}
			//System.out.println("Score du groupe : ");
			//scores.forEach((k,v) -> System.out.println(k + " => " + v));
			// calculer meilleure rangée
			scores.forEach((r, i) -> {
				if (this.best == null) {
					this.best = r;
				} else {
					if (i > scores.get(this.best)) {
						this.best = r;
					}
				}
			});
			// calculer score sans elle => score_max - score_meilleure_rangee
			scores.forEach((r, i) -> {
				if (r == this.best) {
					// nothing to do
				} else {
					this.capacity_score += i;
				}
			});
			return this.capacity_score;
		}
		public int getCapacity() {
			int capacity = 0;
			for (Server s: servers) {
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
        
        rootGameState = new GameState();
        
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
                
                Server k = new Server(z,c,i);
                servers.add(k);
                rootGameState.servers.add(k);
                
        }

        Collections.sort(rootGameState.servers, new CustomComparator());
		rootGameState.setGroups();
		for (Server server: rootGameState.servers) {
			System.out.println(server.ratio + " to group : " + server.group.id);
		}
        //----------------- Logic
        
        //------------------
        //rootGameState.cServ = rootGameState.servers.get(0);
        rootGameState.cRang = rootGameState.rangees.get(0);
        //rootGameState.process();
        
        toProcess.add(rootGameState);
        
        while(toProcess.size()>0){
			GameState tp = toProcess.get(0);
			toProcess.remove(tp);
			tp.process();
			
			//System.out.println(toProcess.size());
		}
        
        // --------
        
        System.exit(0);

    }
    
    public static GameState gameStateDeepCopy(GameState gs){
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = null;
		try {
		  out = new ObjectOutputStream(bos);   
		  out.writeObject(gs);
		  
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
		  try {
			if (out != null) {
			  out.close();
			}
		  } catch (IOException ex) {
			// ignore close exception
		  }
		  try {
			bos.close();
		  } catch (IOException ex) {
			// ignore close exception
		  }
		}
		
		byte[] yourBytes = bos.toByteArray();
		
		ByteArrayInputStream bis = new ByteArrayInputStream(yourBytes);
		ObjectInput ins = null;
		GameState o=null;
		try {
		  ins = new ObjectInputStream(bis);
		  o = (GameState)ins.readObject(); 
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
		  try {
			bis.close();
		  } catch (IOException ex) {
			// ignore close exception
		  }
		  try {
			if (ins != null) {
			  ins.close();
			}
		  } catch (IOException ex) {
			// ignore close exception
		  }
		  catch(Exception e){
			e.printStackTrace();
		}
		}
		
		/*
		for(Rangee r : o.rangees){
			Iterator it = r.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry)it.next();
				for(Server ss : o.servers){
					if(ss.id == pair.getValue().id){
						r.put(pair.getKey(), ss);
						break;
					}
				}
				it.remove(); // avoids a ConcurrentModificationException
			}
		}
		*/
		
		//o.servers.get(0).group.hashCode() == 
		
		return o;
	} 
}
