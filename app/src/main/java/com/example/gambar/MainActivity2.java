package com.example.gambar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity2 extends AppCompatActivity {

    private Canvas mCanvas;
    private Paint mPaint = new Paint(); //digunakan untuk menggambar objek
    private Paint mPaintText = new Paint(Paint.UNDERLINE_TEXT_FLAG); //digunakan untuk membuat tulisan atau label
    private Bitmap mBitmap;
    private ImageView mImageView;
    private Rect mRect = new Rect();
    private Rect mBounds = new Rect();

    private static final int OFFSET = 120; //untuk mengatur posisi dari objek
    private int mOffset = OFFSET;
    private static final int MULTIPLER = 100; //digunakan untuk merubah warna agar bisa berubah secara otomatis
    private static final int COUNT = 0;
    private int count = COUNT;

    private int mColorBackground;
    private int mColorRetangle;
    private int mColorAccent;
    private int bg;
    private int kelopak_1;
    private int kelopak_2;
    private int tengah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mColorBackground = ResourcesCompat.getColor(getResources(), R.color.colorBackground, null);
        mColorRetangle = ResourcesCompat.getColor(getResources(), R.color.colorRetangle, null);
        mColorAccent = ResourcesCompat.getColor(getResources(), R.color.colorAccent, null);
        bg = ResourcesCompat.getColor(getResources(), R.color.bg, null);
        kelopak_1 = ResourcesCompat.getColor(getResources(), R.color.kelopak_1, null);
        kelopak_2 = ResourcesCompat.getColor(getResources(), R.color.kelopak_2, null);
        tengah = ResourcesCompat.getColor(getResources(), R.color.tengah, null);

        mPaint.setColor(mColorBackground);
        mPaintText.setColor(ResourcesCompat.getColor(getResources(), R.color.black, null));
        mPaintText.setTextSize(70);

        mImageView = findViewById(R.id.myimageview);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawSomething(v);

                //draw(view, count);
                //count =+ 1;
            }
        });
    }

    public void drawSomething(View view){
        int vWidth = view.getWidth();
        int vHeight = view.getHeight();
        int halfWidth = vWidth/2;
        int halfHeight = vHeight/2;

        if (mOffset == OFFSET){
            mBitmap = Bitmap.createBitmap(vWidth, vHeight, Bitmap.Config.ARGB_8888);
            mImageView.setImageBitmap(mBitmap);
            mCanvas = new Canvas(mBitmap); //baru draw text
            mCanvas.drawColor(bg); //untuk memberi warna pada background


//            mCanvas.drawCircle(500, 200, halfWidth/3, mPaint);

            count += 1;
            mOffset += OFFSET;
        }
        else {
            if (count ==1){
                mPaint.setColor(kelopak_1);
                //lingkaran atas
                mCanvas.drawCircle(550, 750, halfWidth / 3, mPaint);
                count += 1;
                mOffset += OFFSET;

//                mCanvas.drawCircle(halfWidth, halfHeight, halfWidth / 3, mPaint);
//                mOffset += OFFSET;

            }
            else if (count == 2) {
                mPaint.setColor(kelopak_2);
                mCanvas.drawCircle(750,800, halfWidth/3, mPaint);
                count += 1;
                mOffset += OFFSET;
            }
            else if (count == 3) {
                mPaint.setColor(kelopak_1);
                mCanvas.drawCircle(800, 1000, halfWidth / 3, mPaint);
                count += 1;
                mOffset += OFFSET;
            }
            else if (count == 4) {
                mPaint.setColor(kelopak_2);
                mCanvas.drawCircle(750, 1150, halfWidth / 3, mPaint);
                count += 1;
                mOffset += OFFSET;
            }
            else if (count == 5) {
                mPaint.setColor(kelopak_1);
                mCanvas.drawCircle(550, 1250, halfWidth / 3, mPaint);
                count += 1;
                mOffset += OFFSET;
            }
            else if (count == 6) {
                mPaint.setColor(kelopak_2);
                mCanvas.drawCircle(400, 1150, halfWidth / 3, mPaint);
                count += 1;
                mOffset += OFFSET;
            }
            else if (count == 7) {
                mPaint.setColor(kelopak_1);
                mCanvas.drawCircle(350, 1000, halfWidth / 3, mPaint);
                count += 1;
                mOffset += OFFSET;
            }
            else if (count == 8) {
                mPaint.setColor(kelopak_2);
                mCanvas.drawCircle(400, 825, halfWidth / 3, mPaint);
                count += 1;
                mOffset += OFFSET;
            }
            else if (count == 9){
                mPaint.setColor(tengah);
                mCanvas.drawCircle(550, 1000, halfWidth / 4, mPaint);
                count += 1;
                mOffset += OFFSET;
            }
            else {
                mCanvas.drawText(getString(R.string.bunga), 100, 100, mPaintText);
            }
        }
        view.invalidate();
    }
}