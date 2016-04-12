package ua.in.ngo.ednist.self.form;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import ua.in.ngo.ednist.self.SelfInfo;

public class SelfInfoForm implements SelfInfo {

	private Date modifyTime;
	private String name;
	private String shortName;
	private String overview;
	private String logoPath;
	private MultipartFile logoFile;
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getShortName() {
		return shortName;
	}
	
	@Override
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Override
	public Date getModifyTime() {
		return modifyTime;
	}

	@Override
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Override
	public String getOverview() {
		return overview;
	}

	@Override
	public void setOverview(String overview) {
		this.overview = overview;
	}

	@Override
	public String getLogoPath() {
		return logoPath;
	}

	@Override
	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public MultipartFile getLogoFile() {
		return logoFile;
	}

	public void setLogoFile(MultipartFile logoFile) {
		this.logoFile = logoFile;
	}

	@Override
	public boolean fill(SelfInfo info) {
		if (info != null) {
			this.modifyTime = info.getModifyTime();
			this.name = info.getName();
			this.shortName = info.getShortName();
			this.overview = info.getOverview();
			this.logoPath = info.getLogoPath();
		} else {
			this.modifyTime = null;
			this.name = this.shortName = this.overview = this.logoPath = null;
		}
		return true;
	}
}
