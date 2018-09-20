package oclass.student.com.newproject.retrofit;


import oclass.student.com.newproject.bean.Weather;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;


/**
 * retrofit 接口
 * Created by 2017 12 12
 */

public interface RetrofitService {
//
//    @FormUrlEncoded
//    @POST("njws-oa/appLogin")
//    Call<BaseBean<UserBean>> requestLoginData(@Field("username") String userName, @Field("password") String password);

    //登录接口
    @GET("https://www.sojson.com/open/api/weather/json.shtml?")
    Call<Weather> getWeather(@Body RequestBody body);

    /**
     * 获取收件箱数据
     *
     * @param body
     * @return
     */
//    @POST("njws-oa/mail/appquery/inbox")
//    Call<BaseBean<InboxBaseBean>> requestInbox(@Body RequestBody body);


}
