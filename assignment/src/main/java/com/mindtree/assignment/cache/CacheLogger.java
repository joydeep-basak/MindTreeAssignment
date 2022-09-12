package com.mindtree.assignment.cache;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CacheLogger implements CacheEventListener<Object, Object> {

	@Override
	public void onEvent(CacheEvent<? extends Object, ? extends Object> cacheEvent) {
		log.info("Key: {} | EventType: {} | Old value: {} | New value: {}",
				cacheEvent.getKey(), cacheEvent.getType(), cacheEvent.getOldValue(), 
				cacheEvent.getNewValue());

	}
}