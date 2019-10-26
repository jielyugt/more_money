package uk.ivanc.archi.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;

public interface ItemService {

//    @GET("users/{username}/repos")
//    Observable<List<Repository>> publicRepositories(@Path("username") String username);
//
//    @GET
//    Observable<User> userFromUrl(@Url String userUrl);

//    @Headers({"Authorization", "Bearer: "+ token})
    @GET("inventory/items")
    Call<Object> getAllItem(@Header("Authorization") String authHeader);


    @GET("oauth2/token")
    Call<Object> getToken(@Header("client_id") String client_id, @Header("client_secret") String client_secret);

    class Factory {
        public static ItemService create() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api-reg-apigee.ncrsilverlab.com/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();

//            return retrofit.create(ItemService.class);
            ItemService request = retrofit.create(ItemService.class);

            //对 发送请求 进行封装
            String token = "Bearer " + "gAAAAMwM6o1YRwcYTCIWEPM1E9AImoItHhovLflJhwiehQo6LRELZ9yIDrgYnCaXhFbpee6hodQI_Yl974IVfZxbVmAYWA1pRBzBXdijWuYfNG1pH4tCoKi0WU1JxgUe0Zif00rrUTQo8vpPcGP8VJoB9aHJL5Defvte-snhVykFW31E9AAAAIAAAABd2RHa7-fvbHa1zP_kMRtzCC54JKTPHlQMW-9m-PhY7MsIZHWtZ9PtH66-4rTnwF-qHvdzba8U40oY2TfKQQOuoNEs4hVaevltzP0jFkCs6g4ZweITKg9wic4RzpHQSIm8UbEr4GDRAJwfkAYZsBp1A3aKSUY_xUmOMbryn9bbBYtsmfJW53y06c8tZFXP4YTsNfw0f-oQyvb72iDgB3CoOwf_kM1iAeNN8SMzSv6LAVPqHSpLmJTxlEUaU5t5u5WC54lim33Ep-G5Oelz0YGqGGNgj-NUDxGNAIL2AWJkx-gXMp9gHmqI6v2Sz0OsqOs";

            System.out.println("fuck");
            Call<Object> call = request.getAllItem(token);

//            Call<Object> call = request.getToken("gt_552464", "002b0069-0048-0030-4300-410031007c00");

            //步骤6:发送网络请求(异步)
            call.enqueue(new Callback<Object>() {
                //请求成功时回调
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {
                    // 步骤7：处理返回的数据结果
                    if (response.body() == null) {
                        System.out.println("fuck");
                        return;
                    }

                    System.out.println(response.body());

                }

                @Override
                public void onFailure(Call<Object> call, Throwable throwable) {
                    System.out.println("Connection failed");
                }
            });
            return request;
        }
    }
}
