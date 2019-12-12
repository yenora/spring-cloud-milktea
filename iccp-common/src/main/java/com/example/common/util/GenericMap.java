package com.example.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: yaos
 * @Date: 2019-12-12 21:50
 * @Version：V1.0
 **/
public class GenericMap<K, V> {

    public Builder<K, V> bd;

    public GenericMap(Builder<K, V> bd){
        this.bd = bd;
    }

    public V get(K k){
        return bd.map.get(k);
    }

    public Map<K, V> map(){
        return bd.map;
    }

    public static class Builder<K, V>{
        Map<K,V> map;

        public Builder(){
            map = new HashMap<K,V>();
        }

        public Builder<K, V> put(K k, V v){
            map.put(k, v);
            return this;
        }

        public GenericMap<K, V> builder(){
            return new GenericMap<K, V>(this);
        }
    }

    public static void main(String[] args){
        GenericMap.Builder<Integer,String> tm = new GenericMap.Builder<Integer,String>();
        tm.put(1,"a").put(2,"b").put(3,"c").builder().map();
        System.out.println(tm.builder().get(2));
    }
}
