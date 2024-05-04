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
public class Agente4 extends Agent {

    @Override
    protected void setup() {
        addBehaviour(new Comportamiento());
    }

    class Comportamiento extends CyclicBehaviour {

        boolean enviado = true;

        @Override
        public void action() {
            try {
                ACLMessage aclmsj = blockingReceive();
                Cliente cliente = (Cliente) aclmsj.getContentObject();

                if (enviado) {
                    cliente.setEdad(cliente.getEdad() + 1);
                    System.out.println("CD-04-02");
                    System.out.println("Listo para enviar respuesta al agente 2");
                    System.out.println("La edad en agente 4 es: " + cliente.getEdad());
                    System.out.println("--------------------------");
                    System.out.println(" ");
                    Comunicacion.msj(ACLMessage.REQUEST, getAgent(), "Ag2", null, cliente, "CD-04-02");
                    enviado = false;
                } else {
                    //enviar a 3
                    System.out.println("CD-04-03");
                    System.out.println("Listo para enviar al agente 3");
                    System.out.println("La edad en agente 4 es: " + cliente.getEdad());
                    System.out.println("--------------------------");
                    System.out.println(" ");
                    Comunicacion.msj(ACLMessage.INFORM, getAgent(), "Ag3", null, cliente, "CD-04-03");
                }

                //System.out.println(getName());
            } catch (UnreadableException ex) {
                Logger.getLogger(Agente4.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
