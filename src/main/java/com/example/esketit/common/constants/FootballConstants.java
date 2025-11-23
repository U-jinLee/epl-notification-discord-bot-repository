package com.example.esketit.common.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FootballConstants {
	EPL_STANDING_TITLE("📊 현재 EPL 순위"),
	EPL_MATCH_TITLE("⚽ 오늘의 EPL 경기 일정"),
	MADE_BY("Made by JINJIN");

	private final String value;
}
