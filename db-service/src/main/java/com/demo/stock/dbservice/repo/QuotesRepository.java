package com.demo.stock.dbservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.stock.dbservice.entity.Quote;

public interface QuotesRepository extends JpaRepository<Quote, Integer> {

	List<Quote> findByUserName(String username);

}
