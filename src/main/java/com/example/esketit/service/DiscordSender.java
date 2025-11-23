package com.example.esketit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import com.example.esketit.common.constants.FootballConstants;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DiscordSender는 Discord 채널에 임베드 메시지를 전송하는 서비스입니다.
 */
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

		embed.setFooter(FootballConstants.MADE_BY.getValue(), null);

		if (channel != null) channel.sendMessageEmbeds(embed.build()).queue();
	}

	public void sendEmbed(String title, String imageUrl, String description) {
		TextChannel channel = jda.getTextChannelById(channelId);

		log.info("Sending message to Discord channel ID: {}", channelId);
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle(title);
		embed.setThumbnail(imageUrl);
		embed.setDescription(description);
		embed.setColor(0x1E90FF);
		embed.setFooter(FootballConstants.MADE_BY.getValue(), null);

		if (channel != null)
			channel.sendMessageEmbeds(embed.build()).queue();
	}

	// Fields를 사용한 Embed 전송
	public void sendEmbedWithFields(String title, int color, String thumbnail, List<EmbedField> fields) {
		sendEmbedWithFields(title, color, fields, thumbnail, null);
	}

	// Fields와 thumbnail을 사용한 Embed 전송
	public void sendEmbedWithFields(String title,
									int color,
									List<EmbedField> fields,
									String thumbnailUrl,
									String authorName) {
		TextChannel channel = jda.getTextChannelById(channelId);

		if (channel == null) {
			log.error("TextChannel not found for channelId={}", channelId);
			return;
		}

		EmbedBuilder embed = new EmbedBuilder()
			.setTitle(title)
			.setColor(color)
			.setFooter(FootballConstants.MADE_BY.getValue(), null);

		if (thumbnailUrl != null) {
			embed.setThumbnail(thumbnailUrl);
		}

		if (authorName != null) {
			embed.setAuthor(authorName);
		}

		for (EmbedField field : fields) {
			embed.addField(field.name(), field.value(), field.inline());
		}

		channel.sendMessageEmbeds(embed.build()).queue(
			success -> log.info("Embed with fields sent successfully"),
			error -> log.error("Failed to send embed with fields", error)
		);
	}

	// EmbedField 레코드
	public record EmbedField(String name, String value, boolean inline) {
	}

}
