package br.com.prot.orm.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.prot.orm.R;
import br.com.prot.orm.entity.Cliente;
import br.com.prot.orm.persistence.dao.ClienteDAO;
import br.com.prot.orm.ui.adapters.ClienteAdapter;
import br.com.prot.orm.util.Constatnts;

public class RecoveryFragment extends Fragment {

    private TextView txtMsg;
    private ListView listaClientes;
    private ClienteAdapter adapter;
    private List<Cliente> clientes;
    private ClienteDAO dao = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recovery, container, false);

        initUi(view);

        return view;
    }

    private void initUi(View view){
        this.txtMsg = (TextView) view.findViewById(R.id.txt_msg_empty);
        this.listaClientes = (ListView) view.findViewById(R.id.lista_clientes);

        loadData(view);
    }

    private void loadData(View view){
        this.dao = new ClienteDAO(view.getContext());
        clientes = this.dao.getAll();

        if(clientes.size() > 0){
            this.adapter = new ClienteAdapter(getContext(), clientes);
            this.listaClientes.setAdapter(adapter);
        }else{
            this.txtMsg.setText(Constatnts.MSG_EMPTY_REGISTERS);
        }
    }
}