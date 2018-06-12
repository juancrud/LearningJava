package com.juancrud.linq.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import com.juancrud.linq.LinqCollection;
import com.juancrud.linq.delegates.Function;
import com.juancrud.linq.delegates.Function2;
import com.juancrud.linq.tests.classes.IntStringPair;
import com.juancrud.linq.tests.classes.PrimitiveCollectionPair;
import com.juancrud.linq.tests.helpers.CollectionsHelper;
import com.juancrud.linq.tests.helpers.DelegatesHelper;

public class SelectManyTests {

	@Test
	public void testSelectMany_intStringPairCollection() {
		Collection<Integer> expectedCollection = CollectionsHelper.getIntCollection();
		expectedCollection.addAll(CollectionsHelper.getIntCollection());
		expectedCollection.addAll(CollectionsHelper.getIntCollection());
		expectedCollection.addAll(CollectionsHelper.getIntCollection());
		expectedCollection.addAll(CollectionsHelper.getIntCollection());
		expectedCollection.addAll(CollectionsHelper.getIntCollection());
		expectedCollection.addAll(CollectionsHelper.getIntCollection());
		expectedCollection.addAll(CollectionsHelper.getIntCollection());
		expectedCollection.addAll(CollectionsHelper.getIntCollection());
		expectedCollection.addAll(CollectionsHelper.getIntCollection());
		Object[] expected = expectedCollection.toArray();
		
		// Prepare
		Collection<PrimitiveCollectionPair<Integer>> collection = CollectionsHelper.getIntPrimitiveCollectionPairCollection();
		LinqCollection<PrimitiveCollectionPair<Integer>> linqCollection = LinqCollection.Get(collection);
		
		Function<PrimitiveCollectionPair<Integer>, Collection<Integer>> f = DelegatesHelper.getCollectionFunction();
		
		// Act
		LinqCollection<Integer> result = linqCollection.selectMany(f);
		
		// Assert
		assertArrayEquals(expected, result.toArray());
	}
	
	@Test
	public void testSelectMany_intCollection() {
		Collection<IntStringPair> expectedCollection = new ArrayList<IntStringPair>();
		Collection<Integer> intCollection = CollectionsHelper.getIntCollection();
		Collection<String> stringCollection = CollectionsHelper.getStringCollection();
		for(int i : intCollection) {
			for(String s : stringCollection) {
				expectedCollection.add(new IntStringPair(i, s));
			}
		}
		Object[] expected = expectedCollection.toArray();
		
		// Prepare
		Collection<Integer> collection = CollectionsHelper.getIntCollection();
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		Function<Integer, Collection<String>> f1 = DelegatesHelper.getStringCollectionFunction();
		Function2<Integer, String, IntStringPair> f2 = DelegatesHelper.getIntStringPairFunction();
		
		// Act
		LinqCollection<IntStringPair> result = linqCollection.selectMany(f1, f2);
		
		// Assert
		assertArrayEquals(expected, result.toArray());
	}

}
