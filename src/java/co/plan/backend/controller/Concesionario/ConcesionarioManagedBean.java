
package co.plan.backend.controller.Concesionario;

import co.plan.backend.entities.Concesionario;
import co.plan.backend.entities.Concesionario;
import co.plan.backend.facade.ConcesionarioFacade;
import javax.inject.Named;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;


@Named(value = "concesionarioManagedBean")
@RequestScoped
public class ConcesionarioManagedBean implements Serializable {

     @EJB
    private ConcesionarioFacade concesionarioEJB;
    
    private Concesionario concesionario;
    
  
    public ConcesionarioManagedBean() {
    }

    public Concesionario getConcesionario() {
        return concesionario;
    }

    public void setConcesionario(Concesionario concesionario) {
        this.concesionario = concesionario;
    }
    
     @PostConstruct
    public void init(){
    concesionario = new Concesionario();
    }
    

    
    //************************************************************************************

    public void registrarConcesionario() {
        try {
            concesionarioEJB.create(concesionario);
            manejarExito("Crear");
        } catch (Exception e) {
            manejarError(e);
        }
    }

    public String registrar() {
        return "";
    }

    public List<Concesionario> getConcesionarios() {
        try {
            return this.concesionarioEJB.findAll();
        } catch (Exception e) {
            manejarError(e);
        }
        return null;
    }

    public void eliminarConcesionario(Concesionario c) {
        try {
            concesionarioEJB.remove(c);
            manejarExito("eliminado");
        } catch (Exception e) {
            manejarError(e);
        }
    }

    public void leer(Concesionario leer) {
        concesionario = leer;
        
      
    }

    public void modificar() {
        
         try {
          concesionarioEJB.edit(concesionario);
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
