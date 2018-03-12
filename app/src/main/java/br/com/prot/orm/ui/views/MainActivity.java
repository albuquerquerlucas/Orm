package br.com.prot.orm.ui.views;

import android.content.DialogInterface;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import br.com.prot.orm.R;
import br.com.prot.orm.ui.fragment.RecoveryFragment;
import br.com.prot.orm.ui.fragment.RegisterFragment;
import br.com.prot.orm.util.Constants;

public class MainActivity extends AppCompatActivity {

    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void register(View view) {
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_content, new RegisterFragment());
        ft.commit();
    }

    public void recovery(View view) {
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_content, new RecoveryFragment());
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        confirmation();
    }

    private void confirmation(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setTitle(Constants.TXT_ALERT_TITLE);
        alertDialogBuilder.setIcon(R.drawable.ic_info_outline_black_24dp);

        alertDialogBuilder.setMessage(Constants.TXT_ALERT_MSG).setCancelable(false)
                .setPositiveButton(Constants.TXT_ALERT_POSITIVE_BUTTON,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton(Constants.TXT_ALERT_NEGATIVE_BUTTON,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
