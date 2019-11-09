package com.geektrust.problem.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.geektrust.problem.exception.PersonNotFoundException;
import com.geektrust.problem.family.Family;

public class FamilyTest {

	@Test(expected = PersonNotFoundException.class)
	public void expectExceptionIfPersonNotFound() throws Exception {
		Family family = new Family();
		family.findFamilyMember("Pjali");
	}

}
