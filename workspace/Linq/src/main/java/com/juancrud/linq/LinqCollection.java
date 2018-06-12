package com.juancrud.linq;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

import com.juancrud.linq.delegates.Action;
import com.juancrud.linq.delegates.DoubleFunction;
import com.juancrud.linq.delegates.FloatFunction;
import com.juancrud.linq.delegates.Function;
import com.juancrud.linq.delegates.Function2;
import com.juancrud.linq.delegates.IntegerFunction;
import com.juancrud.linq.delegates.LongFunction;
import com.juancrud.linq.delegates.Predicate;
import com.juancrud.linq.exceptions.LinqException;

@SuppressWarnings("serial")
public class LinqCollection<T> extends ArrayList<T> implements ILinqCollection<T> {
	
	private static final String OutOfRangeMessage = "%s is out of range";
	private static final String NullValueMessage = "%s value is null";
	private static final String ItemNotFoundMessage = "The item was not found";
	private static final String MoreThanOneItemMessage = "There are more than one items";
	private static final String CanNotCreateInstanceMessage = "Can not create an instance of %s";
	
	/* Constructors */
	public LinqCollection() {
		super();
	}
	
	public LinqCollection(Collection<T> collection) {
		super();
		this.addAll(collection);
	}
	
	/* Static methods */
	public static <T> LinqCollection<T> Get(Collection<T> collection) {
		return new LinqCollection<T>(collection);
	}
	
	/* Linq methods */
	public <SelectT> LinqCollection<SelectT> select(Function<T, SelectT> function) {
		LinqCollection<SelectT> result = new LinqCollection<SelectT>();
		for(T item : this) {
			SelectT selectItem = function.execute(item);
			result.add(selectItem);
		}
		return result;
	}

	public <SelectT> LinqCollection<SelectT> selectMany(Function<T, Collection<SelectT>> function) {
		LinqCollection<SelectT> result = new LinqCollection<SelectT>();
		for(T item : this) {
			Collection<SelectT> selectCollection = function.execute(item);
			result.addAll(selectCollection);
		}
		return result;
	}

	public <CollectionT, ResultT> LinqCollection<ResultT> selectMany(Function<T, Collection<CollectionT>> collectionFunction, Function2<T, CollectionT, ResultT> resultFunction) {
		LinqCollection<ResultT> result = new LinqCollection<ResultT>();
		
		for(T item : this) {
			Collection<CollectionT> collection = collectionFunction.execute(item);
			for(CollectionT collectionItem : collection) {
				ResultT value = resultFunction.execute(item, collectionItem);
				result.add(value);
			}
		}
		
		return result;
	}
	
	public LinqCollection<T> distinct() {
		LinqCollection<T> result = new LinqCollection<T>();
		for(T item : this) {
			if(!result.contains(item)) {
				result.add(item);
			}
		}
		return result;
	}

	public LinqCollection<T> where(Predicate<T> predicate) {
		LinqCollection<T> result = new LinqCollection<T>();
		for(T item : this) {
			if(predicate.execute(item)) {
				result.add(item);
			}
		}
		return result;
	}

	public <T2, KeyT, ResultT> LinqCollection<T> join(LinqCollection<T2> collection, String type, Function<T, KeyT> keySelector, Function<T, ResultT> resultSelector) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean all(Predicate<T> predicate) {
		boolean result = true;
		for(T t : this) {
			if(!predicate.execute(t)) {
				result = false;
				break;
			}
		}
		return result;
	}

	public boolean any() {
		return !this.isEmpty();
	}

	public boolean any(Predicate<T> predicate) {
		boolean result = false;
		for(T item : this) {
			if(predicate.execute(item)) {
				result = true;
				break;
			}
		}
		return result;
	}

	public boolean areEqual(LinqCollection<T> collection) throws LinqException {
		if(collection == null) {
			throw new LinqException(String.format(NullValueMessage, "collection"));
		}
		
		if(this.size() != collection.size()) {
			return false;
		}
		
		boolean result = true;
		for(int i = 0; i < this.size(); i++) {
			T item1 = this.get(i);
			T item2 = collection.get(i);
			if(!item1.equals(item2)) {
				result = false;
				break;
			}
		}
		
		return result;
	}
	
	public LinqCollection<T> skip(int number) throws LinqException {
		if(number < 0 || number > this.size()) {
			throw new LinqException(String.format(OutOfRangeMessage, "number")); 
		}
		
		LinqCollection<T> result = new LinqCollection<T>();
		for(int i = number; i < this.size(); i++) {
			T item = this.get(i);
			result.add(item);
		}
		return result;
	}

	public LinqCollection<T> skipWhile(Predicate<T> predicate) {
		LinqCollection<T> result = new LinqCollection<T>();
		boolean skip = true;
		for(T item : this) {
			if(!predicate.execute(item)) {
				skip = false;
			}
			
			if(!skip) {
				result.add(item);
			}
		}
		return result;
	}

	public LinqCollection<T> take(int number) throws LinqException {
		if(number < 0 || number > this.size()) {
			throw new LinqException(String.format(OutOfRangeMessage, "number"));
		}
		
		LinqCollection<T> result = new LinqCollection<T>();
		for(int i = 0; i < number; i++) {
			T item = this.get(i);
			result.add(item);
		}
		return result;
	}

	public LinqCollection<T> takeWhile(Predicate<T> predicate) {
		LinqCollection<T> result = new LinqCollection<T>();
		for(T item : this) {
			if(!predicate.execute(item)) {
				break;
			}
			result.add(item);
		}
		return result;
	}
	
	public <CastT> LinqCollection<CastT> cast(Class<CastT> clazz) {
		LinqCollection<CastT> result = new LinqCollection<CastT>();
		for(T item : this) {
			CastT castedItem = clazz.cast(item);
			result.add(castedItem);
		}
		return result;
	}

	public void forEach(Action<T> action) {
		for(T item : this) {
			action.execute(item);
		}
	}
	
	private T createNewInstance(Class<T> clazz) throws LinqException {
		T result;
		try {
			result = (T)clazz.newInstance();
		}
		catch(Exception ex) {
			throw new LinqException(String.format(CanNotCreateInstanceMessage, clazz.getName()), ex);
		}
		
		return result;
	}	
	
	public T aggregate(Function2<T, T, T> function, Class<T> clazz) throws LinqException {
		// TODO - Fix this method. There is no default(T) in java
		T result = createNewInstance(clazz);
		for(T item : this) {
			result = function.execute(result, item);
		}
		return result;
	}
	
	public <AccumulateT> AccumulateT aggregate(AccumulateT seed, Function2<AccumulateT, T, AccumulateT> function) {
		AccumulateT result = seed;
		for(T item : this) {
			result = function.execute(result, item);
		}
		return result;
	}
	
	public double sum(final DoubleFunction<T> function) {
		Function2<Double, T, Double> func = new Function2<Double, T, Double>() {
			public Double execute(Double value1, T value2) {
				return value1 + function.execute(value2);
			}
		};
		return this.aggregate(0d, func);
	}
	
	public float sum(final FloatFunction<T> function) {
		Function2<Float, T, Float> func = new Function2<Float, T, Float>() {
			public Float execute(Float value1, T value2) {
				return value1 + function.execute(value2);
			}
		};
		return this.aggregate(0f, func);
	}
	
	public long sum(final LongFunction<T> function) {
		Function2<Long, T, Long> func = new Function2<Long, T, Long>() {
			public Long execute(Long value1, T value2) {
				return value1 + function.execute(value2);
			}
		};
		return this.aggregate(0l, func);
	}
	
	public int sum(final IntegerFunction<T> function) {
		Function2<Integer, T, Integer> func = new Function2<Integer, T, Integer>() {
			public Integer execute(Integer value1, T value2) {
				return value1 + function.execute(value2);
			}
		};
		return this.aggregate(0, func);
	}
	
	public double average(DoubleFunction<T> function) {
		int size = this.size();
		return size == 0 ? 0 : this.sum(function) / size;
	}
	
	public float average(FloatFunction<T> function) {
		int size = this.size();
		return size == 0 ? 0 : this.sum(function) / size;
	}
	
	public long average(LongFunction<T> function) {
		int size = this.size();
		return size == 0 ? 0 : this.sum(function) / size;
	}
	
	public int average(IntegerFunction<T> function) {
		int size = this.size();
		return size == 0 ? 0 : this.sum(function) / size;
	}

	public T max(Comparator<T> comparator) {
		T result = null;
		for(T item : this) {
			if(comparator.compare(item, result) > 0) {
				result = item;
			}
		}
		return result;
	}

	public T min(Comparator<T> comparator) {
		T result = null;
		for(T item : this) {
			if(comparator.compare(item, result) < 0) {
				result = item;
			}
		}
		return result;
	}

	public T elementAt(int position) {
		return this.get(position);
	}

	public T elementAtOrDefault(int position) {
		return position < 0 || this.size() <= position ? null : this.get(position);
	}

	public T first() throws LinqException {
		T result;
		try {
			result = this.get(0);
		}
		catch(IndexOutOfBoundsException ex) {
			throw new LinqException(ItemNotFoundMessage, ex);
		}
		
		return result;
	}

	public T first(Predicate<T> predicate) throws LinqException {
		for(T item : this) {
			if(predicate.execute(item)) {
				return item;
			}
		}
		throw new LinqException(ItemNotFoundMessage);
	}

	public T firstOrDefault() {
		return this.any() ? this.get(0) : null;
	}

	public T firstOrDefault(Predicate<T> predicate) {
		T result = null;
		for(T item : this) {
			if(predicate.execute(item)) {
				result = item;
				break;
			}
		}
		return result;
	}
	
	public T last() throws LinqException {
		T result;
		int index = this.size() - 1;
		try {
			result = this.get(index);
		}
		catch(IndexOutOfBoundsException ex) {
			throw new LinqException(ItemNotFoundMessage, ex);
		}
		
		return result;
	}

	public T last(Predicate<T> predicate) throws LinqException {
		T result = null;
		for(T item : this) {
			if(predicate.execute(item)) {
				result = item;
			}
		}
		
		if(result == null) {
			throw new LinqException(ItemNotFoundMessage);
		}
		else {
			return result;
		}
	}

	public T lastOrDefault() {
		int index = this.size() - 1;
		return this.any() ? this.get(index) : null;
	}

	public T lastOrDefault(Predicate<T> predicate) {
		T result = null;
		for(T item : this) {
			if(predicate.execute(item)) {
				result = item;
			}
		}
		return result;
	}

	public T single() throws LinqException {
		if(this.size() == 1) {
			return this.get(0);
		}
		
		throw new LinqException(MoreThanOneItemMessage);
	}

	public T single(Predicate<T> predicate) throws LinqException {
		T result = null;
		for(T item : this) {
			boolean predicateResult = predicate.execute(item); 
			if(predicateResult && result == null) {
				result = item;
			}
			else if(predicateResult && result != null) {
				throw new LinqException(MoreThanOneItemMessage);
			}
		}
		
		if(result == null) {
			throw new LinqException(ItemNotFoundMessage);
		}
		else {
			return result;
		}
	}

	public T singleOrDefault() {
		return this.size() == 1 ? this.get(0) : null;
	}

	public T singleOrDefault(Predicate<T> predicate) throws LinqException {
		T result = null;
		for(T item : this) {
			boolean predicateResult = predicate.execute(item); 
			if(predicateResult && result == null) {
				result = item;
			}
			else if(predicateResult && result != null) {
				throw new LinqException(MoreThanOneItemMessage);
			}
		}
		return result;
	}

	public LinqCollection<T> union(LinqCollection<T> collection) {
		LinqCollection<T> result = new LinqCollection<T>();
		for(T item : this) {
			if(!result.contains(item)) {
				result.add(item);
			}
		}
		for(T item : collection) {
			if(!result.contains(item)) {
				result.add(item);
			}
		}
		return result;
	}

	public LinqCollection<T> intersect(LinqCollection<T> collection) {
		LinqCollection<T> result = new LinqCollection<T>();
		for(T item : this) {
			if(collection.contains(item) && !result.contains(item)) {
				result.add(item);
			}
		}
		for(T item : collection) {
			if(this.contains(item) && !result.contains(item)) {
				result.add(item);
			}
		}
		return result;
	}

	public LinqCollection<T> except(LinqCollection<T> collection) {
		LinqCollection<T> result = new LinqCollection<T>();
		for(T item : this) {
			if(!collection.contains(item) && !result.contains(item)) {
				result.add(item);
			}
		}
		return result;
	}
	
}
