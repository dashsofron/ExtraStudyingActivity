package ru.sofronova.algo.mergesort;

import org.junit.Assert;
import org.junit.Test;

public class DownTopMergeSortTest {
    DownTopMergeSort downTopMergeSort = new DownTopMergeSort();

    @Test
    public void unsortedTest() {
        int[] input = {7, 6, 5, 4, 3, 2, 1};
        int[] sortedOutput = {1, 2, 3, 4, 5, 6, 7};
        downTopMergeSort.sort(input);
        Assert.assertArrayEquals(input,sortedOutput);
    }

    @Test
    public void oneElementTest() {
        int[] input = {1};
        int[] sortedOutput = {1};
        downTopMergeSort.sort(input);
        Assert.assertArrayEquals(input, sortedOutput);
    }

    @Test
    public void smallSortedTest() {
        int[] input = {1, 2};
        int[] sortedOutput = {1, 2};
        downTopMergeSort.sort(input);
        Assert.assertArrayEquals(input, sortedOutput);
    }

    @Test
    public void sortedTest() {
        int[] input = {1, 2, 3, 4, 5};
        int[] sortedOutput = {1, 2, 3, 4, 5};
        downTopMergeSort.sort(input);
        Assert.assertArrayEquals(input, sortedOutput);
    }

    @Test
    public void emptyTest() {
        int[] input = {};
        int[] sortedOutput = {};
        downTopMergeSort.sort(input);
        Assert.assertArrayEquals(input, sortedOutput);
    }

    @Test
    public void nullableTest() {
        int[] input = null;
        int[] sortedOutput = null;
        downTopMergeSort.sort(input);
        Assert.assertArrayEquals(input, sortedOutput);
    }
}
