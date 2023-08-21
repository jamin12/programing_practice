package com.example.ssepractice.service;

import java.io.IOException;
import java.util.Set;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.example.ssepractice.repository.SseEmitterRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SseService {
	private final SseEmitterRepository sseEmitterRepository;

	public SseEmitter subScribe(Long memberId) {
		SseEmitter sseEmitter = new SseEmitter(30000L);
		sseEmitterRepository.save(memberId, sseEmitter);

		sendToClient(memberId, "EventStream Created. [userId=" + memberId + "]");
		return sseEmitter;
	}

	/**
	 * 서버의 이벤트를 클라이언트에게 보내는 메서드
	 * 다른 서비스 로직에서 이 메서드를 사용해 데이터를 Object event에 넣고 전송하면 된다.
	 *
	 * @param userId - 메세지를 전송할 사용자의 아이디.
	 * @param event  - 전송할 이벤트 객체.
	 */
	public void notify(Long userId, Object event) {
		sendToClient(userId, event);
	}

	/**
	 * 클라이언트에게 데이터를 전송
	 *
	 * @param memberId   - 데이터를 받을 사용자의 아이디.
	 * @param data - 전송할 데이터.
	 */
	private void sendToClient(Long memberId, Object data) {
		SseEmitter emitter = sseEmitterRepository.get(memberId);
		if (emitter != null) {
			try {
				emitter.send(SseEmitter.event().id(String.valueOf(memberId)).name("sse").data(data));
			} catch (IOException exception) {
				sseEmitterRepository.deleteById(memberId);
				emitter.completeWithError(exception);
			}
		}
	}

	@Scheduled(fixedDelay = 1500)
	public void test() {
		log.info(
			"========================================================================================================================================================================================================");
		Set<Long> longs = sseEmitterRepository.getSseEmitterMaps().keySet();
		log.info(longs.toString());
		longs.forEach(aLong -> notify(aLong, "테스트입니다"));
	}
}
