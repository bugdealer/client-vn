package com.example;

import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import com.example.dpinventory.DpInventory;
import com.example.dpresource.DpResource;
import com.example.dpresource.Node;
import com.example.dpresource.TerminationPoint;
import com.example.properties.MongoProperties;
import com.example.properties.OdlProperties;
import com.mongodb.DBObject;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;


public class UpdateHandler implements Runnable {
	
	private String updateURI;
	private List<String> DpidList;
	

  private MongoTemplate mongoTemplate;

  
	
	
	public UpdateHandler(String updateURI){
	
		DpidList = new ArrayList<String>();
		this.updateURI = updateURI;
		mongoTemplate = (MongoTemplate) SpringContextUtil.getBean("mongoTemplate");
		
	}
	
	
	@Override
	public void run() {
		
	
		JSONObject topo = new JSONObject();
		JSONObject dp = new JSONObject();
		
		topo = doUpdate(updateURI+"/restconf/operational/network-topology:network-topology");
		dp = doUpdate(updateURI+"/restconf/operational/opendaylight-inventory:nodes");
		
		updateTopo(topo);
		updateInventory(topo);
		updateResource(dp);
		
	}

	public  JSONObject doUpdate(String updateURI){
		System.out.println("Update URI: " + updateURI);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("AUTHORIZATION", "Basic YWRtaW46YWRtaW4=");
		RestTemplate restTemplate = new RestTemplate();
		
		HttpEntity<String> getEntity = new HttpEntity<String>(headers);
		HttpEntity<String> response = null;
		try {
			response = restTemplate.exchange(new URI(updateURI),HttpMethod.GET,getEntity,String.class);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		System.out.println("topo: "+response.getBody());
		
		return new JSONObject(response.getBody());
		
		
	}
	
	public  void updateTopo(JSONObject rsbody){
		
		
		System.out.println(rsbody);
		
		String doc="";
		try {
			doc = rsbody.getJSONObject("network-topology").getJSONArray("topology").getJSONObject(0).toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(doc);
	
		
		DBObject dbObject = (DBObject)JSON.parse(doc);
		
		try {
			mongoTemplate.getCollection("topo").drop();
			mongoTemplate.getCollection("topo").save(dbObject);
		} catch (MongoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void updateInventory(JSONObject rsbody){
		
		List<DpInventory> DpList = new ArrayList<DpInventory>();
		JSONArray InventoryArray = new JSONArray();
		String NodeId;
		try {
			InventoryArray = rsbody.getJSONObject("network-topology").getJSONArray("topology").getJSONObject(0).getJSONArray("node");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//System.out.println(InventoryArray.length());
		for(int i=0;i<InventoryArray.length();i++)
		{
			NodeId = InventoryArray.getJSONObject(i).getString("node-id");
			if(!DpidList.contains(NodeId))
			{
				DpidList.add(NodeId);
				DpInventory tempDp = new DpInventory();
				tempDp.setDpId(Integer.parseInt(NodeId.substring(9)));
				DpList.add(tempDp);
				
			}
			
		}
		System.out.println(DpidList);
		//System.out.println(DpList);
		
		for (DpInventory dpInventory : DpList) {
			
			String uri =updateURI+"/restconf/operational/opendaylight-inventory:nodes/node/openflow:"+ dpInventory.getDpId()+"/flow-node-inventory:switch-features";
			JSONObject table = new JSONObject();
			Integer tableSize;
			table = doUpdate(uri);
			try{
			tableSize = (Integer)table.getJSONObject("flow-node-inventory:switch-features").get("max_tables");
		
			dpInventory.setTableSize(tableSize);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		
		System.out.println(DpList);
		if(DpList.size()>0)
		{
		
	
			try {
				mongoTemplate.insertAll(DpList);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			
	
		}
		
	
	}
	
	public void updateResource(JSONObject rsbody){
		
		JSONArray DpArray = new JSONArray();
		JSONArray tpArray = new JSONArray();
		
		DpResource dpResource = new DpResource();
		List<Node> nodes = new ArrayList<Node>();
		try {
			DpArray = rsbody.getJSONObject("nodes").getJSONArray("node");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		for(int i=0;i<DpArray.length();i++)
		{
			Node temp = new Node();
			List<TerminationPoint> terminationPoints = new ArrayList<TerminationPoint>();
			JSONObject resource = DpArray.getJSONObject(i);
			temp.setNodeId(resource.getString("id"));
			temp.setMaxTables(resource.getJSONObject("flow-node-inventory:switch-features").getInt("max_tables"));
			
			tpArray = DpArray.getJSONObject(i).getJSONArray("node-connector");
			for( int j=0;j<tpArray.length();j++){
				TerminationPoint tp = new TerminationPoint();
				if(tpArray.getJSONObject(j).getString("id").contains("LOCAL"))
					continue;
				tp.setTpId(tpArray.getJSONObject(j).getString("id"));
				tp.setMaximumSpeed(tpArray.getJSONObject(j).getInt("flow-node-inventory:maximum-speed"));
				tp.setCurrentSpeed(tpArray.getJSONObject(j).getInt("flow-node-inventory:current-speed"));
				terminationPoints.add(tp);
			}
			

			
			temp.setTerminationPoint(terminationPoints);
			nodes.add(temp);
			
		}			
		
		dpResource.setNode(nodes);
		
		try {
			mongoTemplate.dropCollection("dpResource");
			mongoTemplate.insert(dpResource);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		
	}


	
	
	
	

}
