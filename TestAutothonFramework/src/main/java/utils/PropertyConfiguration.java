package utils;

import org.aeonbits.owner.ConfigCache;

public class PropertyConfiguration {

	public static IConfiguration getConfig() {
		return ConfigCache.getOrCreate(IConfiguration.class);
	}
	
	public static void main(String[] args) {
		System.out.println(getConfig());
	}
}
