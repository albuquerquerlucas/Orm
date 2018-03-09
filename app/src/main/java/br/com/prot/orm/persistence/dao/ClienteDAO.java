package br.com.prot.orm.persistence.dao;

import android.content.Context;

import br.com.prot.orm.entity.Cliente;

/**
 * Created by Lucas Albuquerque on 09/03/2018.
 */

public class ClienteDAO extends GenericDAO<Cliente> {

    public ClienteDAO(Context context) {
        super(context, Cliente.class);
    }
}
