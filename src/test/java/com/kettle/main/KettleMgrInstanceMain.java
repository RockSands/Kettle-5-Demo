package com.kettle.main;

import org.pentaho.di.core.exception.KettleException;

import com.kettle.core.instance.KettleMgrInstance;

/**
 * 测试
 * 
 * @author Administrator
 *
 */
public class KettleMgrInstanceMain {

	public static void main(String[] args) throws KettleException {
		KettleMgrInstance.getInstance();
	}

}
