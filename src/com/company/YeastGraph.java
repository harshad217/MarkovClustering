package com.company;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by harshad on 11/5/15.
 */
public class YeastGraph {

    TreeSet<Integer> idSet;
    TreeMap<Integer,Integer> idMap;
    TreeMap<Integer,Integer> reverseMap;
    TreeMap<Integer,ArrayList<Integer>> ajdMap;

    public TreeSet<Integer> getIdSet() {
        return idSet;
    }

    public void setIdSet(TreeSet<Integer> idSet) {
        this.idSet = idSet;
    }

    public void setReverseMap(TreeMap<Integer, Integer> reverseMap) {
        this.reverseMap = reverseMap;
    }

    public TreeMap<Integer, Integer> getIdMap() {
        return idMap;
    }

    public void setIdMap(TreeMap<Integer, Integer> idMap) {
        this.idMap = idMap;
    }

    public TreeMap<Integer, Integer> getReverseMap() {
        return reverseMap;
    }

    public TreeMap<Integer, ArrayList<Integer>> getAjdMap() {
        return ajdMap;
    }

    public void setAjdMap(TreeMap<Integer, ArrayList<Integer>> ajdMap) {
        this.ajdMap = ajdMap;
    }
}
