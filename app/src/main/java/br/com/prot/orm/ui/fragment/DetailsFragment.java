package br.com.prot.orm.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import br.com.prot.orm.R;
import br.com.prot.orm.util.Constatnts;

public class DetailsFragment extends Fragment {

    private TextView txtNome, txtIdade, txtSalvar, txtCancelar, txtEditar;
    private EditText edt_comentarios;
    private ImageView imgReturn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        Bundle args = getArguments();
        initUi(view);

        if(args != null){
            this.txtNome.setText(args.getString(Constatnts.LB_NOME));
            this.txtIdade.setText(args.getString(Constatnts.LB_IDADE));
        }

        return view;
    }

    private void initUi(View view){
        this.txtNome = (TextView) view.findViewById(R.id.txt_nome);
        this.txtIdade = (TextView) view.findViewById(R.id.txt_idade);
        this.edt_comentarios = (EditText) view.findViewById(R.id.edt_comentarios);
        this.txtSalvar = (TextView) view.findViewById(R.id.txt_save_comentario);
        this.txtCancelar = (TextView) view.findViewById(R.id.txt_cancel);
        this.txtEditar = (TextView) view.findViewById(R.id.txt_editar);
        this.imgReturn = (ImageView) view.findViewById(R.id.img_return);

        callFragList();
        editComentario();
    }

    private void callFragList(){
        this.imgReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new RecoveryFragment();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.frame_content, fragment).commit();
            }
        });
    }

    private void editComentario(){
        this.txtEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_comentarios.setEnabled(true);
                edt_comentarios.requestFocus();
                if(edt_comentarios.requestFocus()){
                    txtSalvar.setVisibility(View.VISIBLE);
                    txtCancelar.setVisibility(View.VISIBLE);
                }
            }
        });
        funcBtnSave();
        funcBtnCancel();
    }

    private void funcBtnSave(){
        this.txtSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Irá salvar...", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void funcBtnCancel(){
        this.txtCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_comentarios.setText("");
                edt_comentarios.setEnabled(false);
                txtSalvar.setVisibility(View.GONE);
                txtCancelar.setVisibility(View.GONE);
            }
        });
    }
}
