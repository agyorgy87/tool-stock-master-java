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

    @PostMapping(
            path = "/createtool"
    )
    public boolean createTool(@RequestBody Tool tool) {
        toolRepository.save(tool);
        return true;
    }

    @GetMapping(
            path = "/alltools"
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
            return null;
        }else{
            return tool.get();
        }
    }


    @PutMapping(
            path = "/updatetool/{id}"
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
            path = "/deletetool/{id}"
    )
    public boolean deleteUser(@PathVariable int id) {
        Optional<Tool> tool = toolRepository.findById(id);
        if(tool.isEmpty()) {
            return false;
        }else{
            toolRepository.delete(tool.get());
            return true;
        }
    }


    @GetMapping(
            path = "/searchByProductId/{id}"
    )
    public Tool findProductId(@PathVariable int id) {
        Optional<Tool> tool = toolRepository.findByProductId(id);
        if(tool.isPresent()) {
            return tool.get();
        } else {
            return new Tool();
        }
    }

    @GetMapping(path = "/searchByCompany/{company}")
    public List<Tool> findCompany(@PathVariable String company) {
        return toolRepository.findAllByCompanyContainingIgnoreCase(company);
    }

    /*
    @GetMapping(
            path = "/searchByName/{name}"
    )
    public Tool findToolName(@PathVariable String name) {
        Optional<Tool> tool = toolRepository.findAllByToolName(name);
        if(tool.isPresent()) {
            return tool.get();
        } else {
            return new Tool();
        }
    }
    */

    @GetMapping(path = "/searchByName/{name}")
    public List<Tool> findToolName(@PathVariable String name) {
        return toolRepository.findAllByToolNameContainingIgnoreCase(name);
    }

    @GetMapping(
            path = "/searchByMinQuantity/{quantity}"
    )
    public List<Tool> findMinQuantity(@PathVariable int quantity) {
        List<Tool> tool = toolRepository.findAllByQuantityGreaterThanEqual(quantity);
        return tool;
    }

    @GetMapping(
            path = "/searchByQuantityBetween/{minQuantity}/{maxQuantity}"
    )
    public List<Tool> findQuantityBetween(@PathVariable int minQuantity, @PathVariable int maxQuantity) {
        List<Tool> tool = toolRepository.findAllByQuantityBetween(minQuantity, maxQuantity);
        return tool;
    }




}
