
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
 
public class RelativeTest {
 
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
 