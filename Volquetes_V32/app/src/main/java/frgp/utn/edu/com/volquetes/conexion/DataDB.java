package frgp.utn.edu.com.volquetes.conexion;

public class DataDB {

    //Información de la BD
    public static String host="freedb.tech";
    public static String port="3306";
    public static String nameBD="freedbtech_volquetes";
    public static String user="freedbtech_volquetes";
    public static String pass="volquetes2020";


    //Información para la conexion
    public static String urlMySQL = "jdbc:mysql://" + host + ":" + port + "/"+nameBD;
    public static String driver = "com.mysql.jdbc.Driver";

}