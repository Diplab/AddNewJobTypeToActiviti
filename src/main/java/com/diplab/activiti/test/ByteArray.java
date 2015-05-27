package com.diplab.activiti.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ByteArrayRef;

import com.diplab.activiti.engine.impl.cfg.DipProcessEngineConfiguration;

public class ByteArray {

	public static void main(String[] args) throws InterruptedException {
		ProcessEngineConfigurationImpl config = new DipProcessEngineConfiguration();
		config.setJdbcUrl("jdbc:h2:tcp://localhost/Activiti");
		// config.setDatabaseSchemaUpdate("drop-create");
		// config.setJobExecutorActivate(true);

		final ProcessEngine processEngine = config.buildProcessEngine();

		processEngine.getManagementService().executeCommand(
				new Command<Void>() {

					@Override
					public Void execute(CommandContext commandContext) {
						ByteArrayRef byteArrayRef = new ByteArrayRef();
						try {
							byteArrayRef.setValue("name",
									serialize(new Temperature("HI")));
							System.out.println(byteArrayRef.getId());

							ByteArrayRef byteArrayRef2 = new ByteArrayRef(
									byteArrayRef.getId());
							System.out.println(deserialize(byteArrayRef2
									.getBytes()));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						return null;
					}
				});

	}

	static class Temperature implements Serializable {
		String name;

		@Override
		public String toString() {
			return "Temperature [name=" + name + "]";
		}

		public Temperature(String name) {
			super();
			this.name = name;
		}
	}

	public static byte[] serialize(Object obj) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(out);
		os.writeObject(obj);
		return out.toByteArray();
	}

	public static Object deserialize(byte[] data) throws IOException,
			ClassNotFoundException {
		ByteArrayInputStream in = new ByteArrayInputStream(data);
		ObjectInputStream is = new ObjectInputStream(in);
		return is.readObject();
	}
}
