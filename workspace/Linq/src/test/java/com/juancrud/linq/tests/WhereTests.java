package com.juancrud.linq.tests;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import com.juancrud.linq.LinqCollection;
import com.juancrud.linq.delegates.Predicate;
import com.juancrud.linq.tests.classes.IntStringPair;
import com.juancrud.linq.tests.helpers.CollectionsHelper;
import com.juancrud.linq.tests.helpers.DelegatesHelper;

public class WhereTests {
	
	@Test
	public void testWhere_stringCollection() {
		Collection<String> removedItemCollection = CollectionsHelper.getStringCollection();
		removedItemCollection.remove("Three");
		Collection<String> expectedCollection = removedItemCollection;
		expectedCollection.addAll(removedItemCollection);
		Object[] expected = expectedCollection.toArray();
		
		// Prepare
		Collection<String> collection1 = CollectionsHelper.getStringCollection();
		Collection<String> collection2 = CollectionsHelper.getStringCollection();
		LinqCollection<String> linqCollection = LinqCollection.Get(collection1);
		linqCollection.addAll(LinqCollection.Get(collection2));
		
		Predicate<String> p = DelegatesHelper.getIsNotEqualPredicate("Three");
		
		// Act
		LinqCollection<String> result = linqCollection.where(p);
		
		// Assert
		assertArrayEquals(expected, result.toArray());
	}
	
	@Test
	public void testWhere_intStringPairCollection() {
		Collection<IntStringPair> expectedCollection = new ArrayList<IntStringPair>();
		expectedCollection.add(new IntStringPair(3, "Three"));
		expectedCollection.add(new IntStringPair(3, "Three"));
		Object[] expected = expectedCollection.toArray();
		
		// Prepare
		Collection<IntStringPair> collection1 = CollectionsHelper.getIntStringPairCollection();
		Collection<IntStringPair> collection2 = CollectionsHelper.getIntStringPairCollection();
		LinqCollection<IntStringPair> linqCollection = LinqCollection.Get(collection1);
		linqCollection.addAll(LinqCollection.Get(collection2));
		
		Predicate<IntStringPair> p = DelegatesHelper.getIntStringPairIsEqualPredicate(3);
		
		// Act
		LinqCollection<IntStringPair> result = linqCollection.where(p);
		
		// Assert
		assertArrayEquals(expected, result.toArray());
	}
	
}
