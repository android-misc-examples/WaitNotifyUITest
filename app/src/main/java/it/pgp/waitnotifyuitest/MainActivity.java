package it.pgp.waitnotifyuitest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    public boolean wasShortClick = false;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            String s = data.getStringExtra("AAA");
            Toast.makeText(this, s, Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
            Toast.makeText(this, "Exception", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(wasShortClick?R.menu.mymenu:R.menu.mymenu2,menu);

        // to disable context menu on long click
        //  if (wasShortClick) inflater.inflate...

        wasShortClick = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button b = findViewById(R.id.button);
        b.setOnClickListener(view -> {
            new LongRunningThread(MainActivity.this).start();
            b.setEnabled(false);
        });

        final Button b1 = findViewById(R.id.button1);
        b1.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this,HelperActivity.class);
            startActivityForResult(i,0x101);
        });

        final Button dcb = findViewById(R.id.doubleClicksButton);
        registerForContextMenu(dcb);

        dcb.setOnClickListener(view -> {
            wasShortClick = true;
            openContextMenu(dcb);
        });

//        dcb.setOnLongClickListener(view -> {
//            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//            builder.setTitle("Example");
//            builder.setNegativeButton("No", (dialog, which) -> {});
//            builder.setPositiveButton("Yes", (dialog, which) -> {});
//            AlertDialog alertDialog = builder.create();
//            alertDialog.show();
//            return false;
//        });
    }
}
