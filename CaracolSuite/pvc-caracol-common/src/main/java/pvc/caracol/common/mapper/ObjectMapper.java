package pvc.caracol.common.mapper;

public interface ObjectMapper<Destino, Origen> {
    Destino map(Origen origen);
}
