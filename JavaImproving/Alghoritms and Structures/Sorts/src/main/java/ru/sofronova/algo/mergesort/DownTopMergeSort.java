package ru.sofronova.algo.mergesort;

import ru.sofronova.algo.Sort;

public class DownTopMergeSort implements Sort {
    @Override
    public void sort(int[] array) {
        if (array == null || array.length == 0) return;
        partSort(array, 0, array.length);
    }

    private void merge(int[] array, int start, int middle, int end) {
        if (end > array.length) end = array.length;
        for (int j : array) System.out.print(j);
        System.out.println();
        int[] temp = new int[end - start];
        int p = 0;
        int p1 = start;
        int p2 = middle;
        while (p1 < middle && p2 < end)
            temp[p++] = array[array[p1] < array[p2] ? p1++ : p2++];
        if (p1 == middle)
            p1 = p2;
        for (; p < temp.length; p++) {
            temp[p] = array[p1++];
        }

        if (temp.length >= 0) System.arraycopy(temp, 0, array, start, temp.length);
        for (int j : array) System.out.print(j);
        System.out.println();

    }

    private void partSort(int[] array, int start, int end) {
        int i = 2;
        for (; i <= array.length; i *= 2)
            for (int j = 0; j < array.length; j += i)
                merge(array, j, j + i / 2, j + i);
        merge(array, start, i / 2 , end);
    }
}
