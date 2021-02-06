package ru.nsu.fit.g18208.Sofronova;

import org.junit.Assert;
import org.junit.Test;

public class TreeTests {
        @Test
        public void correctWork1(){
            Tree<String> t=new Tree<String>();
            t.setRoot("NSU");
            t.add("NSU","MMF");
            t.add("NSU","FF");
            t.add("NSU","FIT");
            t.add("NSU","GGF");
            t.add("FIT","SI");
            t.add("FIT","KOI");
            t.dFS(t.root);
            System.out.println("\n\n");
            t.bFS(t.root);
        }
}
