package uis.stockapp.apiCall;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import uis.stockapp.dto.StockResponseDTO;

public class DemoAPICall {
	
	private static final Logger logger = LogManager.getLogger();

	
	public void fetchDataFromSanboxAPI() {
		try {
			
			String url="https://sandbox.iexapis.com/stable/stock/market/batch?symbols=aapl,fb,goog&types=quote,chart&range=1m&last=1&token=Tpk_1c095a89eeea47339f8f9b530276818d";
			
			//Calling API
			ResponseEntity<String> responseEntity = new RestTemplate().getForEntity(url, String.class);
			
			//Casting it into java object
			Map<String, StockResponseDTO> map = new ObjectMapper().readValue(responseEntity.getBody(), Map.class);
		
			
		} catch (IOException e) {
			logger.info(">>>>>>>>>>>> EXCEPTION >>>>>>>>> :: fetchDataFromSandboxAPI :: ");
			logger.info(">>>>>>>>>>>> MESSAGE >>>>>>>>> "+ e.getLocalizedMessage());
			logger.info(">>>>>>>>>>>> END");
			e.printStackTrace();
		}
		
	}
	public void fetchDataFromSanboxAPI_2() {
		try {
			
			String url="https://sandbox.iexapis.com/stable/stock/market/batch?symbols=aapl,fb,goog&types=quote,chart&range=1m&last=1&token=Tpk_1c095a89eeea47339f8f9b530276818d";
			
			//Calling API
			ResponseEntity<String> responseEntity = new RestTemplate().getForEntity(url, String.class);
			
			//Casting it into java object
			Map<String, StockResponseDTO> map = new ObjectMapper().readValue(responseEntity.getBody(), Map.class);
		
			
		} catch (IOException e) {
			logger.info(">>>>>>>>>>>> EXCEPTION >>>>>>>>> :: fetchDataFromSandboxAPI :: ");
			logger.info(">>>>>>>>>>>> MESSAGE >>>>>>>>> "+ e.getLocalizedMessage());
			logger.info(">>>>>>>>>>>> END");
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new DemoAPICall().fetchDataFromSanboxAPI();
	}
	
}
