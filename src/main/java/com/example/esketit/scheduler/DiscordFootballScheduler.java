package com.example.esketit.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.esketit.service.DiscordFootballNotifier;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class DiscordFootballScheduler {

	private final DiscordFootballNotifier notifier;

	/**
	 * 매일 오전 10시에 오늘의 축구 경기 정보를 디스코드로 전송
	 */
	@Scheduled(cron = "0 0 10 * * *", zone = "Asia/Seoul")
	public void sendTodayMatches() {
		notifier.notifyTodayChamps();
		notifier.notifyTodayEpl();
	}
}
