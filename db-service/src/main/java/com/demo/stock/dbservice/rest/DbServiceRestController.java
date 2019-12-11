package com.demo.stock.dbservice.rest;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.stock.dbservice.entity.Quote;
import com.demo.stock.dbservice.model.Quotes;
import com.demo.stock.dbservice.repo.QuotesRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/rest/db")
@Api(value = "Stock Management System for Quote", description = "Operations pertaining to user in Stock Management System")
public class DbServiceRestController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	QuotesRepository quotesRepository;

	@ApiOperation(value = "Get Quote for user.")
	@GetMapping("/{username}")
	public List<String> getQuotes(@PathVariable("username") final String username) {
		LOGGER.info("Getting quote for user: " + username);
		quotesRepository.findByUserName(username).stream().map(quote -> quote.getQuote()).collect(Collectors.toList());
		return quotesRepository.findByUserName(username).stream().map(Quote::getQuote).collect(Collectors.toList());
	}

	@ApiOperation(value = "Add Quote for user.")
	@PostMapping("/add")
	public List<String> add(@RequestBody final Quotes quotes) {
		LOGGER.info("Adding quote for user: " + quotes.getUserName());
//		quotes.getQuotes().stream().forEach(quote -> {quotesRepository.save(new Quote (quotes.getUserName(), quote));});
		quotes.getQuotes().stream().map(quote -> new Quote(quotes.getUserName(), quote)) // functional programming
				.forEach(quote -> quotesRepository.save(quote));

		return getQuotesByUserName(quotes.getUserName());
	}

	@ApiOperation(value = "Delete Quote for user.")
	@PostMapping("/delete/{username}")
	public List<String> delete(@PathVariable("username") final String username) {
		LOGGER.info("Deleting quote for user: " + username);
		List<Quote> quotes = quotesRepository.findByUserName(username);
		quotes.stream().forEach(quote -> {
			quotesRepository.delete(quote);
		});

		return getQuotesByUserName(username);
	}

	private List<String> getQuotesByUserName(String userName) {
		LOGGER.info("Getting quote for user: " + userName);
		return quotesRepository.findByUserName(userName).stream().map(Quote::getQuote).collect(Collectors.toList());
	}

	@ApiOperation(value = "Dummy for elk test log.")
	@RequestMapping(value = "/elk")
	public String helloWorld() {
		String response = "Welcome to JavaInUse" + new Date();
		LOGGER.info(response);

		return response;
	}

	@ApiOperation(value = "Dummy for elk test log.")
	@RequestMapping(value = "/exception")
	public String exception() {
		String response = "";
		try {
			throw new Exception("Exception has occured....");
		} catch (Exception e) {
			e.printStackTrace();
//			LOGGER.error(e);

			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String stackTrace = sw.toString();
			LOGGER.error("Exception - " + stackTrace);
			response = stackTrace;
		}

		return response;
	}
}
