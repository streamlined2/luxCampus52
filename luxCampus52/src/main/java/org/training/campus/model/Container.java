package org.training.campus.model;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import org.training.campus.domain.Entity;

public class Container<K extends Comparable<K>,E extends Entity<K>> implements Iterable<E> {
	public static final int INITIAL_CAPACITY=10;
	private Object[] data;
	private int size;

	public Container() {
		this(INITIAL_CAPACITY);
	}
	
	public Container(int capacity) {
		if(capacity<=0) throw new IllegalArgumentException("initial capacity should be positive value");
		data=new Object[capacity];
		size=0;
	}
	
	public int getSize() {
		return size;
	}
	
	public int getCapacity() {
		return data.length;
	}
	
	private int getNewCapacity(int requestedCapacity) {
		return requestedCapacity*2;
	}
	
	private void ensureCapacity(int requestedCapacity) {
		if(requestedCapacity>data.length) {
			allocateCopyData(getNewCapacity(requestedCapacity));
		}
	}
	
	private void shrinkCapacity(int requestedCapacity) {
		if(getNewCapacity(requestedCapacity)<data.length) {
			allocateCopyData(requestedCapacity);			
		}
	}

	private void allocateCopyData(int newCapacity) {
		Object[] newData=new Object[Math.max(size, newCapacity)];
		System.arraycopy(data, 0, newData, 0, size);
		data=newData;
	}
	
	public int indexOf(E e) {
		return indexOf(e.getId());
	}
	
	public int indexOf(K id) {
		int start = 0;
		int finish = size-1;
		while(start<=finish) {
			final int index = (start+finish)/2;
			if(((E)data[index]).getId().compareTo(id)<0) {
				start=index+1;
			}else if(((E)data[index]).getId().compareTo(id)>0) {
				finish=index-1;
			}else {
				return index;
			}
		}
		return -(start+1);
	}
	
	public Stream<E> stream(){
		final Builder<E> b=Stream.builder();
		for(int k=0;k<size;k++) {
			b.accept((E)data[k]);
		}
		return b.build();
	}
	
	public Optional<E> getById(K id) {
		final int index=indexOf(id);
		if(index>=0) return Optional.ofNullable((E)data[index]); 
		return Optional.empty();
	}

	public void add(E e) {
		final int index=indexOf(e);
		if(index>=0) throw new IllegalStateException("can't add element that already exists");
		ensureCapacity(size+1);
		final int placeAt=-index-1;
		System.arraycopy(data, placeAt, data, placeAt+1, size-placeAt);
		data[placeAt]=e;
		size++;
	}
	
	public E remove(K id) {
		final int removeAt=indexOf(id);
		if(removeAt<0) throw new NoSuchElementException("no element to remove");
		final E toBeRemoved=(E)data[removeAt];
		System.arraycopy(data, removeAt+1, data, removeAt, size-1-removeAt);
		size--;
		shrinkCapacity(size);
		return toBeRemoved;		
	}
	
	public E remove(E e) {
		return remove(e.getId());
	}
	
	public E update(E e) {
		final int updateAt=indexOf(e);
		if(updateAt<0) throw new NoSuchElementException("no element to update");
		final E toBeUpdated=(E)data[updateAt];
		data[updateAt]=e;
		return toBeUpdated;
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			private int index=0;

			@Override
			public boolean hasNext() {
				return index<size;
			}

			@Override
			public E next() {
				if(index>=size) throw new NoSuchElementException("check if there are elements left before advancing");
				return (E)(data[index++]);
			}
		};
	}
	
	@Override
	public String toString() {
		final var b=new StringBuilder();
		for(E e:this) {
			b.append(e.toString()).append('\n');
		}
		return b.toString();
	}
	
}
