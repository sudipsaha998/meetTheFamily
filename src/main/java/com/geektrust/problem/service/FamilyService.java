package com.geektrust.problem.service;

import static com.geektrust.problem.constants.FamilyConstants.ADD_CHILD;
import static com.geektrust.problem.constants.FamilyConstants.CHILD_ADDITION_FAILED;
import static com.geektrust.problem.constants.FamilyConstants.CHILD_ADDITION_SUCCEEDED;
import static com.geektrust.problem.constants.FamilyConstants.GET_RELATIONSHIP;
import static com.geektrust.problem.constants.FamilyConstants.PERSON_NOT_FOUND;
import static com.geektrust.problem.constants.FamilyConstants.NONE;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.geektrust.problem.constants.FamilyConstants.ExtendedRelation;
import com.geektrust.problem.exception.ChildAdditionException;
import com.geektrust.problem.exception.PersonNotFoundException;
import com.geektrust.problem.exception.RelationNotFoundException;
import com.geektrust.problem.family.Family;
import com.geektrust.problem.family.Person;
import com.geektrust.problem.family.Relative;

public class FamilyService {

	// Family family;
	Map<String, Person> familyMembers;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Family family = new Family();
		FamilyService familyService = new FamilyService();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(args[0]));
			String line = br.readLine();
			while (line != null) {
				familyService.processInput(line, family);
				line = br.readLine();
			}
		} catch (IOException e) {

		}
	}

	public void processInput(String line, Family family) {
		String[] inputPhrase = line.split(" ");
		familyMembers = family.getFamilyMembers();
		if (ADD_CHILD.equals(inputPhrase[0])) {
			familyMembers = addChildToFamily(inputPhrase, family);
			family.setFamilyMembers(familyMembers);
		}
		if (GET_RELATIONSHIP.equals(inputPhrase[0])) {
			for(Person p : findRelatedPerson(inputPhrase, family)) {
				System.out.print(p.getName()+" ");
			}
			System.out.println();
		}
	}

	@SuppressWarnings("finally")
	public Map<String, Person> addChildToFamily(String[] input, Family family) {
		Person person;
		try {
			person = family.findFamilyMember(input[1]);
			Person child = new Person(input[2], input[3]);
			person.addChild(child);
			familyMembers.put(input[2], child);
			System.out.println(CHILD_ADDITION_SUCCEEDED);
		} catch (PersonNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println(PERSON_NOT_FOUND);
		} catch (ChildAdditionException e) {
			// TODO Auto-generated catch block
			System.out.println(CHILD_ADDITION_FAILED);
		} finally {
			return familyMembers;
		}
	}

	@SuppressWarnings("finally")
	public List<Person> findRelatedPerson(String[] input, Family family) {
		Person person;
		Relative relative = new Relative();
		List<Person> relatedPersons = new ArrayList<Person>();
		try {
			person = family.findFamilyMember(input[1]);

			switch (ExtendedRelation.getExtendedRelationByStr(input[2])) {
			case PATERNAL_UNCLE:
				relatedPersons = relative.getPaternalUncle(person);
				break;
			case MATERNAL_UNCLE:
				relatedPersons = relative.getMaternalUncle(person);
				break;
			case PATERNAL_AUNT:
				relatedPersons = relative.getPaternalAunt(person);
				break;
			case MATERNAL_AUNT:
				relatedPersons = relative.getMaternalAunt(person);
				break;
			case SISTER_IN_LAW:
				relatedPersons = relative.getSisterInLaw(person);
				break;
			case BROTHER_IN_LAW:
				relatedPersons = relative.getBrotherInLaw(person);
				break;
			case SON:
				relatedPersons = relative.getSon(person);
				break;
			case DAUGHTER:
				relatedPersons = relative.getDaughter(person);
				break;
			case SIBLINGS:
				relatedPersons = relative.getSiblings(person);
				break;
			default:
				break;

			}

		} catch (PersonNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.print(PERSON_NOT_FOUND);
		}catch(RelationNotFoundException e){
			System.out.print(NONE);
		}
		finally {
			return relatedPersons;
		}
	}

}
