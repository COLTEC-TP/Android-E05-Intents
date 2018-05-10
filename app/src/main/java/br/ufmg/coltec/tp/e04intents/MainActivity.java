package br.ufmg.coltec.tp.e04intents;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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

public class MainActivity extends Activity {

    private final static int FOTO_CODE = 1;
    private final static int EMAIL_CODE = 2;
    private final static int PHONE_CODE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void textClicked(View view){
        ViewSwitcher VS = (ViewSwitcher) view.getParent();
        EditText TV = VS.findViewById(VS.getNextView().getId());
        EditText ET = VS.findViewById(VS.getNextView().getId());
        textClickedAction(VS, TV, ET);
    }

    public void textClickedAction(View view1, View view2, View view3) {
        final ViewSwitcher switcher = (ViewSwitcher) view1;
        final TextView TV = (TextView) view2;
        final TextView ET = (EditText) view3;
        switcher.showNext();
        ET.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            View view = switcher.getFocusedChild();
                            if (view != null) {
                                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                            }
                            String age = ET.getText().toString();
                            TV.setText(age);
                            switcher.showNext();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ImageView imageView = findViewById(R.id.image);

        // verifica se retorno pertence a Intent chamada anterioremente,
        // e se ela foi executada corretamente
        if (requestCode == FOTO_CODE && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }
    }
}
