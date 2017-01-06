/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.plan.backend.controller;

import co.plan.backend.entities.Cliente;
import co.plan.backend.facade.ClienteFacade;
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
@Named(value = "clienteManagedBean")
@RequestScoped 
public class ClienteManagedBean {


     @EJB
    private ClienteFacade clienteEJB;
    
    private Cliente cliente;
    
    
 public ClienteManagedBean() {
    }
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    @PostConstruct
    public void init(){
    cliente = new Cliente();
    }
    

    
    //************************************************************************************

    public void registrarCliente() {
        try {
            clienteEJB.create(cliente);
            manejarExito("Crear");
        } catch (Exception e) {
            manejarError(e);
        }
    }

    public String registrar() {
        return "";
    }

    public List<Cliente> getClientes() {
        try {
            return this.clienteEJB.findAll();
        } catch (Exception e) {
            manejarError(e);
        }
        return null;
    }

    public void eliminarCliente(Cliente c) {
        try {
            clienteEJB.remove(c);
            manejarExito("eliminado");
        } catch (Exception e) {
            manejarError(e);
        }
    }

    public void leer(Cliente leercliente) {
        cliente = leercliente;
        
      
    }

    public void modificar() {
        
         try {
          clienteEJB.edit(cliente);
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