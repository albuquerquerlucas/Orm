package br.com.prot.orm.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.prot.orm.R;
import br.com.prot.orm.entity.Cliente;

/**
 * Created by Lucas Albuquerque on 09/03/2018.
 */

public class ClienteAdapter extends BaseAdapter {

    private Context context;
    private List<Cliente> clientes;
    private LayoutInflater inflater = null;

    public ClienteAdapter(Context context, List<Cliente> clientes) {
        this.context = context;
        this.clientes = clientes;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return clientes.size();
    }

    @Override
    public Object getItem(int position) {
        return clientes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = new ViewHolder();
        View rowView = convertView;
        rowView = inflater.inflate(R.layout.item_lista, null);

        holder.txtCliente = (TextView) rowView.findViewById(R.id.txt_cliente);
        holder.txtEmail = (TextView) rowView.findViewById(R.id.txt_email);

        holder.txtCliente.setText(
                new StringBuilder(clientes.get(position).getNome() + ", " +
                        clientes.get(position).getIdade() + " anos.")
        );

        holder.txtEmail.setText(clientes.get(position).getEmail());

        return rowView;
    }

    public class ViewHolder{
        TextView txtCliente;
        TextView txtEmail;
    }
}
