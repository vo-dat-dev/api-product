package service.market.product.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class AdvancedJsonFormattingAspect {
    private static final Logger logger = LoggerFactory.getLogger(AdvancedJsonFormattingAspect.class);
    
    private final ObjectMapper prettyMapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    
    private final ObjectMapper compactMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    @Around("execution(* service.market.product.controller.*.*(..))")
    public Object formatJsonResponse(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        
        if (result instanceof ResponseEntity) {
            ResponseEntity<?> responseEntity = (ResponseEntity<?>) result;
            Object body = responseEntity.getBody();
            if (body != null) {

                try {
                    // Check if the client accepts pretty-printed JSON
                    boolean prettyPrint = shouldPrettyPrint(responseEntity);
                    
                    // Format the JSON based on client preference
                    String formattedJson = prettyPrint ? 
                            prettyMapper.writeValueAsString(body) : 
                            compactMapper.writeValueAsString(body);
                    
                    // Add metadata to the response
                    Map<String, Object> enhancedResponse = new HashMap<>();
                    enhancedResponse.put("data", body);
                    enhancedResponse.put("timestamp", System.currentTimeMillis());
                    enhancedResponse.put("message", responseEntity.getHeaders().getFirst("message"));
//                    enhancedResponse.put("formatted", prettyPrint);
                    
                    // Create a new response entity with the enhanced JSON
                    return ResponseEntity.status(responseEntity.getStatusCode())
                            .headers(responseEntity.getHeaders())
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(enhancedResponse);
                } catch (Exception e) {
                    logger.error("Error formatting JSON response: {}", e.getMessage());
                    // Return original response if formatting fails
                    Map<String, Object> enhancedResponse = new HashMap<>();
                    enhancedResponse.put("data", "{}");
                    enhancedResponse.put("timestamp", System.currentTimeMillis());
                    enhancedResponse.put("message", responseEntity.getHeaders().getFirst("message"));
                    return ResponseEntity.status(responseEntity.getStatusCode())
                            .headers(responseEntity.getHeaders())
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(enhancedResponse);
//                    return result;
                }
            }
            else{
                Map<String, Object> enhancedResponse = new HashMap<>();
                enhancedResponse.put("data", null);
                enhancedResponse.put("timestamp", System.currentTimeMillis());
                enhancedResponse.put("message", "");
                return ResponseEntity.status(responseEntity.getStatusCode())
                        .headers(responseEntity.getHeaders())
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(enhancedResponse);
            }
        }
        
        return result;
    }
    
    private boolean shouldPrettyPrint(ResponseEntity<?> responseEntity) {
        // Check if the client accepts pretty-printed JSON
        // This could be based on a custom header, query parameter, or user preference
        return true; // Default to pretty printing
    }
} 