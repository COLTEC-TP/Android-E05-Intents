package br.ufmg.coltec.tp.e05_intents;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final int BARCODE_CODE = 0;
    private static final int CAMERA_CODE = 1;
    private static final int GALLERY_CODE = 2;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode){
                case 0:
                    String contents = data.getStringExtra("SCAN_RESULT");
                    TextView barcode = findViewById(R.id.txt_barcode);
                    barcode.setText("Resultado: " + contents);// Gerei um código de barras e coloquei na câmera do emulador para escanear.
                    break;
                case 1:
                case 2:
                    ImageView image = findViewById(R.id.img_photo);
                    Bitmap photo = data.getExtras().getParcelable("data");
                    image.setImageBitmap(photo);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView image = findViewById(R.id.img_photo);
        TextView name = findViewById(R.id.txt_name);
        TextView age = findViewById(R.id.txt_age);
        TextView phone = findViewById(R.id.txt_phone);
        TextView email = findViewById(R.id.txt_email);
        Button button = findViewById(R.id.btn_barcode);

        name.setText("Gill Bates");
        age.setText("35 anos");
        email.setText("gill.bates@macrosoft.com.br");
        phone.setText("4002-8922");


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Escolha:");
        builder.setItems(new String[]{"Camera", "Galeria"}, (dialog, which) -> {
            switch (which) {
                case 0:
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_CODE);
                    break;
                case 1:
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, GALLERY_CODE);
                    break;
                default:
                    break;
            }
        });

        image.setOnClickListener(v -> builder.show());
        phone.setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(phone.getText().toString()))));
        email.setOnClickListener(v -> {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email.getText().toString()));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Olá!");
            startActivity(emailIntent);
        });
        button.setOnClickListener(v -> startActivityForResult(new Intent("com.google.zxing.client.android.SCAN"), BARCODE_CODE));

    }
}