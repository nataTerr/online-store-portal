package nata.project.repository;

import nata.project.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findAllByParentIdIsNull();

    List<Category> findAllById(Integer parentId);

    List<Category> findAllByParentId(Integer categoryId);
}
