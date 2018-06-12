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

public class LastTests {
	
	@Test
	public void testLast_intStringPairCollection() throws LinqException {
		IntStringPair expected = new IntStringPair(10, "Ten");
		
		// Prepare
		Collection<IntStringPair> collection = CollectionsHelper.getIntStringPairCollection();
		LinqCollection<IntStringPair> linqCollection = LinqCollection.Get(collection);
		
		// Act
		IntStringPair result = linqCollection.last();
		
		// Assert
		assertEquals(expected, result);
	}
	
	@Test (expected=LinqException.class)
	public void testLast_intCollection_emptyCollection() throws LinqException {
		// Prepare
		LinqCollection<Integer> linqCollection = new LinqCollection<Integer>();
		
		// Act
		linqCollection.last();
	}
	
	@Test
	public void testLast_intCollection_WithPredicate() throws LinqException {
		int expected = 4;
		
		// Prepare
		Collection<Integer> collection = CollectionsHelper.getIntCollection();
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		Predicate<Integer> p = DelegatesHelper.getIsLessThanPredicate(5);
		
		// Act
		int result = linqCollection.last(p);
		
		// Assert
		assertEquals(expected, result);
	}
	
	@Test (expected=LinqException.class)
	public void testLast_intCollection_notFound_WithPredicate() throws LinqException {
		// Prepare
		Collection<Integer> collection = CollectionsHelper.getIntCollection();
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		Predicate<Integer> p = DelegatesHelper.getIsEqualPredicate(11);
		
		// Act
		linqCollection.last(p);
	}
	
	@Test
	public void testLastOrDefault_intStringPairCollection() throws LinqException {
		IntStringPair expected = new IntStringPair(10, "Ten");
		
		// Prepare
		Collection<IntStringPair> collection = CollectionsHelper.getIntStringPairCollection();
		LinqCollection<IntStringPair> linqCollection = LinqCollection.Get(collection);
		
		// Act
		IntStringPair result = linqCollection.lastOrDefault();
		
		// Assert
		assertEquals(expected, result);
	}
	
	@Test
	public void testLastOrDefault_intCollection_emptyCollection() throws LinqException {
		// Prepare
		LinqCollection<Integer> linqCollection = new LinqCollection<Integer>();
		
		// Act
		Integer result = linqCollection.firstOrDefault();
		
		// Assert
		assertNull(result);
	}
	
	@Test
	public void testLastOrDefault_intCollection_WithPredicate() throws LinqException {
		int expected = 4;
		
		// Prepare
		Collection<Integer> collection = CollectionsHelper.getIntCollection();
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		Predicate<Integer> p = DelegatesHelper.getIsLessThanPredicate(5);
		
		// Act
		int result = linqCollection.last(p);
		
		// Assert
		assertEquals(expected, result);
	}
	
	@Test
	public void testLastOrDefault_intCollection_returnNull_WithPredicate() throws LinqException {
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
