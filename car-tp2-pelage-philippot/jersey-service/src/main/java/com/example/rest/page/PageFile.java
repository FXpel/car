/**
 * Class PageFile
 * Display files contains in the <code>FTPFile[]</code>
 * Allows the user :
 *  - to download a file
 *  - to delete a file
 *  - to upload a file
 *  
 * @author philippot
 * @version 1
 */
package com.example.rest.page;

import org.apache.commons.net.ftp.FTPFile;

public class PageFile extends Page{
	// ATTRIBUTS
	/**/
	private String path;
	/**/
	private FTPFile[] files;
	/**/
	private static String upload = "<form action=\"upload\" enctype='multipart/form-data' method=\"POST\"><input type=\"text\" name=\"name\" placeholder=\"nom\" required><input type=\"file\" name=\"file\" id=\"file\" required/><input value=\"Uploader\" type=\"submit\"/></form>";
	
	// CONSTRUCTOR
	public PageFile() {
		super("File");
	}

	// METHODS
	@Override
	public String getPage() {
		String page = head;
		for(FTPFile f : files) {
			if(!f.getName().equals(".")) {
				//String p = path+f.getName();
				page += "<p>";
				page += "- Nom : "+f.getName()+" | Taille en octets : "+f.getSize()+" | ";
				if(f.isFile()) {
					page += "<a href=\"\">Download</a>";
				}
				if(f.isDirectory()) {
					page += "<a href=\"\">Open</a>";
				}
				page += "</p>";
			}
		}
		page+=end;
		return page;
	}

	// GETTER AND SETTER
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the files
	 */
	public FTPFile[] getFiles() {
		return files;
	}

	/**
	 * @param files the files to set
	 */
	public void setFiles(FTPFile[] files) {
		this.files = files;
	}

	/**
	 * @return the upload
	 */
	public static String getUpload() {
		return upload;
	}

	/**
	 * @param upload the upload to set
	 */
	public static void setUpload(String upload) {
		PageFile.upload = upload;
	}
}
