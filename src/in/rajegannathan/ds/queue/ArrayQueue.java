package in.rajegannathan.ds.queue;

import java.util.Iterator;

public class ArrayQueue<E> extends MyAbstractQueue<E> {

	private int initialCapacity = 16;
	private Object[] elementData;
	private int headPos = 0, tailPos = 0;

	public ArrayQueue() {
		this(16);
	}

	public ArrayQueue(int initialCapacity) {
		this.initialCapacity = initialCapacity;
		elementData = new Object[initialCapacity];
	}

	@Override
	public int size() {
		return (tailPos - headPos);
	}

	@Override
	public boolean isEmpty() {
		return ((tailPos - headPos) == 0);
	}

	@Override
	public boolean contains(Object o) {
		return indexOf(o) >= 0;
	}

	private int indexOf(Object o) {
		if (o == null) {
			for (int pos = headPos; pos < tailPos; pos++) {
				if (elementData[pos] == null) {
					return pos;
				}

			}
		} else {
			for (int pos = headPos; pos < tailPos; pos++) {
				if (elementData[pos].equals(o)) {
					return pos - 1;
				}
			}
		}
		return -1;
	}

	@Override
	public Iterator<E> iterator() {
		return new Iter();
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public class Iter implements Iterator<E> {

		@Override
		public boolean hasNext() {
			return false;
		}

		@Override
		public E next() {
			
			return null;
		}

		@Override
		public void remove() {
			

		}

	}
}
