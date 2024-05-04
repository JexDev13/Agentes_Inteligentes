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
public class Agente5 extends Agent {

    @Override
    protected void setup() {
        addBehaviour(new Comportamiento());
    }

    class Comportamiento extends CyclicBehaviour {

        @Override
        public void action() {

            try {
                ACLMessage aclmsj = blockingReceive();
                Cliente cliente = (Cliente) aclmsj.getContentObject();
                cliente.setEdad(cliente.getEdad() + 1);
                System.out.println("CD-05-02");
                System.out.println("Listo para enviar respuesta al agente 2");
                System.out.println("La edad en agente 5 es: " + cliente.getEdad());
                System.out.println("--------------------------");
                System.out.println(" ");
                Comunicacion.msj(ACLMessage.REQUEST, getAgent(), "Ag2", null, cliente, "CD-05-02");
            } catch (UnreadableException ex) {
                Logger.getLogger(Agente5.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
