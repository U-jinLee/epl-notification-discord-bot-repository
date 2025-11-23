package com.example.esketit.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class DiscordBotConfig {

	@Value("${discord.bot.token}")
	private String discordToken;

	@Bean
	public JDA jda() throws Exception {
		return JDABuilder.createDefault(this.discordToken)
			.enableIntents(GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS))
			.setAutoReconnect(true)
			.build()
			.awaitReady();
	}

}

