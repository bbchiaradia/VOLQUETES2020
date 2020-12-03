package frgp.utn.edu.com.volquetes.entidad;

public class Zona {

    private int idZona;
    private String nombreZona;

    public Zona(int idZona, String nombreZona) {
        this.idZona = idZona;
        this.nombreZona = nombreZona;
    }

    public void Zona(){
    }

    public int getIdZona() {
        return idZona;
    }

    public void setIdZona(int idZona) {
        this.idZona = idZona;
    }

    public String getNombreZona() {
        return nombreZona;
    }

    public void setNombreZona(String nombreZona) {
        this.nombreZona = nombreZona;
    }

    @Override
  /*  public String toString() {
        return "Forma Pago{" +
                "id=" + idFormaPago +
                ", descripcion=" + descripcionFormaPago +
                '}';
    }*/
    public String toString() {
        return this.nombreZona ;

    }

}
