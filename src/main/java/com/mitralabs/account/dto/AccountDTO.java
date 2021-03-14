package com.mitralabs.account.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

	private String ownerId;
	private String accountId;
	private String accountType;
	private String status;
	private Date createdAt;

}
