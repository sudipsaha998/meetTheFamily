package com.geektrust.problem.family;

import java.util.ArrayList;
import java.util.List;

import com.geektrust.problem.constants.FamilyConstants.RelationshipType;

public class Relationship {

	RelationshipType relationType;
	private List<Person> relatedPersons;
	
	
	public Relationship(RelationshipType relationType) {
		this.relationType = relationType;
		this.relatedPersons = new ArrayList<Person>();
	}

	public List<Person> getRelatedPerson() {
		return relatedPersons;
	}
	
	public void addRelatedPerson(Person person) {
		relatedPersons.add(person);
	}

}
