package com.keytec.activemq.secret;
import com.rop.security.AppSecretManager;

public class LzaAppSecretManager   implements  AppSecretManager{
	@Override
	public String getSecret(String appKey) {
		return "hkkqewqddfcncwdsfdsdgfsncw";
	}

	@Override
	public boolean isValidAppKey(String appKey) {
		String secret= "hkkqewqddfcncwdsfdsdgfsncw";
		if(secret!=null&&!"".equals(secret))
		   return true;
		else
		   return false;
	}
		
}
