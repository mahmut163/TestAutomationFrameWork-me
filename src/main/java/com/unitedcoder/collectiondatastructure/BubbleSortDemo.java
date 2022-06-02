package com.unitedcoder.collectiondatastructure;

import java.lang.reflect.Array;
import java.util.Arrays;

public class BubbleSortDemo {
    public static void main(String[] args) {
        int[] a={20,10,100,300,80,70,90};
        for(int i=0;i<a.length-1;i++){
            for(int j=0;j<a.length-1-i;j++){
                if(a[j]>a[j+1]){
                    int temp=a[j];
                    a[j]=a[j+1];
                    a[j+1]=temp;
                }
            }
        }
        System.out.println(Arrays.toString(a));
    }
}
