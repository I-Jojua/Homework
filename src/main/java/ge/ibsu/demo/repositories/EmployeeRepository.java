package ge.ibsu.demo.repositories;

import ge.ibsu.demo.entities.Employee;
import ge.ibsu.demo.projections.EmployeeSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // 🔹 JPQL
    @Query("select e from Employee e where e.department.id = :depId")
    List<Employee> findAllByDepartment(@Param("depId") Long departmentId);

    // 🔹 Native
    @Query(
            value = "select * from employees e where e.department_id = :depId",
            nativeQuery = true
    )
    List<Employee> findAllByDepartmentViaNative(@Param("depId") Long departmentId);

    // 🔹 Search (JPQL) — FIXED 🔥
    @Query("select e from Employee e " +
            "where (:searchText is null or lower(concat(e.firstName, ' ', e.lastName)) like lower(concat('%', :searchText, '%')))")
    Page<Employee> searchEmployees(@Param("searchText") String searchText, Pageable pageable);

    // 🔹 Search (Native) — FIXED 🔥
    @Query(
            value = "select * from employees e " +
                    "where (:searchText is null or lower(concat(e.first_name, ' ', e.last_name)) like lower(concat('%', :searchText, '%')))",
            countQuery = "select count(*) from employees e " +
                    "where (:searchText is null or lower(concat(e.first_name, ' ', e.last_name)) like lower(concat('%', :searchText, '%')))",
            nativeQuery = true
    )
    Page<Employee> searchEmployeesViaNative(@Param("searchText") String searchText, Pageable pageable);

    // 🔹 Dynamic projection (შენ უკვე გქონდა)
    <T> T findByPhoneOrEmail(String phone, String email, Class<T> type);

    // 🔥 დავალება — Interface-based projection
    List<EmployeeSummary> findAllBy();
}