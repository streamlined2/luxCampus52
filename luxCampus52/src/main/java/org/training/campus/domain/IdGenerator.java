package org.training.campus.domain;

public class IdGenerator {
	private long nextId = Long.MIN_VALUE;

	public long nextId() {
		if (nextId == Long.MAX_VALUE)
			throw new IllegalStateException("generator exhausted");
		return nextId++;
	}
}
