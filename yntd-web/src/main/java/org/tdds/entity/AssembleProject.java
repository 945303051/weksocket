package org.tdds.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lttadministrator
 *
 */
@Table(name="assemble_project")
public class AssembleProject {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="pname")
	private String pname;
	
	@Column(name="donetime")
	private String doneTime;
	
	@Column(name="manager")
	private String manager;

	@Column(name="name")
	private String name;
	
	@Column(name="split_charging_plan")
	private String splitChargingPlan;
	
	@Column(name="split_charging_actual")
	private String splitChargingActual;
	
	@Column(name="final_assembly_plan")
	private String finalAssemblyPlan;
	
	@Column(name="final_assembly_actual")
	private String finalAssemblyActual;
	
	@Column(name="debug_plan")
	private String debugPlan;
	
	@Column(name="debug_actual")
	private String debugActual;
	
	@Column(name="checkup_plan")
	private String checkupPlan;
	
	@Column(name="checkup_actual")
	private String checkupActual;
	
	@Column(name="be_inspected_plan")
	private String beInspectedPlan;
	
	@Column(name="be_inspected_actual")
	private String beInspectedActual;
	
	@Column(name="storage_plan")
	private String storagePlan;
	
	@Column(name="storage_actual")
	private String storageActual;
	
	@Column(name="problem_summary")
	private String problemSummary;
	
	@Column(name="record_time")
	private String recordTime;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getDoneTime() {
		return doneTime;
	}

	public void setDoneTime(String doneTime) {
		this.doneTime = doneTime;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSplitChargingPlan() {
		return splitChargingPlan;
	}

	public void setSplitChargingPlan(String splitChargingPlan) {
		this.splitChargingPlan = splitChargingPlan;
	}

	public String getSplitChargingActual() {
		return splitChargingActual;
	}

	public void setSplitChargingActual(String splitChargingActual) {
		this.splitChargingActual = splitChargingActual;
	}

	public String getFinalAssemblyPlan() {
		return finalAssemblyPlan;
	}

	public void setFinalAssemblyPlan(String finalAssemblyPlan) {
		this.finalAssemblyPlan = finalAssemblyPlan;
	}

	public String getFinalAssemblyActual() {
		return finalAssemblyActual;
	}

	public void setFinalAssemblyActual(String finalAssemblyActual) {
		this.finalAssemblyActual = finalAssemblyActual;
	}

	public String getDebugPlan() {
		return debugPlan;
	}

	public void setDebugPlan(String debugPlan) {
		this.debugPlan = debugPlan;
	}

	public String getDebugActual() {
		return debugActual;
	}

	public void setDebugActual(String debugActual) {
		this.debugActual = debugActual;
	}

	public String getCheckupPlan() {
		return checkupPlan;
	}

	public void setCheckupPlan(String checkupPlan) {
		this.checkupPlan = checkupPlan;
	}

	public String getCheckupActual() {
		return checkupActual;
	}

	public void setCheckupActual(String checkupActual) {
		this.checkupActual = checkupActual;
	}

	public String getBeInspectedPlan() {
		return beInspectedPlan;
	}

	public void setBeInspectedPlan(String beInspectedPlan) {
		this.beInspectedPlan = beInspectedPlan;
	}

	public String getBeInspectedActual() {
		return beInspectedActual;
	}

	public void setBeInspectedActual(String beInspectedActual) {
		this.beInspectedActual = beInspectedActual;
	}

	public String getStoragePlan() {
		return storagePlan;
	}

	public void setStoragePlan(String storagePlan) {
		this.storagePlan = storagePlan;
	}

	public String getStorageActual() {
		return storageActual;
	}

	public void setStorageActual(String storageActual) {
		this.storageActual = storageActual;
	}

	public String getProblemSummary() {
		return problemSummary;
	}

	public void setProblemSummary(String problemSummary) {
		this.problemSummary = problemSummary;
	}

	public String getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}
}
