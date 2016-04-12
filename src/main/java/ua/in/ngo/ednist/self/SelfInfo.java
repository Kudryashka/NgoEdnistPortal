package ua.in.ngo.ednist.self;

import java.util.Date;

public interface SelfInfo {
	
	Date getModifyTime();
	
	void setModifyTime(Date modifyTime);
	
	String getName();
	
	void setName(String name);
	
	String getShortName();
	
	void setShortName(String shortName);
	
	String getOverview();
	
	void setOverview(String overview);
	
	String getLogoPath();
	
	void setLogoPath(String logoPath);
	
	boolean fill(SelfInfo info);
}
