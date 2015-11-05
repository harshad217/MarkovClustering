package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class MarkovClustering {

    public static Graph generateGraph(String path) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String string;
        StringBuilder stringBuilder = new StringBuilder();
        Graph g = new Graph();
        HashMap<Integer,ArrayList<Integer>> adjMap = new HashMap<>();
        while( (string = br.readLine())!=null ) {
            stringBuilder.append(string+"\n");
        }
        String[] rows = stringBuilder.toString().split("\n");

        for(String row : rows) {
            String[] strArr = row.split(" ");
            int n1 = Integer.parseInt(strArr[0]);
            int n2  = Integer.parseInt(strArr[1]);
            if(adjMap.containsKey(n1)) {
                ArrayList<Integer> temp = adjMap.get(n1);
                temp.add(n2);
                adjMap.put(n1,temp);
            } else {
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(n2);
                adjMap.put(n1,temp);
            }
            if(adjMap.containsKey(n2)) {
                ArrayList<Integer> temp = adjMap.get(n2);
                temp.add(n1);
                adjMap.put(n2,temp);
            } else {
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(n1);
                adjMap.put(n2,temp);
            }
        }
        g.setAdjacencyMap(adjMap);
        return g;
    }

    public static void printAdjMap(HashMap hMap) {
        HashMap<Integer,ArrayList<Integer>> map = hMap;
        for(Map.Entry entry : map.entrySet()) {
            System.out.print("node_id: "+((Integer) entry.getKey())+", adjList:  ");
            ArrayList<Integer> temp = (ArrayList<Integer>)entry.getValue();
            for(int n: temp) {
                System.out.print(" " + n);
            }
            System.out.println();
        }
    }

    public static void markovClustering(double[][] matrix) {
        //Start Markov clustering algorithm while loop
        double[][] R = new double[matrix.length][matrix[0].length];
        boolean flag = false;
        long start = System.currentTimeMillis();
        while(true) {
            matrix = expand(matrix);
            matrix = inflate(matrix);
            if(Arrays.deepEquals(matrix, R)) {
                flag = true;
                printMatrix(R);
                break;
            } else {
                R = matrix;
            }
            if(System.currentTimeMillis()>start+1000) {
                break;
            }
        }

        System.out.println(flag);
        for(int i=0;i<matrix.length;i++) {
            ArrayList<Integer> list = new ArrayList<>();
            for(int j=0;j<matrix.length;j++) {
                if(matrix[i][j]!=0) {
                    list.add(j);
                }
            }
            System.out.print("for cluster"+i+": ");
            for(int l: list) {
                System.out.print(l +" ");
            }
            System.out.println();
        }
    }

    public static PhysicsGraph createPhysicsGraph(String path) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String string;
        StringBuilder stringBuilder = new StringBuilder();
        PhysicsGraph g = new PhysicsGraph();

        TreeSet<String> idSet = new TreeSet<>();            //for mapping
        TreeMap<String,Integer> idMap = new TreeMap<>();   //for mapping
        TreeMap<Integer,ArrayList<Integer>> adjMap = new TreeMap<>(); //adjacency matrix for phy graph

        while( (string = br.readLine())!=null ) {
            stringBuilder.append(string+"\n");
        }
        String[] rows = stringBuilder.toString().split("\n");

        for(String row : rows) {
            String[] strArr = row.split(" ");
            String n1 = strArr[0];
            String n2 = strArr[1];
            idSet.add(n1);
            idSet.add(n2);
        }
        int id=0;
        for(String s: idSet) {
            idMap.put(s, id);
            id = id + 1;
        }

        for(String row : rows) {
            String[] strArr = row.split(" ");
            int n1 = idMap.get(strArr[0]);
            int n2 = idMap.get(strArr[1]);

            if(adjMap.containsKey(n1)) {
                ArrayList<Integer> temp = adjMap.get(n1);
                temp.add(n2);
                adjMap.put(n1,temp);
            } else {
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(n2);
                adjMap.put(n1,temp);
            }
            if(adjMap.containsKey(n2)) {
                ArrayList<Integer> temp = adjMap.get(n2);
                temp.add(n1);
                adjMap.put(n2,temp);
            } else {
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(n1);
                adjMap.put(n2,temp);
            }
        }
        g.setIdSet(idSet);
        g.setIdMap(idMap);
        g.setAdjMap(adjMap);
        return g;
    }

    public static void main(String[] args) throws Exception {
	// write your code here
        String pathATT = "/Users/harshad/IdeaProjects/MarkovClustering/Data_For_HW3/attweb_net.txt";
        String pathYeast = "/Users/harshad/IdeaProjects/MarkovClustering/Data_For_HW3/yeast_undirected_metabolic.txt";
        String pathPhysics = "/Users/harshad/IdeaProjects/MarkovClustering/Data_For_HW3/physics_collaboration_net.txt";

        Graph g = generateGraph(pathATT);
        PhysicsGraph pg = createPhysicsGraph(pathPhysics);
        HashMap<Integer,ArrayList<Integer>> hmap = new HashMap<>();
        hmap.putAll(pg.getAdjMap());
        printAdjMap(hmap);


//        printAdjMap(g.getAdjacencyMap());

        double[][] matrix = buildMatrix(g);
        double[][] abc = { {1,2},{3,4} };

//        printMatrix(inflate(abc));
//        printMatrix(matrix);

//        markovClustering(matrix);


    }

    public static double[][] inflate(double[][] matrix) {
        /*
        Square each column, then normalize
        return the resultant matrix
        */
        for(int i=0;i<matrix.length;i++) {      //for each column
            double sum = 0;

            for(int j=0;j<matrix[0].length;j++) {  //go thru all the rows, square all, sum all
                if(matrix[j][i]!=0) {
                    matrix[j][i] = matrix[j][i] * matrix[j][i];
                    sum += matrix[j][i];
                }
            }
            for(int j=0;j<matrix.length;j++) {  //get the norm denom
                if(matrix[j][i]!=0) {
                    matrix[j][i] = matrix[j][i]/sum;
                }
            }
        }
        return matrix;
    }

    public static void printMatrix(double[][] matrix) {

        for(int i=0;i<matrix.length;i++) {
            System.out.print(i+"\t");
            for(int j=0;j<matrix[0].length;j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static double[][] expand(double[][] matrix) {
        double[][] result = new double[matrix.length][matrix[0].length];
        for(int i =0;i<matrix.length;i++) {
            for(int j=0;j<matrix[0].length;j++) {
                for(int k=0;k<matrix.length;k++) {
                    result[i][j] += matrix[i][k] * matrix[k][j];
                }
            }
        }
        return result;
    }

    public static double[][] buildMatrix(Graph g) {
        HashMap<Integer,ArrayList<Integer>> adjMap = g.getAdjacencyMap();
        int N = adjMap.size();
        double[][] matrix = new double[N][N];

        for(Map.Entry entry : adjMap.entrySet()) {
            int node = (Integer) entry.getKey();
            ArrayList<Integer> adjList = (ArrayList<Integer>) entry.getValue();
            matrix[node][node] = 1;
            for(int i : adjList) {
                matrix[i][node] = 1;
            }
            double normDenom= 0;

            for(int i=0; i<matrix.length;i++) {
                if(matrix[i][node]!=0){
                    normDenom = normDenom + matrix[i][node];
                }
            }
//            System.out.println("normalization denominator for=  "+normDenom);
            matrix[node][node] = matrix[node][node] / normDenom;
//            System.out.println("same element="+ matrix[node][node]);
            for(int i: adjList) {
                matrix[i][node] = matrix[i][node]/normDenom;
            }
        }
        return matrix;
    }
}
