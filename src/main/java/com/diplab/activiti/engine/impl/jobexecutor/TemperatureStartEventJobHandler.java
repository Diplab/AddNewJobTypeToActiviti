package com.diplab.activiti.engine.impl.jobexecutor;

import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.jobexecutor.JobHandler;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.JobEntity;

public class TemperatureStartEventJobHandler implements JobHandler {

	public static final String TYPE = "temperature-start-event";

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public void execute(JobEntity job, String configuration,
			ExecutionEntity execution, CommandContext commandContext) {
		// TODO Auto-generated method stub

	}

}
