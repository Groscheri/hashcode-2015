import java.util.*;
import java.io.*;
import java.math.*;

class Main {
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
    public static void main(String args[]) {
        System.out.println(new Point(42,1337));
    }
}