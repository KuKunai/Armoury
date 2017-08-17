package com.minipg.fanster.armoury.manager.http;


import com.minipg.fanster.armoury.dao.CategoryItemDao;
import com.minipg.fanster.armoury.dao.LoginResponseItemDao;
import com.minipg.fanster.armoury.dao.RegisterResponseItemDao;
import com.minipg.fanster.armoury.dao.TopicItemDao;
import com.minipg.fanster.armoury.dao.UserDao;
import com.minipg.fanster.armoury.object.RegisterForm;
import com.minipg.fanster.armoury.object.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Knot on 8/3/2017.
 */

public interface ApiService {

    @GET("topic/getalltopic")
    Call<TopicItemDao> loadAllTopicList();

    @POST("login")
    Call<LoginResponseItemDao> login(@Body User userObject);

    @POST("user/register")
    Call<RegisterResponseItemDao> register(@Body RegisterForm userObject);

    @GET("user/get-by-id/{id}")
    Call<UserDao> loadUserById(@Path("id") String id);

    @GET("topic/get-by-id/{id}")
    Call<TopicItemDao> loadTopicById(@Path("id") String id);

    @GET("topic/get-by-category/{cate}")
    Call<List<TopicItemDao>> loadTopicListByType(@Path("cate") String name);

    @GET("category/getallcategory")
    Call<List<CategoryItemDao>> loadAllCategoryList();
}
