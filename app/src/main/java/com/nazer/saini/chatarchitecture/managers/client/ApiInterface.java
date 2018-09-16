package com.nazer.saini.chatarchitecture.managers.client;



import com.nazer.saini.chatarchitecture.pojomodels.ResponsePojo;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by nazer on 27/9/17.
 */

public interface ApiInterface {


    @Multipart
    @POST("Albums/addPhotosOrVideos")
    Call<ResponsePojo> addPhotoVideo(@Part("type") int type, @PartMap Map<String, RequestBody> map, @Part MultipartBody.Part file);

    @Streaming
    @GET
    Call<ResponseBody> downloadFileWithDynamicUrlAsync(@Url String fileUrl);
}
