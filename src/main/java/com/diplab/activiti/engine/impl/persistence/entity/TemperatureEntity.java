package com.diplab.activiti.engine.impl.persistence.entity;

import java.util.Calendar;

import org.activiti.engine.impl.persistence.entity.ByteArrayRef;
import org.activiti.engine.impl.persistence.entity.JobEntity;
import org.springframework.util.SerializationUtils;

import com.diplab.activiti.Constant.TemperatureMode;
import com.diplab.activiti.engine.impl.jobexecutor.TemperatureDeclarationImpl;

public class TemperatureEntity extends JobEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -252628523662521870L;

	private double condition;
	private TemperatureMode mode;
	private String sensor_id;
	private String self;

	public String getSelf() {
		return self;
	}

	public void setSelf(String self) {
		this.self = self;
	}

	public double getCondition() {
		return condition;
	}

	public void setCondition(double condition) {
		this.condition = condition;
	}

	public TemperatureMode getMode() {
		return mode;
	}

	public void setMode(TemperatureMode mode) {
		this.mode = mode;
	}

	public String getSensor_id() {
		return sensor_id;
	}

	public void setSensor_id(String sensor_id) {
		this.sensor_id = sensor_id;
	}

	public TemperatureEntity(TemperatureDeclarationImpl declaration) {
		jobHandlerType = declaration.getJobHandlerType();
		condition = declaration.getCondition();
		mode = declaration.getMode();
		sensor_id = declaration.getId();
		Calendar instance = Calendar.getInstance();
		// instance.add(Calendar.YEAR, 3);
		duedate = instance.getTime();

		ByteArrayRef ref = new ByteArrayRef();
		ref.setValue("self", SerializationUtils.serialize(this));
		setSelf(ref.getId());
	}

	public TemperatureEntity() {

	}
}
