package frgp.utn.edu.com.volquetes.entidad;

public class FormaPago {

    private int idFormaPago;
    private String descripcionFormaPago;

    public FormaPago(int idFormaPago, String descripcionFormaPago) {
        setDescripcionFormaPago(descripcionFormaPago);
        setIdFormaPago(idFormaPago);
    }

    public void FormaPago(){
    }


    public int getIdFormaPago() {
        return idFormaPago;
    }

    public void setIdFormaPago(int idFormaPago) {
        this.idFormaPago = idFormaPago;
    }

    public String getDescripcionFormaPago() {
        return descripcionFormaPago;
    }

    public void setDescripcionFormaPago(String descripcionFormaPago) {
        this.descripcionFormaPago = descripcionFormaPago;
    }
    @Override
  /*  public String toString() {
        return "Forma Pago{" +
                "id=" + idFormaPago +
                ", descripcion=" + descripcionFormaPago +
                '}';
    }*/
    public String toString() {
        return this.descripcionFormaPago ;

    }

}
