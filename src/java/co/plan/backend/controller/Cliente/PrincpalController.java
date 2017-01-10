

package co.plan.backend.controller.Cliente;



import co.plan.backend.entities.Cliente;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author USUARIO
 */
@Named(value = "princpalController")
@RequestScoped
public class PrincpalController {

   
    public PrincpalController() {
    }
    
    public void verificarUsuario(){
    
    try{
   Cliente us=   (Cliente) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario"); 
        if (us == null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("./../permisos.xhtml"); 
        }
    
    }catch(Exception e){
    
    }
    
    }
    
}
