package soapserver;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import server._services;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class soap_send implements _services {

    @Override
    public boolean sendEmail(String _url, String _payload)
    {
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FhuxFfezdfnybrYk9t6", "KHqrjvn9EUNABwIhtPMGdQNX8QhYHW");
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        //使用https加密连接
        request.setProtocol(com.aliyuncs.http.ProtocolType.HTTPS);
        try {
            //request.setVersion("2017-06-22");// 如果是除杭州region外的其它region（如新加坡region）,必须指定为2017-06-22
            request.setAccountName("myemail@yangyang666.xyz");
            request.setFromAlias("YY");
            request.setAddressType(1);
            //是否需要回信功能
            request.setReplyToAddress(true);
            request.setToAddress(_url);
            request.setSubject("测试邮件");
            request.setHtmlBody(_payload);
            SingleSendMailResponse httpResponse = client.getAcsResponse(request);
            return true;
        } catch (ServerException e) {
            System.out.println("ErrCode : " + e.getErrCode());
            e.printStackTrace();
            return false;
        }
        catch (ClientException e) {
            System.out.println("ErrCode : " + e.getErrCode());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean sendEmailBatch(String _url, String _payload) {
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FhuxFfezdfnybrYk9t6", "KHqrjvn9EUNABwIhtPMGdQNX8QhYHW");
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        //使用https加密连接
        request.setProtocol(com.aliyuncs.http.ProtocolType.HTTPS);
        try {
            request.setAccountName("myemail@yangyang666.xyz");
            request.setFromAlias("YY");
            request.setAddressType(1);
            request.setReplyToAddress(true);
            request.setToAddress(_url);
            //request.setToAddress("邮箱1,邮箱2");
            request.setSubject("测试邮件");
            request.setHtmlBody(_payload);
            SingleSendMailResponse httpResponse = client.getAcsResponse(request);
            return true;
        } catch (ServerException e) {
            System.out.println("ErrCode : " + e.getErrCode());
            e.printStackTrace();
            return false;
        }
        catch (ClientException e) {
            System.out.println("ErrCode : " + e.getErrCode());
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean validateEmailAddress(String _url) {
        if (_url == null)
            return false;
        String[] url=_url.split(",");
        if(url.length==1)
        {
            String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern p;
            Matcher m;
            p = Pattern.compile(regEx1);
            m = p.matcher(_url);
            if (m.matches())
                return true;
            else
                return false;
        }
        else
        {
            for(int i=0;i<url.length;i++)
            {
                String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
                Pattern p;
                Matcher m;
                p = Pattern.compile(regEx1);
                m = p.matcher(url[i]);
                if (m.matches())
                    return true;
                else
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String _url="779771381@qq.com,1573615704@qq.com";
        soap_send re=new soap_send();
        re.sendEmail("779771381@qq.com","我是你爸爸");
        re.sendEmailBatch(_url,"我是你爸爸");
        boolean test;
        test=re.validateEmailAddress("@tredqq.com");
        System.out.println(test);
    }
}
