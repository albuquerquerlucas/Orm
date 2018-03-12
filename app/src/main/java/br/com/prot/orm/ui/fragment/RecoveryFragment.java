package br.com.prot.orm.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import br.com.prot.orm.R;
import br.com.prot.orm.entity.Cliente;
import br.com.prot.orm.persistence.dao.ClienteDAO;
import br.com.prot.orm.ui.adapters.ClienteAdapter;
import br.com.prot.orm.util.Constants;

public class RecoveryFragment extends Fragment {

    private TextView txtMsg;
    private ListView listaClientes;
    private ClienteAdapter adapter;
    private List<Cliente> clientes;
    private ClienteDAO dao = null;
    private int idCli, pos;

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

        this.listaClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int idCliente = clientes.get(position).getId();
                String nome = clientes.get(position).getNome();
                int idade = clientes.get(position).getIdade();
                String email = clientes.get(position).getEmail();
                String comentario = clientes.get(position).getComentario();

                Fragment fragment = new DetailsFragment();
                fragment.setArguments(passParams(idCliente,  nome, idade, email, comentario));
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.frame_content, fragment).commit();
            }
        });

        this.listaClientes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                idCli = clientes.get(position).getId();
                pos = position;
                confirmation();
                return true;
            }
        });
    }

    private void loadData(View view){
        this.dao = new ClienteDAO(view.getContext());
        clientes = this.dao.getAll();

        if(clientes.size() > 0){
            this.adapter = new ClienteAdapter(getContext(), clientes);
            this.listaClientes.setAdapter(adapter);
        }else{
            this.txtMsg.setText(Constants.MSG_EMPTY_REGISTERS);
        }
    }

    private Bundle passParams(int id, String nome, int idade, String email, String comentario){
        Bundle params = new Bundle();
        params.putString(Constants.LB_ID, String.valueOf(id));
        params.putString(Constants.LB_NOME, nome);
        params.putString(Constants.LB_IDADE, String.valueOf(idade));
        params.putString(Constants.LB_EMAIL, email);
        params.putString(Constants.LB_COMENTARIO, comentario);
        return params;
    }

    private void confirmation(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setTitle(Constants.TXT_ALERT_TITLE);
        alertDialogBuilder.setIcon(R.drawable.ic_info_outline_black_24dp);

        alertDialogBuilder.setMessage(Constants.TXT_ALERT_MSG_DEL).setCancelable(false)
                .setPositiveButton(Constants.TXT_ALERT_DEL_POSITIVE_BUTTON,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Cliente cli = dao.getById(idCli);
                                dao.delete(cli);
                                clientes.remove(pos);
                                adapter.notifyDataSetChanged();
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
