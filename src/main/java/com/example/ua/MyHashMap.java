package com.example.ua;

import java.util.Objects;


public class MyHashMap<K, V> {

    private int size = 0;
    private float treshold;
    private Node<K, V>[] table;

    public MyHashMap() {
        table = new Node[16];
        treshold = table.length*0.75f;

    }

    public boolean put(final K key, final V value) {
        if (size + 1 >= treshold) {
            treshold *= 2;
            arrayDoubling();
        }

        Node<K,V> newNode = new Node<K,V>(key, value);
        int index = hash(newNode.key);

        if (table[index] == null) {
            return simpleAdd(index, newNode);
        } else
            return newValueSameKey(newNode, index);
    }

    public V get(final K key) {
        int index = hash(key);

        if (table[index] != null && table[index].key == key) {
            return table[index].value;
        }

        while ((table[index] != null && table[index].key == key) || index > table.length-1) {
            index++;
        }
        return table[index].value;
    }

    private void arrayDoubling() {
        Node<K,V>[] oldTable = table;
        table = new Node[oldTable.length*2];
        size = 0;
        for (Node<K,V> node : oldTable) {
            if (node != null) {
                put(node.key, node.value);
            }
        }
    }

    private boolean simpleAdd(int index, Node<K,V> newNode) {
        table[index] = newNode;
        size++;
        return true;
    }

    private boolean newValueSameKey(Node<K,V> newNode, int index) {
        if (table[index].key.equals(newNode.key)) {
            table[index] = newNode;
            return true;
        } else
            return collisionProcess(newNode, index);
    }

    private boolean collisionProcess(Node<K, V> newNode, int index) {
        while(table[index] != null)
        {
            index++;
        }
        table[index] = newNode;
        size++;
        return true;
    }

    public int size() {
        return size;
    }

    private int hash(final K key) {
        int hash = 31;
        hash = hash * 17 + key.hashCode();
        return hash % table.length;
    }

    private class Node<K, V> {
        private int hash;
        private K key;
        private V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public int hashCode() {
            hash = 31;
            hash = hash * 17 + key.hashCode();
            return hash % table.length;
        }

        public final boolean equals(Object obj) {
            if (this == obj)
                return true;

            if (obj instanceof Node) {
                Node<K,V> node = (Node) obj;
                return (Objects.equals(key, node.key) &&
                        Objects.equals(value, node.value) ||
                        Objects.equals(hash, node.hashCode()));
            }
            return false;
        }
    }
}
