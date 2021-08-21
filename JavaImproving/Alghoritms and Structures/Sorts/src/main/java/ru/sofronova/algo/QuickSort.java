package ru.sofronova.algo;

import java.util.Arrays;

public class QuickSort {
    void sort(int[] array, int left, int right) {
        if (left>=right||array==null) return;
        int left1 = left;
        int right1 = right;
        int p = (left + right) / 2;
        while (left <= right) {
            while (array[left] < array[p])
                left++;
            while (array[right] > array[p])
                right--;
            if (left >= right)
                break;
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            if(p==left||p==right) {
                if(p==left)
                {
                    p=right;
                    right++;
                }
                else
                {
                    p=left;
                    left--;
                }
            }
            left++;
            right--;
        }
        sort(array, left1, p);
        sort(array, p + 1, right1);
    }
}
