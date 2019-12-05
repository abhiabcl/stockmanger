package com.demo.stock.stockservice.rest;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.stock.StockQuote;

@RestController
@RequestMapping("/rest/stock")
public class StockResourceRestController {

	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/{username}")
	public List<Quote> getStock(@PathVariable("username") final String userName) {

//		List<String> quotes = restTemplate.getForEntity("http://localhost:8300/rest/db/"+ userName, List.class);
		// db-service is register with eureka - so we can use the service name in place of ip/port
		ResponseEntity<List<String>> quoteResponse = restTemplate.exchange("http://db-service/rest/db/" + userName,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {
				});

		List<String> quotes = quoteResponse.getBody();

		return quotes.stream().map(quote -> {
			Stock stock = getStockprice(quote);
			return new Quote(quote, stock.getQuote().getPrice());
		}).collect(Collectors.toList());

	}

	private Stock getStockprice(String quote) {
		try {
			return YahooFinance.get(quote);
		} catch (IOException e) {
			e.printStackTrace();
			return new Stock(quote);
		}
	}

}
