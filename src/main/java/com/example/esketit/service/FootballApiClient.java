package com.example.esketit.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.example.esketit.dto.MatchResponseDto;
import com.example.esketit.dto.StandingResponseDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class FootballApiClient {

	@Value("${football.api.token}")
	private String token;

	private static final String BASE_URL = "https://api.football-data.org/v4";
	private static final String AUTH_TOKEN = "X-Auth-Token";

	public MatchResponseDto getEplMatches(String start) {
		RestClient restClient = RestClient.create(BASE_URL);

		String url = "/competitions/PL/matches?dateFrom=" + start + "&dateTo=" + start;

		return restClient.get()
			.uri(url)
			.header(AUTH_TOKEN, token)
			.retrieve()
			.body(MatchResponseDto.class);
	}

	public StandingResponseDto getEplStandings() {
		RestClient restClient = RestClient.create(BASE_URL);

		String url = "/competitions/PL/standings";

		return restClient.get()
			.uri(url)
			.header(AUTH_TOKEN, token)
			.retrieve()
			.body(StandingResponseDto.class);
	}
}
