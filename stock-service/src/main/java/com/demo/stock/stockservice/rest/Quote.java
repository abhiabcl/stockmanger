package com.demo.stock.stockservice.rest;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about the stock. ")
public class Quote {

	@ApiModelProperty(notes = "Stock name for price as per yahoo finance.")
	private String quote;
	@ApiModelProperty(notes = "Current stock price as per yahoo finanace.")
	private BigDecimal price;

	public Quote() {

	}

	public Quote(String quote, BigDecimal price) {
		this.quote = quote;
		this.price = price;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
