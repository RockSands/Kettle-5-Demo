package com.kettle.main.sync.tables;

import org.pentaho.di.core.exception.KettleException;

import com.kettle.core.bean.KettleResult;
import com.kettle.core.instance.KettleMgrInstance;
import com.kettle.core.instance.metas.KettleTableMeta;

public class CreateSTDThread implements Runnable {
	KettleTableMeta source = null;

	KettleTableMeta target = null;

	String cron = null;

	KettleResult result = null;

	CreateSTDThread(KettleTableMeta source, KettleTableMeta target, String cron) {
		this.source = source;
		this.target = target;
		this.cron = cron;
	}

	@Override
	public void run() {
		try {
			long now = System.currentTimeMillis();
			if (cron != null) {
				result = KettleMgrInstance.getInstance().scheduleSyncTablesData(source, target, cron);
			} else {
				result = KettleMgrInstance.getInstance().registeSyncTablesDatas(source, target);
				System.out.println("==>registe used: " + (System.currentTimeMillis() - now));
				KettleMgrInstance.getInstance().excuteJob(result.getId());
			}
			System.out.println("==>apply used: " + (System.currentTimeMillis() - now));
		} catch (KettleException e) {
			e.printStackTrace();
		}
	}

	public void modifyCron(String newCron) throws KettleException {
		KettleMgrInstance.getInstance().modifySchedule(result.getId(), newCron);
	}

	public KettleResult getResult() {
		return result;
	}

}