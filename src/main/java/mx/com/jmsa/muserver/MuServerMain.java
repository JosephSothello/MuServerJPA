package mx.com.jmsa.muserver;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import io.muserver.Method;
import io.muserver.MuServer;
import io.muserver.MuServerBuilder;
import io.muserver.rest.CORSConfigBuilder;
import io.muserver.rest.RestHandlerBuilder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import mx.com.jmsa.muserver.*;

public class MuServerMain {

	public static void main(String[] args) {

        MuServer server = MuServerBuilder.httpServer()
        		.withHttpPort(1337)
        		.addHandler(RestHandlerBuilder.restHandler(new MuServerHandler())
        				.withCORS(CORSConfigBuilder.corsConfig()
								.withAllowedOriginRegex("http(s)?://localhost:[0-9]+")
								.withAllowedHeaders(Arrays.asList(
										"Content-Type", "Access-Control-Allow-Headers", "Authorization", "X-Requested-With"))
								)
						.addCustomWriter(new JacksonJaxbJsonProvider())
	                    .addCustomReader(new JacksonJaxbJsonProvider())		
        				)
        		
        		.start();

//                .addHandler(Method.GET, "/", (request, response, pathParams) -> {
//                    // Use JPA to interact with the database
//                    em.getTransaction().begin();
//                    // Suppose MyEntity is an @Entity class you want to retrieve data from
//                    List<User> entities = em.createQuery("SELECT u FROM User u", User.class).getResultList();
//                    em.getTransaction().commit();
//                    
//                    // Process and respond with the data
//                    response.write(entities.toString());
//                })
//                .addHandler(Method.GET, "/tables", (request, response, pathParams) -> {
//                	em.getTransaction().begin();
//                	List<RestaurantTable> entities = em.createQuery("SELECT rt FROM RestaurantTable rt", RestaurantTable.class).getResultList();
//                	em.getTransaction().commit();
//                	response.write(entities.toString());
//                })
//                .addHandler(Method.GET, "/reservations", (request, response, pathParams) -> {
//                	em.getTransaction().begin();
//                	List<RestaurantTable> entities = em.createQuery("SELECT r FROM Reservation r", RestaurantTable.class).getResultList();
//                	em.getTransaction().commit();
//                	response.write(entities.toString());
//                })

        System.out.println("Server started at " + server.uri());

	}

}
