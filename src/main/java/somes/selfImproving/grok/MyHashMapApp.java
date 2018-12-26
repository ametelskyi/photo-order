package somes.selfImproving.grok;

import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.List;

public class MyHashMapApp {

    public static void main(String[] args) {
        MyHashMap<String, Integer> myHashMap = new MyHashMap();
        myHashMap.add("a", 135);
        myHashMap.add("b", 242);
        myHashMap.add("c", 342);
        myHashMap.add("d", 442);
        myHashMap.add("f", 542);
        myHashMap.add("g", 542);
        myHashMap.add("h", 742);
        myHashMap.add("j", 842);
        myHashMap.add("y", 942);
        myHashMap.add("l", 142);
        myHashMap.add("m", 112);

        if(myHashMap.get("a") != 135) throw new RuntimeException("not here");

        myHashMap.delete("a");

        if(myHashMap.get("a") != null) throw new RuntimeException("not null");


        System.out.println(23);
    }
}

class MyHashMap<K, V> {
    private List<Pair<K,V>>[] array;
    private int defaultArrSize = 300;

    public void add(K key, V value) {

        if(array == null) {
            @SuppressWarnings("unchecked")
            final List<Pair<K,V>>[] a = (List<Pair<K,V>>[]) Array.newInstance(List.class, defaultArrSize);
            array = a;
        }

        int index = key.hashCode()%array.length;
        List<Pair<K,V>> bucketValues;
        if(array[index] == null) {
            bucketValues = new LinkedList<>();
            array[index] = bucketValues;
        } else {
            bucketValues = array[index];
        }

        bucketValues.add(new Pair<>(key, value));
    }

    public V get(K key) {
        int index = key.hashCode()%array.length;
        if(array[index] == null) return null;

        List<Pair<K,V>> values = array[index];
        for (Pair<K, V> value : values) {
            if(value.getKey().equals(key)) return value.getValue();
        }

        return null;
    }

    public void delete(K key) {
        int index = key.hashCode()%array.length;
        if(array[index] == null) return;

        List<Pair<K,V>> values = array[index];
        for (Pair<K, V> value : values) {
            if(value.getKey().equals(key)) {
                values.remove(value);
                return;
            }
        }
    }
}
