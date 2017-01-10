package co.plan.backend.controller.Ventas;

import co.plan.backend.entities.Ventas;
import co.plan.backend.facade.VentasFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

@Named(value = "ventaManagedBean")
@RequestScoped
public class VentaManagedBean {

    @EJB
    private VentasFacade ventasEjb;

    private Ventas venta;

    public VentaManagedBean() {
    }

    public Ventas getVenta() {
        return venta;
    }

    public void setVenta(Ventas venta) {
        this.venta = venta;
    }

    @PostConstruct
    public void init() {
        venta = new Ventas();
    }

    //************************************************************************************
    public void crearVenta() {
        try {
            ventasEjb.create(venta);
            manejarExito("Crear");
        } catch (Exception e) {
            manejarError(e);
        }

    }

    public String registrar() {
        return "";
    }

    public List<Ventas> getVentas() {
        try {
            return this.ventasEjb.findAll();

        } catch (Exception e) {
            manejarError(e);
        }
        return null;
    }

    public void eliminar(Ventas v) {
        try {
            ventasEjb.remove(v);
            manejarExito("eliminado");
        } catch (Exception e) {
            manejarError(e);
        }
    }
    
    public void leer (Ventas leer){
        venta= leer;
    }
    
    public void modificar(){
        try{
            ventasEjb.edit(venta);
            manejarExito("Editado");
        }catch(Exception e){
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
