package com.kettle.remote.record.process;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kettle.core.instance.KettleMgrInstance;
import com.kettle.record.service.RecordService;
import com.kettle.remote.KettleRemoteClient;
import com.kettle.remote.KettleRemotePool;
import com.kettle.remote.record.RemoteSerialRecordHandler;

public class RemoteSerialRecordService extends RecordService {

	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(RemoteSerialRecordService.class);

	/**
	 * 远程池
	 */
	private List<RemoteSerialRecordHandler> handlers;

	/**
	 * 定时任务
	 */
	private ScheduledExecutorService threadPool;

	/**
	 * 运行中
	 */
	private Boolean hasStart;

	/**
	 * 
	 */
	public RemoteSerialRecordService() {
		handlers = new LinkedList<RemoteSerialRecordHandler>();
		KettleRemotePool remotePool = KettleMgrInstance.kettleMgrEnvironment.getRemotePool();
		threadPool = Executors.newScheduledThreadPool(remotePool.getRemoteclients().size());
		for (KettleRemoteClient remoteClient : remotePool.getRemoteclients()) {
			handlers.add(new RemoteSerialRecordHandler(remoteClient));
		}
		start();
	}

	/**
	 * 启动
	 * 
	 * @param remotePool
	 */
	private void start() {
		if (hasStart == null || !hasStart.booleanValue()) {
			for (int index = 0; index < handlers.size(); index++) {
				threadPool.scheduleAtFixedRate(handlers.get(index), 2 * index, 20, TimeUnit.SECONDS);
			}
			logger.info("Kettle远程任务关系系统的线程启动完成,个数:" + handlers.size());
		} else {
			logger.info("Kettle远程任务关系系统的线程已经启动,无法再次启动!");
		}
	}
}
