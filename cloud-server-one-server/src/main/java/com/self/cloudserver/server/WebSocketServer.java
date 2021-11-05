package com.self.cloudserver.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

@ServerEndpoint(value = "/ws/asset")
@Component
public class WebSocketServer {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    private static final AtomicInteger ONlINE_COUNT = new AtomicInteger(0);

    private static CopyOnWriteArraySet<Session> sessionSet = new CopyOnWriteArraySet<>();

    @PostConstruct
    public void init() {
        logger.info("WebSocket加载");
    }

    /**
     * 建立链接成功调用
     * @param session session
     */
    @OnOpen
    public void onOpen(Session session) {
        sessionSet.add(session);
        int cnt = ONlINE_COUNT.incrementAndGet();

        logger.info("有连接加入，当前连接数为：{}", cnt);
        sendMessage(session, "连接成功");
    }

    /**
     * 链接关闭调用
     * @param session session
     */
    @OnClose
    public void onClose(Session session) {
        sessionSet.remove(session);
        int cnt = ONlINE_COUNT.decrementAndGet();
        logger.info("有连接关闭，当前连接数为：{}", cnt);
    }

    /**
     * 收到客户端消息调用
     * @param message 客户端消息
     * @param session session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("来自客户端的消息：{}", message);
        sendMessage(session, "收到消息，消息内容：" + message);
    }

    /**
     * 发生错误调用
     * @param session session
     * @param error 错误
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.error("发生错误：{}，session id：{}", error.getMessage(), session.getId());
    }

    /**
     * 发送消息
     * @param session session
     * @param message 消息
     */
    public static void sendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            logger.error("发送消息出错：{}", e.getMessage());
        }
    }

    /**
     * 群发消息
     * @param message 消息
     */
    public static void broadCastInfo(String message) {
        for (Session session : sessionSet) {
            if(session.isOpen()){
                sendMessage(session, message);
            }
        }
    }

    /**
     * 发送消息
     * @param sessionId 会话id
     * @param message 消息
     */
    public static void sendMessage(String sessionId, String message) {
        Session session = null;
        for (Session s : sessionSet) {
            if(s.getId().equals(sessionId)){
                session = s;
                break;
            }
        }

        if(session != null){
            sendMessage(session, message);
        } else{
            logger.warn("没有找到你指定id的会话：{}", sessionId);
        }
    }

}
