package ecommerce.rmall.portal.dwr;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ChatService implements ApplicationContextAware {

    private ApplicationContext ctx;
    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.ctx = ctx;
    }

    /**
     * <b>function:</b> 向服务器发送信息，服务器端监听ChatMessageEvent事件，当有事件触发就向所有客户端发送信息
     * @author hoojo
     * @createDate 2011-6-8 下午12:37:24
     * @param msg
     */
    public void sendMessage(Message msg) {
        //发布事件
        ctx.publishEvent(new ChatMessageEvent(msg));
    }
}