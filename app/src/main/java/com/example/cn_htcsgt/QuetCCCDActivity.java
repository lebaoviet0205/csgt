//package com.example.cn_htcsgt;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.EditText;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.budiyev.android.codescanner.CodeScanner;
//import com.budiyev.android.codescanner.CodeScannerView;
//import com.budiyev.android.codescanner.DecodeCallback;
//import com.google.zxing.Result;
//
//public class QuetCCCDActivity extends AppCompatActivity {
//
//    String CCCD;
//    EditText txt;
//    CodeScanner codeScanner;
//    CodeScannerView codeScannerView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_quet_cccdactivity);
//
//        txt = (EditText) findViewById(R.id.EextView);
//        codeScannerView = findViewById(R.id.scannerView);
//        codeScanner = new CodeScanner(this, codeScannerView);
//
//        codeScanner.setDecodeCallback(new DecodeCallback() {
//            @Override
//            public void onDecoded(@NonNull Result result) {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        txt.setText(result.getText());
//                        CCCD=txt.getText().toString();
//
//                        Intent i = new  Intent(QuetCCCDActivity.this,LapBB.class);
//                       // String timkiem1 = txt.getText().toString().trim();
//                        i.putExtra("CCCD :", CCCD);
//                        startActivity(i);
//                    }
//                });
//            }
//        });
//
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        requestCamera();
//    }
//
//    private void requestCamera() {
//        codeScanner.startPreview();
//    }
//}