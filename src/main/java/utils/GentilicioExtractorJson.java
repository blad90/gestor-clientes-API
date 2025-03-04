package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
/** Esta es clase de utileria para el manejo de claves relevantes
 * a partir de la respuesta del API para el gentilicio.
 *
 * @author Bladimir Baez
 * @version 1.0.0
 * */
public class GentilicioExtractorJson {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Metodo de utilidad encargado de extraer el valor del gentilicio de acuerdo a la clave.
     * En este caso, la respuesta del API esta estructurada en Ingles. Por tanto, se hace referencia al valor
     * en dicho idioma. Ejemplo: "DOM" -> "Dominican"
     *
     * @param json representa la respuesta del API en formato JSON de la informacion del gentilicio
     * @return String
     */
    public static String extraerValorGentilicio(String json) throws JsonProcessingException {
        List<JsonNode> nodos = objectMapper.readValue(json, List.class);

        if(!nodos.isEmpty()){
            JsonNode primerNodo = objectMapper.valueToTree(nodos.get(0));
            return primerNodo.path("demonyms").path("eng").path("m").asText();
        }
        return null;
    }
}