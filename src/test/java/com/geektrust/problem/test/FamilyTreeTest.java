package com.geektrust.problem.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	RelativeTest.class,
	PersonTest.class,
	FamilyTest.class,
	FamilyServiceTest.class
	})
public class FamilyTreeTest {

}
