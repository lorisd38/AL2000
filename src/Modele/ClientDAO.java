package Modele;

import java.sql.SQLException;

public class ClientDAO extends SqlDAO<Client> {
    public ClientDAO() throws SQLException {
    }

    @Override
    public Client read(Object noC) {
        return null;
    }

    @Override
    public boolean create(Client obj) {
        return false;
    }

    @Override
    public boolean update(Client obj) {
        return false;
    }

    @Override
    public boolean delete(Client obj) {
        return false;
    }
}
