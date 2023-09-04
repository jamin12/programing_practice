package com.example.emailparsing.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.AndTerm;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.FromStringTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MailGetter {

	public void getMain() {
		Properties properties = new Properties();
		properties.put("mail.imaps.host", "imap.naver.com");
		properties.put("mail.imaps.port", "993");
		properties.put("mail.store.protocol", "imaps");
		properties.put("mail.imaps.ssl.enable", "true");

		Session emailSession = setupEmailSession(properties);

		try {
			Store store = emailSession.getStore("imaps");
			store.connect("rudals9901", "");

			Folder emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);

			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, -7);
			Date weekAgo = cal.getTime();

			SearchTerm searchTerm = setupSearchTerm(weekAgo, "kkang0118@energyx.co.kr");
			Message[] messages = emailFolder.search(searchTerm);

			for (Message message : messages) {
				processEmailMessageForPlain(message);
			}

			emailFolder.close(false);
			store.close();
		} catch (Exception e) {
			// Handle exceptions
		}
	}

	public Session setupEmailSession(Properties properties) {
		return Session.getDefaultInstance(properties);
	}

	public SearchTerm setupSearchTerm(Date weekAgo, String fromEmail) {
		SearchTerm newerThan = new ReceivedDateTerm(ComparisonTerm.GT, weekAgo);
		SearchTerm fromEmailTerm = new FromStringTerm(fromEmail);
		return new AndTerm(newerThan, fromEmailTerm);
	}

	public void extractUrlsAndTexts(String text) {
		String[] parts = text.split("<https://");
		ArrayList<String> urls = new ArrayList<>();
		ArrayList<String> texts = new ArrayList<>();

		for (String part : parts) {
			int index = part.indexOf(">");
			if (index != -1) {
				urls.add("https://" + part.substring(0, index));
				String txt = part.substring(index + 1).trim();
				if (!txt.isEmpty()) {
					texts.add(txt);
				}
			} else {
				texts.add(part.trim());
			}
		}

		for (int i = 0; i < urls.size(); i++) {
			String url = urls.get(i);
			String txt = texts.get(i); // Assuming both lists have the same size
			System.out.println("Pair " + (i + 1) + ": URL - " + url + ", Text - " + txt);
		}
	}

	public String getTextBetweenUrls(String text, String firstUrl, String secondUrl) {
		int firstUrlIndex = text.indexOf(firstUrl);
		int secondUrlIndex = text.indexOf(secondUrl);

		if (firstUrlIndex == -1 || secondUrlIndex == -1) {
			System.out.println("URLs not found.");
			return null;
		}

		return text.substring(firstUrlIndex + firstUrl.length(), secondUrlIndex).trim();
	}

	public void processEmailMessageForPlain(Message message) throws MessagingException, IOException {
		Object content = message.getContent();
		if (content instanceof Multipart multipartContent) {
			for (int j = 0; j < multipartContent.getCount(); j++) {
				BodyPart bodyPart = multipartContent.getBodyPart(j);
				if (bodyPart.isMimeType("text/plain")) {
					String text = (String)bodyPart.getContent();

					String firstUrl = "[https://img.stibee.com/6747_1642150344.png]";
					String secondUrl = "[https://img.stibee.com/6747_1642150365.png]";

					String betweenUrls = getTextBetweenUrls(text, firstUrl, secondUrl);
					if (betweenUrls != null) {
						extractUrlsAndTexts(betweenUrls);
					}
				}
			}
		}
	}
}
