package grup.Client;

import java.net.MalformedURLException;
import java.net.URL;

public class RequestBuilder {
    private String url;
    private Boolean primul = true;
    public RequestBuilder(String url, String route){
        this.url = url + route;

    }

    public void addValue(String name, String value)
    {
        if(primul == true)
        {
            url = url+"?"+name+"="+value;
            primul = false;
        }
        else{
            url = url+"&"+name+"="+value;
        }
    }
    public URL build() throws MalformedURLException {
        return new URL(url);
    }
}
