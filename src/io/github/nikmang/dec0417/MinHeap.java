package io.github.nikmang.dec0417;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nik on 12/4/2017.
 * Taken mostly from CS2110 FA17 in Cornell University.
 */
public class MinHeap<T> {

    private int size;
    private Map<T, Integer> map;
    private VP[] arr; //Value-Priority pair

    public MinHeap() {
        arr = createVPArray(10);
        map = new HashMap<>();
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T poll() {
        if(size == 0)
            return null;

        T val = arr[0].value;
        swap(0, --size);
        map.remove(val);

        if(size > 0)
            bubbleDown(0);

        return val;
    }

    public T peek() {
        if(size == 0)
            return null;

        return arr[0].value;
    }

    public void changePriority(T t, double newVal) {
        Integer index = map.get(t);

        if(index == null)
            return;

        double priority = arr[index].priority;
        arr[index].priority = newVal;

        if(newVal < priority) {
            bubbleUp(index);
        } else {
            bubbleDown(index);
        }
    }

    public void add(T t, double priority) {
        if(map.containsKey(t))
            return;

        maintainSpace();
        map.put(t, size);
        arr[size++] = new VP(t, priority);
        bubbleUp(size-1);
    }

    private int smallerChildOf(int n) {
        int child = 2*n+1;

        if(child+1 == size)
            return child;

        return arr[n].priority < arr[n+1].priority ? n : n+1;
    }

    private void swap(int i, int j) {
        VP vp = arr[i];
        arr[i] = arr[j];
        arr[j] = vp;

        map.put(arr[i].value, i);
        map.put(arr[j].value, j);
    }

    private void bubbleUp(int index) {
        while(index > 0) {
            int parent = (index-1)/2;

            if(arr[index].priority >= arr[parent].priority)
                return;

            swap(index, parent);
            index = parent;
        }
    }

    private void bubbleDown(int index) {
        while(2*index+1 < size) {
            int child = smallerChildOf(index);

            if(arr[index].priority <= arr[child].priority)
                return;

            swap(index, child);
            index = child;
        }
    }

    private void maintainSpace() {
        if(size < arr.length)
            return;

        arr = Arrays.copyOf(arr, 2*size);;
    }

    private class VP {
        private T value;
        private double priority;

        VP(T v, double p) {
            value= v;
            priority= p;
        }

        @Override public String toString() {
            return "(" + value + ", " + priority + ")";
        }
    }

    //Because I cant create generic arrays easily
    @SuppressWarnings("unchecked")
    private VP[] createVPArray(int n) {
        return (VP[]) Array.newInstance(VP.class, n);
    }
}
