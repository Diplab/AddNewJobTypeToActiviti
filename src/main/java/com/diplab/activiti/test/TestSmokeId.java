package com.diplab.activiti.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;

import com.diplab.activiti.engine.impl.cfg.DipProcessEngineConfiguration;

public class TestSmokeId {

	public static void main(String[] args) throws InterruptedException {
		ProcessEngineConfigurationImpl config = new DipProcessEngineConfiguration();
		config.setJdbcUrl("jdbc:h2:tcp://localhost/Activiti");
		config.setDatabaseSchemaUpdate("drop-create");
		config.setJobExecutorActivate(true);

		final ProcessEngine processEngine = config.buildProcessEngine();

		processEngine.getRepositoryService().createDeployment()
				.disableSchemaValidation().disableBpmnValidation()
				.addClasspathResource("smokeID.bpmn").deploy();

	}
}
