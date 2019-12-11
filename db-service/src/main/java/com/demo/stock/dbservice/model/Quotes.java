package com.demo.stock.dbservice.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about the quote. ")
public class Quotes {

	@ApiModelProperty(notes = "User name for quote to be added.")
	private String userName;
	
	@ApiModelProperty(notes = "List of quotes to manager for user.")
	private List<String> quotes;

	public Quotes() {

	}

	public Quotes(String userName, List<String> quotes) {
		this.userName = userName;
		this.quotes = quotes;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<String> getQuotes() {
		return quotes;
	}

	public void setQuotes(List<String> quotes) {
		this.quotes = quotes;
	}

}
