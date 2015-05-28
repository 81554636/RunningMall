package ecommerce.rmall.utils;

import java.util.Random;

/***
 * 获取随机密码
 * @author martin
 *
 */
public class RandomCode {

    private static final int CODE_NUM = 6; //验证码字符个数
    //随机字符数组  
    private static char[] charSequence = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();  
    private static Random random = new Random();  
    
	// 随机生成一个字符    
    private static String getRandomChar() {    
        int index = random.nextInt(charSequence.length);  
        return String.valueOf(charSequence[index]);  
    }
    
    public static String obtainRandomCode(){
    	
    	StringBuilder sRand = new StringBuilder(CODE_NUM);
        for (int i = 0; i < CODE_NUM; i++)
        	sRand.append(getRandomChar());
        return sRand.toString();
    	//return "123456";
    }
}
