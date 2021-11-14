package login;

import java.sql.Timestamp;
import java.text.DecimalFormat;

public class Login {
        int id;
        String username;
        String password;
        Timestamp created_at;
        DecimalFormat salario;
        int admin;

        public Login(int id, String username, String password, Timestamp created_at, DecimalFormat salario, int admin) {
            this.id = id;
            this.username = username;
            this.password = password;
            this.created_at = created_at;
            this.salario = salario;
            this.admin = admin;
        }
        public Login(String username) {
            this.username = username;
        }

        public Login() {
            }

        public Login(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public Login(int id, String username, String password) {
            this.id = id;
            this.username = username;
            this.password = password;
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
    }
