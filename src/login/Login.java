package login;

import singleton.DatabaseConnection;

import java.io.IOException;
import java.sql.*;

public class Login {
    int id;
    String username;
    String password;
    Timestamp created_at;
    int salario;
    int admin;
    private static Connection con;


    public Login(int id, String username, String password, Timestamp created_at, int salario, int admin) {
        try {
            con = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException | IOException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        this.id = id;
        this.username = username;
        this.password = password;
        this.created_at = created_at;
        this.salario = salario;
        this.admin = admin;

    }

    public Login(String username) {
        try {
            con = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException | IOException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        this.username = username;
        this.setValues();
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setSalario(int salario) {
        this.salario = salario;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public int getSalario() {
        return salario;
    }

    public int getAdmin() {
        return admin;
    }
    /*
    Extraer los datos con un solo dato
     */
    private void setValues() {
        String query = "SELECT * FROM login WHERE username =\'" + username + "\'";
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                setId(rs.getInt(1));
                setUsername(rs.getString(2));
                setPassword(rs.getString(3));
                setSalario(rs.getInt(5));
                setAdmin(rs.getInt(6));

            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();

        }
    }

    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", created_at=" + created_at +
                ", salario=" + salario +
                ", admin=" + admin +
                '}';
    }



}
