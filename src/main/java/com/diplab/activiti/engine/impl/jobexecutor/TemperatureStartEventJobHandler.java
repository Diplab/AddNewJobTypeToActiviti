package com.diplab.activiti.engine.impl.jobexecutor;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.jobexecutor.JobHandler;
import org.activiti.engine.impl.persistence.entity.ByteArrayRef;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.JobEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.SerializationUtils;

import com.diplab.activiti.engine.impl.persistence.entity.TemperatureEntity;
import com.diplab.activiti.temperature.Temperature;
import com.diplab.activiti.temperature.TemperatureReceiver;

public class TemperatureStartEventJobHandler implements JobHandler {
	private Logger logger = LoggerFactory
			.getLogger(TemperatureStartEventJobHandler.class);

	public static final String TYPE = "temperature-start-event";

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public void execute(JobEntity job, String configuration,
			ExecutionEntity execution, CommandContext commandContext) {
		ByteArrayRef ref = new ByteArrayRef(configuration);
		TemperatureEntity entity = (TemperatureEntity) SerializationUtils
				.deserialize(ref.getBytes());

		TemperatureReceiver receiver = TemperatureReceiver
				.getReceiverByDeviceId(entity.getSensor_id());
		if (receiver == null) {
			throw new ActivitiException(String.format(
					"%s sensor is not presenting yet", entity.getSensor_id()));
		}
		Temperature temperature = receiver.getTemperature();
		logger.info(String.format("get Temperature = %f from %s",
				temperature.getTemperature(), entity.getSensor_id()));
	}
}
