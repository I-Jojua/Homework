package ge.ibsu.demo.repositories;

import ge.ibsu.demo.entities.Product;
import ge.ibsu.demo.dto.ProductStats;
import org.springframework.data.jpa.repository.*;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT new ge.ibsu.demo.dto.ProductStats(p.category, AVG(p.price)) FROM Product p GROUP BY p.category")
    List<ProductStats> getAveragePriceByCategory();
}