package com.asosyalbebe.samplespring.user.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.asosyalbebe.samplespring.user.model.AppUrl;
import com.asosyalbebe.samplespring.user.model.Privilege;
import com.asosyalbebe.samplespring.user.service.UserService;
import com.asosyalbebe.samplespring.utils.Logger;
import com.asosyalbebe.samplespring.utils.Pair;

public class CustomFilterInvocationDefinitionSource implements FilterInvocationSecurityMetadataSource, InitializingBean {

	private Logger log = Logger.getLogger(getClass());

	@Autowired
	protected UserService userService;

	private Map<String, Collection<ConfigAttribute>> urlAttributeCache;
	private List<Pair<Pattern, Collection<ConfigAttribute>>> patternAttrList;

	private Collection<ConfigAttribute> emptyAttributes = new ArrayList<ConfigAttribute>();

	public CustomFilterInvocationDefinitionSource() {
		urlAttributeCache = new HashMap<String, Collection<ConfigAttribute>>();
		patternAttrList = new ArrayList<Pair<Pattern, Collection<ConfigAttribute>>>();
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return emptyAttributes;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) {
		String url = ((FilterInvocation) object).getRequest().getServletPath();
		if (url.length() > 1 && url.endsWith("/")) {
			// For example: /user/ or /admin/
			url = url.substring(0, url.length() - 1);
		}

		Collection<ConfigAttribute> urlAttributes = urlAttributeCache.get(url);

		if (urlAttributes == null) {
			for (Pair<Pattern, Collection<ConfigAttribute>> pair : patternAttrList) {
				if (pair.getFirst().matcher(url).matches()) {
					urlAttributes = pair.getSecond();
					break;
				}
			}

			if (urlAttributes == null) {
				urlAttributes = emptyAttributes;
			}

			urlAttributeCache.put(url, urlAttributes);
		}

		return urlAttributes;
	}

	public void clearCache() {
		urlAttributeCache.clear();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		List<AppUrl> urls = userService.getAllAppUrls();
		for (AppUrl so : urls) {
			Collection<ConfigAttribute> attributes = new HashSet<ConfigAttribute>();

			Set<Privilege> urlPrivileges = so.getPrivileges();
			if (urlPrivileges != null) {
				for (Privilege priv : urlPrivileges) {
					attributes.add(new SecurityConfig(priv.getName()));
				}
			}

			if (so.getIsRegularExpression()) {
				patternAttrList.add(new Pair<Pattern, Collection<ConfigAttribute>>(Pattern.compile(so.getUrl()), attributes));
				log.info("Pattern: {0} privileges: {1}", so.getUrl(), attributes);
			} else {
				urlAttributeCache.put(so.getUrl(), attributes);
				log.info("URL: {0} privileges: {1}", so.getUrl(), attributes);
			}
		}
	}

}
