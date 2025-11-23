package com.example.esketit.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.example.esketit.dto.MatchResponseDto;
import com.example.esketit.dto.StandingResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FootballService {

	private final FootballApiClient footballClient;

	private static final ZoneId UTC = ZoneId.of("UTC");

	public MatchResponseDto getTodayMatches() {
		LocalDateTime londonDate = LocalDateTime.now(UTC);

		String today = londonDate.format(DateTimeFormatter.ISO_LOCAL_DATE);

		return this.footballClient.getEplMatches(today);
	}

	public StandingResponseDto getEplStandings() {
		return this.footballClient.getEplStandings();
	}

}
