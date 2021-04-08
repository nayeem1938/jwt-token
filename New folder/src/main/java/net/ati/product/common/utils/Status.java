/**
 * 
 */
package net.ati.product.common.utils;

/**
 * @author Nayeemul
 *
 */
public enum Status {
	
	ACTIVE("Active", 0),
	INACTIVE("Inactive", 0);
	
	private final String name;
	
	private final int code;

	private Status(String name, int code) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public int getCode() {
		return code;
	}
	
	

}
