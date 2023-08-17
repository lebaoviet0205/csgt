package com.example.cn_htcsgt;


import static android.Manifest.permission.CAMERA;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.cn_htcsgt.api.XeService;
import com.example.cn_htcsgt.helper.SharedPreferencesHelper;
import com.example.cn_htcsgt.model.xe.GetXeResponse;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class scanner extends AppCompatActivity implements View.OnClickListener {
    private Intent intent;
    private Connection connection;
    private EditText TextFindd;
    private ImageView captureIV;
    private TextView resultTV;
    private Button snapBtn, detectBnt, mofileBtn, idBtnDetect3, idBtnDetect4;
    private Bitmap imageBitmap;
    static final int REQUEST_IMAGE_CAPTURE = 1;
   // String BKS, Seri, TenChuXe, DiaChi, Brand, Model, Color, EngineNo, ChassisNo, DateStat, DateEnd, TrangThai, ID_Nganh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
      //  ID_Nganh = getIntent().getExtras().getString("ID_Nganh");
        captureIV = findViewById(R.id.idTVCaptureImage);
        resultTV = findViewById(R.id.idTVDetectText);
        snapBtn = findViewById(R.id.idBntSnap);
        detectBnt = findViewById(R.id.idBtnDetect);
        mofileBtn = findViewById(R.id.idBtnmofile);
        idBtnDetect3 = findViewById(R.id.idBtnDetect3);
        idBtnDetect4 = findViewById(R.id.idBtnDetect4);
//        String token = SharedPreferencesHelper.getInstance().getString("Token_Api");
        TextFindd = findViewById(R.id.TextFind);
        idBtnDetect3.setOnClickListener(this);

        //nút lập bb
        idBtnDetect4.setOnClickListener(this);




        detectBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                detectText();
            }
        });
        snapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkPermissions()) {
                    captureImage();
                } else {
                    requestPermissions();
                }

            }
        });
        mofileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private boolean checkPermissions() {
        int camerPermissions = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
        return camerPermissions == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        int PERMISSION_CODE = 200;
        ActivityCompat.requestPermissions(this, new String[]{CAMERA}, PERMISSION_CODE);

    }

    private void captureImage() {


        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePicture.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePicture, REQUEST_IMAGE_CAPTURE);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0) {
            boolean camerPermissions = grantResults[0] == PackageManager.PERMISSION_GRANTED;
            if (camerPermissions) {
                Toast.makeText(this, "Permissions Granted..", Toast.LENGTH_SHORT).show();
                captureImage();
            } else {
                Toast.makeText(this, "Permissions Granted..", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            captureIV.setImageBitmap(imageBitmap);

        }
    }

    private void detectText() {
        AtomicReference<InputImage> image = new AtomicReference<>(InputImage.fromBitmap(imageBitmap, 0));

        TextRecognizer recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
        Task<Text> result = recognizer.process(image.get()).addOnSuccessListener(new OnSuccessListener<Text>() {
            @Override
            public void onSuccess(@NonNull Text text) {
                StringBuilder result = new StringBuilder();
                for (Text.TextBlock block : text.getTextBlocks()) {
                    String blockText = block.getText();
                    Point[] blockCornerPoint = block.getCornerPoints();
                    Rect blockFame = block.getBoundingBox();
                    for (Text.Line line : block.getLines()) {
                        String lineTExt = line.getText();
                        Point[] lineCornerPoint = line.getCornerPoints();
                        Rect linRect = line.getBoundingBox();
                        for (Text.Element element : line.getElements()) {
                            String elementText = element.getText();
                            result.append(elementText);

                        }
                        TextFindd.setText(blockText);
                    }
                }
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(scanner.this, "sai roi..." + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.idBtnDetect3:

                Map<String, String> data = new HashMap<>();

                data.put("BKS", convert(TextFindd.getText().toString()));
                XeService service = ApiClient.getClient().create(XeService.class);
                service.XeGetXe(data,SharedPreferencesHelper.getInstance().getToken())
                        .enqueue(new Callback<GetXeResponse>() {
                            @Override
                            public void onResponse(Call<GetXeResponse> call, Response<GetXeResponse> response) {
                                if (!response.body().getSuccess()) {
                                    Toast.makeText(scanner.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                } else {
                                    String timkiem1 =TextFindd.getText().toString().trim();
                                  //  if (response.body().data.SeachText(timkiem1 )  ) {

                                        Intent i = new Intent(scanner.this, TTXE.class);

                                      //  i.putExtra("Tìm kiếm :", timkiem1);
                                        i.putExtra("Biển số :", response.body().data.bks);
                                        i.putExtra("SeriNo :", response.body().data.seriNo);
                                        i.putExtra("Tên chủ xe :", response.body().data.tenchuxe);
                                        i.putExtra("Địa chỉ :", response.body().data.diaChi);
                                        i.putExtra("Hãng xe :", response.body().data.hangxe);
                                        i.putExtra("Loại xe :", response.body().data.loaixe);
                                        i.putExtra("Màu xe :", response.body().data.mauxe);
                                        i.putExtra("EngineNo :", response.body().data.engineNo);
                                        i.putExtra("ChassisNo :", response.body().data.chasssisNo);
                                        i.putExtra("DateStart :", response.body().data.ngayMua);
                                        i.putExtra("DateEnd :", response.body().data.iD_XE);
                                        i.putExtra("DateStart :", response.body().data.ngayhethan);

                                        startActivity(i);

                                        //Toast.makeText(scanner.this, response.body().data.bks, Toast.LENGTH_SHORT).show();
                                    }
                                }
                           // }
                            @Override
                            public void onFailure(Call<GetXeResponse> call, Throwable t) {

                            }
                        });
                break;
            case  R.id.idBtnDetect4:
                Intent i = new Intent(scanner.this, LapBB.class);
                startActivity(i);
                break;

        }
    }
    public String convert(String str) {

        String PaseString1 = str.replaceAll("[-\\\\+\\\\.\\\\^:,\\\\ \n \t]", "");


        return PaseString1;
    }

}
