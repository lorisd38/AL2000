package Modele;

import java.sql.SQLException;

public abstract class SqlDAO<T> {
    protected Connexion connection;

    public SqlDAO() throws SQLException {
        connection = Connexion.getInstance();
    }

    public abstract T read(Object o);

    public abstract boolean create(T obj);

    public abstract boolean update(T obj);

    public abstract boolean delete(T obj);
}