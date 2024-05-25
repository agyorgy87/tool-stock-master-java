package hu.acsgyorgy.tool.stock.master;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Integer> {

        Optional<Tool> findByProductId(int id);

        //Optional<Tool> findAllByToolName(String toolName);

        List<Tool> findAllByToolNameContainingIgnoreCase(String name);

        //List<Tool> findAllByQuantityBetween(int minQuantity,int maxQuantity);

}
