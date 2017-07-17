package org.gabriele.progettol.ticket;

import centralsystem.CentralSystemWebServerInterface;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;


import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

import centralsystem.Stub;
import singleton.JSONOperations;


@Provider
public class SecurityFilter implements ContainerRequestFilter{

	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
	private static final String SECURED_URL_PREFIX = "secured";

	CentralSystemWebServerInterface systemStub = Stub.getInstance();

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		
		//controllo subito se usare il filtro oppure no -> lavora solo se nel path c'è "secured"
		String path = requestContext.getUriInfo().getPath();
		
		System.out.println(	"\n\n\n--------------------------" +
							"\nTime : " + new Date().toString() +
							"\nPath: " + path + 
							"\nMethod: " + requestContext.getMethod()+
							"\n--------------------------"); 
		
		if(path.contains(SECURED_URL_PREFIX)){
			
			//System.out.println("filtering..");
			
			List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
			
			if(authHeader != null && authHeader.size() > 0 ){
				

				String authToken = authHeader.get(0);
				//System.out.println(authToken);
			
				if(path.contains("user")){
					
					if(userFilter(authToken)){
						return;
					}
					
				}else if(path.contains("collector")){
					
					if(collectorFilter(authToken)){
						return;
					}
				}		
				
			}
            System.err.println("result from system: UNAUTHORIZED, header: "+authHeader.toString());

			Response unauthorizedStatus = 	Response.status(Status.ACCEPTED)
					.entity(JSONOperations.getInstance().booleanPacket(false))
					.build();
			System.out.println("sending to app:" + unauthorizedStatus.getEntity());
			
			//rimando indietro la risposta
			requestContext.abortWith(unauthorizedStatus);
		}

				
				
	}


	private boolean collectorFilter(String authToken) {
		
		authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
		//System.out.println(authToken);
		String decodedString = Base64.decodeAsString(authToken);
		//System.out.println(decodedString);
		StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
		String username = tokenizer.nextToken();
		String password = tokenizer.nextToken();
		
		//System.out.println("collectorFilter");
		
		return systemStub.collectorLogin(username, password);
	}



	private boolean userFilter(String authToken) {
		
		authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
		//System.out.println(authToken);
		String decodedString = Base64.decodeAsString(authToken);
		//System.out.println(decodedString);
		StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
		String username = tokenizer.nextToken();
		String password = tokenizer.nextToken();
		
		//System.out.println("userFilter");
		
		return systemStub.userLogin(username, password);
	}
}











