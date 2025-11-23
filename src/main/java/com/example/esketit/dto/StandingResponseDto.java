package com.example.esketit.dto;

import java.util.List;

public record StandingResponseDto(List<StandingGroup> standings) {

	public record StandingGroup(
		String stage,
		String type,
		String group,
		List<Table> table) {

		public record Table(Integer position,
							Team team,
							Integer playedGames,
							Integer won,
							Integer draw,
							Integer lost) {
		}
	}
}
