package com.gdysj.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.security.KeyPairGeneratorSpec;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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

public class KeyStoreActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout ll_key_store;
    private EditText et_password;
    private Button btn_generate_key;
    private Button btn_delete_key;
    private Button btn_encrypt_key;
    private Button btn_decrypt_key;
    private TextView tv_original_key;
    private TextView tv_key;
    private TextView tv_encrypt;
    private TextView tv_decrypt;

    private KeyStore mKeyStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_store);

        ll_key_store = (LinearLayout) findViewById(R.id.ll_key_store);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_generate_key = (Button) findViewById(R.id.btn_generate_key);
        btn_delete_key = (Button) findViewById(R.id.btn_delete_key);
        btn_encrypt_key = (Button) findViewById(R.id.btn_encrypt_key);
        btn_decrypt_key = (Button) findViewById(R.id.btn_decrypt_key);
        tv_original_key = (TextView) findViewById(R.id.tv_original_key);
        tv_key = (TextView) findViewById(R.id.tv_key);
        tv_encrypt = (TextView) findViewById(R.id.tv_encrypt);
        tv_decrypt = (TextView) findViewById(R.id.tv_decrypt);

        btn_generate_key.setOnClickListener(this);
        btn_delete_key.setOnClickListener(this);
        btn_encrypt_key.setOnClickListener(this);
        btn_decrypt_key.setOnClickListener(this);

        initData();
        setOriginalKey();

    }

    private void initData(){
        try {
            mKeyStore = KeyStore.getInstance("AndroidKeyStore");
            mKeyStore.load(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setOriginalKey(){
        try {
            String originalKey = "";
            Enumeration<String> aliases =  mKeyStore.aliases();
            while (aliases.hasMoreElements()){
                originalKey = originalKey + aliases.nextElement();
            }

            tv_original_key.setText("OriginalKey: " + originalKey);

        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
    }




    @Override
    public void onClick(View view) {
        String keyString = et_password.getText().toString().trim();
        switch (view.getId()){
            case R.id.btn_generate_key:
                generateKey(keyString);
                break;
            case R.id.btn_delete_key:
                deleteKey(keyString);
                break;
            case R.id.btn_encrypt_key:
//                encryptKey(keyString);
                break;
            case R.id.btn_decrypt_key:
                decryptKey(keyString);
                break;
        }
    }

    private void generateKey(String key){

        try {
            if (!mKeyStore.containsAlias(key)){
                Calendar start = Calendar.getInstance();
                Calendar end = Calendar.getInstance();
                end.add(Calendar.YEAR, 1);
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

                String finalkey = "";
                Enumeration<String> aliases =  mKeyStore.aliases();
                while (aliases.hasMoreElements()){
                    finalkey = finalkey + aliases.nextElement() + " @@!! ";
                }

                tv_key.setText(finalkey);

                encryptKey(finalkey);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteKey(final String key){
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Delete Key")
                .setMessage("Do you want to delete the key\"" + key + "\" from the keystore?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            mKeyStore.deleteEntry(key);
                        } catch (KeyStoreException e) {
                            e.printStackTrace();
                        }
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create();

        alertDialog.show();

        ll_key_store.invalidate();
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
            tv_encrypt.setText(Base64.encodeToString(vals, Base64.DEFAULT));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void decryptKey(String key){
        try {

            KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) mKeyStore.getEntry(key, null);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKeyEntry.getPrivateKey());

            String encryptString = tv_encrypt.getText().toString();
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
            tv_decrypt.setText(finalTextString);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
