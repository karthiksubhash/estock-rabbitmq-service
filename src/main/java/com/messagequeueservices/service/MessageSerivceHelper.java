package com.messagequeueservices.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.messagequeueservices.dto.CompanyInfoModelDTO;
import com.messagequeueservices.dto.StockInfoModelDTO;
import com.messagequeueservices.model.EstockInfoModel;

@Service
public class MessageSerivceHelper {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(MessageSerivceHelper.class);
	
	@Autowired
	private EstockMarketInfoService estockMarketInfoService;
	
	@RabbitListener(queues = "Queue-AddStock")
	public void addNewStockInfo(StockInfoModelDTO stockInfoModelDTO) {
		LOGGER.info("From Queue-AddStock : {}");
		LOGGER.info("From Queue-AddStock : {}", stockInfoModelDTO);
		estockMarketInfoService.saveStockInfo(stockInfoModelDTO);
	}
	
	@RabbitListener(queues = "Queue-DeleteCompanyDetials")
	public void getDeleteCompnayCode(Long code) {
		LOGGER.info("Queue-DeleteCompanyDetials :"+ code);
		estockMarketInfoService.deleteCompany(code);
	}
	
	@RabbitListener(queues = "Queue-CreateCompanyDetials")
	public void getCompanyInfoFromQueue(CompanyInfoModelDTO companyInfoModelDTO) {	

		LOGGER.info("From Queue-CreateCompanyDetials : {}", companyInfoModelDTO);
		EstockInfoModel estockInfoModel = new EstockInfoModel() ;
		companyInfoModelDTO.mapperDTOToModel(estockInfoModel, companyInfoModelDTO);
		LOGGER.info("saveCompanyInfo :: Queue:: estockInfoModel: "+ estockInfoModel);
		estockMarketInfoService.saveCompanyInfo(estockInfoModel);
	}
}
