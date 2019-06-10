package org.tdds.service;
import org.tdds.entity.MonitoringList;


public interface MonitoringService {
	MonitoringList findByName(String name);
}
