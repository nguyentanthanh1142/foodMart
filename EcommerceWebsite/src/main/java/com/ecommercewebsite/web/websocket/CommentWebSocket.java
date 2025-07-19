package com.ecommercewebsite.web.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.ecommercewebsite.model.CommentModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ServerEndpoint("/ws-comments/{postId}")
public class CommentWebSocket {
	private static Map<Long, Set<Session>> sessionsByPost = new ConcurrentHashMap<>();
	private static final ObjectMapper objectMapper = new ObjectMapper();

	@OnOpen
	public void onOpen(Session session, @PathParam("postId") Long postId) {
		sessionsByPost.computeIfAbsent(postId, k -> ConcurrentHashMap.newKeySet()).add(session);
		System.out.println("Client connected to postId: " + postId);
	}

	@OnClose
	public void onClose(Session session, @PathParam("postId") Long postId) {
		Set<Session> sessions = sessionsByPost.get(postId);
		if (sessions != null) {
			sessions.remove(session);
			if (sessions.isEmpty()) {
				sessionsByPost.remove(postId); // cleanup
			}
		}
		System.out.println("Client disconnected from postId: " + postId);
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		System.err.println("WebSocket error in session: " + session.getId());
		throwable.printStackTrace();
	}

	public static void broadcast(Long postId, String type, CommentModel comment) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<>();
		map.put("type", type);
		map.put("comment", comment);
		String json = objectMapper.writeValueAsString(map);
		Set<Session> sessions = sessionsByPost.get(postId);
		if (sessions != null) {
			for (Session s : sessions) {
				try {
					s.getBasicRemote().sendText(json);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
