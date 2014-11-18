package test;


import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import processor.*;
public class TestAllReplacer {

    @Test
    public final void testArrayListReplacer() {

        ArrayListReplacer arr= new ArrayListReplacer();
        String simpleArray= "new java.util.ArrayList ()";
        String arrayWithSize ="new java.util.ArrayList (100)";
        assertEquals("com.google.common.collect.Lists.newArrayListWithCapacity(100)",  arr.processNewArrayList(arrayWithSize));
        assertEquals("com.google.common.collect.Lists.newArrayList()",  arr.processNewArrayList(simpleArray));
        
    
    }
    @Test
    public final void ListReplacer() {
        ListReplacer list = new ListReplacer();
        String unmodifiableList ="List<String> unmodifiableList = Collections.unmodifiableList(arr)";
        assertEquals("com.google.common.collect.ImmutableList.copyOf(arr)",  list.replaceUnmodifiableList(unmodifiableList));
        
        
    }
    
    @Test
    public final void MapReplacer() {
        MapReplacer list = new MapReplacer();
        String simpleHashMap=("new java.util.HashMap()");
        String hashMapWithSize=("new java.util.HashMap(100)");
        String unmodifiableMap ="HashMap unmodifiableList = Collections.unmodifiableMap(arr)";
        assertEquals("com.google.common.collect.Maps.newHashMap()",  list.processNewHashMap(simpleHashMap));
        assertEquals("com.google.common.collect.Maps.newHashMapWithExpectedSize(100)",  list.processNewHashMap(hashMapWithSize));
        assertEquals("com.google.common.collect.ImmutableMap.copyOf(arr)",  list.processImmutableMap(unmodifiableMap));
        
        
    }
    @Test
    public final void SetReplacer() {
        SetReplacer list = new SetReplacer();
        String simpleSet=("new java.util.HashSet()");
        String SetwithSize=("new java.util.HashSet(100)");
        String unmodifiableSet ="Set unmodifiableList = Collections.unmodifiableSet(arr)";
        assertEquals("com.google.common.collect.Sets.newHashSet()",  list.processNewHashSet(simpleSet));
        assertEquals("com.google.common.collect.Sets.newHashSetWithExpectedSize(100)",  list.processNewHashSet(SetwithSize));
        assertEquals("com.google.common.collect.ImmutableSet.copyOf(arr)",  list.processImmutableSet(unmodifiableSet));
        
        
    }
    
    @Test
    public final void SortedMapReplacer() {
        SortedMapReplacer sortedMap = new SortedMapReplacer();
        String simplesortedMap=("new java.util.TreeMap()");
        String sortedMapwithSize=("new java.util.TreeMap(100)");
        String unmodifiablesortedMap ="Set unmodifiableList = Collections.unmodifiableSortedMap(arr)";
        assertEquals("com.google.common.collect.Maps.newTreeMap()",  sortedMap.processNewSortedMap(simplesortedMap,"TreeMap"));
        assertEquals("com.google.common.collect.Maps.newTreeMap()",  sortedMap.processNewSortedMap(sortedMapwithSize,"TreeMap"));
        assertEquals("com.google.common.collect.ImmutableSortedMap.copyOf(arr)",  sortedMap.processImmutableSortedMap(unmodifiablesortedMap));
        
        
    }
    
    
    
    
    
}
