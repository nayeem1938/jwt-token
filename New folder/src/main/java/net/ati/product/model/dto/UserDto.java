/**
 * 
 */
package net.ati.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author Nayeemul
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	
	private Integer id;
	
	private String username;

    private String password;

    private String email;
    
}
