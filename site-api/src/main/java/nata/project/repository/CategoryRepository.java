package nata.project.repository;

import nata.project.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findAllByParentIdIsNull();

    List<Category> findAllById(Integer parentId);

    List<Category> findAllByParentId(Integer categoryId);

    @Query(nativeQuery = true,
            value = "with recursive start_category as (" +
                    "  select c.parent_id, c.id" +
                    "  from categories c" +
                    "  where c. id = :parentId" +
                    "  union" +
                    "  select res.parent_id, res.id" +
                    "  from categories res" +
                    "    join start_category on res.parent_id = start_category.id" +
                    ")" +
                    "select c.id " +
                    "from start_category c"
    )
    List<Integer> findAllChildCategoriesByParent(@Param("parentId") Integer parentCategoryId);
}
