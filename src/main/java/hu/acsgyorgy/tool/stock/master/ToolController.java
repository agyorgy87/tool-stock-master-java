package hu.acsgyorgy.tool.stock.master;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ToolController {

    @Autowired
    private ToolRepository toolRepository;

    /*
    @PostMapping(
            path = "/create-tool"
    )
    public boolean createTool(@RequestBody Tool tool) {
        toolRepository.save(tool);
        return true;
    }
     */

    @PostMapping(
            path = "/create-tool"
    )
    public SuccessResponse createTool(@RequestBody Tool tool) {
        toolRepository.save(tool);
        return new SuccessResponse(true);
    }

    @GetMapping(
            path = "/all-tools"
    )
    public List<Tool> findAll() {
        return toolRepository.findAll();
    }

    @GetMapping(
            path = "/tools/{id}"
    )
    public Tool toolById(@PathVariable int id) {
        Optional<Tool> tool = toolRepository.findById(id);
        if(tool.isEmpty()) {
            throw new IdNotFoundException("tool id not found");
            //return null;
        }else{
            return tool.get();
        }
    }


    @PutMapping(
            path = "/update-tool/{id}"
    )
    public boolean updateUser(@PathVariable int id, @RequestBody Tool tool) {
        Optional<Tool> originalTool = toolRepository.findById(id);
        if(originalTool.isEmpty()) {
            return false;
        }else{
            originalTool.get().setToolName(tool.getToolName());
            originalTool.get().setProductId(tool.getProductId());
            originalTool.get().setCompany(tool.getCompany());
            originalTool.get().setQuantity(tool.getQuantity());
            originalTool.get().setPrice(tool.getPrice());
            toolRepository.save(originalTool.get());
            return true;
        }
    }


    @DeleteMapping(
            path = "/delete-tool/{id}"
    )
    public boolean deleteUser(@PathVariable int id) {
        Optional<Tool> tool = toolRepository.findById(id);
        if(tool.isEmpty()) {
            return false;
        }else{
            toolRepository.delete(tool.get());
            //return true;

        }
    }


    @GetMapping(
            path = "/search-by-product-id/{id}"
    )
    public Tool findProductId(@PathVariable int id) {
        Optional<Tool> tool = toolRepository.findByProductId(id);
        if(tool.isPresent()) {
            return tool.get();
        } else {
            return new Tool();
        }
    }

    @GetMapping(path = "/search-by-company/{company}")
    public List<Tool> findCompany(@PathVariable String company) {
        return toolRepository.findAllByCompanyContainingIgnoreCase(company);
    }

    @GetMapping(path = "/search-by-name/{name}")
    public List<Tool> findToolName(@PathVariable String name) {
        return toolRepository.findAllByToolNameContainingIgnoreCase(name);
    }

    @GetMapping(
            path = "/search-by-min-quantity/{quantity}"
    )
    public List<Tool> findMinQuantity(@PathVariable int quantity) {
        List<Tool> tool = toolRepository.findAllByQuantityGreaterThanEqual(quantity);
        return tool;
    }

    @GetMapping(
            path = "/search-by-max-quantity/{quantity}"
    )
    public List<Tool> findMaxQuantity(@PathVariable int quantity) {
        List<Tool> tool = toolRepository.findAllByQuantityLessThanEqual(quantity);
        return tool;
    }

    @GetMapping(
            path = "/search-by-quantity-between/{minQuantity}/{maxQuantity}"
    )
    public List<Tool> findQuantityBetween(@PathVariable int minQuantity, @PathVariable int maxQuantity) {
        List<Tool> tool = toolRepository.findAllByQuantityBetween(minQuantity, maxQuantity);
        return tool;
    }

    @GetMapping(
            path = "/search-by-min-price/{price}"
    )
    public List<Tool> findMinPrice(@PathVariable double price) {
        List<Tool> tool = toolRepository.findAllByPriceGreaterThanEqual(price);
        return tool;
    }

    @GetMapping(
            path = "/search-by-max-price/{price}"
    )
    public List<Tool> findMaxPrice(@PathVariable double price) {
        List<Tool> tool = toolRepository.findAllByPriceLessThanEqual(price);
        return tool;
    }

    @GetMapping(
            path = "/search-by-price-between/{minPrice}/{maxPrice}"
    )
    public List<Tool> findPriceBetween(@PathVariable double minPrice, @PathVariable double maxPrice) {
        List<Tool> tool = toolRepository.findAllByPriceBetween(minPrice, maxPrice);
        return tool;
    }




}
