
package co.plan.backend.controller.Vehiculo;

import co.plan.backend.entities.Vehiculo;
import co.plan.backend.facade.VehiculoFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@Named(value = "vehiculoManagedBean")
@RequestScoped
public class VehiculoManagedBean {

   @EJB
    private VehiculoFacade vehiculoEJB;
    
    private Vehiculo vehiculo;
    
    public VehiculoManagedBean() {
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
    
     @PostConstruct
    public void init(){
    vehiculo = new Vehiculo();
    }
    

    
    //************************************************************************************

    public void registrarVehiculo() {
        try {
            vehiculoEJB.create(vehiculo);
            manejarExito("Crear");
        } catch (Exception e) {
            manejarError(e);
        }
    }

    public String registrar() {
        return "";
    }

    public List<Vehiculo> getVehiculos() {
        try {
            return this.vehiculoEJB.findAll();
        } catch (Exception e) {
            manejarError(e);
        }
        return null;
    }

    public void eliminarVehiculo(Vehiculo c) {
        try {
            vehiculoEJB.remove(c);
            manejarExito("eliminado");
        } catch (Exception e) {
            manejarError(e);
        }
    }

    public void leer(Vehiculo leer) {
        vehiculo = leer;
        
      
    }

    public void modificar() {
        
         try {
          vehiculoEJB.edit(vehiculo);
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
