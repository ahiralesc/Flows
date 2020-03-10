package edu.dataflow.core.util;

public class FileSetting {

	private String name;
	private String path;
	
	public FileSetting(String name, String path){
		this.name=name;
		this.path=path;
	}
	
	
	public String getName() {
		return name;
	}
	
	public String getPath() {
		return path;
	}
	
}
