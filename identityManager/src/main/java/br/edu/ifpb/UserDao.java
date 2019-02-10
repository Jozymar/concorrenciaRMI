package br.edu.ifpb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private Connection connection;

    public UserDao() {
        try {
            this.connection = ConFactory.getConnectionPostgres();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getLastIdLocked() {
        int resultado = 0;
        try {
            String sql = "SELECT last_id FROM aux_lock ORDER BY last_id DESC LIMIT 1";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                resultado = resultSet.getInt("last_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public void lockId(int id, String instanceApp) {
        try {
            String sql = "INSERT INTO aux_lock (last_id, instance) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, instanceApp);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
