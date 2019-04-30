package application;

import java.util.HashSet;
import java.util.Set;

public class UnionFind {

	private int nodes;
	public int root[];
	public HashSet<Integer> s=new HashSet<>();
	
	//private int size[];
//	public int[] id;
	
	
	
	public UnionFind(int n) {
		this.nodes = n;
		this.root = new int[n];
		//this.size = new int[n];
		
		
		
		//Path Compression
		for (int i=0; i<nodes; i++) {
	        root[i] = i;
	      //  size[i] = 1; 
	    }
	}
	
	
	/*
	 * Specific method in UnionFind Class Changes
	 */
	public void change(int a) {
		root[a] = -1;
		
	}
	
	
	
	public int numberOfNodes() {
		return nodes;
	}
	
	public int parent(int i) {
		if(root[i]==-1) return -1;
	    while (i != root[i]) {
	       root[i] = root[root[i]]; 
	        i = root[i];
	    }
	    return i;
	} 
	
	public void union(int p, int q) {
		int i = parent(p);
		int j = parent(q);
		
		root[j]=root[i];
		
	}
	
	
	
	
	public boolean find(int p, int q) {
		return parent(p) == parent(q);
	}
	
	
	
	public int find(int p)
	{
		return root[p];
	}
	
	
//		if (size[i] < size[j]) {
//	        root[i] = j;
//	        size[j] += size[i];
//	    } else {
//	        root[j] = i;
//	        size[i] += size[j];
//	    }

	

	public int getNumberOfTrees() {
	    Set<Integer> s = new HashSet<Integer>();
	    for (int i=0; i<nodes; i++) {
//	    	System.out.print(root[i]+"\t");
	    	if (root[i] != -1) {
	    		s.add(root[i]);	
			}
	    	
	    }
	    System.out.println("");
	    for (int i=0; i<nodes; i++) {
//	    	System.out.print(size[i]+"\t");
	    }

	    System.out.println("-----" + s+"\t");
	    return s.size();
	}
	
	

}
	
	
	
	
	
	
	
	
	
	
	
	
	
//	private int[] id;
//	public UnionFind(int N)
//	{
//	id = new int[N];
//
//	for (int i = 0; i < N; i++)
//	id[i] = i;
//
//	}
//	public boolean find(int p, int q)
//	{
//	return id[p] == id[q];
//	}
//	
//	
//	public void unite(int p, int q)
//	{
//	int pid = id[p];
//	for (int i = 0; i < id.length; i++)
//	if (id[i] == pid) id[i] = id[q];
//
//	}
	