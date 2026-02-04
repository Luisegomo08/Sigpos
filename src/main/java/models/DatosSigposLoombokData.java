package models;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class DatosSigposLoombokData {

    private String usuario;
    private String clave;

    public static List<DatosSigposLoombokData> setData(DataTable dataTable) {
        List<DatosSigposLoombokData> dates = new ArrayList<>();
        List<Map<String, String>> mapInfo = dataTable.asMaps();
        for (Map<String, String> map : mapInfo) {
            dates.add(new ObjectMapper().convertValue(map, DatosSigposLoombokData.class));
        }
        return dates;
    }

    // Getters explícitos para evitar dependencia estricta de Lombok en tiempo de compilación/IDE
    public String getUsuario() {
        return this.usuario;
    }

    public String getClave() {
        return this.clave;
    }
}
