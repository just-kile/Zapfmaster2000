package de.zapfmaster2000.webservice.achievement;

public class AchievementDescriptor {
	
	private String fName;
	
	private String fDescription;
	
	private String fImagepath;
	
	private boolean fPublic;
	
	private String fType;
	
	private AbstractAchievementProcessor fProcessor;

	public String getName() {
		return fName;
	}

	public void setName(String pName) {
		fName = pName;
	}

	public String getDescription() {
		return fDescription;
	}

	public void setDescription(String pDescription) {
		fDescription = pDescription;
	}

	public String getImagepath() {
		return fImagepath;
	}

	public void setImagepath(String pImagepath) {
		fImagepath = pImagepath;
	}

	public boolean getPublic() {
		return fPublic;
	}

	public void setPublic(boolean pPublic) {
		fPublic = pPublic;
	}

	public String getType() {
		return fType;
	}

	public void setType(String pType) {
		fType = pType;
	}

	public AbstractAchievementProcessor getProcessor() {
		return fProcessor;
	}

	public void setProcessor(AbstractAchievementProcessor pProcessor) {
		fProcessor = pProcessor;
	}
}
