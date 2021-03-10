package com.mitralabs.customer.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class QueryDao {
	@Autowired
	@Qualifier("queryJdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public void insertCustomer(String aggregateId, String firstname, String lastname, String address, String email)
			throws DuplicateKeyException {
		try {
			jdbcTemplate.update(
					"INSERT INTO customer(aggregateid, firstname, lastname, address , email) VALUES (?, ?, ?, ?, ?)",
					aggregateId, firstname, lastname, address, email);

		} catch (DuplicateKeyException e) {
			log.warn("customer [{}] duplicated", email);
			throw new DuplicateKeyException("customer duplicated :" + email);
		}
	}
}
