package com.geektrust.problem.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.geektrust.problem.family.Family;
import com.geektrust.problem.family.Person;
import com.geektrust.problem.family.Relative;
import com.geektrust.problem.service.FamilyService;

public class FamilyServiceTest {

	private static Family family;
	
	@BeforeClass 
	 public static void init(){
		 family = new Family(); 
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
	  
}
