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



}
