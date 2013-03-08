

import java.util.Arrays;

public class LCS {
	char[] x,y;
	int[][] r;
	public LCS(char x[],char y[]){
		this.x=x;
		this.y=y;
		r = new int[x.length][y.length];
		for (int i = 0; i < x.length; i++) {
			Arrays.fill(r[i], -1);
		}
	}
	public int solve(int i,int j){
//		System.out.println(i+" "+j);
		int t=0;
		if(i==-1 || j==-1){
			return 0;
		}		
		if(r[i][j]!=-1){
			return r[i][j];
		}
		if(x[i]==y[j]){
			t = solve(i-1,j-1)+1;
		} else {
			t = Math.max(solve(i-1,j),solve(i,j-1));
		}
		r[i][j] = t;
		return t;
	}
	public static void main(String[] args){
		String x="abcfe";
		String y="adccb";
		System.out.println(new LCS(x.toCharArray(),y.toCharArray()).solve(x.length()-1, y.length()-1));
	}
}
