package ontice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@ServerEndpoint(value="/webSocket/{userId}")
public class MyWebSoket {

    private Integer userId;

    private Session session;

    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static ConcurrentHashMap<Integer, MyWebSoket> webSocketMap = new ConcurrentHashMap<>();

    //建立连接会调用的方法
    @OnOpen
    public void onOpen(@PathParam("userId") Integer userId, Session session){
        this.session = session;
        webSocketMap.put(userId, this);
        log.info("[websocket消息] 有新的连接，总数：{}",webSocketMap.size());
        log.info("[websocket消息] 连接用户id===>{}",userId);
    }
    //关闭连接时调用
    @OnClose
    public void onClose(){
        webSocketMap.remove(this);
        log.info("[websocket消息] 连接断开，总数：{}",webSocketMap.size());
    }
    //收到消息时
    @OnMessage
    public void onMessage(String message){
        log.info("[websocket消息] 收到客户端发来的消息：{}",message);
    }

    /**
     *  向客户端广播发送信息
     */

    public void sendMessage(String message){

        for (MyWebSoket webSocket : webSocketMap.values()) {
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
            log.info("[websocket消息] 广播消息，message={}",message);
        }


    }

    public void sendMessage(String message,Integer toUser){
        if(webSocketMap.get(toUser) != null){//如果用户已登录
            log.info("[websocket消息] 向{}发消息，message={}",toUser,message);
            try {
                webSocketMap.get(toUser).session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else{//没有登录，先存起来

        }


    }



}
