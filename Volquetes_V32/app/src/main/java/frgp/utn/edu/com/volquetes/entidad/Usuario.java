package frgp.utn.edu.com.volquetes.entidad;

public class Usuario {

    private int id;
    private String nombreUsuario;
    private String contraseniaUsuario;

    public Usuario() {

    }
    public Usuario(int id , String nombreUsuario, String contraseniaUsuario) {
        setId(id);
        setNombre(nombreUsuario);
        setContrasenia(contraseniaUsuario);
    }

    public int getId() {
        return id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContraseniaUsuarioUsuario() {
        return nombreUsuario;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setContrasenia(String contraseniaUsuario) {
        this.contraseniaUsuario = contraseniaUsuario;
    }
    @Override
    public String toString(){
      return getNombreUsuario();
    }

}
