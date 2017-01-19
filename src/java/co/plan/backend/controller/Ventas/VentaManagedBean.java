/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.plan.backend.controller.Ventas;


import co.plan.backend.entities.Ventas;
import co.plan.backend.facade.VentasFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;


/**
 *
 * @author USUARIO
 */
@Named(value = "ventaManagedBean")
@RequestScoped 
public class VentaManagedBean {


     @EJB
    private VentasFacade VentasEJB;
    
    private Ventas Ventas;
    
    
 public VentaManagedBean() {
    }
    public Ventas getVentas() {
        return Ventas;
    }

    public void setVentas(Ventas Ventas) {
        this.Ventas = Ventas;
    }
    
    @PostConstruct
    public void init(){
    Ventas = new Ventas();
    }
    

    
    //************************************************************************************

    public void registrarVentas() {
        try {
            VentasEJB.create(Ventas);
            manejarExito("Crear");
        } catch (Exception e) {
            manejarError(e);
        }
    }

    public String registrar() {
        return "";
    }

    public List<Ventas> getVentass() {
        try {
            return this.VentasEJB.findAll();
        } catch (Exception e) {
            manejarError(e);
        }
        return null;
    }

    public void eliminarVentas(Ventas c) {
        try {
            VentasEJB.remove(c);
            manejarExito("eliminado");
        } catch (Exception e) {
            manejarError(e);
        }
    }

    public void leer(Ventas leer) {
        Ventas = leer;
        
      
    }

    public void modificar() {
        
         try {
          VentasEJB.edit(Ventas);
            manejarExito("Editado");
        } catch (Exception e) {
            manejarError(e);
        }     
        
    }
    

    //******************************************************************************************


 
    private void
            manejarError(Exception e) {
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "se produjo el siguiente error : ", e.getMessage()));

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al insertar :", e.getMessage());

        RequestContext.getCurrentInstance().showMessageInDialog(msg);
    }

    private void manejarExito(String operacion) {
        String msg = "Se ha realizado exitosamente la operacion de " + operacion;

        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage(msg));

        FacesMessage sal = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Opereción exitosa : ", msg);
        RequestContext.getCurrentInstance().showMessageInDialog(sal);

    }
}
