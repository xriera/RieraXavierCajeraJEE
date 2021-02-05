package ec.edu.ups.rieraxaviercajerajee.service;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ec.edu.ups.rieraxaviercajerajee.modelo.Cliente;
import ec.edu.ups.rieraxaviercajerajee.modelo.Cuenta;
import ec.edu.ups.rieraxaviercajerajee.negocio.CajeroON;

@Path("clientes")
public class ClienteRest {

    @Inject
    private CajeroON on;

    @POST
    @Path("/recarga")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta crearProducto2(Parametos p) throws IOException, Exception {
        Respuesta resp = new Respuesta();

        Cuenta c = on.buscarCuenta(p.getIdCuenta());
        System.out.println("Cuenta ID " + c.getIdCuenta());

        if (c.getSaldo() <= p.getSaldo()) {
            resp.setCodigo(2);
            resp.setMensaje("Registro No satisfactorio no se Generara La Factura");
        } else {

            Cliente cli = on.buscarCliente(c.getCliente().getCedula());
            System.out.println("Cliente" + cli.getCedula() + cli.getNombre());

            on.recarga(cli.getCedula(), p.getSaldo(), c.getIdCuenta());

            resp.setCodigo(1);
            resp.setMensaje("Registro satisfactorio");
        }

       
        return resp;
    }
}
