package com.example.esketit.service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.esketit.dto.MatchResponseDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class DiscordFootballNotifier {

	private final FootballService footballService;
	private final DiscordSender discordSender;

	private static final String TITLE = "⚽ 오늘의 EPL 경기 일정";

	public void notifyTodayEpl() {
		List<MatchResponseDto.Match> matches = this.footballService.getTodayMatches().matches();

		if (matches.isEmpty()) {
			discordSender.sendEmbed(
				TITLE,
				"오늘은 경기 없습니데이~ 대답 좀여.."
			);
			return;
		}

		for (MatchResponseDto.Match m : matches) {
			ZonedDateTime krTime = m.utcDate().withZoneSameInstant(ZoneId.of("Asia/Seoul"));
			String formatKRTime = DateTimeFormatter.ofPattern("yy년 MM월 dd일 HH시 mm분").format(krTime);

			discordSender.sendEmbed(TITLE,
									m.homeTeam().crest(),
									"""
										(H) %s VS (A) %s
										
										한국 시간: %s
										"""
										.formatted(
											m.homeTeam().shortName(),
											m.awayTeam().shortName(),
											formatKRTime
										)
			);
		}
	}

}
