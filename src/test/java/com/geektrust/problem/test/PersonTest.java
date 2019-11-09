package com.geektrust.problem.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.geektrust.problem.constants.FamilyConstants.RelationshipType;
import com.geektrust.problem.exception.ChildAdditionException;
import com.geektrust.problem.family.Family;
import com.geektrust.problem.family.Person;
import com.geektrust.problem.family.Relationship;

public class PersonTest {
	
	private static Family family;
	private static Map<String, Person> familyMembers;

	@BeforeClass 
	 public static void init(){
		 family = new Family(); 
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
	  
	  @Test(expected = ChildAdditionException.class)
	  public void childShouldNotGetAddedIfThePersonIsMale() throws Exception {  
		  Person person = family.findFamilyMember("Chit");
			Person child = new Person("Srutak", "Male");
			person.addChild(child);
			familyMembers.put("Srutak", child);
		  }
	

}
