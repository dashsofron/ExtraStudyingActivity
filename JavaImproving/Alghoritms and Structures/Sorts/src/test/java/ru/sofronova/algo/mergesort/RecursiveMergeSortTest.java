package ru.sofronova.algo.mergesort;

import org.junit.Assert;
import org.junit.Test;

public class RecursiveMergeSortTest {
    RecursiveMergeSort recursiveMergeSort = new RecursiveMergeSort();

    @Test
    public void unsortedTest(){
        int[] input={7,6,5,4,3,2,1};
        int[] sortedOutput={1,2,3,4,5,6,7};
        recursiveMergeSort.sort(input);
        Assert.assertArrayEquals(input,sortedOutput);
    }
    @Test
    public void oneElementTest(){
        int[] input={1};
        int[] sortedOutput={1};
        recursiveMergeSort.sort(input);
        Assert.assertArrayEquals(input,sortedOutput);
    }
    @Test
    public void smallSortedTest(){
        int[] input={1,2};
        int[] sortedOutput={1,2};
        recursiveMergeSort.sort(input);
        Assert.assertArrayEquals(input,sortedOutput);
    }
    @Test
    public void sortedTest(){
        int[] input={1,2,3,4,5};
        int[] sortedOutput={1,2,3,4,5};
        recursiveMergeSort.sort(input);
        Assert.assertArrayEquals(input,sortedOutput);
    }
    @Test
    public void emptyTest(){
        int[] input={};
        int[] sortedOutput={};
        recursiveMergeSort.sort(input);
        Assert.assertArrayEquals(input,sortedOutput);
    }
    @Test
    public void nullableTest(){
        int[] input=null;
        int[] sortedOutput=null;
        recursiveMergeSort.sort(input);
        Assert.assertArrayEquals(input,sortedOutput);
    }
}
