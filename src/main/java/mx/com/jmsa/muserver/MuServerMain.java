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

        System.out.println("Server started at " + server.uri());

	}

}
