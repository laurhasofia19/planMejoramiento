package co.plan.backend.controller.Ventas;




import co.plan.backend.entities.Vehiculo;
import co.plan.backend.facade.VehiculoFacade;
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
@ManagedBean(name = "controladorVehiculo")
@ViewScoped
public class ControladorVehiculo implements Serializable{

    /**
     * Creates a new instance of ContconcesionarioadorVehiculo
     */
    public ControladorVehiculo() {
    }
    
    private Vehiculo vehiculo;
    
    @EJB
    private VehiculoFacade facadeVehiculo;
    
    @PostConstruct
    public void init(){
       vehiculo = new Vehiculo();
    }
    
    public List<Vehiculo> getAll(){
        List<Vehiculo> li = facadeVehiculo.findAll();
        return li;
    }
    
    
    
     public Vehiculo getSelected() {
       if (vehiculo == null) {
           vehiculo = new Vehiculo();
           int selectedItemIndex = -1;
       }
       return vehiculo;
   }
     
  // public SelectItem[] getItemsAvailableSelectOne() {
    //   return JsfUtil.getSelectItems(facadeVehiculo.findAll(),false);
   //}
   
    public Vehiculo getVehiculo(java.lang.Integer id) {
       return facadeVehiculo.find(id);
   }
    @FacesConverter(forClass = Vehiculo.class)
   public static class VehiculoConverter implements Converter {

       @Override
       public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
           if (value == null || value.length() == 0) {
               return null;
           }
           ControladorVehiculo controlador = (ControladorVehiculo) facesContext.getApplication().getELResolver().
                   getValue(facesContext.getELContext(), null, "controladorVehiculo");
           return controlador.getVehiculo(getKey(value));
           
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
           if (object instanceof Vehiculo) {
              Vehiculo  o = (Vehiculo) object;
               return getStringKey(o.getIdvehiculo());
           } else {
               throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Vehiculo.class.getName());
           }
       }

   }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

  

  
    
}

