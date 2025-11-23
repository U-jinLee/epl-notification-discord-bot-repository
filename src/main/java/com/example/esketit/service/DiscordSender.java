package com.example.esketit.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class DiscordSender {
	private final JDA jda;

	@Value("${discord.bot.channel.id}")
	private String channelId;

	public void sendEmbed(String title, String description) {
		TextChannel channel = jda.getTextChannelById(this.channelId);

		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle(title);
		embed.setDescription(description);
		embed.setColor(0x1E90FF);

		if(channel != null) channel.sendMessageEmbeds(embed.build()).queue();
	}

	public void sendEmbed(String title, String imageUrl, String description) {
		TextChannel channel = jda.getTextChannelById(channelId);

		log.info("Sending message to Discord channel ID: {}", channelId);
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle(title);
		embed.setThumbnail(imageUrl);
		embed.setDescription(description);
		embed.setColor(0x1E90FF);

		if(channel != null) channel.sendMessageEmbeds(embed.build()).queue();
	}

}

