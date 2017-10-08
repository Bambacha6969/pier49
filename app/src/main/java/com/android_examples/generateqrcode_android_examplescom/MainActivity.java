package com.android_examples.generateqrcode_android_examplescom;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;

    ImageView imageView;

    public final static int QRcodeWidth = 500 ;

    Bitmap bitmap ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        imageView = (ImageView) findViewById(R.id.imageView);

        // Get user ID and nonce from shared preferences
        String userID = sharedpreferences.getString(LoginActivity.SHARED_PREFS_KEY_USER_ID, null);
        String nonce = sharedpreferences.getString(LoginActivity.SHARED_PREFS_KEY_NONCE, null);
        try {
            String nonce_hash = SHA256(nonce);

            // Create JSON object
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode objectNode1 = mapper.createObjectNode();
            objectNode1.put("userID", userID);
            objectNode1.put("nonce_hash", nonce_hash);
            String qrCodeString = objectNode1.toString();

            bitmap = TextToImageEncode(qrCodeString);
            imageView.setImageBitmap(bitmap);
        } catch (Exception e) {
            // TODO Implement proper error handling
            e.printStackTrace();
        }
    }
    public static String SHA256 (String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(text.getBytes("UTF-8"));
        byte[] digest = md.digest();
        return String.format("%064x", new java.math.BigInteger(1, digest));
    }

    Bitmap TextToImageEncode(String Value) throws WriterException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(
                    Value,
                    BarcodeFormat.DATA_MATRIX.QR_CODE,
                    QRcodeWidth, QRcodeWidth, null
            );
        } catch (IllegalArgumentException Illegalargumentexception) {
            return null;
        }
        int bitMatrixWidth = bitMatrix.getWidth();
        int bitMatrixHeight = bitMatrix.getHeight();
        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];
        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;
            for (int x = 0; x < bitMatrixWidth; x++) {
                pixels[offset + x] = bitMatrix.get(x, y) ?
                        getResources().getColor(R.color.QRCodeBlackColor):getResources().getColor(R.color.QRCodeWhiteColor);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);
        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }
}
