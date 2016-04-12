package ua.in.ngo.ednist.self;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.in.ngo.ednist.self.form.SelfInfoForm;
import ua.in.ngo.ednist.upload.FileUploadService;

@Service
public class SelfService {

	private SelfInfoDao selfInfoDao;
	private SelfInfoForm selfInfoFormReusableObject = new SelfInfoForm();
	
	private FileUploadService fileUploadService;

	@Resource
	public void setFileUploadService(FileUploadService fileUploadService) {
		this.fileUploadService = fileUploadService;
	}

	@Resource
	public void setSelfInfoDao(SelfInfoDao selfInfoDao) {
		this.selfInfoDao = selfInfoDao;
	}
	
	@Transactional
	public SelfInfo getSelfInfo() {
		SelfInfo info = selfInfoDao.getSelfInfo();
		info.setLogoPath("/resources/self/logo/logo.png");
		return info;
	}
	
	@Transactional
	public SelfInfoForm getSelfInfoForm() {
		selfInfoFormReusableObject.fill(getSelfInfo());
		return selfInfoFormReusableObject;
	}
	
	@Transactional
	public void updateSelfInfo(SelfInfo selfInfo) {
		selfInfoDao.updateSelfInfo(selfInfo);
		if (selfInfo instanceof SelfInfoForm) {
			//logic with multiform
			fileUploadService.upload("self", "logo", "logo.png", ((SelfInfoForm) selfInfo).getLogoFile());
		}
	}
}
