package br.com.facilit.classloading.service.impl;

import br.com.facilit.classloading.service.Animal;

public class Dog implements Animal {

	private long id;

	@Override
	public String getBreed() {

		return "Vira-lata";
	}

	@Override
	public String getName() {

		return "Totó";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Animal other = (Animal) obj;
		if (id != other.getId())
			return false;
		return true;
	}

}
