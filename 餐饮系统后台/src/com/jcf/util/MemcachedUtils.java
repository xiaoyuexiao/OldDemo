package com.jcf.util;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class MemcachedUtils {
	public MemCachedClient client;
	public String port;
	
    public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public MemCachedClient getInstance()
    {
        if(client==null)
        {
        	client = new MemCachedClient();
    		String[] addr = { port };
    		Integer[] weights = { 3 };
    		SockIOPool pool = SockIOPool.getInstance();
    		pool.setServers(addr);
    		pool.setWeights(weights);
    		pool.setInitConn(5);
    		pool.setMinConn(5);
    		pool.setMaxConn(200);
    		pool.setMaxIdle(1000 * 30 * 30);
    		pool.setMaintSleep(30);
    		pool.setNagle(false);
    		pool.setSocketTO(30);
    		pool.setSocketConnectTO(0);
    		pool.initialize();
    		return client;
        }
        
    	return client;
    }
}
