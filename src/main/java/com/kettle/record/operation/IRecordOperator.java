package com.kettle.record.operation;

import org.pentaho.di.core.exception.KettleException;

import com.kettle.core.bean.KettleRecord;

/**
 * Record的操作定义
 * 
 * @author Administrator
 *
 */
public interface IRecordOperator {
	/**
	 * 加载Record
	 * @param record
	 * @return
	 */
	public boolean attachRecord(KettleRecord record);
	
	/**
	 * 卸载Record
	 * @param record
	 * @return
	 */
	public KettleRecord getRecord();
	
	/**
	 * 是否空闲
	 * @return
	 */
	public boolean isFree();
	
	/**
	 * 申请
	 */
	public void dealApply() throws KettleException;

	/**
	 * 重复
	 */
	public void dealRepeat() throws KettleException;

	/**
	 * 注册
	 */
	public void dealRegiste() throws KettleException;

	/**
	 * 异常
	 * 
	 * @throws KettleException
	 */
	public void dealError() throws KettleException;

	/**
	 * 完成
	 */
	public void dealFinished() throws KettleException;

	/**
	 * 运行中
	 */
	public void dealRunning() throws KettleException;
}
