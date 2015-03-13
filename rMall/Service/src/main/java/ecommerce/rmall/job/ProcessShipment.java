package ecommerce.rmall.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.yun.channel.auth.ChannelKeyPair;
import com.baidu.yun.channel.client.BaiduChannelClient;
import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.PushUnicastMessageRequest;
import com.baidu.yun.channel.model.PushUnicastMessageResponse;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.google.gson.Gson;

import ecommerce.rmall.domain.Shipment;
import ecommerce.rmall.domain.Station;

public class ProcessShipment implements IJob {
	
	private static Logger logger = LoggerFactory.getLogger(ProcessShipment.class);

	private Gson jsonConvert = new Gson();
	private String apiKey;
	private String secretKey;
	public void setApiKey(String val){ this.apiKey = val; }
	public void setSecretKey(String val){ this.secretKey = val; }

	@Override
	public void execute(String content) {

		Shipment ship = this.jsonConvert.fromJson(content, Shipment.class);

		// 1. 设置developer平台的ApiKey/SecretKey
		//String apiKey = "u3an5YDSjcaujWqjHhpX9okM";
		//String secretKey = "z4XIGTaaMUmcYVGjObRApaOwOzjGfhqR";
		String apiKey = this.apiKey;
		String secretKey = this.secretKey;
		ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);
		logger.debug("baidu developer { apiKey : {}, secretKey : {} }", apiKey, secretKey);

		// 2. 创建BaiduChannelClient对象实例
		BaiduChannelClient channelClient = new BaiduChannelClient(pair);

		// 3. 若要了解交互细节，请注册YunLogHandler类
		channelClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				System.out.println(event.getMessage());
			}
		});

		try {
			// 4. 创建请求类对象
			PushUnicastMessageRequest request = new PushUnicastMessageRequest();
			Station station = ship.getStation();
			if( null != station.getChannel()){
				request.setDeviceType(Integer.parseInt(station.getChannel().getOsType()));
				//device_type => 1: web 2: pc 3:android 4:ios 5:wp
				request.setChannelId(station.getChannel().getChannelID());
				request.setUserId(station.getChannel().getUserID());
				
				String msg = String.format("{id:%d}", ship.getId());
				request.setMessage(msg);
				
				logger.debug("baidu message request={ os:{}, channel:{}L, userId:{}, msg:{} }", 
						station.getChannel().getOsType(), 
						station.getChannel().getChannelID(), 
						station.getChannel().getUserID(),
						msg);

				// 5. 调用pushMessage接口
				PushUnicastMessageResponse response = channelClient.pushUnicastMessage(request);

				// 6. 认证推送成功
				logger.debug("baidu message response={push_amount:{}}", response.getSuccessAmount());
			}
		} catch (ChannelClientException e) {
			// 处理客户端错误异常
			logger.error(e.getMessage());
		} catch (ChannelServerException e) {
			// 处理服务端错误异常
			logger.error("request_id: {}, error_code: {}, error_message: {}",
					e.getRequestId(), e.getErrorCode(), e.getErrorMsg());
		}
	}
}
