package com.example.esketit.common.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LeagueConstants {
	EPL("PL", "English Premier League"),
	CHAMPIONS_LEAGUE("CL", "UEFA Champions League"),
	LA_LIGA("PD", "La Liga"),
	BUNDESLIGA("BL1", "Bundesliga"),
	SERIE_A("SA", "Serie A"),
	LIGUE_1("FL1", "Ligue 1");

	private final String code;
	private final String name;
}
