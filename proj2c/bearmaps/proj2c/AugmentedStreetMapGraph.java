package bearmaps.proj2c;

import bearmaps.hw4.streetmap.Node;
import bearmaps.hw4.streetmap.StreetMapGraph;
import bearmaps.proj2ab.Point;
import bearmaps.proj2ab.WeirdPointSet;
import bearmaps.proj2c.utils.TrieSet;

import java.util.*;

/**
 * An augmented graph that is more powerful that a standard StreetMapGraph.
 * Specifically, it supports the following additional operations:
 *
 *
 * @author Alan Yao, Josh Hug, ________
 */
public class AugmentedStreetMapGraph extends StreetMapGraph {
    private List<Node> nodes;
    private List<Point> points;
    private Map<Point, Node> pointToNode;
    private TrieSet trie;
    private Map<String, LinkedList<String>> cleanedToReal;
    public AugmentedStreetMapGraph(String dbPath) {
        super(dbPath);
        nodes = this.getNodes();
        points = new ArrayList<>();
        pointToNode= new HashMap<>();
        trie = new TrieSet();
        cleanedToReal = new HashMap<>();

        for(Node n : nodes){
            double calculatedY = n.lat();
            double calculatedX = n.lon();
            Point convertedPoint = new Point(calculatedX, calculatedY);
            if(n.name() != null) {
                String cleaned = cleanString(n.name());
                trie.add(cleaned);
                if (cleanedToReal.containsKey(cleaned)) {
                    cleanedToReal.get(cleaned).add(n.name());
                } else {
                    LinkedList<String> newBucket = new LinkedList<>();
                    newBucket.add(n.name());
                    cleanedToReal.put(cleaned, newBucket);
                }
            }
            if(this.neighbors(n.id()).size()==0){
                continue;
            }
            pointToNode.put(convertedPoint, n);
            points.add(convertedPoint);
        }
    }
    /**
     * For Project Part III (gold points)
     * In linear time, collect all the names of OSM locations that prefix-match the query string.
     * @param prefix Prefix string to be searched for. Could be any case, with our without
     *               punctuation.
     * @return A <code>List</code> of the full names of locations whose cleaned name matches the
     * cleaned <code>prefix</code>.
     */
    public List<String> getLocationsByPrefix(String prefix) {
        String cleaned = cleanString(prefix);
        List<String> results = new LinkedList<>();
        if(cleaned == ""){
            for(String p : cleanedToReal.get(cleaned)){
                results.add(p);
            }
        }
        if(cleanedToReal.containsKey(cleaned)){
            for(String s : trie.keysWithPrefix(prefix)){
                for(String p: cleanedToReal.get(s)){
                    results.add(p);
                }
            }
        }
        return results;
    }

    /**
     * For Project Part III (gold points)
     * Collect all locations that match a cleaned <code>locationName</code>, and return
     * information about each node that matches.
     * @param locationName A full name of a location searched for.
     * @return A list of locations whose cleaned name matches the
     * cleaned <code>locationName</code>, and each location is a map of parameters for the Json
     * response as specified: <br>
     * "lat" -> Number, The latitude of the node. <br>
     * "lon" -> Number, The longitude of the node. <br>
     * "name" -> String, The actual name of the node. <br>
     * "id" -> Number, The id of the node. <br>
     */
    public List<Map<String, Object>> getLocations(String locationName) {
        return null;
    }

    /**
     * Useful for Part III. Do not modify.
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    private static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }


    /**
     * For Project Part II
     * Returns the vertex closest to the given longitude and latitude.
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    public long closest(double lon, double lat) {
        WeirdPointSet kd = new WeirdPointSet(this.points);
        Point best = kd.nearest(lon,lat);
        return pointToNode.get(best).id();
    }
}
