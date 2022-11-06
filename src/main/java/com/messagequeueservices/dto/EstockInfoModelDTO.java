package com.messagequeueservices.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;

import com.messagequeueservices.model.EstockInfoModel;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@ToString 
public class EstockInfoModelDTO {

	@Id
	private Long id;
	private String companyName;
	private String turnover;
	private String ceo;
	private String website;
	private String stockexchangeenlisted;
	private String dateTime;
	private List<StockInfoModelDTO> stocksList;
	
	private static final Logger LOGGER=LoggerFactory.getLogger(EstockInfoModelDTO.class);

	public String getStockexchangeenlisted() {
		return stockexchangeenlisted;
	}

	public void setStockexchangeenlisted(String stockexchangeenlisted) {
		this.stockexchangeenlisted = stockexchangeenlisted;
	}

	public String getDateTime() { 
		return dateTime; 
	}

	public void setDateTime(String dateTime) { 
		this.dateTime = dateTime; 
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getTurnover() {
		return turnover;
	}
	public void setTurnover(String turnover) {
		this.turnover = turnover;
	}
	public String getCeo() {
		return ceo;
	}
	public void setCeo(String ceo) {
		this.ceo = ceo;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public List<StockInfoModelDTO> getStocksList() {
		return stocksList;
	}
	public void setStocksList(List<StockInfoModelDTO> stocksList) {
		this.stocksList = stocksList;
	}

	public void mapperToDTO(EstockInfoModel estockInfo, EstockInfoModelDTO estockInfoModelDTO) {
		estockInfoModelDTO.setCeo(estockInfo.getCeo());
		estockInfoModelDTO.setCompanyName(estockInfo.getCompanyName());
		estockInfoModelDTO.setId(estockInfo.getId());
		estockInfoModelDTO.setStockexchangeenlisted(estockInfo.getStockexchangeenlisted());
		estockInfoModelDTO.setTurnover(estockInfo.getTurnover());
		estockInfoModelDTO.setWebsite(estockInfo.getWebsite());
		estockInfoModelDTO.setDateTime(estockInfo.getDateTime().toString());
		if(null != estockInfo.getStocksList() && !estockInfo.getStocksList().isEmpty()) {
			StockInfoModelDTO stockInfoModelDTO = new StockInfoModelDTO();
			List<StockInfoModelDTO> stocksList = stockInfoModelDTO.mapperToDTOList(estockInfo.getStocksList());
			if(null != stocksList && !stocksList.isEmpty()) {
				estockInfoModelDTO.setStocksList(stocksList);
			}
		}
	}	
	
	public void mapperToModel(EstockInfoModel estockInfo, EstockInfoModelDTO estockInfoModelDTO) {
		estockInfo.setCeo(estockInfoModelDTO.getCeo());
		estockInfo.setCompanyName(estockInfoModelDTO.getCompanyName());
		estockInfo.setId(estockInfoModelDTO.getId());
		estockInfo.setStockexchangeenlisted(estockInfoModelDTO.getStockexchangeenlisted());
		estockInfo.setTurnover(estockInfoModelDTO.getTurnover());
		estockInfo.setWebsite(estockInfoModelDTO.getWebsite());
		
		try {
			DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.S", Locale.ENGLISH);
			LocalDateTime dateTime = LocalDateTime.parse(estockInfoModelDTO.getDateTime(), inputFormatter);
			estockInfo.setDateTime(dateTime);
		}catch (Exception ex) {
			LOGGER.error(ex.toString());
		}
		if(null != estockInfoModelDTO.getStocksList() && !estockInfoModelDTO.getStocksList().isEmpty()) {
			StockInfoModelDTO StockInfoModelDTO = new StockInfoModelDTO();
			List<StockInfoModelDTO> stocksList = StockInfoModelDTO.mapperToDTOList(estockInfo.getStocksList());
			if(null != stocksList && !stocksList.isEmpty()) {
				estockInfoModelDTO.setStocksList(stocksList);
			}
		}
	}	
}
