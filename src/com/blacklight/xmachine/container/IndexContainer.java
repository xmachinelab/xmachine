package com.blacklight.xmachine.container;

import java.util.concurrent.ConcurrentHashMap;

import com.blacklight.xmachine.fundamentals.XObject;

public final class IndexContainer {
	
	public enum IndexEnum {
		OBJECT_INDEX, PROGRAM_INDEX;
		
		ConcurrentHashMap<Long, XObject> indexMap = null;
		
		IndexEnum() {
			indexMap = new ConcurrentHashMap<Long, XObject>();
		}
		
		void addIndex(XObject val) {
			long key = val.getXMachineIndex();
			indexMap.put(key, val);
		}
		
		XObject getXObject(long key) {
			return indexMap.get(key);
		}

		ConcurrentHashMap<Long, XObject> getIndexMap() {
			return indexMap;
		}
	}
	
	public static IndexContainer SINGLETON = new IndexContainer();

	
	private IndexContainer() {}
	
	public void addIndex(IndexEnum indexEnum, XObject val) {
		indexEnum.addIndex(val);
	}
	
	public XObject getXObject(IndexEnum indexEnum, long key) {
		return indexEnum.getXObject(key);
	}

	public ConcurrentHashMap<Long, XObject> getObjectIndexes(IndexEnum indexEnum) {
		return indexEnum.getIndexMap();
	}

}
