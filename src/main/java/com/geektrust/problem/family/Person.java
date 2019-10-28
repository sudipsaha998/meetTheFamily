package com.geektrust.problem.family;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.geektrust.problem.constants.FamilyConstants.Gender;
import com.geektrust.problem.constants.FamilyConstants.RelationshipType;
import com.geektrust.problem.exception.ChildAdditionException;

public class Person {

	private final String name;
	private final Gender gender;
	private Map<RelationshipType, Relationship> relationshipMap;
	
	public Person(String name, String gender) {
		this.name = name;
		this.gender = Gender.getGenderByStr(gender);
		this.relationshipMap = new HashMap<RelationshipType, Relationship>();
	}

	public Gender getGender() {
		return gender;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (gender != other.gender)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	

	public void addRelationship(RelationshipType type, Person person) {
		Relationship relation = getRelationship(type);
		relation.addRelatedPerson(person);
		relationshipMap.put(type, relation);
	}
	
	private Relationship getRelationship(RelationshipType type) {
		
		if (relationshipMap.containsKey(type)) {
			return relationshipMap.get(type);
		}
		else
			return new Relationship(type);
	}
	
	private void addParents(List<Person> parents) {
		for(Person person: parents) {
			addRelationship(RelationshipType.PARENT, person);	
		}
	}

	public void addChild(Person person) throws ChildAdditionException {
		if(person != null) {
			if(Relative.checkIfFemale(this)) {
				List<Person> parentsOfChild = getPartner();
				parentsOfChild.add(this);
				person.addParents(parentsOfChild);
				addRelationship(RelationshipType.CHILD, person);
			}
			else {
				throw new ChildAdditionException();
			}
		}
	}

	public List<Person> getPartner() {
		Relationship relationship = getRelationship(RelationshipType.PARTNER);
		List<Person> partnerList = relationship.getRelatedPerson();
		partnerList.remove(this);
		return partnerList;
	}

	public List<Person> getParents() {
		Relationship relationship = getRelationship(RelationshipType.PARENT);
		return relationship.getRelatedPerson();
	}

	public List<Person> getChildren() {
		Relationship relationship = getRelationship(RelationshipType.CHILD);
		return relationship.getRelatedPerson();
	}

}
