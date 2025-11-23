package com.example.esketit.service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.esketit.common.constants.FootballConstants;
import com.example.esketit.dto.MatchResponseDto;
import com.example.esketit.dto.StandingResponseDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DiscordFootballNotifier는 축구 경기 일정과 순위 정보를 디스코드 채널로 전송하는 역할을 합니다. <br>
 * <br>
 * FootballService를 통해 데이터를 가져오고, DiscordSender를 사용하여 디스코드에 메시지를 보냅니다.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DiscordFootballNotifier {

	private final FootballService footballService;
	private final DiscordSender discordSender;

	private static final int STANDING_COLOR = 0x3C1053; // 프리미어리그 보라색

	public void notifyTodayEpl() {

		List<MatchResponseDto.Match> matches = this.footballService.getTodayEplMatches().matches();
		StandingResponseDto standings = this.footballService.getEplStandings();

		if (matches.isEmpty()) {
			discordSender.sendEmbed(FootballConstants.EPL_MATCH_TITLE.getValue(),
									"오늘은 경기 없습니데이~ 대답 좀여..");
		} else {
			sendStandingsWithFields(standings);
			sendMatchesWithFields(matches);
		}

	}

	private void sendMatchesWithFields(List<MatchResponseDto.Match> matches) {
		List<DiscordSender.EmbedField> fields = new ArrayList<>();

		for (MatchResponseDto.Match m : matches) {
			ZonedDateTime krTime = m.utcDate().withZoneSameInstant(ZoneId.of("Asia/Seoul"));
			String formatKRTime = DateTimeFormatter.ofPattern("MM월 dd일 HH:mm").format(krTime);

			String matchInfo = String.format(
				"**%s** vs **%s**\n %s",
				m.homeTeam().tla(),
				m.awayTeam().tla(),
				formatKRTime
			);

			fields.add(
				new DiscordSender.EmbedField(
					"경기 " + (fields.size() + 1),
					matchInfo,
					true
				));
		}

		discordSender.sendEmbedWithFields(FootballConstants.EPL_MATCH_TITLE.getValue(), 0x1E90FF, null, fields);
	}

	private void sendStandingsWithFields(StandingResponseDto standings) {
		List<StandingResponseDto.StandingGroup.Table> table = standings.standings().getFirst().table();
		List<DiscordSender.EmbedField> fields = new ArrayList<>();

		// 챔스권 (1~4위)
		StringBuilder championsLeague = new StringBuilder();
		for (int i = 0; i < 4 && i < table.size(); i++) {
			championsLeague.append(formatStanding(table.get(i)));
		}
		fields.add(new DiscordSender.EmbedField(
			"Champions League",
			championsLeague.toString(),
			false
		));

		// 유로파 (5위)
		if (table.size() > 4) {
			fields.add(new DiscordSender.EmbedField(
				"Europa League",
				formatStanding(table.get(4)),
				false
			));
		}

		// 컨퍼런스 (6~7위)
		StringBuilder conference = new StringBuilder();
		for (int i = 5; i < 7 && i < table.size(); i++) {
			conference.append(formatStanding(table.get(i)));
		}
		fields.add(new DiscordSender.EmbedField(
			"Conference League",
			conference.toString(),
			false
		));

		// 중위권 (8~17위)
		StringBuilder mid = new StringBuilder();
		for (int i = 7; i < 17 && i < table.size(); i++) {
			mid.append(formatStanding(table.get(i)));
		}
		fields.add(new DiscordSender.EmbedField(
			"Mid Zone",
			mid.toString(),
			false
		));

		// 강등권 (18~20위)
		StringBuilder relegation = new StringBuilder();
		for (int i = 17; i < table.size(); i++) {
			relegation.append(formatStanding(table.get(i)));
		}
		fields.add(new DiscordSender.EmbedField(
			"Relegation Zone",
			relegation.toString(),
			false
		));

		// 단일 Embed로 전송
		discordSender.sendEmbedWithFields(FootballConstants.EPL_STANDING_TITLE.getValue(),
										  STANDING_COLOR,
										  null,
										  fields);
	}

	private String formatStanding(StandingResponseDto.StandingGroup.Table team) {
		return
			String.format(
				" **%d위. %s** - %d승 %d무 %d패 (%d 경기)\n",
				team.position(),
				team.team().shortName(),
				team.won(),
				team.draw(),
				team.lost(),
				team.playedGames()
			);
	}

}
