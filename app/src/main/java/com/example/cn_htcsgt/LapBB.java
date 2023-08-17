package com.example.cn_htcsgt;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.cn_htcsgt.Intent.AppUtil;
import com.example.cn_htcsgt.adapter.LoiViPhamAdapter;
import com.example.cn_htcsgt.adapter.PhuongXaAdapter;
import com.example.cn_htcsgt.adapter.QuanHuyenAdapter;
import com.example.cn_htcsgt.adapter.QuocGiaAdapter;
import com.example.cn_htcsgt.adapter.TTCDadapter;
import com.example.cn_htcsgt.adapter.ThanhPhoAdapter;
import com.example.cn_htcsgt.api.HuyenService;
import com.example.cn_htcsgt.api.LoiViPhamService;
import com.example.cn_htcsgt.api.PhuongServie;
import com.example.cn_htcsgt.api.QuocGiaService;
import com.example.cn_htcsgt.api.TTCDService;
import com.example.cn_htcsgt.api.TinhService;
import com.example.cn_htcsgt.api.UserService;
import com.example.cn_htcsgt.helper.SharedPreferencesHelper;
import com.example.cn_htcsgt.model.Huyen.GetHuyenResPonse;
import com.example.cn_htcsgt.model.LoiViPham.GetLoiViPhamResponse;
import com.example.cn_htcsgt.model.Phuong.GetPhuongResonse;
import com.example.cn_htcsgt.model.QuocGia.GetQuocGiaResponse;
import com.example.cn_htcsgt.model.TTCD.GetTTCDResponse;
import com.example.cn_htcsgt.model.TinhThanh.GetTinhRespone;
import com.example.cn_htcsgt.model.bienban.GetbienbanRequest;
import com.example.cn_htcsgt.model.bienban.GetbienbanResponse;
import com.example.cn_htcsgt.model.user.GetUserResponse;
import com.example.cn_htcsgt.model.xe.GetXeResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LapBB extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private Button LuuBB,idBtnDetect3;
    private ImageView scanButton;

    public static final int CAMERA_PERMISSION_CODE = 100;
    private EditText txtBienKiemSoat, txtNgayGio, txtNoiViPham, txtMaNganh, txtTenDayDu, txtNgaySinh, txtNoiDungVP, txtYKien,txtBSX;
    private Spinner spinnerQuocGia, spinnerThanhPho, spinnerQuan, spinnerPhuong;
    String[] permissions = {
            Manifest.permission.CAMERA
    };
    String ID_User;
    String BKS ;
    String CCCD ,iD_XE,CCCD1,CCCD2;
    int PERM_CODE = 11;
    private AutoCompleteTextView aucompleLoiVP,txtcccd;
    private ThanhPhoAdapter thanhPhoAdapter;
    private QuanHuyenAdapter quanHuyenAdapter;
    private QuocGiaAdapter quocGiaAdapter;
    private PhuongXaAdapter phuongXaAdapter;
    private LoiViPhamAdapter loiViPhamAdapter;
    private TTCDadapter tTCDadapter;
    private GetPhuongResonse.Data phuongXaData;
    private GetLoiViPhamResponse.Data LoiViPhamXaDaTa;
    private GetTTCDResponse.Data TTCDdata;
    private GetXeResponse.Data Xedata;
    private GetUserResponse.Data Userdata ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lap_bb);
        quocGiaAdapter = new QuocGiaAdapter(this);
        thanhPhoAdapter = new ThanhPhoAdapter(this);
        quanHuyenAdapter = new QuanHuyenAdapter(this);
        phuongXaAdapter = new PhuongXaAdapter(this);
        loiViPhamAdapter = new LoiViPhamAdapter(this);
        tTCDadapter = new TTCDadapter(this);
       idBtnDetect3 = findViewById(R.id.idBtnDetect3);
        aucompleLoiVP = findViewById(R.id.aucompleLoiVP);
        txtNoiViPham = findViewById(R.id.txtNoiViPham);

        scanButton = findViewById(R.id.scanButton);
        txtcccd = findViewById(R.id.txtcccd);
        txtNoiDungVP = findViewById(R.id.txtNoiDungVP);
        txtYKien = findViewById(R.id.txtYKien);
        LuuBB = findViewById(R.id.LuuBB);
        spinnerQuocGia = findViewById(R.id.spinnerQuocGia);
        spinnerThanhPho = findViewById(R.id.spinnerThanhPho);
        spinnerQuan = findViewById(R.id.spinnerQuan);
        spinnerPhuong=findViewById(R.id.spinnerPhuong);
        spinnerQuocGia.setAdapter(quocGiaAdapter);
        spinnerThanhPho.setAdapter(thanhPhoAdapter);
        txtcccd.setAdapter(tTCDadapter);
        spinnerQuan.setAdapter(quanHuyenAdapter);
        spinnerPhuong.setAdapter(phuongXaAdapter);
        aucompleLoiVP.setAdapter(loiViPhamAdapter);
        getSupportActionBar().setTitle("Activity 2");

        iD_XE=getIntent().getExtras().getString("ID");


        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext(),SCANRCCCDActivity.class));
               // startActivity(new Intent(getApplicationContext(), QuetCCCDActivity.class));
            }
        });
        txtcccd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("onItemClick", position + tTCDadapter.getItemData(position).id );
                TTCDdata = tTCDadapter.getItemData(position);
            }
        });
        aucompleLoiVP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("onItemClick", position + loiViPhamAdapter.getItemData(position).id_loi );
                LoiViPhamXaDaTa = loiViPhamAdapter.getItemData(position);
            }
        });
        spinnerQuocGia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("onItemClick", position + quocGiaAdapter.getItemData(position).iD_quocgia );
               CallApiGetTinhthanh(quocGiaAdapter.getItemData(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerThanhPho.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("onItemClick", position + thanhPhoAdapter.getItemData(position).id_tinh );
                CallApiGetHuyen(thanhPhoAdapter.getItemData(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerQuan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("onItemClick", position + quanHuyenAdapter.getItemData(position).id_tinh );
                CallApiGetPhuong(quanHuyenAdapter.getItemData(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerPhuong.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("onItemClick", position + phuongXaAdapter.getItemData(position).id_huyen );
                phuongXaData = phuongXaAdapter.getItemData(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        CallApiGetQuocGia();
        CallApiGetLoiVP();
        CallApiGetTTCD();

        LuuBB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    GetbienbanRequest getbienbanRequest = new GetbienbanRequest();
                    getbienbanRequest.setNoiDungViPham(txtNoiDungVP.getText().toString());
                    getbienbanRequest.setYKienNguoiViPham(txtYKien.getText().toString());
                    getbienbanRequest.setTrangThai(txtNoiViPham.getText().toString());
                    getbienbanRequest.setMaPhuong(phuongXaData.iD_phuong);
                    BKS = getIntent().getExtras().getString("BKS");
                    if (BKS != null && BKS != "") {
                        getbienbanRequest.setMaXe(BKS);

                    }
                    getbienbanRequest.setMaLoi(LoiViPhamXaDaTa.id_loi);
                    getbienbanRequest.setMaNguoiViPham(TTCDdata.id);
                    Getbienban(getbienbanRequest);

                    Toast.makeText(LapBB.this, "Lưu thành công", Toast.LENGTH_SHORT).show();

                }catch (Exception exception)
                {
                    Toast.makeText(LapBB.this, "Lưu không thành công do thiếu trường chưa điền !", Toast.LENGTH_SHORT).show();
                }



            }
        });
    }

    private boolean checkpermissions(){
        List<String> listofpermisssions = new ArrayList<>();
        for (String perm: permissions){
            if (ContextCompat.checkSelfPermission(getApplicationContext(), perm) != PackageManager.PERMISSION_GRANTED){
                listofpermisssions.add(perm);
            }
        }
        if (!listofpermisssions.isEmpty()){
            ActivityCompat.requestPermissions(this, listofpermisssions.toArray(new String[listofpermisssions.size()]), PERM_CODE);
            return false;
        }
        return true;
    }
    private void CallApiGetLoiVP() {
        LoiViPhamService service = ApiClient.getClient().create(LoiViPhamService.class);
        service.GetLoiViPhamIndex(SharedPreferencesHelper.getInstance().getToken())
                .enqueue(new Callback<GetLoiViPhamResponse>() {
                    @Override
                    public void onResponse(Call<GetLoiViPhamResponse> call, Response<GetLoiViPhamResponse> response) {
                        if (!response.body().getSuccess()) {
                            Toast.makeText(LapBB.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            loiViPhamAdapter.setDatas(response.body().data);
                        }
                    }
                    @Override
                    public void onFailure(Call<GetLoiViPhamResponse> call, Throwable t) {
                    }
                });



    }
    private void CallApiGetTTCD() {
        TTCDService service = ApiClient.getClient().create(TTCDService.class);
        service.GetTTCDIndex(SharedPreferencesHelper.getInstance().getToken())
                .enqueue(new Callback<GetTTCDResponse>() {
                    @Override
                    public void onResponse(Call<GetTTCDResponse> call, Response<GetTTCDResponse> response) {
                        if (!response.body().getSuccess()) {
                            Toast.makeText(LapBB.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            tTCDadapter.setDatas(response.body().data);
                        }
                    }
                    @Override
                    public void onFailure(Call<GetTTCDResponse> call, Throwable t) {
                    }
                });



    }

    private void CallApiGetQuocGia() {
        QuocGiaService service = ApiClient.getClient().create(QuocGiaService.class);
        service.GetQuocGia(SharedPreferencesHelper.getInstance().getToken())
                .enqueue(new Callback<GetQuocGiaResponse>() {
                    @Override
                    public void onResponse(Call<GetQuocGiaResponse> call, Response<GetQuocGiaResponse> response) {
                        if (!response.body().getSuccess()) {
                            Toast.makeText(LapBB.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            quocGiaAdapter.setDatas(response.body().data);

                        }
                    }

                    @Override
                    public void onFailure(Call<GetQuocGiaResponse> call, Throwable t) {

                    }
                });


    }
    private void CallApiGetTinhthanh(GetQuocGiaResponse.Data data) {
        TinhService service = ApiClient.getClient().create(TinhService.class);
        service.GetTinh(SharedPreferencesHelper.getInstance().getToken(),data.iD_quocgia,data.tenChinhThuc)
                .enqueue(new Callback<GetTinhRespone>() {
                    @Override
                    public void onResponse(Call<GetTinhRespone> call, Response<GetTinhRespone> response) {
                        if (!response.body().getSuccess()) {
                            Toast.makeText(LapBB.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        } else {

                            thanhPhoAdapter.setDatas(response.body().data);
//
                        }
                    }

                    @Override
                    public void onFailure(Call<GetTinhRespone> call, Throwable t) {

                    }
                });


    }
    private void CallApiGetHuyen(GetTinhRespone.Data itemData) {
        HuyenService service = ApiClient.getClient().create(HuyenService.class);
        service.GetHuyen(SharedPreferencesHelper.getInstance().getToken(),itemData.id_tinh,itemData.tentinhthanh)
                .enqueue(new Callback<GetHuyenResPonse>() {
                    @Override
                    public void onResponse(Call<GetHuyenResPonse> call, Response<GetHuyenResPonse> response) {
                        if (!response.body().getSuccess()) {
                            Toast.makeText(LapBB.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
////
                            quanHuyenAdapter.setDatas(response.body().data);
                        }
                    }

                    @Override
                    public void onFailure(Call<GetHuyenResPonse> call, Throwable t) {

                    }
                });


    }
    private void CallApiGetPhuong(GetHuyenResPonse.Data itemData) {
        PhuongServie service = ApiClient.getClient().create(PhuongServie.class);
        service.GetPhuong(SharedPreferencesHelper.getInstance().getToken(), itemData.id_huyen, itemData.tenhuyen)
                .enqueue(new Callback<GetPhuongResonse>() {
                    @Override
                    public void onResponse(Call<GetPhuongResonse> call, Response<GetPhuongResonse> response) {
                        if (!response.body().getSuccess()) {
                            Toast.makeText(LapBB.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
////
                            phuongXaAdapter.setDatas(response.body().data);
                        }
                    }

                    @Override
                    public void onFailure(Call<GetPhuongResonse> call, Throwable t) {

                    }
                });


    }
    public void Getbienban(GetbienbanRequest getbienbanRequest) {
        Call<GetbienbanResponse> getbienbanResponseCall = ApiClient.getService()
                .getBienBan(SharedPreferencesHelper.getInstance().getToken(), getbienbanRequest);
        getbienbanResponseCall.enqueue(new Callback<GetbienbanResponse>() {
            @Override
            public void onResponse(Call<GetbienbanResponse> call, Response<GetbienbanResponse> response) {
                if (!response.body().getSuccess()) {
                    Toast.makeText(LapBB.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LapBB.this, "succes", Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onFailure(Call<GetbienbanResponse> call, Throwable t) {

            }
        });

    }
    public void GetIDUSer() {
        Map<String, String> data = new HashMap<>();
        UserService service = ApiClient.getClient().create(UserService.class);
        service.GetIDUser(data,SharedPreferencesHelper.getInstance().getToken())
                .enqueue(new Callback<GetUserResponse>() {
                    @Override
                    public void onResponse(Call<GetUserResponse> call, Response<GetUserResponse> response) {
                        if (!response.body().getSuccess()) {
                            Toast.makeText(LapBB.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        } else {

                            ID_User =response.body().data.id;

                            //Toast.makeText(scanner.this, response.body().data.bks, Toast.LENGTH_SHORT).show();
                        }
                    }


                    @Override
                    public void onFailure(Call<GetUserResponse> call, Throwable t) {

                    }
                });

    }
    @Override
    public void onClick(View v) {

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



    }

    public void ScanCCCD(){

    }
    public void nextActivity(){

     String txtBSX1 = txtBSX.getText().toString().trim();

        AppUtil.BKS = txtBSX1 ;

        finish();
    }



}









