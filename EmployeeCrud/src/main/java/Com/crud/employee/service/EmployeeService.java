package Com.crud.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Com.crud.Converter.EmployeeConverter;
import Com.crud.dto.EmployeeRequest;
import Com.crud.employee.model.Employee;
import Com.crud.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	@Autowired
	EmployeeConverter employeeConverter;

	public EmployeeRequest saveEmployee(EmployeeRequest employeeDto) {
		Employee e1 = employeeConverter.dtoToEntity(employeeDto);
		Employee ee = repository.save(e1);
		EmployeeRequest dto = employeeConverter.entityToDto(ee);

		return dto;
	}

	public List<Employee> saveEmployees(List<Employee> employee) {
		return repository.saveAll(employee);
	}

	public List<EmployeeRequest> findAllEmployees() {
		List<Employee> le = repository.findAll();
		List<EmployeeRequest> dto = employeeConverter.entityToDto(le);
		return dto;
	}

	public EmployeeRequest findEmployeeById(Long id) {

		Employee ee = repository.findById(id).orElse(null);
		EmployeeRequest dto = employeeConverter.entityToDto(ee);

		return dto;

	}

	public String deleteEmployee(Long id) {
		repository.deleteById(id);
		return "employee removed !! " + id;
	}

	public EmployeeRequest updateEmployee(EmployeeRequest employeeDto) {
		Employee e1 = employeeConverter.dtoToEntity(employeeDto);

		Employee existingEmployee = repository.findById(e1.getId()).orElse(null);
		existingEmployee.setFirstName(e1.getFirstName());
		existingEmployee.setLastName(e1.getLastName());
		existingEmployee.setEmail(e1.getEmail());
		existingEmployee.setAge(e1.getAge());

		EmployeeRequest dto = employeeConverter.entityToDto(existingEmployee);

		return dto;
	}

}
