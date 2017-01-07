
package co.plan.backend.controller;

import co.plan.backend.entities.Cliente;
import co.plan.backend.facade.ClienteFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


@Named(value = "loginContolador")
@SessionScoped
public class LoginContolador implements Serializable {
    @EJB
   private ClienteFacade clienteEjb; 
   private Cliente cliente; 
   
   @PostConstruct
   
    public void init(){
        cliente=new Cliente(); 
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String iniciarSesion(){
        Cliente cli;
        String redireccion=null; 
        cli=clienteEjb.iniciarSesion(cliente);
        if (cli != null) {
           FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", cli);
            redireccion= "/protegido/Principal?faces-redirect=true";
        
         }else{
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"AVISO","Nombre  de usuario INCORRECTO"));
        }
      
        try {
        } catch (Exception e) {
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL,"AVISO", "INCORRECTO"));
        }
        return redireccion;
    }
    
      public void cerrarSesion() {

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
    
    
}
