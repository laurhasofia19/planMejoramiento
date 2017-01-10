package co.plan.backend.controller.Vehiculo;



import co.plan.backend.entities.Concesionario;
import co.plan.backend.facade.ConcesionarioFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
//import com.util.JsfUtil;

/**
 *
 * @author ADMIN
 */
@ManagedBean(name = "controladorConcesionario")
@ViewScoped
public class ControladorConcesionario implements Serializable{

    /**
     * Creates a new instance of ContconcesionarioadorConcesionario
     */
    public ControladorConcesionario() {
    }
    
    private Concesionario concesionarios;
    
    @EJB
    private ConcesionarioFacade facadeConcesionario;
    
    @PostConstruct
    public void init(){
       concesionarios = new Concesionario();
    }
    
    public List<Concesionario> getAll(){
        List<Concesionario> li = facadeConcesionario.findAll();
        return li;
    }
    
    
    
     public Concesionario getSelected() {
       if (concesionarios == null) {
           concesionarios = new Concesionario();
           int selectedItemIndex = -1;
       }
       return concesionarios;
   }
     
  // public SelectItem[] getItemsAvailableSelectOne() {
    //   return JsfUtil.getSelectItems(facadeConcesionario.findAll(),false);
   //}
   
    public Concesionario getConcesionario(java.lang.Integer id) {
       return facadeConcesionario.find(id);
   }
    @FacesConverter(forClass = Concesionario.class)
   public static class ConcesionarioConverter implements Converter {

       @Override
       public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
           if (value == null || value.length() == 0) {
               return null;
           }
           ControladorConcesionario controlador = (ControladorConcesionario) facesContext.getApplication().getELResolver().
                   getValue(facesContext.getELContext(), null, "controladorConcesionario");
           return controlador.getConcesionario(getKey(value));
           
       }

       java.lang.Integer getKey(String value) {
           java.lang.Integer key;
           key = Integer.valueOf(value);
           return key;
       }

       String getStringKey(java.lang.Integer value) {
           StringBuilder sb = new StringBuilder();
           sb.append(value);
           return sb.toString();
       }

       @Override
       public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
           if (object == null) {
               return null;
           }
           if (object instanceof Concesionario) {
              Concesionario  o = (Concesionario) object;
               return getStringKey(o.getNit());
           } else {
               throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Concesionario.class.getName());
           }
       }

   }

    public Concesionario getConcesionarios() {
        return concesionarios;
    }

    public void setConcesionarios(Concesionario concesionarios) {
        this.concesionarios = concesionarios;
    }

  
    
}

