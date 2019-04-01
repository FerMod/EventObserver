package com.fermod.data.serializable;

import java.io.Serializable;
import java.util.function.BiConsumer;

import com.fermod.event.ValueChangeListener;
import com.fermod.observer.ObservedValue;

public class PersonTest implements Serializable {

	private ObservedValue<String> observedName = new ObservedValue<String>();
	private int age; 
	
	public PersonTest(String name, int age) {
		this.observedName.set(name);
		this.age = age;
	}

	public String getName() {
		return observedName.get();	
	}

	public void setName(String name) {
		observedName.set(name);
	}
	
	public void onNameChanged(BiConsumer<String, String> consumer) {
		ValueChangeListener<String> listener = observedName.registerListener((oldValue, newValue) -> consumer.accept(oldValue, newValue));
		observedName.registerListener(listener);
	}

	public int getAge() {
		return age;	
	}

	public void setAge(int age) {
		this.age = age;	
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) {
			return false;
		}

		if (!PersonTest.class.isAssignableFrom(obj.getClass())) {
			return false;
		}

		final PersonTest other = (PersonTest) obj;
		if ((this.observedName == null) ? (other.observedName != null) : !this.observedName.equals(other.observedName)) {
			return false;
		}

		if (this.age != other.age) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 53 * hash + (this.observedName != null ? this.observedName.hashCode() : 0);
		hash = 53 * hash + this.age;
		return hash;
	}

	private static final long serialVersionUID = -8858049414160214232L;

}
