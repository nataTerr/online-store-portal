package nata.project.repository;

import nata.project.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    @Query(value = "SELECT a FROM Product a " +
            "JOIN FETCH a.price b WHERE a.id = ?1 "
    )
    Product fetchProductPriceByProductId(long productId);

    @Query(
            value = "select p from Product p join fetch p.price join p.category where p.category.id in :categoryIds",
            countQuery = "select count(p) from Product p join p.price join p.category where p.category.id in :categoryIds"
    )
    Page<Product> fetchAllByCategoryIdIn(@Param("categoryIds") List<Integer> categoryIds, Pageable pageable);
}
