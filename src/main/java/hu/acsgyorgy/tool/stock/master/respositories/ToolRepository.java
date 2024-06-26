package hu.acsgyorgy.tool.stock.master.respositories;
import hu.acsgyorgy.tool.stock.master.domain.entities.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Integer> {

        Optional<Tool> findByProductId(int id);

        List<Tool> findAllByCompanyContainingIgnoreCase(String name);

        List<Tool> findAllByToolNameContainingIgnoreCase(String name);

        List<Tool> findAllByQuantityGreaterThanEqual(int quantity);

        List<Tool> findAllByQuantityLessThanEqual(int quantity);

        List<Tool> findAllByQuantityBetween(int minQuantity,int maxQuantity);

        List<Tool> findAllByPriceGreaterThanEqual(double price);

        List<Tool> findAllByPriceLessThanEqual(double price);

        List<Tool> findAllByPriceBetween(double minPrice,double maxPrice);

}
