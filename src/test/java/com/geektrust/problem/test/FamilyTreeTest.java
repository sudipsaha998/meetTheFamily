
package com.geektrust.problem.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.geektrust.problem.exception.ChildAdditionException;
import com.geektrust.problem.exception.PersonNotFoundException;
import com.geektrust.problem.exception.RelationNotFoundException;
import com.geektrust.problem.family.Family;
import com.geektrust.problem.family.Person;
import com.geektrust.problem.family.Relative;
import com.geektrust.problem.service.FamilyService;
 
public class FamilyTreeTest {
 
	private static Family family;
	private static Map<String, Person> familyMembers;
	
 @BeforeClass 
 public static void init(){
	 family = new Family(); 
	 }

  
  @Test(expected = ChildAdditionException.class)
  public void childShouldNotGetAddedIfThePersonIsMale() throws Exception {  
	  familyMembers = family.getFamilyMembers();
	  Person person = family.findFamilyMember("Chit");
		Person child = new Person("Srutak", "Male");
		person.addChild(child);
		familyMembers.put("Srutak", child);
	  }
  
  @Test
  public void getDaughterOfAMother() {
	  String[] inputPhrase = "GET_RELATIONSHIP Lika Daughter".split(" ");
	  FamilyService familyService = new FamilyService();
	  List<Person> expectedDaughterList = Arrays.asList(new Person[] {new Person("Vila", "Female"), new Person("Chika", "Female")});
	  List<Person> daughterList = familyService.findRelatedPerson(inputPhrase, family);
	  Assert.assertEquals(expectedDaughterList, daughterList);
	  }
  
  @Test
  public void getSonOfAFather() {
	  String[] inputPhrase = "GET_RELATIONSHIP Vyan Son".split(" ");
	  FamilyService familyService = new FamilyService();
	  List<Person> expectedSonList = Arrays.asList(new Person[] {new Person("Asva", "Male"), new Person("Vyas", "Male")});
	  List<Person> sonList = familyService.findRelatedPerson(inputPhrase, family);
	  Assert.assertEquals(expectedSonList, sonList);
	  }
  
  @Test
  public void getBrotherInLawOfAPerson() {
	  String[] inputPhrase = "GET_RELATIONSHIP Jaya Brother-in-law".split(" ");
	  FamilyService familyService = new FamilyService();
	  List<Person> expectedBrotherInLawList = Arrays.asList(new Person[] {new Person("Vritha", "Male")});
	  List<Person> BrotherInLawList = familyService.findRelatedPerson(inputPhrase, family);
	  Assert.assertEquals(expectedBrotherInLawList, BrotherInLawList);
	  }
  
  @Test(expected = PersonNotFoundException.class)
  public void expectExceptionIfPersonNotFound() throws Exception {
	  family.findFamilyMember("Pjali");
	  }
  
  @Test(expected = RelationNotFoundException.class)
  public void expectExceptionIfRelationNotFound() throws Exception {
	  List<Person> relatedPersons = new ArrayList<Person>();
	  String[] inputPhrase = "GET_RELATIONSHIP Dritha Brother-in-law".split(" ");
	  FamilyService familyService = new FamilyService();
	  Person person = family.findFamilyMember("Dritha");
	  relatedPersons = Relative.getBrotherInLaw(person);
	  }
  
  
  }
 