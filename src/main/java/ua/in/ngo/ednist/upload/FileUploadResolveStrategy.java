package ua.in.ngo.ednist.upload;

import java.io.File;

public enum FileUploadResolveStrategy {

	REPLACE(new Strategy() {
		@Override
		public String resolve(String name, File location) {
			return name;
		}
	});
	
	private Strategy strategy;
	
	private FileUploadResolveStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	
	public String resolve(String name, File location) {
		return strategy.resolve(name, location);
	}
	
	private interface Strategy {
		
		String resolve(String name, File location);
	}
}
