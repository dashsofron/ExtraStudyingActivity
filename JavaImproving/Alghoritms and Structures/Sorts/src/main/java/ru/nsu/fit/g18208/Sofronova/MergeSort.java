package ru.nsu.fit.g18208.Sofronova;

public class MergeSort {
    void sort(int[] array, int start, int end) {
        if (array == null) return;
        if(array.length==0)return;
        if (start == end) return;
        sort(array, start, (end + start) / 2);
        sort(array, (end + start + 1) / 2, end);
        int[] array2 = new int[end - start + 1];
        int k = 0;
        int k1 = start;
        int k2 = (end + start + 1) / 2;
        while ((k1 < (end + start + 1) / 2) && (k2 <= end)) {
            if (array[k1] <= array[k2]) {
                array2[k++] = array[k1++];
            } else {
                array2[k++] = array[k2++];
            }
        }
        if (k1 < (end + start + 1) / 2)
            for (; k1 < (end + start + 1) / 2; k1++)
                array2[k++] = array[k1];
        else
            for (; k2 <= end; k2++)
                array2[k++] = array[k2];
        for (int i = start; i <= end; i++)
            array[i] = array2[i - start];
    }
}
