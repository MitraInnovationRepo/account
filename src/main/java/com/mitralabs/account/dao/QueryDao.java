package com.mitralabs.account.dao;

import java.util.Date;

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

	public void insertAccount(String ownerId, String accountId, String accountType, Date createdAt)
			throws DuplicateKeyException {
		try {
			jdbcTemplate.update("INSERT INTO account(ownerid,accountid, accountType, createdAt) VALUES (?, ?, ?, ?)",
					ownerId, accountId, accountType, createdAt);

		} catch (DuplicateKeyException e) {
			log.warn("accountId [{}] duplicated", accountId);
			throw new DuplicateKeyException("accountId duplicated :" + accountId);
		}
	}
}
