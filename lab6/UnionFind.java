public class UnionFind {

    int[] set;
    int[] size;

    public UnionFind(int n) {
        set = new int[n];
        size = new int[n];
        for (int i = 0; i < n ; i++) {
            set[i] = -1;
            size[i] = 1;
        }
    }

    private void validate(int vertex) {
        boolean validated = vertex <= set.length - 1 && vertex >= 0;
        if (!validated) {
            throw new IllegalArgumentException("v1 is not a valid index");
        } else {
            return;
        }
    }

    public int sizeOf(int v1) {
        validate(v1);
        return size[v1];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        int parent = set[v1];
        if (parent != -1) {
            return parent;
        }
        return -1;
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        if (find(v1) == find(v2) && (set[v2] != -1 && set[v1] != -1)) {
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
        size[v1]++;
        size[v2]++;
        int size1 = sizeOf(v1);
        int size2 = sizeOf(v2);
        if (size2 > size1) {
            set[v2] = find(v1);
        } else {
            set[v1] = find(v2);
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        int rootVal = set[vertex];
        if (rootVal == -1) {
            return rootVal;
        } else {
            return find(set[rootVal]);
        }
    }

}
