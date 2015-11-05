package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.TreeSet;

/**
 * Created by harshad on 11/5/15.
 */
public class NewSets {


    public static void main(String[] args) throws Exception {
        String pathATT = "/Users/harshad/IdeaProjects/MarkovClustering/pajek/new_collaboration.txt";
        BufferedReader br = new BufferedReader(new FileReader(pathATT));
        String string;
        StringBuilder stringBuilder = new StringBuilder();

        TreeSet<Integer> idSet = new TreeSet<>();

        while( (string = br.readLine())!=null ) {
            stringBuilder.append(string+"\n");
        }

        String[] rows = stringBuilder.toString().split("\n");
        for(String row : rows) {
            int n1 = Integer.parseInt(row.split(" ")[0]);
            int n2 = Integer.parseInt(row.split(" ")[1]);
            idSet.add(n1);
            idSet.add(n2);
        }

        for(int i: idSet){
            System.out.println(i);
        }

    }
}
