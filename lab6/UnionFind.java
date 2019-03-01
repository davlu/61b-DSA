public class UnionFind {

    // TODO - Add instance variables?
    int[] set;
    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        set = new int[n];
        for(int i =0 ; i<n-1; i++){
            set[n] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        boolean validated = vertex <= set.length-1 && vertex >= 0;
        if(!validated){
            throw new IllegalArgumentException("v1 is not a valid index");
        }
        else{
            return;
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        validate(v1);
        int size = 0;
        for(int i = 0; i < set.length; i++){
            if(find(i) == find(v1)){
                size ++;
            }
        }
        return size;
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        int parent = set[v1];
        if(parent != -1){
            return parent;
        }
        return -1;
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        if(find(v1)==find(v2)){
            return true;
        }
        return false;
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
        int size1 = sizeOf(v1);
        int size2 = sizeOf(v2);
        if(size1<size2){
            int parent = parent(v1);
            set[parent] = find(v2);
        }
        else{
            int parent = parent(v2);
            set[parent] = find(v1);
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        int rootVal = set[vertex];
        if(rootVal == -1){
            return rootVal;
        }
        else{
            return find(set[rootVal]);
        }
    }

}
