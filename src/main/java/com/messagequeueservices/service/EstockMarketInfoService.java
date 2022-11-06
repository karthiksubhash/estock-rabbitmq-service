package com.messagequeueservices.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.messagequeueservices.dto.EstockInfoModelDTO;
import com.messagequeueservices.dto.StockInfoModelDTO;
import com.messagequeueservices.model.EstockInfoModel;
import com.messagequeueservices.model.StockModel;
import com.messagequeueservices.repository.EstockInfoRepository;

@Service
@Transactional
public class EstockMarketInfoService {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(EstockMarketInfoService.class);
	
	@Autowired 
	private EstockInfoRepository estockInfoRepository;
	
	public EstockInfoModel saveCompanyInfo(EstockInfoModel estockInfoModel) {
		LOGGER.info("saveCompanyInfo into MangoDB");
		LOGGER.info("saveCompanyInfo :: estockInfoModel: "+ estockInfoModel);
		estockInfoRepository.save(estockInfoModel);
		return estockInfoModel;
	}

	public EstockInfoModelDTO findInfoById(Long id) {
		
		Optional<EstockInfoModel> estockInfoModel= estockInfoRepository.findById(id);
		
		if(estockInfoModel.isPresent()) {
			EstockInfoModelDTO estockInfoModelDTO = new EstockInfoModelDTO();
			EstockInfoModel estockInfo = estockInfoModel.get();
			
			estockInfoModelDTO.mapperToDTO(estockInfo, estockInfoModelDTO);
			if(null != estockInfoModelDTO.getId()) {
				return estockInfoModelDTO;
			}
		}
		return null;	
	}


	public void saveStockInfo(StockInfoModelDTO stockModelDTO) {
		if(null != stockModelDTO.getCompanyCode()) {
			Optional<EstockInfoModel> estockInfo = estockInfoRepository.findById(stockModelDTO.getCompanyCode());
			if(estockInfo.isPresent()) {
				StockModel stockModel= new StockModel();
				StockInfoModelDTO stockInfo = new StockInfoModelDTO();
				
				EstockInfoModel estock =estockInfo.get(); 
				List<StockModel> stocksList= estock.getStocksList() ;
				if(null != stocksList && !stocksList.isEmpty()) {
					stockInfo.mapperToModel(stockModel,stockModelDTO);
					stocksList.add(stockModel);
					estockInfoRepository.save(estock);
				}
				else {	
					List<StockModel> newStocksList = new ArrayList<StockModel>();
					stockInfo.mapperToModel(stockModel,stockModelDTO);
					newStocksList.add(stockModel);
					estock.setStocksList(newStocksList);
					estockInfoRepository.save(estock);
				}
			}
		}	
	}

	public void deleteCompany(Long code) {
		estockInfoRepository.deleteById(code);
	}
}
