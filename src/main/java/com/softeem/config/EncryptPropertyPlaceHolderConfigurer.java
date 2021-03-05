package com.softeem.config;

import com.softeem.utils.DESUtils;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.io.IOException;
import java.util.Properties;

/**
 * @author 高玉好
 * @ClassName EncryptPropertyPlaceHolderConfigurer
 * @since 2021/1/25 6:43
 */
public class EncryptPropertyPlaceHolderConfigurer extends PropertySourcesPlaceholderConfigurer {
    /**
    *已经加密的字段
    */
    private String[] encryptProNames ={"jdbc.username","jdbc.password"};

    /**
     * 新版本
     * @param props
     * @throws IOException
     */
    @Override
    protected void loadProperties(Properties props) throws IOException {
        super.loadProperties(props);
        for (String key : props.stringPropertyNames()) {
            System.out.println("=========");
            //对已加密的字段进行解密
            if (isEncryptProp(key)) {
                String decryptString = DESUtils.getDecryptString(props.getProperty(key));
                props.setProperty(key, decryptString);
            }
    }
}
    /**
     * 判断该属性是否已经被加密(该属性需不需要解密)
     * @param propertyName
     * @return
     */
    private boolean isEncryptProp(String propertyName) {
        for(String encryptProName : encryptProNames){
            if(encryptProName.equals(propertyName)){
                if(encryptProName.equals(propertyName)){
                    return true;
                }
            }
        }
        return false;
    }

}
