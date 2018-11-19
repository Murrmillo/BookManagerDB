package db.manager;



import security.DBsecurity;

import java.sql.*;

public class DBmanager {
    Connection conn;
    DBsecurity sec;
    public  DBmanager()
    {
        conn = null;
        sec = new DBsecurity();
        try {
            //Указываем название драйвера, если требуется
            Class.forName("com.mysql.jdbc.Driver");
            //Выполняем подключение
            conn = DriverManager.getConnection(sec.connectionUrl,sec.userName,sec.password);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    //Пример работы Insert
    public void insert(String name,String writer, String price) {

        int iprice = Integer.parseInt(price);
        try {




            //Выполняем наш SQL запрос на вставку
            Statement stat = conn.createStatement();
            //stat.executeUpdate("DROP table books");
            stat.executeUpdate("CREATE TABLE IF NOT EXISTS books (id MEDIUMINT NOT NULL AUTO_INCREMENT, name CHAR(30),writer CHAR(30), price INT, PRIMARY KEY (id))");
            ResultSet resultSetPre = stat.executeQuery("select*from books");

            //int count_id=getResultSetRowCount(resultSetPre)+1;
            stat.executeUpdate("INSERT INTO books(name,writer,price) " + "VALUES ('"+name+"','"+writer+"', '"+iprice+"')");



        } catch (Exception e)
        {
            e.printStackTrace();
            try {
                conn.close();
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }

    }

    public String select()
    {
        String resultSelect="";
        try {
            Statement stat = conn.createStatement();
            ResultSet resultSet = stat.executeQuery("select*from books");

            while (resultSet.next()) {
                resultSelect=resultSelect+"<p>"+(resultSet.getInt("id") + ") ")+"\"" + (resultSet.getString("name")) + "\" "
                +(resultSet.getString("writer"))+" "+(resultSet.getInt("price"))+"</p>"+"<p>" + "---------------------" + "</p>";


            }
            conn.close();
            stat.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            try {
                conn.close();
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }

        }


        return resultSelect;
    }
    public void closeConncetion()
    {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static int getResultSetRowCount(ResultSet resultSet) {
        int size = 0;
        try {
            resultSet.last();
            size = resultSet.getRow();
            resultSet.beforeFirst();
        }
        catch(SQLException ex) {
            return 0;
        }
        return size;
    }

}
