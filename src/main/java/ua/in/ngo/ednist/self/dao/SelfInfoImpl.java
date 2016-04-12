package ua.in.ngo.ednist.self.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import ua.in.ngo.ednist.self.SelfInfo;
import ua.in.ngo.ednist.util.CompareUtil;

@Entity(name = "SelfInfo")
@Table(name = "self_info")
public class SelfInfoImpl implements SelfInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private int id;
	
	@Column(name = "last_modify_time", nullable = false)
	private Date modifyTime;
	
	@Column(name = "name", nullable = false, length = 150)
	private String name;
	
	@Column(name = "short_name", nullable = false, length = 100)
	private String shortName;
	
	@Column(name = "overview", columnDefinition = "TEXT")
	private String overview;
	
	@Transient
	private String logoPath;

	@Override
	public Date getModifyTime() {
		return modifyTime;
	}

	@Override
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

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

	@Override
	public boolean fill(SelfInfo info) {
		if (!CompareUtil.equalOrBothNull(
				new Object[]{info.getName(), info.getShortName(), info.getOverview(), info.getLogoPath()}, 
				new Object[]{this.name, this.shortName, this.overview, this.logoPath})) {
			this.modifyTime = new Date();
			this.name = info.getName();
			this.shortName = info.getShortName();
			this.overview = info.getOverview();
			this.logoPath = info.getLogoPath();
			return true;
		}
		return false;
	}
}
