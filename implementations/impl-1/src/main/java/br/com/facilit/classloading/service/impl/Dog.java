package br.com.facilit.classloading.service.impl;

import br.com.facilit.classloading.service.Animal;

public class Dog implements Animal {

	private int id;

	@Override
	public String getBreed() {

		return "Chabiguara";
	}

	@Override
	public String getName() {

		return "Bosley";
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = (int) id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
