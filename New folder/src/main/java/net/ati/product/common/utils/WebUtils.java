/**
 * 
 */
package net.ati.product.common.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Nayeemul
 *
 */
@Component
public class WebUtils {
	
    private HttpServletRequest request;

    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
    

    public String getClientIp() {
    	
    	System.out.println(request);

        String remoteAddr = "1";

        if (request != null) {
        	
        	
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "1".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }
	
}
