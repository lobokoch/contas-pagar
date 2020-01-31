/**********************************************************************************************
Code generated with MKL Plug-in version: 60.0.6
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.kerubin.api.database.core.ServiceContext;

public class ServiceHandlerInterceptorAdapter extends HandlerInterceptorAdapter {
	
	public static final String HEADER_USER = "X-User-Header";
	public static final String HEADER_TENANT = "X-Tenant-Header";
	public static final String HEADER_TENANT_ACCOUNT_TYPE = "X-Tenant-AccountType-Header";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		updateServiceContext(request);
		
		return true;
	}
	
	private void updateServiceContext(HttpServletRequest request) {
		String currentTenant = request.getHeader(HEADER_TENANT);
		String currentUser = request.getHeader(HEADER_USER);
		String currentTenantAccountType = request.getHeader(HEADER_TENANT_ACCOUNT_TYPE);
		
		ServiceContext.setTenant(currentTenant);
		ServiceContext.setUser(currentUser);
		ServiceContext.setTenantAccountType(currentTenantAccountType);
		
		ServiceContext.setDomain(FinanceiroContasPagarConstants.DOMAIN);
		ServiceContext.setService(FinanceiroContasPagarConstants.SERVICE);
		
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		ServiceContext.clear();
		
	}

}

