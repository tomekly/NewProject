package oclass.student.com.newproject.retrofit;




import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


/**
 * retrofit 框架
 * Created by 2017/12/15
 */

public class RetrofitFactory {

    //195 147
    public static String BASE_URL = "http://192.168.1.155:8080/";
    private static final int RETURN_TYPE_STRING = 0;
    private static final int RETURN_TYPE_GSON = 1;
    private final static int TIME_OUT = 30000;

    /**
     * TODO: WSY
     * 全局使用同一个okhttp clinet
     */
    private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder().cookieJar(new CookieJar() {
        /**
         * cookie
         */
        private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();

        //http://192.168.1.195:8080/njws-oa/appLogin
        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
//            cookieStore.put(url, cookies);
            cookieStore.put(HttpUrl.parse(""), cookies);
            for (Cookie cookie : cookies) {
                System.out.println("cookie Name:" + cookie.name());
                System.out.println("cookie Path:" + cookie.path());
            }
        }

        //http://192.168.1.195:8080/njws-oa/appLogin
        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            List<Cookie> cookies = cookieStore.get(HttpUrl.parse(""));
            if (cookies == null) {
                System.out.println("没加载到cookie");
            }
            return cookies != null ? cookies : new ArrayList<Cookie>();
        }
    })

            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request().newBuilder()
                            .addHeader("Content-Type", "application/json;charset=UTF-8")
                            .addHeader("Connection", "keep-alive")
                            .addHeader("Accept", "*/*").build();
                    return chain.proceed(request);
                }
            })
            .addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    okhttp3.Response proceed = chain.proceed(request);
                    return proceed;
                }
            })
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .build();

    private static Retrofit getRetrofit(String url, int returnType) {
        Retrofit retrofit = null;
        if (returnType == RETURN_TYPE_STRING) {
            retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(ScalarsConverterFactory.create()).client(getOkHttpClient()).build();
        }
        if (returnType == RETURN_TYPE_GSON) {
            retrofit = new Retrofit.Builder().baseUrl(url)
// 使用rxjava2作为返回处理
  .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getOkHttpClient())
                    .build();
        }
        return retrofit;
    }

    /**
     * 返回类型为实体Bean
     *
     * @param url
     * @return
     */
    public static RetrofitService getGsonRetrofitService(String url) {
        return getRetrofit(url, RETURN_TYPE_GSON).create(RetrofitService.class);
    }

    /**
     * 返回类型为String
     *
     * @param url
     * @return
     */
    public static RetrofitService getStrongRetrofitService(String url) {
        return getRetrofit(url, RETURN_TYPE_STRING).create(RetrofitService.class);
    }

    /**
     * 设置 超时
     *
     * @return
     */
    private static OkHttpClient getOkHttpClient() {

        return OK_HTTP_CLIENT;

    }


    /**
     * 解析特殊json
     *
     * @param t
     * @return
     */
    public static RequestBody getParams(Object t) {
        Gson gson = new Gson();
        String params = gson.toJson(t);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), params);
        return body;
    }
}
