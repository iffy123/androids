package com.example.mcqschecker;


import static android.Manifest.permission.CAMERA;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;

public class Scanner extends AppCompatActivity {
    private ImageView camIV;
    private TextView resultIV;
    private Button snapbtn,dectBtn;
    private Bitmap imagebitmap;
    static final int REQUEST_IMAGE_CAPTURE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        camIV=findViewById(R.id.imageView);
        resultIV=findViewById(R.id.textView2);
        snapbtn=findViewById(R.id.snap);
        dectBtn=findViewById(R.id.dect);

        dectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dectext();
            }
        });
        snapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPremissions()) {
                    captureImage();
                }
                else {requestPermission();}
            }
        });
    }
    private boolean checkPremissions(){
        int camerPermision= ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
        return camerPermision == PackageManager.PERMISSION_GRANTED;
    }
    private void requestPermission(){
        int PERMISSION_CODE = 200;
        ActivityCompat.requestPermissions(this,new String[]{CAMERA},PERMISSION_CODE);
    }

    private void captureImage(){
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePicture.resolveActivity(getPackageManager())!=null){
            startActivityForResult(takePicture,REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length>0){
            boolean cameraPermission=grantResults[0]==PackageManager.PERMISSION_GRANTED;
            if(cameraPermission){
                Toast.makeText(this, "permission granted", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "premissions denined ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_IMAGE_CAPTURE && resultCode==RESULT_OK){
            Bundle extras=data.getExtras();
            imagebitmap=(Bitmap) extras.get("data");
            camIV.setImageBitmap(imagebitmap);

        }
    }

    private void dectext(){
        InputImage image = InputImage.fromBitmap(imagebitmap,0);
        TextRecognizer recognizer = TextRecognition.getClient((TextRecognizerOptions.DEFAULT_OPTIONS));
        Task<Text> result = recognizer.process(image).addOnSuccessListener(new OnSuccessListener<Text>() {
            @Override
            public void onSuccess(Text text) {
                StringBuilder result = new StringBuilder();
                for (Text.TextBlock block: text.getTextBlocks()){
                    String blockText = block.getText();
                    Point[] blockCornerPoint = block.getCornerPoints();
                    Rect blockFrame=block.getBoundingBox();
                    for (Text.Line line : block.getLines()){
                        String lineTExt = line.getText();
                        Point[] lineCornerPoint = line.getCornerPoints();
                        Rect lineRect = line.getBoundingBox();
                        for (Text.Element element : line.getElements()){
                            String elementText = element.getText();
                            result.append(elementText);
                        }
                        resultIV.setText(blockText);

                    }
                }


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Scanner.this, "FAIL TO DETECT THEXT FROM IMAGE ", Toast.LENGTH_SHORT).show();
            }
        });


    }


}