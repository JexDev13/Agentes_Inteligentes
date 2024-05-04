package agentes;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.wrapper.AgentContainer;
import jade.wrapper.StaleProxyException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Cliente;

/**
 *
 * @author Isma2
 */
public class AgenteH extends Agent {

    Cliente cliente;
    AgentContainer contenedorPrincipal;
    int alias;

    @Override
    protected void setup() {
        addBehaviour(new Comportamiento());
    }

    @Override
    protected void takeDown() {
        try {
            contenedorPrincipal.createNewAgent("AgenteHijo_" + alias, AgenteH.class.getName(), new Object[]{cliente, contenedorPrincipal, alias}).start();
            System.out.println("El agenteHijo_" + alias + " esta muriendo");
            System.out.println("El agenteHijo_" + alias + " esta heredando al agenteHijo_" + (alias + 1));
            System.out.println("--------------------------");
            System.out.println(" ");
        } catch (StaleProxyException ex) {
            Logger.getLogger(AgenteH.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    class Comportamiento extends SimpleBehaviour {

        @Override
        public void action() {
            cliente = (Cliente) getArguments()[0];
            cliente.setEdad(cliente.getEdad() + 1);
            contenedorPrincipal = (AgentContainer) getArguments()[1];
            alias = ((int) getArguments()[2]) + 1;
            System.out.println("El agenteHijo_" + alias + " recibio del padre");
            System.out.println("El agenteHijo_" + alias + " esta listo para morir");
            System.out.println("La edad en agenteHijo_" + alias + " es: " + cliente.getEdad());
            System.out.println("--------------------------");
            System.out.println(" ");
            doDelete();
        }

        @Override
        public boolean done() {
            return true;
        }
    }

}
