package br.com.prot.orm.ui.views;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.com.prot.orm.R;
import br.com.prot.orm.ui.fragment.RecoveryFragment;
import br.com.prot.orm.ui.fragment.RegisterFragment;

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
}
