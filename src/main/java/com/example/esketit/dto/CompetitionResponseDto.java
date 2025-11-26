package com.example.esketit.dto;

public record CompetitionResponseDto(Competition competition, MatchResponseDto matches) {
	public record Competition(String id, String name, String code, String type, String emblem) {
	}
}
