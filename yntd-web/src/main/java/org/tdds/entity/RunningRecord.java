package org.tdds.entity;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "znzz_running_record")
public class RunningRecord {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	@Column(name = "id")
	private Long id;
	
	@Column(name = "machine_id")
	private Long machineId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMachineId() {
		return machineId;
	}

	public void setMachineId(Long machineId) {
		this.machineId = machineId;
	}

}
