
package com.billing.login.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.billing.login.security.services.UserDetailsServiceImpl;

@Component
public class JwtAuthTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JwtProvider tokenProvider;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	private static final Logger logger = LoggerFactory.getLogger(JwtAuthTokenFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// --- TEMP DEBUG ---
	    javax.servlet.http.HttpSession existingSession = request.getSession(false);
	    logger.info("Path: {} | Incoming JSESSIONID cookie: {} | Session exists: {} | SessionId: {}",
	            request.getRequestURI(),
	            getCookieValue(request, "JSESSIONID"),
	            existingSession != null,
	            existingSession != null ? existingSession.getId() : "none");
	    // --- END TEMP DEBUG ---
	    
		try {

			String jwt = getJwt(request);
			if (jwt != null && tokenProvider.validateJwtToken(jwt)) {
				String username = tokenProvider.getUserNameFromJwtToken(jwt);
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}

		} catch (Exception e) {
			logger.error("Can NOT set user authentication -> Message: {}", e);
		}

		filterChain.doFilter(request, response);
	}

	private Object getCookieValue(HttpServletRequest request, String string) {
		// TODO Auto-generated method stub
		return null;
	}

	private String getJwt(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			return authHeader.replace("Bearer ", "");
		}
		return null;
	}
}
