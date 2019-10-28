package Client;


import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


public class Cli_rest {

    public Cli_rest(){
    };


    public String do_restserver_sendemails(String _url,String payload)
    {
//创建一个Client对象
        String[] url=_url.split(",");
        System.out.println(_url);
        if(url.length==1)
        {
            Client client = ClientBuilder.newClient();
            WebTarget roottarget = client.target("http://yangyang666.xyz:8080/rest/rest_send");
            WebTarget sendEmailtarget = roottarget.path("/sendEmail");//创建一个子资源URI
            WebTarget sendEmailtargetWithQueryParam = sendEmailtarget.queryParam("url", _url).queryParam("payload",payload);
            Invocation.Builder invocationBuilder = sendEmailtargetWithQueryParam.request(MediaType.TEXT_PLAIN_TYPE);
            invocationBuilder.header("some-header", "true");
            Response response = invocationBuilder.get();
            response.getStatus();
            return response.readEntity(String.class);
        }
        else
        {
            Client client = ClientBuilder.newClient();
            WebTarget roottarget = client.target("http://yangyang666.xyz:8080/rest/rest_send");
            WebTarget sendEmailtarget = roottarget.path("/sendEmailBatch");//创建一个子资源URI
            WebTarget sendEmailtargetWithQueryParam =sendEmailtarget.queryParam("url",_url).queryParam("payload",payload);
            Invocation.Builder invocationBuilder = sendEmailtargetWithQueryParam.request(MediaType.TEXT_PLAIN_TYPE);
            invocationBuilder.header("some-header", "true");
            Response response = invocationBuilder.get();
            response.getStatus();
            return response.readEntity(String.class);
        }

    }

    public String do_restserver_check(String check)
    {
//创建一个Client对象
        Client client = ClientBuilder.newClient();
        WebTarget roottarget = client.target("http://yangyang666.xyz:8080/rest/rest_send");
        WebTarget sendEmailtarget = roottarget.path("/validateEmailAddress");//创建一个子资源URI
        WebTarget sendEmailtargetWithQueryParam =
                sendEmailtarget.queryParam("url", check);
        Invocation.Builder invocationBuilder =
                sendEmailtargetWithQueryParam.request(MediaType.TEXT_PLAIN_TYPE);
        invocationBuilder.header("some-header", "true");
        Response response = invocationBuilder.get();
        response.getStatus();
        return response.readEntity(String.class);
    }


    public static void main(String[] args) throws IOException {
        Cli_rest c=new Cli_rest();
        String _url="779771381@qq.com,1573615704@qq.com";
        String _url2="779771381@qq.com";
        c.do_restserver_sendemails(_url,"test！");
        c.do_restserver_sendemails(_url2,"test2！");


    }


}
