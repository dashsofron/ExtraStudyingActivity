package ru.sofronova.algo.mergesort;

import ru.sofronova.algo.Sort;

public class RecursiveMergeSort implements Sort {

    @Override
    public void sort(int[] array) {
        if (array == null || array.length == 0) return;
        partSort(array, 0, array.length);
    }

    private void merge(int[] array, int start, int middle, int end) {
        int[] temp = new int[end - start];
        int p = 0;
        int p1 = start;
        int p2 = middle;
        while (p1 < middle && p2 < end)
            temp[p++] = array[array[p1] < array[p2] ? p1++ : p2++];
        if (p1 == middle)
            p1 = p2;
        for (;p < temp.length; p++) {
            temp[p] = array[p1++];
        }

        if (temp.length >= 0) System.arraycopy(temp, 0, array, start, temp.length);
    }

    private void partSort(int[] array, int start, int end) {
        if (end - start < 2) return;
        int middle = start + (end - start) / 2;

        if (end - start > 2) {
            partSort(array, start, middle);
            partSort(array, middle, end);
        }
        merge(array, start, middle, end);
    }
}
