package com.example.cn_htcsgt;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cn_htcsgt.adapter.BienBanAdapter;
import com.example.cn_htcsgt.adapter.PhuongXaAdapter;
import com.example.cn_htcsgt.adapter.QuanHuyenAdapter;
import com.example.cn_htcsgt.adapter.QuocGiaAdapter;
import com.example.cn_htcsgt.adapter.TTCDadapter;
import com.example.cn_htcsgt.adapter.ThanhPhoAdapter;
import com.example.cn_htcsgt.api.BienBanServie;
import com.example.cn_htcsgt.api.HuyenService;
import com.example.cn_htcsgt.api.PhuongServie;
import com.example.cn_htcsgt.api.QuocGiaService;
import com.example.cn_htcsgt.api.TTCDService;
import com.example.cn_htcsgt.api.TinhService;
import com.example.cn_htcsgt.helper.SharedPreferencesHelper;
import com.example.cn_htcsgt.model.Huyen.GetHuyenResPonse;
import com.example.cn_htcsgt.model.Phuong.GetPhuongResonse;
import com.example.cn_htcsgt.model.QuocGia.GetQuocGiaResponse;
import com.example.cn_htcsgt.model.TTCD.GetTTCDResponse;
import com.example.cn_htcsgt.model.TinhThanh.GetTinhRespone;
import com.example.cn_htcsgt.model.bienban.GetTTBBRespnse;
import com.example.cn_htcsgt.model.bienban.GetbienbanResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TTBBActivity extends AppCompatActivity implements View.OnClickListener {

   // Button Btntimkiemttcd;
   // ListView lvProgram;
   // AutoCompleteTextView TextFind;
   private ThanhPhoAdapter thanhPhoAdapter;
    private QuanHuyenAdapter quanHuyenAdapter;
    private QuocGiaAdapter quocGiaAdapter;
    private PhuongXaAdapter phuongXaAdapter;
    private TTCDadapter tTCDadapter;
    private BienBanAdapter Bienbanadapter;
    private GetPhuongResonse.Data phuongXaData;
    private GetTTBBRespnse.Data BienbanData;
    private GetTTCDResponse.Data ttcdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ttbbactivity);
        ListView lvProgram = (ListView) findViewById(R.id.lvProgram);
        AutoCompleteTextView TextFind = (AutoCompleteTextView) findViewById(R.id.TextFind);
//        Btntimkiemttcd.findViewById(R.id.Btntimkiemttcd);
        quocGiaAdapter = new QuocGiaAdapter(this);
        thanhPhoAdapter = new ThanhPhoAdapter(this);
        quanHuyenAdapter = new QuanHuyenAdapter(this);
        phuongXaAdapter = new PhuongXaAdapter(this);
        tTCDadapter = new TTCDadapter(this);
        Bienbanadapter = new BienBanAdapter(this);
        lvProgram.setAdapter(Bienbanadapter);
        TextFind.setAdapter(tTCDadapter);
        TextFind.findViewById(R.id.TextFind);
        lvProgram.findViewById(R.id.lvProgram);
        CallGetTTCD();
        CallApiGetQuocGia();
        TextFind.setAdapter(tTCDadapter);
       // Btntimkiemttcd.setOnClickListener(this);
        TextFind.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("onItemClick", position + tTCDadapter.getItemData(position).id );


                GetBienBan(ttcdata = tTCDadapter.getItemData(position));
            }
        });
        lvProgram.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("onItemClick", position + Bienbanadapter.getItemData(position).phuong );
               // CallApiGetPhuong(Bienbanadapter.getItemData(position));


               // phuongXaAdapter.getItemData(position).tenphuong;
                Intent i = new Intent(TTBBActivity.this, Listlichsu.class);

                //  i.putExtra("Tìm kiếm :", timkiem1);
           //    i.putExtra("Phường :",    CallApiGetPhuong(Bienbanadapter.getItemData(position).idphuong);
                i.putExtra("Mã Cán Bộ :",   Bienbanadapter.getItemData(position).maTaikhoan );
                i.putExtra("Biển số :",   Bienbanadapter.getItemData(position).xe );
                i.putExtra("Số định danh:",  Bienbanadapter.getItemData(position).nguoiViPham);
                i.putExtra("Tên chủ xe:",  Bienbanadapter.getItemData(position).nguoiviphon);
                i.putExtra("Phường :",   Bienbanadapter.getItemData(position).phuong );
                i.putExtra("Huyện :",   Bienbanadapter.getItemData(position).huyen );
                i.putExtra("Tỉnh :",   Bienbanadapter.getItemData(position).tinh );
                i.putExtra("Quốc Gia :",   Bienbanadapter.getItemData(position).quocgia );
                i.putExtra("Tên Lỗi :",   Bienbanadapter.getItemData(position).loi );
                i.putExtra("Mức phạt:",  Bienbanadapter.getItemData(position).mucphat);
                i.putExtra("Mã Biên Bản :",   Bienbanadapter.getItemData(position).iD_BB );
                i.putExtra("Ngày Lập :", Bienbanadapter.getItemData(position).ngaylap);
                i.putExtra("Nội dung vi phạm:",  Bienbanadapter.getItemData(position).noidungloi);



                startActivity(i);
               // BienbanData = Bienbanadapter.getItemData(position);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
    public void CallGetTTCD(){
        TTCDService service = ApiClient.getClient().create(TTCDService.class);
        service.GetTTCDIndex(SharedPreferencesHelper.getInstance().getToken())
                .enqueue(new Callback<GetTTCDResponse>() {
                    @Override
                    public void onResponse(Call<GetTTCDResponse> call, Response<GetTTCDResponse> response) {
                        if (!response.body().getSuccess()) {
                            Toast.makeText(TTBBActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            tTCDadapter.setDatas(response.body().data);
                        }
                    }
                    @Override
                    public void onFailure(Call<GetTTCDResponse> call, Throwable t) {
                    }
                });



    }
    public  void GetBienBan(GetTTCDResponse.Data itemData){
        BienBanServie service = ApiClient.getClient().create(BienBanServie.class);
        service.GetBienBanIndex(SharedPreferencesHelper.getInstance().getToken())
                .enqueue(new Callback<GetTTBBRespnse>() {
                    @Override
                    public void onResponse(Call<GetTTBBRespnse> call, Response<GetTTBBRespnse> response) {
                        if (!response.body().getSuccess()) {
                            Toast.makeText(TTBBActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Bienbanadapter.setDatas(response.body().data);
                        }
                    }
                    @Override
                    public void onFailure(Call<GetTTBBRespnse> call, Throwable t) {
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
                            Toast.makeText(TTBBActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(TTBBActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(TTBBActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
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
    private void CallApiGetPhuong(GetbienbanResponse.Data itemData) {
        PhuongServie service = ApiClient.getClient().create(PhuongServie.class);
        service.GetPhuong(SharedPreferencesHelper.getInstance().getToken(), itemData.iD_BB, itemData.maBienBan)
                .enqueue(new Callback<GetPhuongResonse>() {
                    @Override
                    public void onResponse(Call<GetPhuongResonse> call, Response<GetPhuongResonse> response) {
                        if (!response.body().getSuccess()) {
                            Toast.makeText(TTBBActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
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
}