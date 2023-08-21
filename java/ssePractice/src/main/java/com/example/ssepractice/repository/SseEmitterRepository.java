package com.example.ssepractice.repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Repository
@Getter
@RequiredArgsConstructor
public class SseEmitterRepository {
	private final Map<Long, SseEmitter> sseEmitterMaps = new ConcurrentHashMap<>();

	/**
	 * 주어진 아이디와 이미터를 저장
	 *
	 * @param id      사용자 아이디.
	 * @param emitter 이벤트 Emitter.
	 * @return
	 */
	public void save(Long id, SseEmitter emitter) {
		sseEmitterMaps.put(id, emitter);
		emitter.onCompletion(() -> deleteById(id));
		emitter.onTimeout(() -> deleteById(id));
	}

	/**
	 * 주어진 아이디의 Emitter를 제거
	 *
	 * @param id  사용자 아이디.
	 */
	public void deleteById(Long id) {
		sseEmitterMaps.remove(id);
	}

	/**
	 * 주어진 아이디의 Emitter를 가져옴.
	 *
	 * @param id  사용자 아이디.
	 * @return SseEmitter  이벤트 Emitter.
	 */
	public SseEmitter get(Long id) {
		return sseEmitterMaps.get(id);
	}

	/**
	 * 주어진 아이디의 Emitter를 가져옴.
	 *
	 * @param ids  사용자 아이디.
	 * @return SseEmitter  이벤트 Emitter.
	 */
	public List<SseEmitter> getAll(List<Long> ids) {
		return ids.stream()
			.map(sseEmitterMaps::get)
			.toList();
	}
}
