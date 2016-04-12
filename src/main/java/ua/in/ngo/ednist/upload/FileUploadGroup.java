package ua.in.ngo.ednist.upload;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * This entity represents some setting for storing of files grouped and separated from other files by some logical criteria.
 * @author dimasik
 */
public class FileUploadGroup {

	private final String name;
	private final String location;
	private final FileUploadResolveStrategy fileUploadResolveStrategy;
	private final List<String> extensions;
	
	/**
	 * @param name Name of the group.
	 * @param location Location of the group in the system file storage location.
	 * @param fileUploadResolveStrategy {@link FileUploadResolveStrategy} relative to the group.
	 * @param extensions Allowed extensions for the group started from the '<b>.</b>'. e.g.: <b>.png</b>
	 */
	public FileUploadGroup(String name, String location, FileUploadResolveStrategy fileUploadResolveStrategy, String... extensions) {
		this.name = name;
		this.location = location;
		this.fileUploadResolveStrategy = fileUploadResolveStrategy;
		List<String> arrayAsList = Arrays.asList(extensions);
		Collections.<String>sort(arrayAsList, 
				Collections.reverseOrder(
						(String o1, String o2) -> Integer.compare(o1.length(), o2.length())));
		this.extensions = Collections.<String>unmodifiableList(arrayAsList);
	}

	/**
	 * <b>Get name of the group.</b>
	 * @return Name of the group as {@link String}
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * <b>Get root location of the group.</b>
	 * @return Root location of the group as {@link String}
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * <b>Get file upload resolve strategy relative to the group.</b>
	 * @return {@link FileUploadResolveStrategy}
	 */
	public FileUploadResolveStrategy getFileUploadResolveStrategy() {
		return fileUploadResolveStrategy;
	}

	/**
	 * <b>Get allowed extensions for the {@link FileUploadGroup}.</b>
	 * Returned list of extensions unmodifiable and sorted in reverse order.
	 * Create your own copy of list if you want to modify it for some reasons. 
	 * @return Unmodifiable list of allowed extensions sorted in reverse order by list length.
	 */
	public List<String> getExtensions() {
		return extensions;
	}
}
