package co.plan.backend.controller.Ventas;

import co.plan.backend.entities.Cliente;
import co.plan.backend.facade.ClienteFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import javax.faces.view.ViewScoped;
//import com.util.JsfUtil;

/**
 *
 * @author ADMIN
 */
@ManagedBean(name = "controladorCliente")
@ViewScoped
public class ControladoCliente implements Serializable{

    /**
     * Creates a new instance of ContconcesionarioadorCliente
     */
    public ControladoCliente() {
    }
    
    private Cliente clientes;
    
    @EJB
    private ClienteFacade facadeCliente;
    
    @PostConstruct
    public void init(){
       clientes = new Cliente();
    }
    
    public List<Cliente> getAll(){
        List<Cliente> li = facadeCliente.findAll();
        return li;
    }
    
    
    
     public Cliente getSelected() {
       if (clientes == null) {
           clientes = new Cliente();
           int selectedItemIndex = -1;
       }
       return clientes;
   }
     
  // public SelectItem[] getItemsAvailableSelectOne() {
    //   return JsfUtil.getSelectItems(facadeCliente.findAll(),false);
   //}
   
    public Cliente getCliente(java.lang.Integer id) {
       return facadeCliente.find(id);
   }
    @FacesConverter(forClass = Cliente.class)
   public static class ClienteConverter implements Converter {

       @Override
       public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
           if (value == null || value.length() == 0) {
               return null;
           }
           ControladoCliente controlador = (ControladoCliente) facesContext.getApplication().getELResolver().
                   getValue(facesContext.getELContext(), null, "controladorCliente");
           return controlador.getCliente(getKey(value));
           
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
           if (object instanceof Cliente) {
              Cliente  o = (Cliente) object;
               return getStringKey(o.getIdcliente());
           } else {
               throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Cliente.class.getName());
           }
       }

   }

    public Cliente getClientes() {
        return clientes;
    }

    public void setClientes(Cliente clientes) {
        this.clientes = clientes;
    }

   
  
    
}

