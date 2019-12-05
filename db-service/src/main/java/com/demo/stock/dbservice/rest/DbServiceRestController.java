package com.demo.stock.dbservice.rest;

import java.util.List;
import java.util.stream.Collectors;

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

@RestController
@RequestMapping("/rest/db")
public class DbServiceRestController {

	@Autowired
	QuotesRepository quotesRepository;

	@GetMapping("/{username}")
	public List<String> getQuotes(@PathVariable("username") final String username) {
		quotesRepository.findByUserName(username).stream().map(quote -> quote.getQuote()).collect(Collectors.toList());
		return quotesRepository.findByUserName(username).stream().map(Quote::getQuote).collect(Collectors.toList());
	}

	@PostMapping("/add")
	public List<String> add(@RequestBody final Quotes quotes) {

//		quotes.getQuotes().stream().forEach(quote -> {quotesRepository.save(new Quote (quotes.getUserName(), quote));});
		quotes.getQuotes().stream().map(quote -> new Quote(quotes.getUserName(), quote)) // functional programming
				.forEach(quote -> quotesRepository.save(quote));

		return getQuotesByUserName(quotes.getUserName());
	}

	@PostMapping("/delete/{username}")
	public List<String> delete(@PathVariable("username") final String username) {

		List<Quote> quotes = quotesRepository.findByUserName(username);
		quotes.stream().forEach(quote -> {
			quotesRepository.delete(quote);
		});

		return getQuotesByUserName(username);
	}

	private List<String> getQuotesByUserName(String userName) {

		return quotesRepository.findByUserName(userName).stream().map(Quote::getQuote).collect(Collectors.toList());
	}
}
