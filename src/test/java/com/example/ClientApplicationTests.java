package com.example;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.dpinventory.DpInventory;
import com.example.properties.MongoProperties;
import com.example.properties.OdlProperties;
import com.example.properties.StreamProperties;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ClientApplicationTests.class)
@ComponentScan
public class ClientApplicationTests {

	@Autowired
	private OdlProperties odlProperties;
	
	@Autowired
	private StreamProperties streamProperties;
	
	@Autowired
	private MongoProperties mongoProperties;
	
	//private ConfigGenerator configGenerator;
	
	
	
	@Test
	public void test(){
		
//		System.out.println("odl address: "+odlProperties.getAddress());
		System.out.println(mongoProperties.getAddress());
		
		//System.out.println("stream path: " + streamProperties.getPath().get(0));
		
		//ScheduledExecutorService scheduledExecutorService =
		 //       Executors.newScheduledThreadPool(2);
		//ScheduledFuture scheduledFuture =
			//    scheduledExecutorService.scheduleAtFixedRate(new ConfigGenerator(), 2, 2, TimeUnit.SECONDS);
		
//		mongoConnector = new MongoConnector();
//		System.out.println(mongoConnector.getMongoClient().toString());
		/*String teString = "openflow:333";
		
		System.out.println(Integer.parseInt(teString.substring(9)));
		UpdateHandler updateHandler = new UpdateHandler("");
		JSONObject test = new JSONObject();
		test = updateHandler.doUpdate("http://172.17.17.2:8181/restconf/operational/network-topology:network-topology");
		updateHandler.updateInventory(test);*/
		
		/*List<DpInventory> Dplist = new ArrayList<DpInventory>();
		DpInventory tempdp = new DpInventory();
		tempdp.setDpId(1);
		tempdp.setTableSize(64);
		Dplist.add(tempdp);
		tempdp.setDpId(2);
		tempdp.setTableSize(50);
		Dplist.add(tempdp);
		
		System.out.println(Dplist.get(0));
		
		
		UpdateHandler updateHandler = new UpdateHandler("http://172.17.17.9:8181/restconf/operational/network-topology:network-topology");
		Thread uThread = new Thread(updateHandler);
		uThread.start();
		*/
		
		
		
	}
	
	

}
