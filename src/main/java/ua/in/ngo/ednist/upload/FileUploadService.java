package ua.in.ngo.ednist.upload;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * The service for uploading and handling uploaded content stored on file system storage.
 * @author dimasik
 */
@Service
public class FileUploadService {
	
	private static Logger logger = LoggerFactory.getLogger(FileUploadService.class);

	private final String fileSystemStorageLocation;
	private final String applicationFilesStorageLocation;
	private final Map<String, FileUploadGroup> groupsMap = new HashMap<>();
	
	public FileUploadService(String fileSystemStorageLocation, String namespace, FileUploadGroup... groups) {
		this.fileSystemStorageLocation = fileSystemStorageLocation;
		this.applicationFilesStorageLocation = fileSystemStorageLocation + File.separator + namespace + File.separator;
		for (FileUploadGroup group : groups) {
			groupsMap.put(group.getName(), group);
		}
	}
	
	@PostConstruct
	public void init() {
		File storageLocation = new File(fileSystemStorageLocation);
		if (storageLocation.exists()) {
			if (storageLocation.isFile()) {
				throw new IllegalStateException("Sestem storage location folder is a file. Location: " + fileSystemStorageLocation);
			}
		}
		File applicationStorageLocation = new File(applicationFilesStorageLocation);
		if (!applicationStorageLocation.exists()) {
			if (applicationStorageLocation.mkdirs()) {
				logger.info("Application files storage location created by path: " + applicationFilesStorageLocation);
			} else {
				//security exception should be thrown
				logger.error("Fail to create application files storage location by path: " + applicationFilesStorageLocation);
			}
		}
	}
	
	/**
	 * <b>Get extension for the file</b>
	 * @param groupName Group name relative to the file.
	 * @param fileName The file name.
	 * @return Extension of the file or <i>null</i> if extension for this file was not registered.
	 */
	public String getFileExtension(String groupName, String fileName) {
		FileUploadGroup group = groupsMap.get(groupName);
		for (String extension : group.getExtensions()) {
			if (fileName.endsWith(extension)) {
				return extension;
			}
		}
		return null;
	}
	
	/**
	 * Upload file to file system storage location.
	 * 
	 * @param groupName - Name of {@link FileUploadGroup} relative to the file.
	 * @param uploadLocation - Local location of the file in the group directory.
	 * @param name - Desired name of the file.
	 * @param file - The file.
	 * @return File name after name resolving or <i>null</i> if nothing was uploaded.
	 */
	public String upload(String groupName, String uploadLocation, String name, MultipartFile file) {
		FileUploadGroup uploadGroup = groupsMap.get(groupName);
		if (uploadGroup == null) {
			throw new IllegalStateException(String.format("Group with name='%s' not registered.", groupName));
		}
		if (file != null  && !file.isEmpty()) {
			File groupLocation = new File(applicationFilesStorageLocation + uploadGroup.getLocation());
			if (!groupLocation.exists()) {
				//security exception should be thrown if fail to create directory
				try {
					groupLocation.mkdirs();
				} catch (SecurityException e) {
					logger.error("Fail to create group location directory. Error message: " + e.getMessage());
					return null;
				}
			}
			
			if (uploadLocation == null) {
				uploadLocation = "";
			}
			
			File fileUploadLocation = new File(groupLocation + File.separator + uploadLocation);
			if (fileUploadLocation.exists() && fileUploadLocation.isFile()) {
				throw new IllegalArgumentException("Upload location is a file.");
			}
			if (!fileUploadLocation.exists()) {
				//security exception should be thrown if fail to create directory
				try {
					fileUploadLocation.mkdirs();
				} catch (SecurityException e) {
					logger.error("Fail to create file upload location. Error message: " + e.getMessage());
					return null;
				}
			}
			
			FileUploadResolveStrategy resolveStrategy = uploadGroup.getFileUploadResolveStrategy();
			name = resolveStrategy.resolve(name, fileUploadLocation);
			
			String localStorageFilePath = uploadLocation + File.separator + name;
			String fileStoragePath = groupLocation + File.separator + localStorageFilePath;
			try {
				BufferedOutputStream outStream = new BufferedOutputStream(
												new FileOutputStream(fileStoragePath));
				try {
					FileCopyUtils.copy(file.getInputStream(), outStream);
				} finally {
					try {
						outStream.close();
					} catch (IOException e) {
						logger.error("Fail to close output stream!");
					}
				}
				logger.info("Successfull upload file to " + fileStoragePath);
				return fileStoragePath;
			} catch (IOException e) {
				logger.error("Fail to upload file. Error message: " + e.getMessage());
			}
		}
		return null;
	}
}
