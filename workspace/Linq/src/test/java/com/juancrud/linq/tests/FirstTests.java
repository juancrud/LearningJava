package com.juancrud.linq.tests;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

import com.juancrud.linq.LinqCollection;
import com.juancrud.linq.delegates.Predicate;
import com.juancrud.linq.exceptions.LinqException;
import com.juancrud.linq.tests.classes.IntStringPair;
import com.juancrud.linq.tests.helpers.CollectionsHelper;
import com.juancrud.linq.tests.helpers.DelegatesHelper;

public class FirstTests {
	
	@Test
	public void testFirst_intStringPairCollection() throws LinqException {
		IntStringPair expected = new IntStringPair(1, "One");
		
		// Prepare
		Collection<IntStringPair> collection = CollectionsHelper.getIntStringPairCollection();
		LinqCollection<IntStringPair> linqCollection = LinqCollection.Get(collection);
		
		// Act
		IntStringPair result = linqCollection.first();
		
		// Assert
		assertEquals(expected, result);
	}
	
	@Test (expected=LinqException.class)
	public void testFirst_intCollection_emptyCollection() throws LinqException {
		// Prepare
		LinqCollection<Integer> linqCollection = new LinqCollection<Integer>();
		
		// Act
		linqCollection.first();
	}
	
	@Test
	public void testFirst_intStringPairCollection_WithPredicate() throws LinqException {
		IntStringPair expected = new IntStringPair(4, "Four");
		
		// Prepare
		Collection<IntStringPair> collection = CollectionsHelper.getIntStringPairCollection();
		LinqCollection<IntStringPair> linqCollection = LinqCollection.Get(collection);
		
		Predicate<IntStringPair> p = DelegatesHelper.getIntStringPairIsEqualPredicate(4);
		
		// Act
		IntStringPair result = linqCollection.first(p);
		
		// Assert
		assertEquals(expected, result);
	}
	
	@Test (expected=LinqException.class)
	public void testFirst_intCollection_emptyCollection_WithPredicate() throws LinqException {
		// Prepare
		LinqCollection<Integer> linqCollection = new LinqCollection<Integer>();
		
		Predicate<Integer> p = DelegatesHelper.getIsEqualPredicate(4);
		
		// Act
		linqCollection.first(p);
	}
	
	@Test
	public void testFirstOrDefault_intStringPairCollection() throws LinqException {
		IntStringPair expected = new IntStringPair(1, "One");
		
		// Prepare
		Collection<IntStringPair> collection = CollectionsHelper.getIntStringPairCollection();
		LinqCollection<IntStringPair> linqCollection = LinqCollection.Get(collection);
		
		// Act
		IntStringPair result = linqCollection.firstOrDefault();
		
		// Assert
		assertEquals(expected, result);
	}
	
	@Test
	public void testFirstOrDefault_intCollection_emptyCollection() throws LinqException {
		// Prepare
		LinqCollection<Integer> linqCollection = new LinqCollection<Integer>();
		
		// Act
		Integer result = linqCollection.firstOrDefault();
		
		// Assert
		assertNull(result);
	}
	
	@Test
	public void testFirstOrDefault_intStringPairCollection_WithPredicate() throws LinqException {
		IntStringPair expected = new IntStringPair(4, "Four");
		
		// Prepare
		Collection<IntStringPair> collection = CollectionsHelper.getIntStringPairCollection();
		LinqCollection<IntStringPair> linqCollection = LinqCollection.Get(collection);
		
		Predicate<IntStringPair> p = DelegatesHelper.getIntStringPairIsEqualPredicate(4);
		
		// Act
		IntStringPair result = linqCollection.firstOrDefault(p);
		
		// Assert
		assertEquals(expected, result);
	}
	
	@Test
	public void testFirstOrDefault_intCollection_returnNull_WithPredicate() throws LinqException {
		// Prepare
		Collection<Integer> collection = CollectionsHelper.getIntCollection();
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		Predicate<Integer> p = DelegatesHelper.getIsEqualPredicate(11);
		
		// Act
		Integer result = linqCollection.firstOrDefault(p);
		
		// Assert
		assertNull(result);
	}
	
}
