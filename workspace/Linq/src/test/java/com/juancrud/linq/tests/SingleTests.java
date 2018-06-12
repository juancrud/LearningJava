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

public class SingleTests {
	
	@Test
	public void testSingle_intStringPairCollection() throws LinqException {
		IntStringPair expected = new IntStringPair(1, "One");
		
		// Prepare
		LinqCollection<IntStringPair> linqCollection = new LinqCollection<IntStringPair>();
		linqCollection.add(new IntStringPair(1, "One"));
		
		// Act
		IntStringPair result = linqCollection.single();
		
		// Assert
		assertEquals(expected, result);
	}
	
	@Test (expected=LinqException.class)
	public void testSingle_intCollection_emptyCollection() throws LinqException {
		// Prepare
		LinqCollection<Integer> linqCollection = new LinqCollection<Integer>();
		
		// Act
		linqCollection.single();
	}
	
	@Test (expected=LinqException.class)
	public void testSingle_intCollection_multipleResults() throws LinqException {
		// Prepare
		LinqCollection<Integer> linqCollection = new LinqCollection<Integer>();
		linqCollection.add(1);
		linqCollection.add(2);
		
		// Act
		linqCollection.single();
	}
	
	@Test
	public void testSingle_intStringPairCollection_WithPredicate() throws LinqException {
		IntStringPair expected = new IntStringPair(4, "Four");
		
		// Prepare
		Collection<IntStringPair> collection = CollectionsHelper.getIntStringPairCollection();
		LinqCollection<IntStringPair> linqCollection = LinqCollection.Get(collection);
		
		Predicate<IntStringPair> p = DelegatesHelper.getIntStringPairIsEqualPredicate(4);
		
		// Act
		IntStringPair result = linqCollection.single(p);
		
		// Assert
		assertEquals(expected, result);
	}
	
	@Test (expected=LinqException.class)
	public void testSingle_intCollection_emptyCollection_WithPredicate() throws LinqException {
		// Prepare
		Collection<Integer> collection = CollectionsHelper.getIntCollection(1, 3);
		LinqCollection<Integer> linqCollection = new LinqCollection<Integer>(collection);
		
		Predicate<Integer> p = DelegatesHelper.getIsEqualPredicate(4);
		
		// Act
		linqCollection.single(p);
	}
	
	@Test (expected=LinqException.class)
	public void testSingle_intCollection_multipleResults_WithPredicate() throws LinqException {
		// Prepare
		LinqCollection<Integer> linqCollection = new LinqCollection<Integer>();
		linqCollection.add(4);
		linqCollection.add(4);
		
		Predicate<Integer> p = DelegatesHelper.getIsEqualPredicate(4);
		
		// Act
		linqCollection.single(p);
	}
	
	@Test
	public void testSingleOrDefault_intStringPairCollection() throws LinqException {
		IntStringPair expected = new IntStringPair(1, "One");
		
		// Prepare
		LinqCollection<IntStringPair> linqCollection = new LinqCollection<IntStringPair>();
		linqCollection.add(new IntStringPair(1, "One"));
		
		// Act
		IntStringPair result = linqCollection.singleOrDefault();
		
		// Assert
		assertEquals(expected, result);
	}
	
	@Test
	public void testSingleOrDefault_intCollection_emptyCollection() throws LinqException {
		// Prepare
		LinqCollection<Integer> linqCollection = new LinqCollection<Integer>();
		
		// Act
		Integer result = linqCollection.singleOrDefault();
		
		// Assert
		assertNull(result);
	}
	
	@Test
	public void testSingleOrDefault_intCollection_multipleResults() throws LinqException {
		// Prepare
		LinqCollection<Integer> linqCollection = new LinqCollection<Integer>();
		linqCollection.add(1);
		linqCollection.add(2);
		
		// Act
		Integer result = linqCollection.singleOrDefault();
		
		// Assert
		assertNull(result);
	}
	
	@Test
	public void testSingleOrDefault_intStringPairCollection_WithPredicate() throws LinqException {
		IntStringPair expected = new IntStringPair(4, "Four");
		
		// Prepare
		Collection<IntStringPair> collection = CollectionsHelper.getIntStringPairCollection();
		LinqCollection<IntStringPair> linqCollection = LinqCollection.Get(collection);
		
		Predicate<IntStringPair> p = DelegatesHelper.getIntStringPairIsEqualPredicate(4);
		
		// Act
		IntStringPair result = linqCollection.singleOrDefault(p);
		
		// Assert
		assertEquals(expected, result);
	}
	
	@Test
	public void testSingleOrDefault_intCollection_emptyCollection_WithPredicate() throws LinqException {
		// Prepare
		Collection<Integer> collection = CollectionsHelper.getIntCollection();
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		Predicate<Integer> p = DelegatesHelper.getIsEqualPredicate(11);
		
		// Act
		Integer result = linqCollection.singleOrDefault(p);
		
		// Assert
		assertNull(result);
	}
	
	@Test (expected=LinqException.class)
	public void testSingleOrDefault_intCollection_multipleResults_WithPredicate() throws LinqException {
		// Prepare
		Collection<Integer> collection = CollectionsHelper.getIntCollection();
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		Predicate<Integer> p = DelegatesHelper.getIsLessThanPredicate(5);
		
		// Act
		linqCollection.singleOrDefault(p);
	}
	
}
