package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by harshad on 11/4/15.
 */
public class PhysicsGraph implements Serializable {

    TreeSet<String> idSet;  //all unique ids
    TreeMap<String, Integer> idMap; //for mapping ser num to ids
    TreeMap<Integer, String> reverseMap; //reverse
    TreeMap<Integer,ArrayList<Integer>> adjMap; //adjacency matrix

    public TreeMap<String, Integer> getIdMap() {
        return idMap;
    }

    public void setIdMap(TreeMap<String, Integer> idMap) {
        this.idMap = idMap;
    }

    public TreeSet<String> getIdSet() {
        return idSet;
    }

    public void setIdSet(TreeSet<String> idSet) {
        this.idSet = idSet;
    }

    public TreeMap<Integer, ArrayList<Integer>> getAdjMap() {
        return adjMap;
    }

    public void setAdjMap(TreeMap<Integer, ArrayList<Integer>> adjMap) {
        this.adjMap = adjMap;
    }

    public void setReverseMap(TreeMap<Integer, String> reverseMap) {
        this.reverseMap = reverseMap;
    }

    public TreeMap<Integer, String> getReverseMap() {
        return reverseMap;
    }
}
