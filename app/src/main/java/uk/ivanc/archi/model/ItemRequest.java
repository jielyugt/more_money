package uk.ivanc.archi;


import java.util.*;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.ivanc.archi.model.ItemService;
import uk.ivanc.archi.model.Item;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;


public class ItemRequest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static void request() {

        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-reg-apigee.ncrsilverlab.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        // 步骤5:创建 网络请求接口 的实例
        ItemService request = retrofit.create(ItemService.class);

        //对 发送请求 进行封装
        Call<List<Item>> call = request.getAllItem("");

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<List<Item>>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                // 步骤7：处理返回的数据结果
                for (Item i: response.body()) {
                    i.show();
                }
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<List<Item>> call, Throwable throwable) {
                System.out.println("Connection failed");
            }
        });
    }
}
