package com.spark.eureka;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by liudan on 2019/3/10.
 */
public class LRUMap<K,V> {

    /**
     * 最大缓存大小
     */
    private int cacheSize;
    private LinkedHashMap<K,V> cacheMap ;
    public LRUMap(int cacheSize) {
        this.cacheSize = cacheSize;
        cacheMap = new LinkedHashMap(16,0.75F,true){
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                if (cacheSize + 1 == cacheMap.size()){
                    return true ;
                }else {
                    return false ;
                }
            }
        };
    }
    public void put(K key,V value){
        cacheMap.put(key,value) ;
    }
    public V get(K key){
        return cacheMap.get(key) ;
    }
    public Collection<Map.Entry<K, V>> getAll() {
        return new ArrayList<Map.Entry<K, V>>(cacheMap.entrySet());
    }


    public static void main (String[] args) {


        LRUMap<String,String> link = new LRUMap(6);

        link.put("1","第一个");
        link.put("2","第二个");
        link.put("3","第三个");
        link.put("4","第四个");
        link.put("5","第五个");
        link.put("6","第六个");
        link.put("7","第七个");



        for (Map.Entry<String, String> e : link.getAll()){
            System.out.println(e.getKey() + " : " + e.getValue() + "\t");
        }

        link.get("3");

        System.out.println("*************");

        for (Map.Entry<String, String> e : link.getAll()){
            System.out.println(e.getKey() + " : " + e.getValue() + "\t");
        }
    }
}
