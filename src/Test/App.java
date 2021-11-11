package Test;

import modelo.Login;
import mysql.LoginAccessDB;

import java.sql.SQLException;

public class App {

    public static void main(String[] args){

        try{
            for (Login login : LoginAccessDB.getLogins()){
                System.out.println(login.getUsername()+" - "+login.getPassword()+" - "+login.getCreated_at());
            }
        }catch(SQLException e){
            //TODO
            e.printStackTrace();
        }
    }
}
