package ec.edu.ups.rieraxaviercajerajee.vista;

import java.io.IOException;
import ec.edu.ups.rieraxaviercajerajee.dao.CajeraDAO;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.rieraxaviercajerajee.modelo.Cliente;
import ec.edu.ups.rieraxaviercajerajee.modelo.Cuenta;
import ec.edu.ups.rieraxaviercajerajee.negocio.CajeroON;

@WebServlet("/cajero")
public class main extends HttpServlet {

    @Inject
    private CajeroON on;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("<h1>Hola mundo</h1>");
        try {
        	Cliente cl = new Cliente();
            cl.setCedula("01048395451");
            cl.setNombre("vinicio");
            cl.setNumeroCelular("0992801686");
            cl.setSaldo(5);

            on.insertCliente(cl);

            String aleatorio = String.valueOf(numbGen());

            Cuenta cuenta = new Cuenta();
            cuenta.setIdCuenta("654321");
            cuenta.setSaldo(17);
            cuenta.setCliente(cl);

            on.insertCuenta(cuenta);

//            Cuenta c = on.buscarCuenta("123456");
//            System.out.println("Cuenta ID " + c.getIdCuenta());
//
//            Cliente cli = on.buscarCliente(c.getCliente().getCedula());
//            System.out.println("Cliente" + cli.getCedula() + cli.getNombre());
//
//            on.recarga(cli.getCedula(), 3.2, c.getIdCuenta());            
            
            
         
        } catch (Exception e) {
            response.getWriter().println("<h1>" + e.getMessage() + "</h1>");
        }
    }

	private long numbGen() {
        while (true) {
            long numb = (long)(Math.random() * 100000000 * 1000000); // had to use this as int's are to small for a 13 digit number.
            if (String.valueOf(numb).length() == 12)
                return numb;
            }
	}

}
