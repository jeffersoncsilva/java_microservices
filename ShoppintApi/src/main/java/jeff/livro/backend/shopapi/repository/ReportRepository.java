package jeff.livro.backend.shopapi.repository;

import jeff.livro.backend.shopapi.dtos.ShopReportDTO;
import jeff.livro.backend.shopapi.models.Shop;

import java.time.LocalDate;
import java.util.List;

public interface ReportRepository {

    List<Shop> getShopByFilters(LocalDate dataInicio, LocalDate dataFim, Float valorMinimo);

    ShopReportDTO getReportByDate(LocalDate dataInicio, LocalDate dataFim);


}
