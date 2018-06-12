package com.juancrud.linq.tests.classes;

import java.util.Collection;

public class PrimitiveCollectionPair<T> {
	
	private T primitive;
	private Collection<T> collection;
	
	public PrimitiveCollectionPair(T primitive, Collection<T> collection) {
		this.setPrimitive(primitive);
		this.setCollection(collection);
	}

	public T getPrimitive() {
		return primitive;
	}

	public void setPrimitive(T primitive) {
		this.primitive = primitive;
	}

	public Collection<T> getCollection() {
		return collection;
	}

	public void setCollection(Collection<T> collection) {
		this.collection = collection;
	}

}
