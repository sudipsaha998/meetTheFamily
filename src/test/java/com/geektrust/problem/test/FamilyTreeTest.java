
package com.geektrust.problem.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.geektrust.problem.constants.FamilyConstants.RelationshipType;
import com.geektrust.problem.exception.ChildAdditionException;
import com.geektrust.problem.exception.PersonNotFoundException;
import com.geektrust.problem.exception.RelationNotFoundException;
import com.geektrust.problem.family.Family;
import com.geektrust.problem.family.Person;
import com.geektrust.problem.family.Relationship;
import com.geektrust.problem.family.Relative;
import com.geektrust.problem.service.FamilyService;
 
public class FamilyTreeTest {
 
	private static Family family;
	private static Relative relative;
	private static Map<String, Person> familyMembers;
	
 @BeforeClass 
 public static void init(){
	 family = new Family(); 
	 relative = new Relative();
	 familyMembers = family.getFamilyMembers();
	 }

  @Test
  public void checkGetPartnerForAPerson() {
	  Person person = familyMembers.get("Vich");
	  Person partner = new Person("Lika", "Female");
	  Assert.assertEquals(Arrays.asList(new Person[] {partner}), person.getPartner());
	  
  }
  
  @Test
  public void checkAddChildForAPerson() throws Exception {
	  Person person = familyMembers.get("Lika");
	  Person child = new Person("Vyks", "Male");
	  person.addChild(child);
	  Relationship childRelation = person.getRelationshipMap().get(RelationshipType.CHILD);
	  Assert.assertTrue(childRelation.getRelatedPerson().contains(child));
  }
  
  @Test
  public void checkGetParentsForAPerson() {
	  Person person = familyMembers.get("Vich");
	  Person father = new Person("King Shan", "Male");
	  Person mother = new Person("Queen Anga", "Female");
	  Assert.assertEquals(Arrays.asList(new Person[] {father, mother}), person.getParents());
	  
  }
  
  @Test
  public void checkGetChildrenForAPerson() {
	  Person person = familyMembers.get("Dritha");
	  Person child = new Person("Yodhan", "Male");
	  Assert.assertEquals(Arrays.asList(new Person[] {child}), person.getChildren());
	  
  }
  
  @Test
  public void testWhetherAPersonIsFemale() {
	  Person person = new Person("Harry", "Male");
	  Assert.assertFalse(Relative.checkIfFemale(person));
  }
  
  @Test
  public void testGetMother() {
	  Person person = familyMembers.get("Ahit");
	  Assert.assertEquals(new Person("Chitra", "Female"), relative.getMother(person));
  }
  
  @Test
  public void testGetFather() {
	  Person person = familyMembers.get("Vila");
	  Assert.assertEquals(new Person("Vich", "Male"), relative.getFather(person));
  }
  
  @Test
  public void testGetSiblings() throws Exception {
	  Person person = familyMembers.get("Jnki");
	  Assert.assertEquals(Arrays.asList(new Person[] {new Person("Ahit", "Male")}), relative.getSiblings(person));
  }
  
  @Test
  public void testGetBrothers() throws Exception {
	  Person person = familyMembers.get("Asva");
	  Assert.assertEquals(Arrays.asList(new Person[] {new Person("Vyas", "Male")}), relative.getBrothers(person));
  }
  
  @Test
  public void testGetSisters() throws Exception {
	  Person person = familyMembers.get("Tritha");
	  Assert.assertEquals(Arrays.asList(new Person[] {new Person("Dritha", "Female")}), relative.getSisters(person));
  }
  
  @Test
  public void testGetSon() throws Exception {
	  Person person = familyMembers.get("Jnki");
	  Assert.assertEquals(Arrays.asList(new Person[] {new Person("Laki", "Male")}), relative.getSon(person));
  }
  
  @Test
  public void testGetDaughter() throws Exception {
	  Person person = familyMembers.get("Arit");
	  Assert.assertEquals(Arrays.asList(new Person[] {new Person("Lavnya", "Female")}), relative.getDaughter(person));
  }
  
  @Test
  public void testGetBrotherInLaw() throws Exception {
	  Person person = familyMembers.get("Arit");
	  Assert.assertEquals(Arrays.asList(new Person[] {new Person("Ahit", "Male")}), relative.getBrotherInLaw(person));
  }
  
  @Test
  public void testGetSisterInLaw() throws Exception {
	  Person person = familyMembers.get("Vich");
	  Assert.assertEquals(Arrays.asList(new Person[] {new Person("Amba", "Female"), new Person("Chitra", "Female")}), relative.getSisterInLaw(person));
  }
  
  @Test
  public void testGetMaternalUncle() throws Exception {
	  Person person = familyMembers.get("Yodhan");
	  Assert.assertEquals(Arrays.asList(new Person[] {new Person("Vritha", "Male")}), relative.getMaternalUncle(person));
  }
  
  @Test
  public void testGetPaternalUncle() throws Exception {
	  Person person = familyMembers.get("Krithi");
	  Assert.assertEquals(Arrays.asList(new Person[] {new Person("Asva", "Male")}), relative.getPaternalUncle(person));
  }
  
  @Test
  public void testGetMaternalAunt() throws Exception {
	  Person person = familyMembers.get("Yodhan");
	  Assert.assertEquals(Arrays.asList(new Person[] {new Person("Tritha", "Female")}), relative.getMaternalAunt(person));
  }
  
  @Test
  public void testGetPaternalAunt() throws Exception {
	  Person person = familyMembers.get("Krithi");
	  Assert.assertEquals(Arrays.asList(new Person[] {new Person("Atya", "Female")}), relative.getPaternalAunt(person));
  }
  
  @Test(expected = ChildAdditionException.class)
  public void childShouldNotGetAddedIfThePersonIsMale() throws Exception {  
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
	  Relative relative = new Relative();
	  relatedPersons = relative.getBrotherInLaw(person);
	  }
  
  
  }
 