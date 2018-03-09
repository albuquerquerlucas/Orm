package br.com.prot.orm.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.prot.orm.R;
import br.com.prot.orm.entity.Cliente;
import br.com.prot.orm.persistence.dao.ClienteDAO;

public class RegisterFragment extends Fragment implements View.OnClickListener {

    private EditText edtNome, edtIdade;
    private Button btnSave;
    private ClienteDAO dao = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        this.dao = new ClienteDAO(view.getContext());
        initUi(view);

        return view;
    }

    private void initUi(View view){
        this.edtNome = (EditText) view.findViewById(R.id.edt_nome);
        this.edtIdade = (EditText) view.findViewById(R.id.edt_idade);
        this.btnSave = (Button) view.findViewById(R.id.btn_save);

        configComponents();
    }

    private void configComponents(){
        this.edtNome.requestFocus();
        this.edtNome.setText("");
        this.edtNome.setSelection(edtNome.getText().length());
        this.edtIdade.setText("");
        this.edtIdade.setSelection(edtIdade.getText().length());

        this.btnSave.setOnClickListener(RegisterFragment.this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_save){
            saveCliente();
        }
    }

    private void saveCliente(){

        if(this.edtNome.getText().toString().equals("")){this.edtNome.setError("Informe o nome."); return;}
        if(this.edtIdade.getText().toString().equals("")){this.edtIdade.setError("informe a idade."); return;}

        Cliente cliente = new Cliente();
        cliente.setNome(edtNome.getText().toString());
        cliente.setIdade(Integer.parseInt(edtIdade.getText().toString()));

        boolean status = this.dao.insert(cliente);

        if(status){
            configComponents();
            Toast.makeText(getContext(), "Salvo com sucesso!", Toast.LENGTH_SHORT).show();
        }
    }
}
