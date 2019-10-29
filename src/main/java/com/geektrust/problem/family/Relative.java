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

		List<Person> siblings = mother.getChildren();
		siblings.remove(person);
		if (siblings.size() == 0) {
			throw new RelationNotFoundException("relation do not exist");
		}
		return siblings;
	}

	public List<Person> getBrothers(Person person) throws RelationNotFoundException {
		List<Person> brothers = getSiblings(person);
		Iterator<Person> itr = brothers.iterator();

		while (itr.hasNext()) {
			if (checkIfFemale(itr.next())) {
				itr.remove();
			}
		}
		if (brothers.size() == 0) {
			throw new RelationNotFoundException("relation do not exist");
		}
		return brothers;
	}

	public List<Person> getSisters(Person person) throws RelationNotFoundException {
		List<Person> sisters = getSiblings(person);
		Iterator<Person> itr = sisters.iterator();

		while (itr.hasNext()) {
			if (!checkIfFemale(itr.next())) {
				itr.remove();
			}
		}
		if (sisters.size() == 0) {
			throw new RelationNotFoundException("relation do not exist");
		}
		return sisters;
	}

	public List<Person> getSon(Person person) throws RelationNotFoundException {
		List<Person> sonList;

		if (checkIfFemale(person)) {
			sonList = person.getChildren();
		} else {
			sonList = person.getPartner().get(0).getChildren();
		}

		Iterator<Person> itr = sonList.iterator();

		while (itr.hasNext()) {
			if (checkIfFemale(itr.next())) {
				itr.remove();
			}
		}
		if (sonList.size() == 0) {
			throw new RelationNotFoundException("relation do not exist");
		}

		return sonList;
	}

	public List<Person> getDaughter(Person person) throws RelationNotFoundException {
		List<Person> daughterList;

		if (checkIfFemale(person)) {
			daughterList = person.getChildren();
		} else {
			daughterList = person.getPartner().get(0).getChildren();
		}
		Iterator<Person> itr = daughterList.iterator();

		while (itr.hasNext()) {
			if (!checkIfFemale(itr.next())) {
				itr.remove();
			}
		}
		if (daughterList.size() == 0) {
			throw new RelationNotFoundException("relation do not exist");
		}

		return daughterList;

	}

	public List<Person> getBrotherInLaw(Person person) throws RelationNotFoundException {
		List<Person> spouse = person.getPartner();
		List<Person> brotherInLaws = new ArrayList<Person>();
		try {
			if (spouse.size() > 0) {
				brotherInLaws = getBrothers(spouse.get(0));
			}
			for (Person p : getSisters(person)) {
				brotherInLaws.addAll(p.getPartner());
			}
		} catch (RelationNotFoundException ex) {
			if (brotherInLaws.size() == 0) {
				throw new RelationNotFoundException("relation do not exist");
			}
		}

		return brotherInLaws;
	}

	public List<Person> getSisterInLaw(Person person) throws RelationNotFoundException {
		List<Person> spouse = person.getPartner();
		List<Person> sisterInLaws = new ArrayList<Person>();
		try {
			if (spouse.size() > 0) {
				sisterInLaws = getSisters(spouse.get(0));
			}
			for (Person p : getBrothers(person)) {
				sisterInLaws.addAll(p.getPartner());
			}
		} catch (RelationNotFoundException ex) {
			if (sisterInLaws.size() == 0) {
				throw new RelationNotFoundException("relation do not exist");
			}
		}

		return sisterInLaws;

	}

	public List<Person> getMaternalAunt(Person person) throws RelationNotFoundException {
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
