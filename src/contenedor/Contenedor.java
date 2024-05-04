package contenedor;

import agentes.Agente1;
import agentes.Agente2;
import agentes.Agente3;
import agentes.Agente4;
import agentes.Agente5;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.StaleProxyException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * @author Isma2
 */
public class Contenedor {

    public void crearContenedor(String host, int port, String name) {
        jade.core.Runtime runtime = jade.core.Runtime.instance();

        Profile p = new ProfileImpl(host, port, name);
        AgentContainer contenedorPrincipal = runtime.createMainContainer(p);
        crearAgentes(contenedorPrincipal);
    }

    private void crearAgentes(AgentContainer contenedorPrincipal) {
        try {
            contenedorPrincipal.createNewAgent("Ag3", Agente3.class.getName(), new Object[]{contenedorPrincipal}).start();
            contenedorPrincipal.createNewAgent("Ag4", Agente4.class.getName(), null).start();
            contenedorPrincipal.createNewAgent("Ag2", Agente2.class.getName(), null).start();
            contenedorPrincipal.createNewAgent("Ag5", Agente5.class.getName(), null).start();
            contenedorPrincipal.createNewAgent("Ag1", Agente1.class.getName(), null).start();
        } catch (StaleProxyException ex) {
            System.out.print(ex);
            System.out.println("Error al agregar los agentes");

        }

    }
}
