package com.example.cn_htcsgt;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cn_htcsgt.activity.BaseActivity;
import com.example.cn_htcsgt.api.UserService;
import com.example.cn_htcsgt.helper.SharedPreferencesHelper;
import com.example.cn_htcsgt.model.user.GetTokenRequest;
import com.example.cn_htcsgt.model.user.GetTokenResponse;
import com.google.gson.JsonSyntaxException;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity implements Callback<ResponseBody> {


    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;

    @Override
    protected void InitView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.login;
    }
    @OnClick({R.id.loginButton})
    void onClick(View v) {
        switch (v.getId()){
            case  R.id.loginButton:
                CallLoginSV();
                break;
        }
    }


    private void CallLoginSV() {
        UserService service = ApiClient.getClient().create(UserService.class);
        GetTokenRequest request = new GetTokenRequest(username.getText().toString(),password.getText().toString());
        service.getToken(request).enqueue(new Callback<GetTokenResponse>() {
            @Override
            public void onResponse(Call<GetTokenResponse> call, Response<GetTokenResponse> response) {

                if (response.isSuccessful()) {
                    try {
                        GetTokenResponse data = response.body();
                        if(data.getSuccess()){

                            String token = response.body().getData();
                            SharedPreferencesHelper.getInstance().setToken(token);
                            Intent i = new Intent(MainActivity.this, home.class);
                            startActivity(i);

                            Toast.makeText(MainActivity.this, "login succes", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this, data.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }catch (IllegalStateException | JsonSyntaxException exception)
                        {

                            //...
                        }
                } else {
                    Toast.makeText(MainActivity.this, "login field", Toast.LENGTH_SHORT).show();
                }


            }




            @Override
            public void onFailure(Call<GetTokenResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {

    }
}
