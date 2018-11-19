package security;

public class DBsecurity {
    //Для примера данные конфигурации вынесены здесь. В рабочих проектах данные конфигурации выносятся в отдельный файл.
    private final String url = "jdbc:mysql://";
    private final String serverName= "localhost";
    private final String portNumber = "3306";
    private final String databaseName= "mysql";
    public final String userName = "root";
    public final String password = "12345qwerty";
    //Пример заполнения конфигурации подключения. Можно заполнять страндартными методами Java JDBC
    public String connectionUrl = url + serverName + ":" + portNumber + "/" + databaseName+"?serverTimezone=UTC&useSSL=false";
}
