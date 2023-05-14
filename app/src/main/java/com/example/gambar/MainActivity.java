package com.example.gambar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

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

    private int mColorBackground;
    private int mColorRetangle;
    private int mColorAccent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mColorBackground = ResourcesCompat.getColor(getResources(), R.color.colorBackground, null);
        mColorRetangle = ResourcesCompat.getColor(getResources(), R.color.colorRetangle, null);
        mColorAccent = ResourcesCompat.getColor(getResources(), R.color.colorAccent, null);

        mPaint.setColor(mColorBackground);
        mPaintText.setColor(ResourcesCompat.getColor(getResources(), R.color.black, null));
        mPaintText.setTextSize(70);

        mImageView = findViewById(R.id.myimageview);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawSomething(v);
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
            mCanvas.drawColor(mColorBackground); //untuk memberi warna pada background

            mCanvas.drawText(getString(R.string.keep_tapping), 100, 100, mPaintText);
            mOffset += OFFSET;
        }
        else {
            if (mOffset < halfWidth && mOffset < halfHeight){
                mPaint.setColor(mColorRetangle - MULTIPLER * mOffset);

                mRect.set(mOffset, mOffset, vWidth - mOffset, vHeight - mOffset);
                mCanvas.drawRect(mRect, mPaint);
                mOffset += OFFSET;
            }
            else {
//                mPaint.setColor(mColorAccent);
//                mPaint.setColor(mColorAccent - MULTIPLER * mOffset);
                mCanvas.drawCircle(halfWidth, halfHeight, halfWidth / 3, mPaint);
                mOffset += OFFSET;

                //menggambar segitiga
                Point a = new Point(halfWidth - 50, halfHeight - 50); //titik kanan
                Point b = new Point(halfWidth + 50, halfHeight - 50); //titik kiri
                Point c = new Point(halfWidth, halfHeight + 250); //titik tengah bawah

                Path path = new Path();
                path.setFillType(Path.FillType.EVEN_ODD);
                //untuk memebrikan warna didalam tag menggunakan even_odd
                //kalau di luar tag menggunakan inverse_winding
                path.lineTo(a.x, a.y);
                path.lineTo(b.x, b.y);
                path.lineTo(c.x, c.y);
                path.lineTo(a.x, a.y);
                //jika path terakhit di hilangkan maka gambarnya akan berantakan karena kembali ke 0.0
                path.close();

                mPaint.setColor(mColorRetangle - MULTIPLER * mOffset);

                mCanvas.drawPath(path, mPaint);

                String text = getString(R.string.done);

                 //Get bounding box for text to calculate where to draw it.
                mPaintText.getTextBounds(text, 0, text.length(), mBounds);

                // Calculate x and y for text so it's centered.
                int x = halfWidth - mBounds.centerX();
                int y = halfHeight - mBounds.centerY();
                mCanvas.drawText(text, x, y, mPaintText);
            }
        }
        view.invalidate();
    }
}