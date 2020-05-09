package com.h3b.investment.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.h3b.investment.model.BccIndexEntity;
import com.h3b.investment.service.BccIndexService;
import com.h3b.investment.webservice.BccIndex;
import com.h3b.investment.webservice.CreateBccIndexRequest;
import com.h3b.investment.webservice.CreateBccIndexResponse;
import com.h3b.investment.webservice.GetBccIndexRequest;
import com.h3b.investment.webservice.GetBccIndexResponse;
import com.h3b.investment.webservice.ListBccIndexResponse;
import com.h3b.investment.webservice.ServiceStatus;
import com.h3b.investment.webservice.UpdateBccIndexRequest;
import com.h3b.investment.webservice.UpdateBccIndexResponse;

@Endpoint
public class BccIndexEndpoint {
	
	private static final String NAMESPACE_URI = "http://h3b.com/investment/webservice";
	
	@Autowired
	private BccIndexService bccIndexService;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBccIndexRequest")
	@ResponsePayload
	public GetBccIndexResponse getBccIndex(@RequestPayload GetBccIndexRequest request){
		
		BccIndexEntity entity = bccIndexService.getBccIndex(request.getIndexCode());

		if(entity == null)
			return new GetBccIndexResponse(); 
		
		BccIndex bccIndex = new BccIndex();
		bccIndex.setCode(entity.getCode());
		bccIndex.setName(entity.getName());
		bccIndex.setVariation(entity.getVariation());
		bccIndex.setDescription(entity.getDescription());
			
		GetBccIndexResponse response = new GetBccIndexResponse();
		response.setBccIndex(bccIndex);
		
		return response;
		
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "listBccIndexRequest")
	@ResponsePayload
	public ListBccIndexResponse listBccIndex() {
		
		ListBccIndexResponse response = new ListBccIndexResponse();
		List<BccIndexEntity> listEntity = bccIndexService.listBccIndex();
		
		for(BccIndexEntity entity: listEntity) {
			BccIndex bccIndex = new BccIndex();
			bccIndex.setCode(entity.getCode());
			bccIndex.setName(entity.getName());
			bccIndex.setVariation(entity.getVariation());
			bccIndex.setDescription(entity.getDescription());
			response.getBccIndex().add(bccIndex);
		}
		
		return response;
		
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "createBccIndexRequest")
	@ResponsePayload
	public CreateBccIndexResponse createBccIndexRequest(@RequestPayload CreateBccIndexRequest request) {
		CreateBccIndexResponse response = new CreateBccIndexResponse();
		ServiceStatus serviceMessage = new ServiceStatus();
		
		BccIndexEntity entity = new BccIndexEntity();
		entity.setCode(request.getBccIndex().getCode());
		entity.setName(request.getBccIndex().getName());
		entity.setVariation(request.getBccIndex().getVariation());
		entity.setDescription(request.getBccIndex().getDescription());
		
		bccIndexService.createBccIndexRequest(entity);
		
		serviceMessage.setStatusCode("SUCCESS");
		serviceMessage.setMessage("BCC Index created.");
		response.setServiceMessage(serviceMessage);
		return response;
		
	}
	
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateBccIndexRequest")
	@ResponsePayload
	public UpdateBccIndexResponse updateBccIndexRequest(@Validated @RequestPayload UpdateBccIndexRequest request) {
		
		UpdateBccIndexResponse response = new UpdateBccIndexResponse();
		ServiceStatus serviceMessage = new ServiceStatus();
		
		
		BccIndexEntity entity = new BccIndexEntity();
		entity.setCode(request.getBccIndex().getCode());
		entity.setName(request.getBccIndex().getName());
		entity.setVariation(request.getBccIndex().getVariation());
		entity.setDescription(request.getBccIndex().getDescription());
		
		bccIndexService.updateBccIndexRequest(entity);
		
		serviceMessage.setStatusCode("SUCCESS");
		serviceMessage.setMessage("BCC Index updated.");
		response.setServiceMessage(serviceMessage);
		return response;

		
	}

	
	
}
