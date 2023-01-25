package nata.project.repository;

import nata.project.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    @Query(value = "SELECT a FROM Product a " +
            "JOIN FETCH a.price b WHERE a.id = ?1 " +
            "and b.dateFrom <= current_date and (b.dateBy >= current_date or b.dateBy is null)")
    Product fetchProductPriceByProductIdInnerJoin(long productId);

//    @Query(value = "SELECT a FROM Product a " +
//            "JOIN  a.price b " +
//            "JOIN  a.category d WHERE d.id in ?1 " +
//            "and b.dateFrom <= current_date and (b.dateBy >= current_date or b.dateBy is null)")
    Page<Product> findAllByCategoryIdIn(List<Integer> categoryList, Pageable pageable);
}
