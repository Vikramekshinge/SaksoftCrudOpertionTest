package Com.crud.employee.controller;

import java.net.http.HttpHeaders;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.ServerRequest.Headers;

import Com.crud.Converter.EmployeeConverter;
import Com.crud.dto.EmployeeRequest;
import Com.crud.employee.model.Employee;
import Com.crud.employee.service.EmployeeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	//@Autowired
	private EmployeeService service;

	//@Autowired
	private EmployeeConverter employeeConverter;
	
	
	
	@Autowired
	public EmployeeController(EmployeeService service, EmployeeConverter employeeConverter) {
		super();
		this.service = service;
		this.employeeConverter = employeeConverter;
	}

	/*
	 * Employee employee;
	 * 
	 * @Autowired EmployeeController(Employee employee){ this.employee=employee;
	 * 
	 * }
	 */
	
	
	

	@PostMapping("/employees")
	public ResponseEntity<EmployeeRequest> addEmployee(@RequestBody @Valid EmployeeRequest employeeDto) {
		EmployeeRequest dto = service.saveEmployee(employeeDto);
		
		ResponseEntity<EmployeeRequest> responseEntity = new ResponseEntity<EmployeeRequest>(dto, null, HttpStatus.CREATED);
		return responseEntity;

	}

	@GetMapping("/employees")
	public ResponseEntity<List<EmployeeRequest>> getAllEmployees() {
		List<EmployeeRequest> findAll = service.findAllEmployees();

		ResponseEntity<List<EmployeeRequest>> responseEntity = new ResponseEntity<List<EmployeeRequest>>(findAll, HttpStatus.OK);
		return responseEntity;

	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<EmployeeRequest> getEmployeeById(@PathVariable Long id) {
		EmployeeRequest dto = service.findEmployeeById(id);
		
		ResponseEntity<EmployeeRequest> responseEntity = new ResponseEntity<EmployeeRequest>(dto, HttpStatus.OK);
		return responseEntity;
	}

	@PutMapping("/employees")
	public ResponseEntity<EmployeeRequest> updateEmployee(@RequestBody EmployeeRequest employeeDto) {
		
		EmployeeRequest dto = service.updateEmployee(employeeDto);
		ResponseEntity<EmployeeRequest> responseEntity = new ResponseEntity<EmployeeRequest>(dto, HttpStatus.OK);
		return responseEntity;
	}

	@DeleteMapping("/employees/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
		String s1 = service.deleteEmployee(id);
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(s1, HttpStatus.NO_CONTENT);
		return responseEntity;

	}

}
