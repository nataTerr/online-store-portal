package nata.project.repository;

import nata.project.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    //TODO поправить запрос, т.к. не получает одну цену
    @Query(value = "SELECT a FROM Product a " +
            "JOIN FETCH a.price b WHERE a.id = ?1 " +
            "and b.dateFrom <= current_date and (b.dateBy >= current_date or b.dateBy is null)")
    Product fetchProductPriceByProductIdInnerJoin(long productId);

    @Query(value = "SELECT a FROM Product a " +
            "JOIN FETCH a.category d " +
            "JOIN FETCH a.price b " +
            "WHERE d.id in ?1 and (b.dateFrom <= current_date and (b.dateBy >= current_date or b.dateBy is null))")
    List<Product> findAllByCategoryIdIn(List<Integer> categoryList);

    //Сделала список продуктов и в сервисе преобразовала в Page
    // Page<Product> findAllByCategoryIdIn(List<Integer> categoryList, Pageable pageable);
}
