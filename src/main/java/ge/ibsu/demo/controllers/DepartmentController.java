package ge.ibsu.demo.controllers;

import ge.ibsu.demo.dto.DepartmentDTO;
import ge.ibsu.demo.services.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // 🔹 GET all + filtering
    @GetMapping
    public List<DepartmentDTO> getDepartments(
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String city
    ) {
        return departmentService.getDepartments(country, city);
    }

    // 🔹 GET by id (DTO version recommended)
    @GetMapping("/{id}")
    public DepartmentDTO getById(@PathVariable Long id) {
        return departmentService.getById(id);
    }
}