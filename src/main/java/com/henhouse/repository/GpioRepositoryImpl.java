package com.henhouse.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.henhouse.model.EngineAction;
import com.henhouse.service.ConstantsService;

@Repository
public class GpioRepositoryImpl implements GpioRepository {

	private WebClient webClient = WebClient.builder().baseUrl(ConstantsService.GPIO_API_URL).build();

	@Override
	public boolean getGpioState(int gpio) {
		return webClient.get().uri("gpio/get/" + String.valueOf(gpio)).retrieve().bodyToMono(Boolean.class).block();
	}

	@Override
	public void saveGpioState(int gpio, boolean state) {
		webClient.get().uri("gpio/set/" + String.valueOf(gpio) + "/" + (state ? "True" : "False")).retrieve()
				.bodyToMono(ClientResponse.class).block();
	}

	@Override
	public void engineRun(int gpio, int speed, int buttonGpio, int limit, EngineAction engineAction) {

		Map<String, String> engineMap = new HashMap<String, String>();
		engineMap.put("gpio", String.valueOf(gpio));
		engineMap.put("speed", String.valueOf(speed));
		engineMap.put("buttonGpio", String.valueOf(buttonGpio));
		engineMap.put("limit", String.valueOf(limit));
		engineMap.put("isUp", String.valueOf(engineAction.isUp()));
		engineMap.put("isForce", String.valueOf(engineAction.isForce()));

		webClient.post().uri("engineUpOrDown/set").contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(engineMap)).retrieve().bodyToMono(ClientResponse.class).block();
	}

	@Override
	public void engineRun(int gpio, int speed, int limit, EngineAction engineAction) {
		engineRun(gpio, speed, 0, limit, engineAction);
	}

	@Override
	public int getEngineState() {
		return webClient.get().uri("state/get").retrieve().bodyToMono(Integer.class).block();
	}

	@Override
	public void saveEngineState(int state) {
		webClient.get().uri("state/set/" + String.valueOf(state)).retrieve().bodyToMono(ClientResponse.class).block();
	}
}
