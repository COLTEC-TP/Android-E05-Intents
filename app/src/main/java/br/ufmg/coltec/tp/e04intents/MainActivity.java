package br.ufmg.coltec.tp.e04intents;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import org.w3c.dom.Text;

public class MainActivity extends Activity {

    private final static int FOTO_CODE = 1;
    private final static int BARCODE_INTENT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ImageView imageView = findViewById(R.id.image);

        if (requestCode == FOTO_CODE && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }

        if (requestCode == BARCODE_INTENT && resultCode == Activity.RESULT_OK) {
            String contents = data.getStringExtra("SCAN_RESULT");
            String format = data.getStringExtra("SCAN_RESULT_FORMAT");

            Toast.makeText(this, contents + "/" + format, Toast.LENGTH_SHORT).show();
        }
    }

    public void textClicked(View view) {
        final ViewSwitcher VS = (ViewSwitcher) view.getParent();
        final TextView TV = VS.findViewById(VS.getCurrentView().getId());
        final EditText ET = VS.findViewById(VS.getNextView().getId());
        VS.showNext();
        ET.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            View view = VS.getFocusedChild();
                            if (view != null) {
                                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                            }
                            String age = ET.getText().toString();
                            VS.showPrevious();
                            TV.setText(age);
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });
    }

    public void editImage(View view) {
        Intent fotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(fotoIntent, FOTO_CODE);
    }

    public void telClicked(View view) {
        TextView tel = (TextView) view;
        Uri uri = Uri.parse("tel:"+tel.getText().toString());
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(intent);
    }

    public void emailClicked(View view) {
        TextView email = (TextView) view;
        String[] TO = {email.getText().toString()};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");


        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public void scanBarcode(View view) {
        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        startActivityForResult(intent, BARCODE_INTENT);
    }
}
