/*
Implement a SnapshottableMap class.

https://leetcode.com/discuss/interview-question/358048/Google-or-Snapshottable-Map
*/

import java.util.*;

public class SnapshottableMap {
	private int currentSnapshotId;
	private HashMap<Integer, HashMap<String, String>> snapshotMap;
  
    public static void main(String[] args) {
    	SnapshottableMap map = new SnapshottableMap();
		Snapshot s0 = map.createSnapshot();

		System.out.println("putting name -> John and country - > UK in map.");
		map.put("name", "John");
		map.put("country", "UK");
		System.out.println("creating snapshot.");
		Snapshot s1 = map.createSnapshot();
		 
		System.out.println("from this snapshot, getting name, should be john, is " + 
			s1.get("name"));
		System.out.println("from this snapshot, getting coutry, should be UK, is " +
			s1.get("country"));
		 
		System.out.println("putting name -> Marta in map.");
		map.put("name", "Marta");
		System.out.println("creating snapshot.");
		Snapshot s2 = map.createSnapshot();
		 
		System.out.println("getting name from snapshot, should be marta, is " + 
			s2.get("name"));
		System.out.println("getting country from snapshot, should be UK, is " +
			s2.get("country"));
		System.out.println("getting name from original snapshot, should be john, is " + 
			s1.get("name"));
	}

    public SnapshottableMap() {
    	this.currentSnapshotId = 0;
    	snapshotMap = new HashMap<Integer, HashMap<String, String>>();
    }

    public void put(String key, String value) { 
    	if (!snapshotMap.containsKey(currentSnapshotId)) {
    		snapshotMap.put(currentSnapshotId, new HashMap<String, String>());
    	}
    	snapshotMap.get(currentSnapshotId).put(key, value);
  	}

  	public String get(String key) { 
	    int cur_snapshot = currentSnapshotId;
	    while(cur_snapshot >= 0) {
			HashMap<String, String> curMap = snapshotMap.get(cur_snapshot);
			if (curMap == null) continue;
	    	if (curMap.get(key) != null) {
		  		return snapshotMap.get(cur_snapshot).get(key);
			}
			cur_snapshot --;
		}
		return null;
    }

    public Snapshot createSnapshot() {
    	HashMap<String, String> snapshot = new HashMap<String, String>();
    	for (int i = 0; i <= currentSnapshotId; i++) {
			HashMap<String, String> curMap = snapshotMap.get(i);
			if (curMap == null) continue;
			for (String key : curMap.keySet()) {
	  			snapshot.put(key, curMap.get(key));
			}
   		}
	    currentSnapshotId++;
	    return new Snapshot(snapshot);
    }

    public List<Snapshot> getSnapshots()  {
    	List<Snapshot> result = new ArrayList<Snapshot>();
    	Map<String, String> current_snapshot = new HashMap<String, String>();
    	for (int i = 0; i <= currentSnapshotId; i++) {
			current_snapshot = copyMap(current_snapshot);
			HashMap<String, String> curMap = snapshotMap.get(i);
			if (curMap == null) {
				result.add(new Snapshot(current_snapshot));
				continue;
			}
			for (String key : curMap.keySet()) {
  				current_snapshot.put(key, curMap.get(key));
			}
			result.add(new Snapshot(current_snapshot));
    	}
  		return result;
	}

	private Map<String, String> copyMap(Map<String, String> original) {
		return new HashMap<String, String>(original);
	}

	public class Snapshot {
		private Map<String, String> snapshot;
	  
		public Snapshot(Map<String, String> s) {
	  		this.snapshot = s;
	  	}

	  	public String get(String key) { return snapshot.get(key); }
	}
}
