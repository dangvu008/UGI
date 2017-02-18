package dang.ugi.com.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DANG on 1/2/2017.
 */

public class RetrofitHandler {
   public static  Retrofit retrofit(String baseURL){
       HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
       interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
       OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
       Gson gson = new GsonBuilder().setLenient().create();
       final Retrofit retrofit = new Retrofit.Builder()
               .baseUrl(baseURL)
               .client(client)
               .addConverterFactory(new NullOnEmptyConverterFactory())
               .addConverterFactory(GsonConverterFactory.create(gson))
               .build();
       return retrofit;
   }
    static class NullOnEmptyConverterFactory extends Converter.Factory {

        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            final Converter<ResponseBody, ?> delegate = retrofit.nextResponseBodyConverter(this, type, annotations);

            return new Converter<ResponseBody, Object>() {

                @Override
                public Object convert(ResponseBody value) throws IOException {

                    if (value.contentLength() == 0) return null;
                    return delegate.convert(value);
                }
            };
        }
    }
}
