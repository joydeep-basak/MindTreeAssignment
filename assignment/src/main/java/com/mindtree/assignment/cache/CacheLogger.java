package com.mindtree.assignment.cache;

import java.util.Map;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.MDC;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CacheLogger implements CacheEventListener<Object, Object> {

	@Override
	public void onEvent(CacheEvent<? extends Object, ? extends Object> cacheEvent) {
		Map<String, String> contextMap = MDC.getCopyOfContextMap();
		MDC.put("calledid", contextMap.get("calledid"));
		log.info("Key: {} | EventType: {} | Old value: {} | New value: {}",
				cacheEvent.getKey(), cacheEvent.getType(), cacheEvent.getOldValue(), 
				cacheEvent.getNewValue());

	}
}