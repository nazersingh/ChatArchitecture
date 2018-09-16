package com.nazer.saini.chatarchitecture.managers.client;

import android.os.AsyncTask;
import android.util.Log;


import com.nazer.saini.chatarchitecture.BuildConfig;
import com.nazer.saini.chatarchitecture.application.MyApplication;
import com.nazer.saini.chatarchitecture.callbacks.GenericCallBack;
import com.nazer.saini.chatarchitecture.managers.prefs.PreferenceHandler;
import com.nazer.saini.chatarchitecture.utils.PrintLog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nazer on 9/16/2017.
 */

public class RetrofitClient {

    private static String TAG = "RetrofitClient";
    private static RetrofitClient retrofitClient = null;


    private RetrofitClient() {

    }

    public static ApiInterface getRetrofitInstance() {
        if (retrofitClient == null) {
            retrofitClient = new RetrofitClient();
        }
        return retrofitClient.getClient();
    }

    public static RetrofitClient getInstance() {
        if (retrofitClient == null)
            retrofitClient = new RetrofitClient();
        return retrofitClient;
    }

    final String mBaseUrl = "http://techslides.com/demos/sample-videos/";

    private ApiInterface getClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new ConnectivityInterceptor(MyApplication.getInstance()))
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        String token = PreferenceHandler.readString(MyApplication.getInstance(), PreferenceHandler.PREF_KEY_LOGIN_TOKEN, "");
                        Request request = chain.request().newBuilder()
                                .addHeader("x-logintoken", token)
                                .build();
                        if (BuildConfig.DEBUG) {
                            Log.e(getClass().getName(), request.headers() + "" + request.body() + "" + request.method() + " " + request.url());
                            Log.e(getClass().getName(), "" + request.header("Cookie"));
                            RequestBody rb = request.body();
                            Buffer buffer = new Buffer();
                            if (rb != null)
                                rb.writeTo(buffer);
                            Log.e(getClass().getName(), "Payload- " + buffer.readUtf8());
                        }
                        return chain.proceed(request);
                    }
                })
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        return new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiInterface.class);

    }

    /**
     * ==============================Api
     */

    public void callDownLoadFile(String url, GenericCallBack genericCallBack) {
        getRetrofitInstance().downloadFileWithDynamicUrlAsync(url)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Log.d(TAG, "server contacted and has file");

                            DownloadFile(response);
                        } else {
                            Log.d(TAG, "server contact failed");
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
    }

    private static void DownloadFile(final retrofit2.Response<ResponseBody> responseBody) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                boolean writtenToDisk = writeResponseBodyToDisk(responseBody.body());
                Log.d(TAG, "file download was a success? " + writtenToDisk);
                return null;
            }
        }.execute();
    }

    private static boolean writeResponseBodyToDisk(ResponseBody body) {
        try {
            File futureStudioIconFile = new File(MyApplication.getInstance().getExternalFilesDir(null) + File.separator + "Future Studio Icon.mp4");
            PrintLog.e(TAG, "" + futureStudioIconFile.getPath());
            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    Log.d(TAG, "file download: " + fileSizeDownloaded + " of " + fileSize);
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }


}
