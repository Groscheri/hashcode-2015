import java.util.*;
import java.io.*;
import java.math.*;

class Main {
	public static int uneBonneGrosseVariableGlobale = 3;
	public static class Point{
		public int x,y;
		Point(int n, int v){
			this.x = n;
			this.y = v;
		}

		public String toString(){
			return x+"-"+y;
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

    public static void main(String args[]) {
    	uneBonneGrosseVariableGlobale ++;
        System.out.println(new Point(42,uneBonneGrosseVariableGlobale));

        // créer la map
        HashMap<Integer, Point> map = new HashMap<Integer, Point>();

        // ajouter des couples clé/valeur (UNIQUE ATTENTION SINON ERREUR)
        map.put(1, new Point(42,32));
        map.put(6, new Point(42,3));
        map.put(2925, new Point(4,32));
        map.put(920, new Point(402,320));
        map.put(-12, new Point(422,322));


        // taille de la map
        System.out.println("Taille de la map : " + map.size());

        // obtenir un objet de la map
        System.out.println("Élément 1 de la map : " + map.get(1));

        // chercher si un objet existe dans la map
        Point p = new Point(42,3);
        boolean containingValue = map.containsValue(p);
        System.out.println("La map" + ((containingValue)  ? "ne" : "")  + " contient" + ((containingValue) ? " pas" : "") + " le point : " + p);

        // chercher si une clé existe dans la map
        int key = 1;
        boolean containingKey = map.containsKey(key);
        System.out.println("La map " + ((containingKey)  ? "ne" : "")  + " contient" + ((containingKey) ? " pas" : "") + " la clé : " + key);

        // parcourir la map sans fonction
        map.forEach((k,v) -> System.out.println("Key : " + k + " => Value : " + v));

        // parcourir la map avec une fonction
        System.out.println("-----");
        map.forEach((k,v) -> displayMap(k,v));

        // effacer la map
        map.clear();
        System.out.println("Taille de la map après clear : " + map.size());


        Server a = new Server(4,5);
        System.out.println(a.ratio);
    }

    public static void displayMap(Integer key, Point p) {
        System.out.println(key + " => " + p);
    }
}