package agentes;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Cliente;

/**
 *
 * @author Isma2
 */
public class Agente1 extends Agent {

    private boolean bandera = true;

    @Override
    protected void setup() {
        addBehaviour(new Comportamiento());
    }

    class Comportamiento extends CyclicBehaviour {

        @Override
        public void action() {

            Comunicacion.msj(ACLMessage.INFORM, getAgent(), "Ag2", null, new Cliente("Jeremy", "Arias", "1", 995, 23), "CD-01-02");
            System.out.println("CD-01-02");
            System.out.println("Agente 1 y La edad es: 23");
            System.out.println("--------------------------");
            System.out.println(" ");
            blockingReceive();
        }
    }
}
