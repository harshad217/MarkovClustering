package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by harshad on 11/3/15.
 */
public class DepGraph implements Serializable {

    HashMap<Integer, ArrayList<Integer>> adjacencyMap;

    public HashMap<Integer, ArrayList<Integer>> getAdjacencyMap() {
        return adjacencyMap;
    }

    public void setAdjacencyMap(HashMap<Integer, ArrayList<Integer>> adjacencyMap) {
        this.adjacencyMap = adjacencyMap;
    }

}
