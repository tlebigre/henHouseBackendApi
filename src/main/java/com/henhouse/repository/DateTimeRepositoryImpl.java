package com.henhouse.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.henhouse.board.BoardGrpc;
import com.henhouse.board.BoardGrpc.BoardBlockingStub;
import com.henhouse.board.DateTimeGetRequest;
import com.henhouse.board.DateTimeReply;
import com.henhouse.board.DateTimeSetRequest;
import com.henhouse.model.DateTime;
import com.henhouse.service.ConstantsService;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Repository
public class DateTimeRepositoryImpl implements DateTimeRepository {

	private static final Logger LOGGER = LogManager.getLogger(DateTimeRepositoryImpl.class);
	
	private final BoardBlockingStub blockingStub;

	public DateTimeRepositoryImpl() {
		ManagedChannelBuilder<?> channelBuilder = ManagedChannelBuilder.forAddress(ConstantsService.GPIO_API_URL, 9000)
				.usePlaintext();
		ManagedChannel channel = channelBuilder.build();
		blockingStub = BoardGrpc.newBlockingStub(channel);
	}

	
	@Override
	public Optional<DateTime> get() {
		DateTimeGetRequest dateTimeGetRequest = DateTimeGetRequest.newBuilder().build();
		DateTimeReply dateTimeReply = blockingStub.getDateTime(dateTimeGetRequest);
		return Optional.of(new DateTime(dateTimeReply.getDate(),dateTimeReply.getTime()));
	}

	@Override
	public void save(DateTime dateTime) {
		String date = dateTime.getDate();
		DateTimeSetRequest dateTimeSetRequest = DateTimeSetRequest.newBuilder().setDate(date).setTime(dateTime.getTime()).setDayOfWeek(getDayOfWeek(date)).build();
		blockingStub.setDateTime(dateTimeSetRequest);
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
