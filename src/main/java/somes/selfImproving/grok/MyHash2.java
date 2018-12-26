package somes.selfImproving.grok;

/**
 * Created by anme on 10.12.18.
 */
public class MyHash2 {
    public static void main(String[] args) {
        MyHash<Integer> myHash = new MyHash<>();

        myHash.add("one", 111);
        myHash.add("two", 222);
        myHash.add("tree", 333);
        myHash.add("tree1", 3433);
        myHash.add("tree2", 3353);
        myHash.add("tree3", 3363);
        myHash.add("tree4", 7333);
        myHash.add("tree6", 4333);
        myHash.add("tree5", 3633);
        myHash.add("tree7", 33873);
        myHash.add("tree8", 3933);
        myHash.add("tree9", 39333);

        System.out.println(myHash.get("two"));
        System.out.println(myHash.get("twodsf"));
        System.out.println(myHash.get("tree7"));


    }


}

class MyHash<V> {
    private Node[] arr;

    public MyHash() {
        arr = new Node[8];
    }

    public void add(String key, V value) {
        checkIfResizeNeeded();

        long hash = myHash(key);
        int index = (int)(hash % arr.length);

        Node<V> node = new Node<>(key, value);
        if(arr[index] == null) {
            arr[index] = node;
        } else {
            Node list = arr[index];
            while(list.getNext() != null) {
                list = list.getNext();
            }
            list.setNext(node);
        }
    }

    private void checkIfResizeNeeded() {
        double loadFactor = elCount() / arr.length;
        if(loadFactor > 0.7) {
            Node[] oldArr = arr;
            arr = new Node[arr.length*2];
            for (int i = 0; i < oldArr.length; i++) {
                if(oldArr[i] != null) {
                    Node<V> el = oldArr[i];
                    do {
                        add(el.getKey(), el.getValue());
                        el = el.getNext();
                    } while (el != null);
                }
            }
        }
    }

    private int elCount() {
        int cnt = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] != null) {
                Node el = arr[i];
                do {
                    cnt++;
                    el = el.getNext();
                } while (el != null);
            }
        }
        return cnt;
    }

    public V get(String key) {
        long hash = myHash(key);
        int index = (int)(hash % arr.length);

        if(arr[index] == null) {
            return null;
        } else {
            Node<V> list = arr[index];
            do {
                if(list.getKey().equals(key)) return list.getValue();
                list = list.getNext();
            } while (list != null);
        }

        return null;
    }

    private static long myHash(String str) {
        long hash = 0;
        int[] chars = str.chars().toArray();
        for (int i = 0; i < chars.length; i++) {
            hash += i * 10 + chars[i];
        }
        return hash;
    }

}

class Node<V> {
    private String key;
    private V value;

    private Node next;

    public Node(String key, V value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
