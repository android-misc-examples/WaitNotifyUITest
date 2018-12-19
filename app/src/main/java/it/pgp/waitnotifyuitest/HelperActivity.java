package it.pgp.waitnotifyuitest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class HelperActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper);

        Button b = findViewById(R.id.backToMainActivityBtn);
        b.setOnClickListener(view -> {
            Intent i = new Intent();
            i.putExtra("AAA","BBB");
            setResult(RESULT_OK,i); // can be read by onActivityResult
            finish();
        });
    }
}
