package com.zhouyi.business.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.zhouyi.business.model.LoginData;


public class LocalCacheProvider  {

	private volatile static LocalCacheProvider provider;
	private Map<String, LoginData> loginMap=new ConcurrentHashMap<String, LoginData>();
	private Map<String, String> userTokenMap=new ConcurrentHashMap<String, String>();
	private Map<String, String> configMap=new ConcurrentHashMap<String, String>();
	private Map<String, String> lockers=new ConcurrentHashMap<String, String>();
	private Map<String, Integer> cardMap=new ConcurrentHashMap<String, Integer>();
	private Map<String, Integer> manFeeMap=new ConcurrentHashMap<String, Integer>();
	
	private LocalCacheProvider(){
		cardMap.put("10", 1200);
		cardMap.put("25", 1800);
		cardMap.put("45", 2500);
		cardMap.put("-1", 6800);
		
		manFeeMap.put("0", 29800);//298套餐
		manFeeMap.put("1", 68800);//688套餐
	}
	
	public static LocalCacheProvider getInstance(){
		if(provider==null){
			synchronized(Object.class){
				if(provider==null){
					provider=new LocalCacheProvider();
				}
			}
		}
		return provider;
	}
	
	public Map<String, LoginData> getLoginMap(){
		return loginMap;
	}
	
	public Map<String, String> getUserTokenMap(){
		return userTokenMap;
	}
	
	public Map<String, String> getConfigMap(){
		return configMap;
	}
	
	public String getLocker(String id) {
        if (!lockers.containsKey(id)) {
            lockers.put(id, "Lock" + id);
        }
        return lockers.get(id);
    }
	
	public Map<String, Integer> getCardMap(){
		return cardMap;
	}
	
	public Map<String, Integer> getManFeeMap(){
		return manFeeMap;
	}
}
