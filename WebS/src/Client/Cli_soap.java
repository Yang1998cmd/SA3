package Client;
import org.apache.axis.utils.StringUtils;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Cli_soap {
//    public void do_soapserver_sendemails() throws IOException {
//        //第一步：创建服务地址
//        URL url = new URL("http://localhost:8080/services/soapserver?wsdl");
//        //第二步：打开一个通向服务地址的连接
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        //第三步：设置参数
//        connection.setRequestMethod("POST");
//        //3.2设置数据格式：content-type
//        connection.setRequestProperty("content-type", "text/xml;charset=utf-8");
//        //3.3设置输入输出，因为默认新创建的connection没有读写权限，
//        connection.setDoInput(true);
//        connection.setDoOutput(true);
//
//        //第四步：组织SOAP数据，发送请求
//        String soapXML = get_send_XML1("779771381@qq.com","nihao!!");
//
//        System.out.println(soapXML);
//        OutputStream os = connection.getOutputStream();
//        os.write(soapXML.getBytes());
//        //第五步：接收服务端响应，打印
//        int responseCode = connection.getResponseCode();
//        System.out.println(responseCode);
//        if(200 == responseCode){//表示服务端响应成功
//            InputStream is = connection.getInputStream();
//            InputStreamReader isr = new InputStreamReader(is);
//            BufferedReader br = new BufferedReader(isr);
//
//            StringBuilder sb = new StringBuilder();
//            String temp = null;
//            while(null != (temp = br.readLine())){
//                sb.append(temp);
//            }
//            System.out.println(sb.toString());
//
//            is.close();
//            isr.close();
//            br.close();
//        }
//        os.close();
//    }
//
//    public  String get_send_XML1(String url,String payload){
//
//        String soapXML ="<?xml version=\"1.0\" encoding=\"UTF-8\"?> "
//                +"<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">"
//                +"<soap:Body>"
//                +"<sendEmail xmlns=\"http://soapserver\">"
//                + "<_url>"+ url +"</_url>"
//                +"<_payload>"+payload+"</_payload>"
//                +"</sendEmail>"
//                +"</soap:Body>"
//                +"</soap:Envelope>";
////        +"<Envelope xmlns=\"http://schemas.xmlsoap.org/soap/envelope/\">"
////                +"<Body>"
////                +"<sendEmail xmlns=\"http://soapserver\">"
////                +"<_url>"+url+"</_url>"
////                +"<_payload>"+payload+"</_payload>"
////                +" </sendEmail>"
////                +" </Body>"
////                +"</Envelope>";
//        return soapXML;
//    }
    //实现WebService上发布的服务调用
    public String do_soapserver(String url, String method, Object[] args) {
        String result = null;
        if(StringUtils.isEmpty(url)) {
            return "url地址为空";
        }
        if(StringUtils.isEmpty(method)) {
            return "method地址为空";
        }
        Call rpcCall = null;
        try {
            //实例websevice调用实例
            Service webService = new Service();
            rpcCall = (Call) webService.createCall();
            rpcCall.setTargetEndpointAddress(new java.net.URL(url));
            rpcCall.setOperationName(method);
            //执行webservice方法
            result = rpcCall.invoke(args).toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public String sendEmailSOAP(String _url,String _payload){
        String url = "http://yangyang666.xyz:8080/services/soapserver?wsdl";
        //调用的方法
        String method = "sendEmail";
        //调用方法的参数列表
        Object[] parms = new Object[]{_url,_payload};
        //调用方法
        return do_soapserver(url, method, parms);
    }

    public String sendEmailBatchSOAP(String _url, String _payload){
        String url = "http://yangyang666.xyz:8080/services/soapserver?wsdl";
        //调用的方法
        String method = "sendEmailBatch";
        //调用方法的参数列表
        Object[] parms = new Object[]{_url,_payload};
        //调用方法
        return do_soapserver(url, method, parms);
    }

    public String  validateEmailAddressSOAP(String _url){
        String url = "http://yangyang666.xyz:8080/services/soapserver?wsdl";
        //调用的方法
        String method = "validateEmailAddress";
        //调用方法的参数列表
        Object[] parms = new Object[]{_url};
        //调用方法
        return do_soapserver(url, method, parms);
    }

    public static void main(String[] args) throws IOException {
        Cli_soap c=new Cli_soap();
        String _url="779771381@qq.com,1573615704@qq.com";
        // c.do_restserver_sendemails();
        //c.do_restserver_sendemails(_url,"test！");
        System.out.println(c.sendEmailSOAP("779771381@qq.com","好了没？"));
        c.sendEmailBatchSOAP(_url,"好了没？");

    }
}