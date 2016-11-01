package com.gdysj.myapplication;

import android.os.Bundle;
import android.security.KeyPairGeneratorSpec;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.security.auth.x500.X500Principal;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "keystore";

    private EditText et_original_password;
    private EditText et_password;
    private Button btn_login;
    private Button btn_save_password;
    private TextView tv_encrypt_password;
    private Button btn_delete_key;
    private TextView et_delete_password;

    private KeyStore mKeyStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        et_original_password = (EditText) findViewById(R.id.et_original_password);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_save_password = (Button) findViewById(R.id.btn_save_password);
        tv_encrypt_password = (TextView) findViewById(R.id.tv_encrypt_password);
        btn_delete_key = (Button) findViewById(R.id.btn_delete_key);
        et_delete_password = (TextView) findViewById(R.id.et_delete_password);

        btn_login.setOnClickListener(this);
        btn_save_password.setOnClickListener(this);
        btn_delete_key.setOnClickListener(this);

        initData();

    }

    private void initData(){
        try {
            mKeyStore = KeyStore.getInstance("AndroidKeyStore");
            mKeyStore.load(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btn_login:
                String loginPassword = et_password.getText().toString().trim();
                String encryptPassword = tv_encrypt_password.getText().toString().trim();
                decryptKey(loginPassword, encryptPassword);
                break;

            case R.id.btn_save_password:
                String password = et_original_password.getText().toString().trim();
                generateKey(password);
                break;

            case R.id.btn_delete_key:
                String deleteKey = et_delete_password.getText().toString().trim();
                deleteKey(deleteKey);
                break;
        }
    }

    private void generateKey(String key){
        Log.i(TAG, "generateKey Key " + key);
        try {
            if (!mKeyStore.containsAlias(key)){
                Calendar start = Calendar.getInstance();
                Calendar end = Calendar.getInstance();
                end.add(Calendar.MONTH, 1);
                KeyPairGeneratorSpec spec = new KeyPairGeneratorSpec.Builder(this)
                        .setAlias(key)
                        .setSubject(new X500Principal("CN=Sample Name, O=Android Authority"))
                        .setSerialNumber(BigInteger.ONE)
                        .setStartDate(start.getTime())
                        .setEndDate(end.getTime())
                        .build();

                KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
                generator.initialize(spec);

                KeyPair keyPair = generator.generateKeyPair();

                String finalKey = "";
                Enumeration<String> aliases =  mKeyStore.aliases();
                while (aliases.hasMoreElements()){
                    finalKey = finalKey + aliases.nextElement();
                }

                Log.i(TAG, "generateKey finalKey " + finalKey);

                encryptKey(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "generateKey error");
        }
    }

    private void encryptKey(String key){
        try {
            KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry)mKeyStore.getEntry(key, null);
            RSAPublicKey publicKey = (RSAPublicKey) privateKeyEntry.getCertificate().getPublicKey();

            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "AndroidOpenSSL");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, cipher);
            cipherOutputStream.write(key.getBytes("UTF-8"));
            cipherOutputStream.close();

            byte[] vals = outputStream.toByteArray();
            String encryptPassword = Base64.encodeToString(vals, Base64.DEFAULT);

            Log.i(TAG, "encryptKey  " + encryptPassword);
            tv_encrypt_password.setText(encryptPassword);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "encryptKey error");
        }
    }

    private void decryptKey(String key, String encryptString){

        Log.i(TAG, "decryptKey  key " + key + "   encryptString " + encryptString);
        try {

            KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) mKeyStore.getEntry(key, null);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKeyEntry.getPrivateKey());

            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64.decode(encryptString, Base64.DEFAULT));
            CipherInputStream cipherInputStream = new CipherInputStream(inputStream, cipher);

            ArrayList<Byte> values = new ArrayList<>();
            int nextByte;
            while ((nextByte = cipherInputStream.read()) != -1){
                values.add((byte) nextByte);
            }

            byte[] bytes = new byte[values.size()];
            for (int i = 0; i < bytes.length; i++){
                bytes[i] = values.get(i).byteValue();
            }

            String finalTextString = new String(bytes, 0, bytes.length, "UTF-8");


            Log.i(TAG, "decryptKey  finalTextString " + finalTextString);

            String originalKey = et_original_password.getText().toString().trim();
            if (!TextUtils.equals(finalTextString, originalKey)){
                Toast.makeText(this, "密码错误", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "decryptKey  error ");
            Toast.makeText(this, "密码错误", Toast.LENGTH_LONG).show();
        }
    }

    private void deleteKey(String key){
        Log.i(TAG, "deleteKey key " + key);
        try {
            mKeyStore.deleteEntry(key);
        } catch (KeyStoreException e) {
            e.printStackTrace();
            Toast.makeText(this, "密码不正确", Toast.LENGTH_LONG).show();
        }
    }
}
