package com.example.esketit.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.esketit.common.constants.LeagueConstants;

@SpringBootTest
class FootballApiClientTest {

	@Autowired
	FootballApiClient footballApiClient;

	@Test
	@DisplayName("Get Matches from Football API Client")
	void getMatches() {
		LocalDateTime londonDate = LocalDateTime.now();

		String today = londonDate.format(DateTimeFormatter.ISO_LOCAL_DATE);

		assertNotNull(this.footballApiClient.getMatches(LeagueConstants.CHAMPIONS_LEAGUE, today));
	}
	
}