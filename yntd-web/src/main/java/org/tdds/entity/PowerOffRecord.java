package org.tdds.entity;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "znzz_poweroff_record")
public class PowerOffRecord {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	@Column(name = "id")
	private Long id;
	
	@Column(name = "machine_id")
	private Long machineId;

	@Column(name = "machine_signal")
	private String machineSignal;
	
	@Column(name = "machine_mode")
	private String machineMode;
	
	@Column(name = "machine_status")
	private String machineStatus;
	
	@Column(name = "alarm_no")
	private String alarmNo;
	 
	@Column(name = "alarm_message")
	private String alarmMessage;
	
	@Column(name = "alarm_textColor")
	private String alarmTextColor;
	
	@Column(name = "alarm_backgroundColor")
	private String alarmBackgroundColor;
	
	@Column(name = "maintenance_signal")
	private String maintenanceSignal;
	
	@Column(name = "mainProgram_no")
	private String mainProgramNo;
	
	@Column(name = "mainProgramComment")
	private String mainProgramComment;
	
	@Column(name = "subProgram_no")
	private String subProgramNo;
	
	@Column(name = "subProgramComment")
	private String subProgramComment;
	
	@Column(name = "tool_no")
	private String toolNo;
	
	@Column(name = "tool_suffix")
	private String toolSuffix;
	
	@Column(name = "tool_name")
	private String toolName;
	
	@Column(name = "tool_part")
	private String toolPart;
	
	@Column(name = "tool_partBackgroundColor")
	private String toolPartBackgroundColor;
	
	@Column(name = "tool2_no")
	private String tool2No;
	
	@Column(name = "tool2_suffix")
	private String tool2Suffix;
	
	@Column(name = "tool2_name")
	private String tool2Name;
	
	@Column(name = "tool2_part")
	private String tool2Part;
	
	@Column(name = "tool2_partBackgroundColor")
	private String tool2PartBackgroundColor;
	
	@Column(name = "partsCount_target")
	private String partsCountTarget;
	
	@Column(name = "partsCount_result")
	private String partsCountResult;
	
	@Column(name = "machiningTime_progress")
	private String machiningTimeProgress;
	
	@Column(name = "override_rapid")
	private String overrideRapid;
	
	@Column(name = "override_spindle")
	private String overrideSpindle;
	
	@Column(name = "override_feed")
	private String overrideFeed;
	
	@Column(name = "spindle_mode")
	private String spindleMode;
	
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

	public String getMachineSignal() {
		return machineSignal;
	}

	public void setMachineSignal(String machineSignal) {
		this.machineSignal = machineSignal;
	}

	public String getMachineMode() {
		return machineMode;
	}

	public void setMachineMode(String machineMode) {
		this.machineMode = machineMode;
	}

	public String getMachineStatus() {
		return machineStatus;
	}

	public void setMachineStatus(String machineStatus) {
		this.machineStatus = machineStatus;
	}

	public String getAlarmNo() {
		return alarmNo;
	}

	public void setAlarmNo(String alarmNo) {
		this.alarmNo = alarmNo;
	}

	public String getAlarmMessage() {
		return alarmMessage;
	}

	public void setAlarmMessage(String alarmMessage) {
		this.alarmMessage = alarmMessage;
	}

	public String getAlarmTextColor() {
		return alarmTextColor;
	}

	public void setAlarmTextColor(String alarmTextColor) {
		this.alarmTextColor = alarmTextColor;
	}

	public String getAlarmBackgroundColor() {
		return alarmBackgroundColor;
	}

	public void setAlarmBackgroundColor(String alarmBackgroundColor) {
		this.alarmBackgroundColor = alarmBackgroundColor;
	}

	public String getMaintenanceSignal() {
		return maintenanceSignal;
	}

	public void setMaintenanceSignal(String maintenanceSignal) {
		this.maintenanceSignal = maintenanceSignal;
	}

	public String getMainProgramNo() {
		return mainProgramNo;
	}

	public void setMainProgramNo(String mainProgramNo) {
		this.mainProgramNo = mainProgramNo;
	}

	public String getMainProgramComment() {
		return mainProgramComment;
	}

	public void setMainProgramComment(String mainProgramComment) {
		this.mainProgramComment = mainProgramComment;
	}

	public String getSubProgramNo() {
		return subProgramNo;
	}

	public void setSubProgramNo(String subProgramNo) {
		this.subProgramNo = subProgramNo;
	}

	public String getSubProgramComment() {
		return subProgramComment;
	}

	public void setSubProgramComment(String subProgramComment) {
		this.subProgramComment = subProgramComment;
	}

	public String getToolNo() {
		return toolNo;
	}

	public void setToolNo(String toolNo) {
		this.toolNo = toolNo;
	}

	public String getToolSuffix() {
		return toolSuffix;
	}

	public void setToolSuffix(String toolSuffix) {
		this.toolSuffix = toolSuffix;
	}

	public String getToolName() {
		return toolName;
	}

	public void setToolName(String toolName) {
		this.toolName = toolName;
	}

	public String getToolPart() {
		return toolPart;
	}

	public void setToolPart(String toolPart) {
		this.toolPart = toolPart;
	}

	public String getToolPartBackgroundColor() {
		return toolPartBackgroundColor;
	}

	public void setToolPartBackgroundColor(String toolPartBackgroundColor) {
		this.toolPartBackgroundColor = toolPartBackgroundColor;
	}

	public String getTool2No() {
		return tool2No;
	}

	public void setTool2No(String tool2No) {
		this.tool2No = tool2No;
	}

	public String getTool2Suffix() {
		return tool2Suffix;
	}

	public void setTool2Suffix(String tool2Suffix) {
		this.tool2Suffix = tool2Suffix;
	}

	public String getTool2Name() {
		return tool2Name;
	}

	public void setTool2Name(String tool2Name) {
		this.tool2Name = tool2Name;
	}

	public String getTool2Part() {
		return tool2Part;
	}

	public void setTool2Part(String tool2Part) {
		this.tool2Part = tool2Part;
	}

	public String getTool2PartBackgroundColor() {
		return tool2PartBackgroundColor;
	}

	public void setTool2PartBackgroundColor(String tool2PartBackgroundColor) {
		this.tool2PartBackgroundColor = tool2PartBackgroundColor;
	}

	public String getPartsCountTarget() {
		return partsCountTarget;
	}

	public void setPartsCountTarget(String partsCountTarget) {
		this.partsCountTarget = partsCountTarget;
	}

	public String getPartsCountResult() {
		return partsCountResult;
	}

	public void setPartsCountResult(String partsCountResult) {
		this.partsCountResult = partsCountResult;
	}

	public String getMachiningTimeProgress() {
		return machiningTimeProgress;
	}

	public void setMachiningTimeProgress(String machiningTimeProgress) {
		this.machiningTimeProgress = machiningTimeProgress;
	}

	public String getOverrideRapid() {
		return overrideRapid;
	}

	public void setOverrideRapid(String overrideRapid) {
		this.overrideRapid = overrideRapid;
	}

	public String getOverrideSpindle() {
		return overrideSpindle;
	}

	public void setOverrideSpindle(String overrideSpindle) {
		this.overrideSpindle = overrideSpindle;
	}

	public String getOverrideFeed() {
		return overrideFeed;
	}

	public void setOverrideFeed(String overrideFeed) {
		this.overrideFeed = overrideFeed;
	}

	public String getSpindleMode() {
		return spindleMode;
	}

	public void setSpindleMode(String spindleMode) {
		this.spindleMode = spindleMode;
	}
	
}
