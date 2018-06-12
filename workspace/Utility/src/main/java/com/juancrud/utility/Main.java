package com.juancrud.utility;

import java.util.ArrayList;
import java.util.Collection;

import com.juancrud.utility.linq.Action;

public class Main {
	
	public static void main(String[] args){
		// Test Select
		testSelect1();
		testSelect2();
		
		// Test SelectMany
		testSelectMany1();
		testSelectMany2();
		
		// Test Distinct
		testDistinct1();
		testDistinct2();
		
		// Test Where
		testWhere1();
		testWhere2();
		
		// Test All
		testAll1();
		testAll2();
		
		// Test Any
		testAny1();
		testAny2();
		testAny3();
		
		// Test AreEqual
		testAreEqual1();
		testAreEqual2();
		testAreEqual3();
		testAreEqual4();
		
		// Test Skip & Take
		testSkipTake1();
		testSkipTake2();
		testSkipTake3();
		
		// Test SkipWhile & TakeWhile
		testSkipWhileTakeWhile1();
		testSkipWhileTakeWhile2();
		testSkipWhileTakeWhile3();
		
		System.out.println("Finished");
	}
	
	private static void testSelect1() {
		Collection<Integer> collection = intCollection();
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		Function<Integer, Integer> f = new Function<Integer, Integer>() {
			public Integer execute(Integer value) {
				return value + 1;
			}
		};
		
		print(linqCollection.select(f));
	}
	
	private static void testSelect2() {
		Collection<TestClass> collection = testClassCollection();
		LinqCollection<TestClass> linqCollection = LinqCollection.Get(collection);
		
		Function<TestClass, String> f = new Function<TestClass, String>() {
			public String execute(TestClass value) {
				return value.stringField;
			}
		};
		
		print(linqCollection.select(f));
	}
	
	private static void testSelectMany1() {
		Collection<TestClass> collection = testClassCollection();
		LinqCollection<TestClass> linqCollection = LinqCollection.Get(collection);
		
		Function<TestClass, Collection<Integer>> f = new Function<TestClass, Collection<Integer>>() {
			public Collection<Integer> execute(TestClass value) {
				return value.intCollectionField;
			}
		};
		
		print(linqCollection.selectMany(f));
	}
	
	private static void testSelectMany2() {
		Collection<Integer> collection = intCollection();
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		Function<Integer, Collection<String>> f1 = new Function<Integer, Collection<String>>() {
			public Collection<String> execute(Integer value) {
				return stringCollection();
			}
		};
		
		Function2<Integer, String, TestClass> f2 = new Function2<Integer, String, Main.TestClass>() {
			public TestClass execute(Integer value1, String value2) {
				return new TestClass(value1, value2);
			}
		};
		
		print2(linqCollection.selectMany(f1, f2));
	}
	
	private static void testDistinct1() {
		Collection<String> collection1 = stringCollection();
		Collection<String> collection2 = stringCollection();
		LinqCollection<String> linqCollection = LinqCollection.Get(collection1);
		linqCollection.addAll(LinqCollection.Get(collection2));
		
		print(linqCollection.distinct());
	}
	
	private static void testDistinct2() {
		Collection<TestClass> collection1 = testClassCollection();
		Collection<TestClass> collection2 = testClassCollection();
		LinqCollection<TestClass> linqCollection = LinqCollection.Get(collection1);
		linqCollection.addAll(LinqCollection.Get(collection2));
		
		print2(linqCollection.distinct());
	}
	
	private static void testWhere1() {
		Collection<String> collection1 = stringCollection();
		Collection<String> collection2 = stringCollection();
		LinqCollection<String> linqCollection = LinqCollection.Get(collection1);
		linqCollection.addAll(LinqCollection.Get(collection2));
		
		Predicate<String> p = new Predicate<String>() {
			public boolean execute(String value) {
				return !value.equals("3");
			}
		};
		
		print(linqCollection.where(p));
	}
	
	private static void testWhere2() {
		Collection<TestClass> collection1 = testClassCollection();
		Collection<TestClass> collection2 = testClassCollection();
		LinqCollection<TestClass> linqCollection = LinqCollection.Get(collection1);
		linqCollection.addAll(LinqCollection.Get(collection2));
		
		Predicate<TestClass> p = new Predicate<TestClass>() {
			public boolean execute(TestClass value) {
				return value.intField == 3;
			}
		};
		
		print2(linqCollection.where(p));
	}
	
	private static void testAll1() {
		Collection<Integer> collection = intCollection();
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		Predicate<Integer> p = new Predicate<Integer>() {
			public boolean execute(Integer value) {
				return value > 5;
			}
		};
		
		System.out.println(linqCollection.all(p));
	}
	
	private static void testAll2() {
		Collection<TestClass> collection = testClassCollection();
		LinqCollection<TestClass> linqCollection = LinqCollection.Get(collection);
		
		Predicate<TestClass> p = new Predicate<TestClass>() {
			public boolean execute(TestClass value) {
				return value.stringCollectionField.size() > 0;
			}
		};
		
		System.out.println(linqCollection.all(p));
	}
	
	private static void testAny1() {
		Collection<Integer> collection = intCollection();
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		Predicate<Integer> p = new Predicate<Integer>() {
			public boolean execute(Integer value) {
				return value > 5;
			}
		};
		
		System.out.println(linqCollection.any(p));
	}
	
	private static void testAny2() {
		Collection<TestClass> collection = testClassCollection();
		LinqCollection<TestClass> linqCollection = LinqCollection.Get(collection);
		
		Predicate<TestClass> p = new Predicate<TestClass>() {
			public boolean execute(TestClass value) {
				return value.stringCollectionField.size() > 0;
			}
		};
		
		System.out.println(linqCollection.any(p));
	}
	
	private static void testAny3() {
		Collection<Integer> collection = intCollection();
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		System.out.println(linqCollection.any());
	}
	
	private static void testAreEqual1() {
		Collection<Integer> collection1 = intCollection();
		Collection<Integer> collection2 = intCollection();
		collection2.remove(4);
		LinqCollection<Integer> linqCollection1 = LinqCollection.Get(collection1);
		LinqCollection<Integer> linqCollection2 = LinqCollection.Get(collection2);
		
		try {
			System.out.println(linqCollection1.areEqual(linqCollection2));
		}
		catch(LinqException ex) {
			
		}
	}
	
	private static void testAreEqual2() {
		Collection<String> collection1 = stringCollection();
		Collection<String> collection2 = stringCollection();
		collection2.clear();
		LinqCollection<String> linqCollection1 = LinqCollection.Get(collection1);
		LinqCollection<String> linqCollection2 = LinqCollection.Get(collection2);
		
		try {
			System.out.println(linqCollection1.areEqual(linqCollection2));
		}
		catch(LinqException ex) {
			
		}
	}
	
	private static void testAreEqual3() {
		Collection<TestClass> collection1 = testClassCollection();
		Collection<TestClass> collection2 = testClassCollection();
		LinqCollection<TestClass> linqCollection1 = LinqCollection.Get(collection1);
		LinqCollection<TestClass> linqCollection2 = LinqCollection.Get(collection2);
		
		try {
			System.out.println(linqCollection1.areEqual(linqCollection2));
		}
		catch(LinqException ex) {
			
		}
	}
	
	private static void testAreEqual4() {
		Collection<String> collection1 = stringCollection();
		Collection<String> collection2 = stringCollection();
		LinqCollection<String> linqCollection1 = LinqCollection.Get(collection1);
		LinqCollection<String> linqCollection2 = LinqCollection.Get(collection2);
		
		try {
			System.out.println(linqCollection1.areEqual(linqCollection2));
		}
		catch(LinqException ex) {
			
		}
	}
	
	private static void testSkipTake1() {
		Collection<Integer> collection = intCollection();
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		try {
			print(linqCollection.skip(1).take(3));
		}
		catch(LinqException ex) {
			
		}
	}
	
	private static void testSkipTake2() {
		Collection<Integer> collection = intCollection();
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		try {
			print(linqCollection.skip(10).take(3));
		}
		catch(LinqException ex) {
			System.out.println("Exception is Ok");
		}
	}
	
	private static void testSkipTake3() {
		Collection<Integer> collection = intCollection();
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		try {
			print(linqCollection.skip(1).take(5));
		}
		catch(LinqException ex) {
			System.out.println("Exception is Ok");
		}
	}
	
	private static void testSkipWhileTakeWhile1() {
		Collection<Integer> collection = intCollection();
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		Predicate<Integer> p = new Predicate<Integer>() {
			public boolean execute(Integer value) {
				return value < 2;
			}
		};
		
		Predicate<Integer> p2 = new Predicate<Integer>() {
			public boolean execute(Integer value) {
				return value < 5;
			}
		};
		
		print(linqCollection.skipWhile(p).takeWhile(p2));
	}
	
	private static void testSkipWhileTakeWhile2() {
		Collection<Integer> collection = intCollection();
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		Predicate<Integer> p = new Predicate<Integer>() {
			public boolean execute(Integer value) {
				return value < 10;
			}
		};
		
		Predicate<Integer> p2 = new Predicate<Integer>() {
			public boolean execute(Integer value) {
				return value < 5;
			}
		};
		
		print(linqCollection.skipWhile(p).takeWhile(p2));
	}
	
	private static void testSkipWhileTakeWhile3() {
		Collection<Integer> collection = intCollection();
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		Predicate<Integer> p = new Predicate<Integer>() {
			public boolean execute(Integer value) {
				return value < 2;
			}
		};
		
		Predicate<Integer> p2 = new Predicate<Integer>() {
			public boolean execute(Integer value) {
				return value < 0;
			}
		};
		
		print(linqCollection.skipWhile(p).takeWhile(p2));
	}
	
 	private static void print(LinqCollection<? extends Object> collection) {
		String text = "";
		for(Object item : collection) {
			text += item + " ";
		}
		System.out.println(text);
	}
	
 	private static void print2(LinqCollection<TestClass> collection) {
 		Action<TestClass> a = new Action<TestClass>() {
			public void execute(TestClass value) {
				String text = "(%s,%s) ";
				System.out.print(String.format(text, value.intField, value.stringField));
			}
		};
		
		collection.forEach(a);
		System.out.print("\n");
 	}
 	
	private static Collection<String> stringCollection() {
		Collection<String> collection = new ArrayList<String>();
		collection.add("1");
		collection.add("2");
		collection.add("3");
		collection.add("4");
		collection.add("5");
		return collection;
	}
	
	private static Collection<Integer> intCollection() {
		Collection<Integer> collection = new ArrayList<Integer>();
		collection.add(1);
		collection.add(2);
		collection.add(3);
		collection.add(4);
		collection.add(5);
		return collection;
	}
	
	private static Collection<TestClass> testClassCollection() {
		Collection<TestClass> collection = new ArrayList<TestClass>();
		collection.add(new TestClass(1, "1"));
		collection.add(new TestClass(2, "2"));
		collection.add(new TestClass(3, "3"));
		collection.add(new TestClass(4, "4"));
		collection.add(new TestClass(5, "5"));
		return collection;
	}
	
	private static class TestClass {
		public TestClass(int intField, String stringField) {
			this.intField = intField;
			this.stringField = stringField;
			this.intCollectionField = intCollection();
			this.stringCollectionField = stringCollection();
		}
		
		public int intField;
		public Collection<Integer> intCollectionField;
		public String stringField;
		public Collection<String> stringCollectionField;
	}
}
