package frgp.utn.edu.com.volquetes.entidad;

import java.sql.Date;

public class Reserva {

   private int idReserva;
   private int idCliente;
   private int idVolquete;
   private int idUsuario;
   private float precio;
   private int idFormaPago;
   private Date fechaEntrega;
   private Date fechaRetiro;

   public Reserva(){

   }

    public Reserva( int idReserva , int idCliente, int idVolquete , int idUsuario , float precio ,int idFormaPago ,Date fechaEntrega, Date fechaRetiro){

        this.idReserva=idReserva;
        this.idCliente=idCliente;
        this.idVolquete=idVolquete;
        this.idUsuario=idUsuario;
        this.precio=precio;
        this.idFormaPago=idFormaPago;
        this.fechaEntrega=fechaEntrega;
        this.fechaRetiro=fechaRetiro;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdVolquete() {
        return idVolquete;
    }

    public void setIdVolquete(int idVolquete) {
        this.idVolquete = idVolquete;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getIdFormaPago() {
        return idFormaPago;
    }

    public void setIdFormaPago(int idFormaPago) {
        this.idFormaPago = idFormaPago;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Date getFechaRetiro() {
        return fechaRetiro;
    }

    public void setFechaRetiro(Date fechaRetiro) {
        this.fechaRetiro = fechaRetiro;
    }
}
