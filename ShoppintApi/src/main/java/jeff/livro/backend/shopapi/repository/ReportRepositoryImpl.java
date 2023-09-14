package jeff.livro.backend.shopapi.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jeff.livro.backend.shopapi.dtos.ShopReportDTO;
import jeff.livro.backend.shopapi.models.Shop;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

public class ReportRepositoryImpl implements ReportRepository{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Shop> getShopByFilters(LocalDate dataInicio, LocalDate dataFim, Float valorMinimo){
        StringBuilder sb = new StringBuilder();
        sb.append("select s");
        sb.append("from shop s");
        sb.append("where s.date >= :dataInicio");
        if(dataFim != null)
            sb.append("and s.date <= :dataFim ");
        if(valorMinimo != null)
            sb.append("and s.total <= :valorMinimo ");

        Query q = em.createQuery(sb.toString());
        q.setParameter("dataInicio", dataInicio.atTime(0, 0));
        if (dataFim != null){
            q.setParameter("dataFim", dataFim.atTime(23, 59));
        }
        if(valorMinimo != null)
            q.setParameter("valorMinimo", valorMinimo);

        return q.getResultList();
    }

    @Override
    public ShopReportDTO getReportByDate(LocalDate dataInicio, LocalDate dataFim){
        StringBuilder sb = new StringBuilder();
        sb.append("select count(sp.id), sum(sp.total), avg(sp.total) from shopping.shop sp where sp.date >= :dataInicio and sp.date <= :dataFim");
        Query query = em.createNamedQuery(sb.toString());
        query.setParameter("dataInicio", dataInicio.atTime(0,0));
        query.setParameter("dataFim", dataFim.atTime(23, 59));
        Object[] result = (Object[]) query.getSingleResult();
        ShopReportDTO dto = new ShopReportDTO();
        dto.setCount(((BigInteger) result[0]).intValue());
        dto.setTotal((Double) result[1]);
        dto.setMean((Double)result[2]);
        return dto;
    }
}
