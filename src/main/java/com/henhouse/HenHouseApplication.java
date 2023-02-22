package com.henhouse;

import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.henhouse.service.ConstantsService;

@SpringBootApplication
@EnableScheduling
public class HenHouseApplication {
	public static void main(String[] args) {
		while (testURL() == false)
			System.out.println("Wait gpio service...");

		SpringApplication.run(HenHouseApplication.class, args);
	}

	public static boolean testURL() {
		String strUrl = ConstantsService.GPIO_API_URL + "docs";
		
		try {
			URL url = new URL(strUrl);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.connect();
			return HttpURLConnection.HTTP_OK == httpURLConnection.getResponseCode();
		} catch (Exception e) {
			return false;
		}
	}
}
