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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import uis.stockapp.dto.SingleStockDTO;
import uis.stockapp.dto.StockSymbolDTO;

public class StockAPICall {
	
	private static final Logger logger = LogManager.getLogger();

	
	
	public List<StockSymbolDTO> fetchSymbolFromAPI() {
		try {
			
			String url="https://cloud.iexapis.com/beta/ref-data/symbols?filter=symbol,name,exchange&token=pk_f2ef8ff533a14e56994e437f4da5cc2a";
			
			//Calling API
			ResponseEntity<String> responseEntity = new RestTemplate().getForEntity(url, String.class);
			
			//Casting it into java object
			List<StockSymbolDTO> map = new ObjectMapper().readValue(responseEntity.getBody(), new TypeReference<List<StockSymbolDTO>>(){});
		
			return map;
		} catch (IOException e) {
			logger.info(">>>>>>>>>>>> EXCEPTION >>>>>>>>> :: fetchSymbolFromAPI :: ");
			logger.info(">>>>>>>>>>>> MESSAGE >>>>>>>>> "+ e.getLocalizedMessage());
			logger.info(">>>>>>>>>>>> END");
			e.printStackTrace();
			return null;
		}
		
	}
	public SingleStockDTO fetchStockInfo(String stockSymbol) {
		try {
			
			String url="https://cloud.iexapis.com/stable/stock/market/batch?symbols="+stockSymbol+"&types=quote&filter=open,close,symbol,change,low,high,latestPrice,changePercent,week52High,week52Low,primaryExchange&token=pk_f2ef8ff533a14e56994e437f4da5cc2a";
			
			//Calling API
			ResponseEntity<Map> responseEntity = new RestTemplate().getForEntity(url, Map.class);
			
			//Casting it into java object
			SingleStockDTO map = new ObjectMapper().readValue(new JSONObject(responseEntity.getBody()).getJSONObject(stockSymbol.toUpperCase()).getJSONObject("quote").toString(), SingleStockDTO.class);
		
			return map;
		} catch (IOException e) {
			logger.info(">>>>>>>>>>>> EXCEPTION >>>>>>>>> :: fetchStockInfo :: ");
			logger.info(">>>>>>>>>>>> MESSAGE >>>>>>>>> "+ e.getLocalizedMessage());
			logger.info(">>>>>>>>>>>> END");
			e.printStackTrace();
			return null;
		}
		
	}
	public Map<String, SingleStockDTO> fetchMultipleStockInfo(String stockSymbol) {
		try {
			
			String url="https://cloud.iexapis.com/stable/stock/market/batch?symbols="+stockSymbol+"&types=quote&filter=open,close,symbol,change,low,high,latestPrice,changePercent,week52High,week52Low,primaryExchange&token=pk_f2ef8ff533a14e56994e437f4da5cc2a";
			
			//Calling API
			ResponseEntity<String> responseEntity = new RestTemplate().getForEntity(url, String.class);
			
			//Casting it into java object
			Map map = new ObjectMapper().readValue(responseEntity.getBody(), Map.class);
			
			Map<String, SingleStockDTO> records=new HashMap<>();
			
			for(Object key : map.keySet()) {
				JSONObject sym = new JSONObject((Map)map.get(key));
				SingleStockDTO data= new ObjectMapper().readValue(sym.getJSONObject("quote").toString(), SingleStockDTO.class);
				records.put(key.toString(), data);
			}
			
		
			return records;
		} catch (IOException e) {
			logger.info(">>>>>>>>>>>> EXCEPTION >>>>>>>>> :: fetchStockInfo :: ");
			logger.info(">>>>>>>>>>>> MESSAGE >>>>>>>>> "+ e.getLocalizedMessage());
			logger.info(">>>>>>>>>>>> END");
			e.printStackTrace();
			return null;
		}
		
		
	}
	
//	public static void main(String[] args) {
//		new DemoAPICall().fetchDataFromSanboxAPI();
//	}
	
}
