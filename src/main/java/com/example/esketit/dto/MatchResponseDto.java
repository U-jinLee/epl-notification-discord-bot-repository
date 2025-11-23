package com.example.esketit.dto;

import java.time.ZonedDateTime;
import java.util.List;

public record MatchResponseDto(List<Match> matches) {

    public record Match(
        Long id,
        ZonedDateTime utcDate,
        String status,
        Team homeTeam,
        Team awayTeam,
        Score score
    ) {
    }

    public record Score(
        String winner,
        TimeScore fullTime
    ) {
        public record TimeScore(
            Integer home,
            Integer away
        ) {
        }
    }
}