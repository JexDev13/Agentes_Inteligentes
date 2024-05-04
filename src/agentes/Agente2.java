package agentes;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import modelo.Cliente;

/**
 *
 * @author Isma2
 */
public class Agente2 extends Agent {

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
                String conversationId = aclmsj.getConversationId();
                switch (conversationId) {
                    case "CD-01-02" -> {
                        // Enviar al agente 5
                        System.out.println("CD-02-05");
                        System.out.println("Listo para enviar al agente 5");
                        System.out.println("La edad en agente 2 es: " + cliente.getEdad());
                        System.out.println("--------------------------");
                        System.out.println(" ");
                        Comunicacion.msj(ACLMessage.REQUEST, getAgent(), "Ag5", null, cliente, "CD-02-05");
                    }
                    case "CD-05-02" -> {
                        // Enviar al agente 4
                        System.out.println("CD-02-04");
                        System.out.println("Listo para enviar al agente 4");
                        System.out.println("La edad en agente 2 es: " + cliente.getEdad());
                        System.out.println("--------------------------");
                        System.out.println(" ");
                        Comunicacion.msj(ACLMessage.REQUEST, getAgent(), "Ag4", null, cliente, "CD-02-04");
                    }
                    case "CD-04-02" -> {
                        // Enviar al agente 4
                        System.out.println("CD-02-04");
                        System.out.println("Listo para enviar última respuesta al agente 4");
                        System.out.println("La edad en agente 2 es: " + cliente.getEdad());
                        System.out.println("--------------------------");
                        System.out.println(" ");
                        Comunicacion.msj(ACLMessage.REQUEST, getAgent(), "Ag4", null, cliente, "CD-02-04");
                    }
                }
            } catch (UnreadableException ex) {
                System.out.println("No serializado");
            }
        }
    }

}
