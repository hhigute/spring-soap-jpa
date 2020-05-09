package com.h3b.investment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h3b.investment.exception.ServiceFaultException;
import com.h3b.investment.model.BccIndexEntity;
import com.h3b.investment.repository.BccIndexRespository;
import com.h3b.investment.webservice.ServiceStatus;

@Service
public class BccIndexService {
	@Autowired
	private BccIndexRespository bccIndexRespository;
	
	public BccIndexEntity getBccIndex(int code){
		
		Optional<BccIndexEntity> optionalBccIndexEntity = bccIndexRespository.findById(code);
		
		if(optionalBccIndexEntity == null || !optionalBccIndexEntity.isPresent())
			return null;
		
		return optionalBccIndexEntity.get();
		
	}
	

	public List<BccIndexEntity> listBccIndex() {
		List<BccIndexEntity> listEntity = bccIndexRespository.findAll();
		return listEntity;
		
	}
	
	public BccIndexEntity createBccIndexRequest(BccIndexEntity requestEntity) {
		BccIndexEntity createdBccIndexEntity = bccIndexRespository.save(requestEntity);
		return createdBccIndexEntity;
	}
	
	
	public BccIndexEntity updateBccIndexRequest(BccIndexEntity requestEntity) {
		int indexCode = requestEntity.getCode();
		BccIndexEntity auxEntity = bccIndexRespository.findById(indexCode).orElseThrow(
																						()-> {
																							String errorMessage = "ERROR";
																							ServiceStatus serviceStatus = new ServiceStatus();
																							serviceStatus.setStatusCode("NOT_FOUND");
																							serviceStatus.setMessage("Index BCC " + indexCode + " not found");
																							throw new ServiceFaultException(errorMessage, serviceStatus);
																						});
		
		auxEntity.setName(requestEntity.getName());
		auxEntity.setDescription(requestEntity.getDescription());
		auxEntity.setVariation(requestEntity.getVariation());
		BccIndexEntity entityUpdated = bccIndexRespository.save(auxEntity);
		
		return entityUpdated;

		
	}
}
