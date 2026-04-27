package ge.ibsu.demo.repositories;

import ge.ibsu.demo.dto.DepartmentDTO;
import ge.ibsu.demo.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // 🔹 შენი ძველი DTO query (არ ვშლით)
    @Query("""
        SELECT new ge.ibsu.demo.dto.DepartmentDTO(
            d.name,
            CONCAT(m.firstName, ' ', m.lastName),
            c.name,
            l.city,
            l.streetAddress
        )
        FROM Department d
        JOIN d.manager m
        JOIN d.location l
        JOIN l.country c
        WHERE (:country IS NULL OR c.name = :country)
        AND (:city IS NULL OR l.city = :city)
    """)
    List<DepartmentDTO> findDepartments(
            @Param("country") String country,
            @Param("city") String city
    );

    // 🔥 ახალი — დავალებისთვის (JOIN FETCH)
    @Query("SELECT d FROM Department d JOIN FETCH d.employees")
    List<Department> findAllWithEmployees();
}