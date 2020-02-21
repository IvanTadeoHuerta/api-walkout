package com.tech.pro.walker.api.auth;

import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.tech.pro.walker.api.models.entity.Walker;
import com.tech.pro.walker.api.services.IWalkerServiceImp;

@Component
public class infoAdicinalToken  implements TokenEnhancer{
	
	@Autowired
	private IWalkerServiceImp iWalkerServiceImp;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Walker usuario = iWalkerServiceImp.findByUsuario(authentication.getName());
		
		Map<String, Object> additionalInfo = new HashMap<>();
		
		additionalInfo.put("apellido_paterno", usuario.getApellido_paterno());
		additionalInfo.put("apellido_materno", usuario.getApellido_materno());
		additionalInfo.put("nombre", usuario.getNombre());
		additionalInfo.put("id_walker", usuario.getId_walker());
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
		return accessToken;
	}

}
