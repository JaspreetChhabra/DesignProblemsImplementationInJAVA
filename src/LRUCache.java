import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    Map<Integer, DLinkListNode> keyNodeMap = new HashMap<>();
    int capacity;
    DLinkListNode head = new DLinkListNode(0,0);
    DLinkListNode tail = new DLinkListNode(0,0);

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key){
        if(keyNodeMap.containsKey(key)){
            DLinkListNode node = keyNodeMap.get(key);
            remove(node);
            insert(node);
            return node.val;
        }
        return -1;
    }

    public void put(int key, int val){
        if(keyNodeMap.containsKey(key))
            remove(keyNodeMap.get(key));

        if(keyNodeMap.size() == capacity)
            remove(tail.prev);

        insert(new DLinkListNode(key, val));
    }

    public void remove(DLinkListNode node){
        keyNodeMap.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void insert(DLinkListNode node){
        keyNodeMap.put(node.key, node);
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    public static void main(String[] args){
        LRUCache lruCache = new LRUCache(2);

        lruCache.put(1,2);
        System.out.println(lruCache.get(1));
        lruCache.put(2,2);
        lruCache.put(3,2);
        lruCache.put(5,1);
        System.out.println(lruCache.get(2));
        System.out.println(lruCache.get(1));
    }
}

class DLinkListNode{

    int val;
    int key;
    DLinkListNode next;
    DLinkListNode prev;

    public DLinkListNode(int key, int val) {
        this.val = val;
        this.key = key;
    }
}
