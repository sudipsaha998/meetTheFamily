package com.geektrust.problem.constants;

public class FamilyConstants {

	public static enum Gender {

		FEMALE, MALE;

		public static Gender getGenderByStr(String str) {

			if ("FEMALE".equalsIgnoreCase(str))
				return FEMALE;
			else
				return MALE;
		}
	}

	public static enum RelationshipType {

		PARENT, PARTNER, CHILD
	}

	public static enum ExtendedRelation {

		PATERNAL_UNCLE, MATERNAL_UNCLE, PATERNAL_AUNT, MATERNAL_AUNT, SISTER_IN_LAW, BROTHER_IN_LAW, SON, DAUGHTER,
		SIBLINGS;
		
		public static ExtendedRelation getExtendedRelationByStr(String str) {

			if ("PATERNAL-UNCLE".equalsIgnoreCase(str))
				return PATERNAL_UNCLE;
			else if ("MATERNAL-UNCLE".equalsIgnoreCase(str))
				return MATERNAL_UNCLE;
			else if ("PATERNAL-AUNT".equalsIgnoreCase(str))
				return PATERNAL_AUNT;
			else if ("MATERNAL-AUNT".equalsIgnoreCase(str))
				return MATERNAL_AUNT;
			else if ("SISTER-IN-LAW".equalsIgnoreCase(str))
				return SISTER_IN_LAW;
			else if ("BROTHER-IN-LAW".equalsIgnoreCase(str))
				return BROTHER_IN_LAW;
			else if ("SON".equalsIgnoreCase(str))
				return SON;
			else if ("DAUGHTER".equalsIgnoreCase(str))
				return DAUGHTER;
			else if ("SIBLINGS".equalsIgnoreCase(str))
				return SIBLINGS;
			else
				return null;
		}
		
	}

	public static final String ADD_CHILD = "ADD_CHILD";
	public static final String GET_RELATIONSHIP = "GET_RELATIONSHIP";
	public static final String PERSON_NOT_FOUND = "PERSON_NOT_FOUND";
	public static final String CHILD_ADDITION_FAILED = "CHILD_ADDITION_FAILED";
	public static final String CHILD_ADDITION_SUCCEEDED = "CHILD_ADDITION_SUCCEEDED";
	public static final String NONE = "NONE";

}
