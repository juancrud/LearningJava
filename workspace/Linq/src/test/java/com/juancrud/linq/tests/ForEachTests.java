package com.juancrud.linq.tests;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

import com.juancrud.linq.LinqCollection;
import com.juancrud.linq.delegates.Action;
import com.juancrud.linq.tests.classes.IntStringPair;
import com.juancrud.linq.tests.helpers.CollectionsHelper;
import com.juancrud.linq.tests.helpers.DelegatesHelper;

public class ForEachTests {
	
	@Test
	public void testForEach_stringCollection() {
		String expected = "";
		Collection<String> expectedCollection = CollectionsHelper.getStringCollection();
		for(String s : expectedCollection) {
			expected += s;
		}
		
		// Prepare
		Collection<String> collection = CollectionsHelper.getStringCollection();
		LinqCollection<String> linqCollection = LinqCollection.Get(collection);
		
		StringBuilder stringBuilder = new StringBuilder();
		Action<String> a = DelegatesHelper.getConcatStringAction(stringBuilder);
		
		// Act
		linqCollection.forEach(a);
		
		// Assert
		assertEquals(expected, stringBuilder.toString());
	}
	
	@Test
	public void testForEach_intCollection() {
		int expected = 0;
		for(int i = 1; i <= 10; i++) {
			expected += i;
		}
		
		// Prepare
		Collection<Integer> collection = CollectionsHelper.getIntCollection();
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		IntStringPair intStringPair = new IntStringPair(0, "");
		Action<Integer> a = DelegatesHelper.getSumAction(intStringPair);
		
		// Act
		linqCollection.forEach(a);
		
		// Assert
		assertEquals(expected, intStringPair.getIntValue());
	}

}
