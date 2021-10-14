package org.training.campus.domain;

public interface Entity<K extends Comparable<K>> {
	K getId();
}
