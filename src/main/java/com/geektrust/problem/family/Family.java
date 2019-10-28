package com.geektrust.problem.family;

import java.util.HashMap;
import java.util.Map;

import com.geektrust.problem.constants.FamilyConstants.RelationshipType;
import com.geektrust.problem.exception.ChildAdditionException;
import com.geektrust.problem.exception.PersonNotFoundException;

public class Family {

	private Map<String, Person> familyMembers = new HashMap<String, Person>();
	
	public Family() {
		initiateFamily();
	}
	
	public Map<String, Person> getFamilyMembers() {
		return familyMembers;
	}

	public void setFamilyMembers(Map<String, Person> familyMembers) {
		this.familyMembers = familyMembers;
	}

	private void initiateFamily() {
		
		Person king = addMember("King Shan", "male");
		Person queen = addMember("Queen Anga", "female");
		king.addRelationship(RelationshipType.PARTNER, queen);
		queen.addRelationship(RelationshipType.PARTNER, king);
		
		Person Chit = addMember("Chit", "male");
		Person Amba = addMember("Amba", "female");
		Chit.addRelationship(RelationshipType.PARTNER, Amba);
		Amba.addRelationship(RelationshipType.PARTNER, Chit);
		
		Person Ish = addMember("Ish", "male");
		
		Person Vich = addMember("Vich", "male");
		Person Lika = addMember("Lika", "female");
		Vich.addRelationship(RelationshipType.PARTNER, Lika);
		Lika.addRelationship(RelationshipType.PARTNER, Vich);
		
		Person Aras = addMember("Aras", "male");
		Person Chitra = addMember("Chitra", "female");
		Aras.addRelationship(RelationshipType.PARTNER, Chitra);
		Chitra.addRelationship(RelationshipType.PARTNER, Aras);
		
		Person Satya = addMember("Satya", "female");
		Person Vyan = addMember("Vyan", "male");
		Satya.addRelationship(RelationshipType.PARTNER, Vyan);
		Vyan.addRelationship(RelationshipType.PARTNER, Satya);
		
		Person Dritha = addMember("Dritha", "female");
		Person Jaya = addMember("Jaya", "male");
		Dritha.addRelationship(RelationshipType.PARTNER, Jaya);
		Jaya.addRelationship(RelationshipType.PARTNER, Dritha);
		
		Person Tritha = addMember("Tritha", "female");
		
		Person Vritha = addMember("Vritha", "male");
		
		Person Vila = addMember("Vila", "female");
		
		Person Chika = addMember("Chika", "female");
		
		Person Arit = addMember("Arit", "male");
		Person Jnki = addMember("Jnki", "female");
		Arit.addRelationship(RelationshipType.PARTNER, Jnki);
		Jnki.addRelationship(RelationshipType.PARTNER, Arit);
		
		Person Ahit = addMember("Ahit", "male");
		
		Person Satvy = addMember("Satvy", "female");
		Person Asva = addMember("Asva", "male");
		Satvy.addRelationship(RelationshipType.PARTNER, Asva);
		Asva.addRelationship(RelationshipType.PARTNER, Satvy);
		
		Person Krpi = addMember("Krpi", "female");
		Person Vyas = addMember("Vyas", "male");
		Krpi.addRelationship(RelationshipType.PARTNER, Vyas);
		Vyas.addRelationship(RelationshipType.PARTNER, Krpi);
		
		Person Atya = addMember("Atya", "female");
		
		Person Yodhan = addMember("Yodhan", "male");
		Person Laki = addMember("Laki", "male");
		Person Lavnya = addMember("Lavnya", "female");
		Person Vasa = addMember("Vasa", "male");
		Person Kriya = addMember("Kriya", "male");
		Person Krithi = addMember("Krithi", "female");
		
		
		try {
			queen.addChild(Chit);
			queen.addChild(Ish);
			queen.addChild(Vich);
			queen.addChild(Aras);
			queen.addChild(Satya);
			
			Amba.addChild(Dritha);
			Amba.addChild(Tritha);
			Amba.addChild(Vritha);
			
			Chitra.addChild(Jnki);
			Chitra.addChild(Ahit);
			
			Satya.addChild(Asva);
			Satya.addChild(Vyas);
			Satya.addChild(Atya);
			
			Dritha.addChild(Yodhan);
			
			Lika.addChild(Vila);
			Lika.addChild(Chika);
			
			Jnki.addChild(Lavnya);
			Jnki.addChild(Laki);
			
			Satvy.addChild(Vasa);
			
			Krpi.addChild(Kriya);
			Krpi.addChild(Krithi);
			
			
		} catch (ChildAdditionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private Person addMember(String name, String gender){
		Person person = new Person(name, gender);
		familyMembers.put(name, person);
		
		return person;
	}
	
	public Person findFamilyMember(String name) throws PersonNotFoundException {
		if(familyMembers.containsKey(name)) {
			return familyMembers.get(name);
		}
		else
			throw new PersonNotFoundException("member does not exist");
	}
}
