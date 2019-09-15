package com.example.demomybatis.message;

import com.example.demomybatis.utils.AesEncryptUtils;
import com.example.demomybatis.utils.RSAUtils;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import java.util.Map;

public class AnalysisRequestData  {

    @Value("${server.private.key}")
    private String SERVER_PRIVATE_KEY;
    /**
     *
     * @param requestData
     * @return
     */
    public String easpString(String requestData) {
        if(requestData != null && !requestData.equals("")){
            Map<String,String> map = new Gson().fromJson(requestData,new TypeToken<Map<String,String>>() {
            }.getType());
            // 密文
            String data = map.get("requestData");
            // 加密的ase秘钥
            String encrypted = map.get("encrypted");
            if(StringUtils.isEmpty(data) || StringUtils.isEmpty(encrypted)){
                throw new RuntimeException("参数【requestData】缺失异常！");
            }else{
                String content = null ;
                String aseKey = null;
                try {
                    aseKey = RSAUtils.decryptDataOnJava(encrypted,SERVER_PRIVATE_KEY);
                }catch (Exception e){
                    throw  new RuntimeException("参数【aseKey】解析异常！");
                }
                try {
                    content  = AesEncryptUtils.decrypt(data, aseKey);
                }catch (Exception e){
                    throw  new RuntimeException("参数【content】解析异常！");
                }
                if (StringUtils.isEmpty(content) || StringUtils.isEmpty(aseKey)){
                    throw  new RuntimeException("参数【requestData】解析参数空指针异常!");
                }
                return content;
            }
        }
        throw new RuntimeException("参数【requestData】不合法异常！");
    }

}
