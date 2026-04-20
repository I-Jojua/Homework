package ge.ibsu.demo.services;

import ge.ibsu.demo.dto.DepartmentDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {

    private final List<DepartmentDTO> departments = new ArrayList<>();

    public DepartmentService() {
        // test data
        departments.add(new DepartmentDTO("IT", "John Doe", "USA", "New York", "5th Avenue"));
        departments.add(new DepartmentDTO("HR", "Jane Smith", "UK", "London", "Baker Street"));
    }

    public List<DepartmentDTO> getDepartments(String country, String city) {
        return departments.stream()
                .filter(d -> country == null || d.getCountry().equalsIgnoreCase(country))
                .filter(d -> city == null || d.getCity().equalsIgnoreCase(city))
                .toList();
    }

    public DepartmentDTO getById(Long id) {
        if (id < 0 || id >= departments.size()) {
            throw new RuntimeException("Department not found");
        }
        return departments.get(id.intValue());
    }
}