package agentes;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.wrapper.AgentContainer;
import jade.wrapper.StaleProxyException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Cliente;

/**
 *
 * @author Isma2
 */
public class Agente3 extends Agent {

    Cliente cliente;
    
    @Override
    protected void setup() {
        addBehaviour(new Comportamiento());
    }

    @Override
    protected void takeDown() {
        try {
            AgentContainer contenedorPrincipal = (AgentContainer) getArguments()[0];
            contenedorPrincipal.createNewAgent("AgenteH", AgenteH.class.getName(), new Object[]{cliente, contenedorPrincipal, 0}).start();
            System.out.println("El agente 3 esta muriendo");
            System.out.println("El agente 3 esta heredando al Agente H");
            System.out.println("La edad en agente 3 es: " + cliente.getEdad());
            System.out.println("--------------------------");
            System.out.println(" ");
        } catch (StaleProxyException ex) {
            Logger.getLogger(Agente3.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    class Comportamiento extends Behaviour {

        @Override
        public void action() {
            try {
                ACLMessage aclmsj = blockingReceive();
                cliente = (Cliente) aclmsj.getContentObject();
                cliente.setEdad(cliente.getEdad() * 2);
                System.out.println("El agente 3 recibio el paquete");
                System.out.println("Listo para morir");
                System.out.println("La edad en agente 3 es: " + cliente.getEdad());
                System.out.println("--------------------------");
                System.out.println(" ");
                doDelete();
            } catch (UnreadableException ex) {
                Logger.getLogger(Agente3.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public boolean done() {
            return true;
        }
    }

}
