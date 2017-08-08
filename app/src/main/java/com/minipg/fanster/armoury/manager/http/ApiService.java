package com.minipg.fanster.armoury.manager.http;


import com.minipg.fanster.armoury.dao.TopicItemDao;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Knot on 8/3/2017.
 */

public interface ApiService {

    @GET("topic/getalltopic")
    Call<TopicItemDao> loadAllTopicList();

    @GET("topic/get/{type}")
    Call<TopicItemDao> loadTopicListByType(@Path("type") String type);
}
