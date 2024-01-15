package pvc.caracol.tienda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pvc.caracol.common.service.BaseService;
import pvc.caracol.tienda.model.TiendaFisica;
import pvc.caracol.tienda.repository.ITiendaFisicaRepository;
import pvc.caracol.tienda.service.interfaces.ITiendaFisicaService;
import pvc.caracol.tienda.singleton.ISingletonList;

@Service
public class TiendaFisicaService extends BaseService implements ITiendaFisicaService {

    private final ITiendaFisicaRepository tiendaRepository;
    private ISingletonList<TiendaFisica> tiendaFisicaSingleton;

    @Autowired
    public TiendaFisicaService(ITiendaFisicaRepository tiendaRepository, ISingletonList<TiendaFisica> tiendaFisicaSingleton) {
        this.tiendaRepository = tiendaRepository;
        this.tiendaFisicaSingleton = tiendaFisicaSingleton;
    }

    @Override
    public TiendaFisica buscarTiendaFisica(String codeTienda) {
        TiendaFisica tienda = tiendaFisicaSingleton.findFirst(t -> t.getCode().equals(codeTienda));
        if (tienda == null) {
            tienda = tiendaRepository.findById(codeTienda).orElseGet(() -> createAndSaveNewTienda(codeTienda));
            tiendaFisicaSingleton.add(tienda);
        }
        return tienda;
    }

    private TiendaFisica createAndSaveNewTienda(String codeTienda) {
        TiendaFisica tienda = new TiendaFisica();
        tienda.setCode(codeTienda);
        return tiendaRepository.save(tienda);
    }
}
