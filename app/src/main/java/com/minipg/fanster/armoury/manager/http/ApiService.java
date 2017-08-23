package com.minipg.fanster.armoury.manager.http;


import com.minipg.fanster.armoury.dao.CategoryItemDao;
import com.minipg.fanster.armoury.dao.LoginResponseItemDao;
import com.minipg.fanster.armoury.dao.RegisterResponseItemDao;
import com.minipg.fanster.armoury.dao.TopicItemDao;
import com.minipg.fanster.armoury.dao.UserDao;
import com.minipg.fanster.armoury.dao.UserRankingDao;
import com.minipg.fanster.armoury.object.RegisterForm;
import com.minipg.fanster.armoury.object.TopicForm;
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

    @GET("topic/get-popular-topic")
    Call<List<TopicItemDao>> loadAllPopularList();

    @GET("topic/get-like-topic/{id}")
    Call<List<TopicItemDao>> loadAllLikeList(@Path("id") String id);

    @GET("user/get-user-by-rank")
    Call<List<UserRankingDao>> loadAllRanking();

    @POST("login")
    Call<LoginResponseItemDao> login(@Body User userObject);

    @POST("user/register")
    Call<RegisterResponseItemDao> register(@Body RegisterForm userObject);

    @POST("user/share/{id}")
    Call<String> addTopic(@Path("id") String id,@Body TopicForm topicObject);

    @POST("user/like/{userid}/{topicid}")
    Call<Boolean> likeTopic(@Path("userid") String userid,@Path("topicid") String topicid);

    @GET("user/get-by-id/{id}")
    Call<UserDao> loadUserById(@Path("id") String id);

    @GET("topic/get-by-id/{id}")
    Call<TopicItemDao> loadTopicById(@Path("id") String id);

    @GET("topic/get-by-category/{cate}")
    Call<List<TopicItemDao>> loadTopicListByType(@Path("cate") String name);

    @GET("category/getallcategory")
    Call<List<CategoryItemDao>> loadAllCategoryList();

}
