package frgp.utn.edu.com.volquetes.entidad;

public class Cliente {

    private int id;
    private String nombreCliente;
    private String direccionCliente;
    private String emailCliente;
    private String cuitCliente;
    private String celularCliente;
    private String tel_par_Cliente;
    private String tel_lab_Cliente;
    private String codigoCliente;
    private String latitud;
    private String longitud;

    public Cliente(){
    }
    public Cliente(String nombre , String direccion, String email , String cuit , String celular , String tel_part,String tel_lab, String codigoCli, String lat, String lon){
        this.nombreCliente=nombre;
        this.direccionCliente=direccion;
        this.emailCliente=email;
        this.cuitCliente=cuit;
        this.celularCliente=celular;
        this.tel_par_Cliente=tel_part;
        this.tel_lab_Cliente=tel_lab;
        this.codigoCliente=codigoCli;
        this.latitud=lat;
        this.longitud=lon;
    }



    public int getId() {
        return id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public String getCuitCliente() {
        return cuitCliente;
    }

    public String getCelularCliente() {
        return celularCliente;
    }

    public String getTel_par_Cliente() {
        return tel_par_Cliente;
    }

    public String getTel_lab_Cliente() {
        return tel_lab_Cliente;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public String getLongitud() {
        return longitud;
    }
    public String getLatitud() {
        return latitud;
    }

    public void setId(int id) {
        this.id = id;
    }
    public  void setNombreCli(String nombreCliente) {this.nombreCliente = nombreCliente;}
    public void setDireccionCliente(String direccionCliente) { this.direccionCliente = direccionCliente;}
    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }
    public void setCUITCliente(String cuitCliente) {
        this.cuitCliente = cuitCliente;
    }
    public void setCelularCliente(String celularCliente) {
        this.celularCliente = celularCliente;
    }
    public void setTelParticularCliente(String tel_par_Cliente) { this.tel_par_Cliente = tel_par_Cliente;}
    public void setTelLaboralCliente(String tel_lab_Cliente) {this.tel_lab_Cliente = tel_lab_Cliente;}
    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }


    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }



    @Override
    public String toString(){
        return getNombreCliente();
    }
}