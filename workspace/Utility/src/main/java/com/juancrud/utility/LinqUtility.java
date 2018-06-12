package com.juancrud.utility;

import java.util.ArrayList;
import java.util.Collection;

import com.juancrud.utility.linq.Action;

public class LinqUtility {
	
	public static <X> void forEach(Collection<X> collection, Action<X> forEachDelegate) {
		for(X x : collection) {
			forEachDelegate.execute(x);
		}
	}
	
	public static <X, Y> Collection<Y> select(Collection<X> collection, Function<X, Y> selectDelegate) {
		Collection<Y> result = new ArrayList<Y>();
		for(X x : collection) {
			Y y = selectDelegate.execute(x);
			result.add(y);
		}
		return result;
	}
	
	public static <X> Collection<X> where(Collection<X> collection, Predicate<X> whereDelegate) {
		Collection<X> result = new ArrayList<X>();
		for(X x : collection) {
			if(whereDelegate.execute(x)){
				result.add(x);
			}
		}
		return result;
	}
	
}
