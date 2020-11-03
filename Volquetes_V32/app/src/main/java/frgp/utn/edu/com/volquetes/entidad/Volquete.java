package frgp.utn.edu.com.volquetes.entidad;

public class Volquete {

    private int id;
    private int idestadoVolquete;
    private String codigoVolquete;

    public Volquete() {

    }
    public Volquete(int id, int idestadoVolquete , String codigoVolquete) {
        setId(id);
        setIdEstadoVolquete(idestadoVolquete);
        setCodigoVolquete(codigoVolquete);
    }


    public int getId() {
        return id;
    }
    public int getIdestadoVolquete() {
        return idestadoVolquete;
    }
    public String getCodigoVolquete() {
        return codigoVolquete;
    }



    public void setId(int id) {
        this.id = id;
    }
    public void setCodigoVolquete(String codigoVolquete) {
        this.codigoVolquete = codigoVolquete;
    }
    public void setIdEstadoVolquete(int idestadoVolquete) {
        this.idestadoVolquete = idestadoVolquete;
    }


    @Override
    public String toString(){
      return getCodigoVolquete();
    }

}
