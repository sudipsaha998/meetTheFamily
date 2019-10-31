package com.geektrust.problem.family;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.geektrust.problem.exception.RelationNotFoundException;

public class Relative {

	public static boolean checkIfFemale(Person person) {
		if (person.getGender().name().equals("FEMALE"))
			return true;
		else
			return false;
	}

	public Person getMother(Person person) {
		for (Person p : person.getParents()) {
			if (checkIfFemale(p)) {
				return p;
			}
		}
		return null;
	}

	public Person getFather(Person person) {
		for (Person p : person.getParents()) {
			if (!checkIfFemale(p)) {
				return p;
			}
		}
		return null;
	}

	public List<Person> getSiblings(Person person) throws RelationNotFoundException {
		Person mother = getMother(person);

		if (mother == null)
			return new ArrayList<Person>();

		List<Person> siblings = new ArrayList<Person>();

		mother.getChildren().forEach((p) -> {
			if (!p.equals(person))
				siblings.add(p);
		});

		if (siblings.size() == 0) {
			throw new RelationNotFoundException("relation do not exist");
		}
		return siblings;
	}

	public List<Person> getBrothers(Person person) {
		List<Person> brothers = new ArrayList<Person>();

		try {
			getSiblings(person).forEach((p) -> {
				if (!checkIfFemale(p)) {
					brothers.add(p);
				}
			});
		} catch (RelationNotFoundException e) {
			e.printStackTrace();
		}

		return brothers;
	}

	public List<Person> getSisters(Person person) {
		List<Person> sisters = new ArrayList<Person>();

		try {
			getSiblings(person).forEach((p) -> {
				if (checkIfFemale(p)) {
					sisters.add(p);
				}
			});
		} catch (RelationNotFoundException e) {
			e.printStackTrace();
		}

		return sisters;
	}

	public List<Person> getSon(Person person) throws RelationNotFoundException {
		List<Person> children;
		List<Person> sonList = new ArrayList<Person>();

		if (checkIfFemale(person)) {
			children = person.getChildren();
		} else {
			children = person.getPartner().get(0).getChildren();
		}
		children.forEach((p) -> {
			if (!checkIfFemale(p)) {
				sonList.add(p);
			}
		});

		if (sonList.size() == 0) {
			throw new RelationNotFoundException("relation do not exist");
		}

		return sonList;
	}

	public List<Person> getDaughter(Person person) throws RelationNotFoundException {
		List<Person> children;
		List<Person> daughterList = new ArrayList<Person>();

		if (checkIfFemale(person)) {
			children = person.getChildren();
		} else {
			children = person.getPartner().get(0).getChildren();
		}

		children.forEach((p) -> {
			if (checkIfFemale(p)) {
				daughterList.add(p);
			}
		});
		if (daughterList.size() == 0) {
			throw new RelationNotFoundException("relation do not exist");
		}

		return daughterList;

	}

	public List<Person> getBrotherInLaw(Person person) throws RelationNotFoundException {
		List<Person> spouse = person.getPartner();
		List<Person> brotherInLaws = new ArrayList<Person>();
		if (spouse.size() > 0) {
			brotherInLaws = getBrothers(spouse.get(0));
		}
		for (Person p : getSisters(person)) {
			brotherInLaws.addAll(p.getPartner());
		}
		if (brotherInLaws.size() == 0) {
			throw new RelationNotFoundException("relation do not exist");
		}
		return brotherInLaws;
	}

	public List<Person> getSisterInLaw(Person person) throws RelationNotFoundException {
		List<Person> spouse = person.getPartner();
		List<Person> sisterInLaws = new ArrayList<Person>();
		if (spouse.size() > 0) {
			sisterInLaws = getSisters(spouse.get(0));
		}
		for (Person p : getBrothers(person)) {
			sisterInLaws.addAll(p.getPartner());
		}
		if (sisterInLaws.size() == 0) {
			throw new RelationNotFoundException("relation do not exist");
		}

		return sisterInLaws;

	}

	public List<Person> getMaternalAunt(Person person) throws RelationNotFoundException {
		if (getMother(person) == null)
			throw new RelationNotFoundException("relation do not exist");
		
		List<Person> maternalAunt = getSisters(getMother(person));
		if (maternalAunt.size() == 0)
			throw new RelationNotFoundException("relation do not exist");
		return maternalAunt;
	}

	public List<Person> getPaternalAunt(Person person) throws RelationNotFoundException {
		if (getFather(person) == null)
			throw new RelationNotFoundException("relation do not exist");

		List<Person> paternalAunt = getSisters(getFather(person));
		if (paternalAunt.size() == 0)
			throw new RelationNotFoundException("relation do not exist");
		return paternalAunt;
	}

	public List<Person> getMaternalUncle(Person person) throws RelationNotFoundException {
		if (getMother(person) == null)
			throw new RelationNotFoundException("relation do not exist");
		
		List<Person> maternalUncles = getBrothers(getMother(person));
		if (maternalUncles.size() == 0)
			throw new RelationNotFoundException("relation do not exist");
		return maternalUncles;
	}

	public List<Person> getPaternalUncle(Person person) throws RelationNotFoundException {
		if (getFather(person) == null)
			throw new RelationNotFoundException("relation do not exist");

		List<Person> paternalUncles = getBrothers(getFather(person));
		if (paternalUncles.size() == 0)
			throw new RelationNotFoundException("relation do not exist");
		return paternalUncles;
	}
}
