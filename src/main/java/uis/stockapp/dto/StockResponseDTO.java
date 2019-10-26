package uis.stockapp.dto;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StockResponseDTO {
	
	@JsonProperty("chart")
	private List<Map<String, Object>> chart;
	
	@JsonProperty("quote")
	private Map<String, Object> quote;


	public List<Map<String, Object>> getChart() {
		return chart;
	}

	public void setChart(List<Map<String, Object>> chart) {
		this.chart = chart;
	}

	public Map<String, Object> getQuote() {
		return quote;
	}

	public void setQuote(Map<String, Object> quote) {
		this.quote = quote;
	}
}
