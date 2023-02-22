package com.henhouse.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.henhouse.model.DateTime;
import com.henhouse.service.ConstantsService;

@Repository
public class DateTimeRepositoryImpl implements DateTimeRepository {

	private static final Logger LOGGER = LogManager.getLogger(DateTimeRepositoryImpl.class);

	private WebClient webClient = WebClient.builder().baseUrl(ConstantsService.GPIO_API_URL).build();

	@Override
	public Optional<DateTime> get() {
		return webClient.get().uri("dateTime/get").accept(MediaType.APPLICATION_JSON).retrieve()
				.bodyToMono(DateTime.class).blockOptional();
	}

	@Override
	public void save(DateTime dateTime) {
		Map<String, String> dateTimeWithDayOfWeekMap = new HashMap<String, String>();
		String date = dateTime.getDate();
		dateTimeWithDayOfWeekMap.put("date", date);
		dateTimeWithDayOfWeekMap.put("time", dateTime.getTime());
		dateTimeWithDayOfWeekMap.put("dayOfWeek", String.valueOf(getDayOfWeek(date)));

		webClient.post().uri("dateTime/set").contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(dateTimeWithDayOfWeekMap)).retrieve().bodyToMono(ClientResponse.class).block();;
	}

	private static int getDayOfWeek(String date) {
		Calendar calendar = Calendar.getInstance();
		parseDate(date, "dd/MM/yyyy").ifPresent(d -> calendar.setTime(d));
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	private static Optional<Date> parseDate(String date, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		try {
			return Optional.of(dateFormat.parse(date));
		} catch (ParseException e) {
			LOGGER.warn("Bad date format");
		}
		return Optional.empty();
	}
}
