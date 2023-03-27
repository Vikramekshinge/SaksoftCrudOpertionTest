package Com.crud.Converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import Com.crud.dto.EmployeeRequest;
import Com.crud.employee.model.Employee;

@Component
public class EmployeeConverter {
	
	public static EmployeeRequest entityToDto(Employee employee) {
		EmployeeRequest dto=new EmployeeRequest();
		dto.setId(employee.getId());
		dto.setFirstName(employee.getFirstName());
		dto.setLastName(employee.getLastName());
		dto.setEmail(employee.getEmail());
		dto.setAge(employee.getAge());
		
		return dto;
	}
	
	public Employee dtoToEntity(EmployeeRequest dto) {
		Employee ep=new Employee();
		ep.setId(dto.getId());
		ep.setFirstName(dto.getFirstName());
		ep.setEmail(dto.getEmail());
		ep.setAge(dto.getAge());
		return ep;
	}
	
	public List<EmployeeRequest> entityToDto(List<Employee> employee) {
		
	return employee.stream().map(EmployeeConverter::entityToDto).collect(Collectors.toList());
	}
	
	public List<Employee> dtoToEntity(List<EmployeeRequest> dto) {
		return dto.stream().map(x->dtoToEntity(x)).collect(Collectors.toList());
	}
	

}
