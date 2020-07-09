package cn.dbdj1201.websocket;

import cn.dbdj1201.util.MessageUtil;
import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yz1201
 * @date 2020-07-08 9:32
 **/
@ServerEndpoint(value = "/websocket", configurator = GetHttpSessionConfigurator.class)
public class ChatSocket {
    private Session session;
    private HttpSession httpSession;
    //保存当前系统中登录用户的HttpSession信息，及对应的Endpoint实例信息
    private static Map<HttpSession, ChatSocket> onlineUsers = new HashMap<>();
    private static int onlineCount = 0;

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        //记录websocket的会话信息
        this.session = session;
        //获取当前用户的HttpSession信息
        this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());

        System.out.println("user： " + httpSession.getAttribute("username") + " Endpoing: " + hashCode());

        //记录当前登录用户信息及对应的Endpoint实例
        if (this.httpSession.getAttribute("username") != null) {
            onlineUsers.put(this.httpSession, this);
        }
        //获取到当前所有登录用户
        String names = getNames();
        //组装消息
        String message = MessageUtil.getContent(MessageUtil.TYPE_USER, "", "", names);
        //通过广播形式发送消息
        broadcastAllUsers(message);
        //记录当前用户登录数
        increCount();
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("onMessage: name: " + httpSession.getAttribute("username") + " - " + message);
        //获取客户端消息信息并解析
        Map<String, String> messageMap = JSON.parseObject(message, Map.class);
        String fromName = messageMap.get("fromName");
        String toName = messageMap.get("toName");
        String content = messageMap.get("content");
        //判定是否有接收人
        if (toName == null || toName.isEmpty()) {
            return;
        }
        //判断接收人是否为广播all，是则发送广播
        String messageContent = MessageUtil.getContent(MessageUtil.TYPE_MESSAGE, fromName, toName, content);
        System.out.println("server to client messages: " + messageContent);
        if ("all".equals(toName)) {
            //组装消息，发送广播
            broadcastAllUsers(messageContent);
        } else {
            //否则给指定用户推送消息
            singlePushMessage(messageContent, fromName, toName);
        }

    }

    private void singlePushMessage(String messageContent, String fromName, String toName) {
        //判断接收人是否在线
        boolean isOnline = false;
        for (HttpSession hsession : onlineUsers.keySet()) {
            if (toName.equals(hsession.getAttribute("username"))) {
                isOnline = true;
            }
        }

        try {
            if (isOnline) {
                for (HttpSession hsession : onlineUsers.keySet()) {
                    if (hsession.getAttribute("username").equals(fromName) ||
                            hsession.getAttribute("username").equals(toName)) {
                        onlineUsers.get(hsession).session.getBasicRemote().sendText(messageContent);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param message 广播消息
     */
    private void broadcastAllUsers(String message) {
        for (HttpSession hsession : onlineUsers.keySet()) {
            try {
                onlineUsers.get(hsession).session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @return 在线用户
     */
    private String getNames() {
        StringBuilder names = new StringBuilder();
        if (onlineUsers.size() > 0) {
            for (HttpSession session : onlineUsers.keySet()) {
                String username = (String) session.getAttribute("username");
                names.append(username).append(",");
            }
        }
        return names.substring(0, names.length() - 1);
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        decreCount();
        System.out.println("client close a connection, current online user " + getOnlineCount());
        onlineUsers.remove(session.getUserProperties().get(HttpSession.class.getName()));
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
        System.out.println("service error");
    }

    public int getOnlineCount() {
        return onlineCount;
    }

    public synchronized void increCount() {
        onlineCount++;
    }

    public synchronized void decreCount() {
        onlineCount--;
    }
}
