package com.jcf.controller;

import org.junit.Test;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

	public class MemcachedTest 
	{
		@Test
		public void MemTest(){
			MemCachedClient client = new MemCachedClient();
			String[] addr = { "localhost:11211" };
    		Integer[] weights = { 1 };
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
			client.set("lee", "xiao");
			String str = (String) client.get("lee");
			
			System.out.println(str);
		}
	}
