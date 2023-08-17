package com.example.cn_htcsgt;

import com.example.cn_htcsgt.api.BienBanServie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class ApiClient {

    private static String BASE_URL="https://192.168.0.11";
    private static Retrofit retrofit;

    public static  Retrofit getClient() {

       if (retrofit==null){
           HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
           interceptor.level(HttpLoggingInterceptor.Level.BODY);

           OkHttpClient mOkHttpClient = getUnsafeOkHttpClient();

           Gson gson = new GsonBuilder()
                   .setLenient()
                   .create();

           retrofit = new Retrofit.Builder()
                   .baseUrl(BASE_URL)
                   .client(mOkHttpClient)
                   .addConverterFactory(GsonConverterFactory.create(gson))
                   .build();
       }

       return retrofit;

    }
    public static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };
            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.level(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager)trustAllCerts[0]);
            builder.hostnameVerifier((hostname, session)->true);
            return builder.addInterceptor(interceptor).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static BienBanServie getService(){
        BienBanServie bienBanServie = getClient().create(BienBanServie.class);
        return bienBanServie;

    }

 }

