package com.dive.divewebapi.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // @Autowired
    // private CustomHandshakeHandler customHandshakeHandler;

    // @Autowired
    // private UserInterceptor userInterceptor;

    /**
     * メッセージブローカーの設定
     * メッセージブローカー：クライアントとコントローラの仲介にあり、チャンネル(部屋)のようなもの
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // メッセージブローカーを有効化
        // "/topic"､"/queue"から始まるパスはメッセージブローカーを経由
        registry.enableSimpleBroker("/topic", "/queue");

        // メッセージブローカーを経由させず、Controllerで直接受ける場合は、
        // "/app"から始まるパスで受ける
        registry.setApplicationDestinationPrefixes("/app");
    }

    /**
     * エンドポイントの設定
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // クライアントから接続する際のエンドポイントを"/endpoint"に設定
        registry.addEndpoint("/endpoint")
                .setAllowedOrigins("http://localhost:8081")
                .withSockJS();
    }

    // /**
    //  * クライアントからのメッセージ受信時の処理定義
    //  * @param registration
    //  */
    // @Override
    // public void configureClientInboundChannel(ChannelRegistration registration) {
    //     // クライアントからメッセージを受信した際のインターセプタ(割り込み処理)としてuserInterceptorを設定する。
    //     // こちらも独自で実装しているクラスで、セッションとユーザー情報の紐づけを行っている。（後述）
    //     // registration.interceptors(userInterceptor);
    // }
}
