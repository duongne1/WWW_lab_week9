package fit.iuh.edu.vn.week7.controllers;

import fit.iuh.edu.vn.week7.enums.EmployeeStatus;
import fit.iuh.edu.vn.week7.models.Employee;
import fit.iuh.edu.vn.week7.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController{
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public  String showEmployeeList(Model model){
        model.addAttribute("employeeList",  employeeRepository.findAll());
        return "employee/list";
    }

    @GetMapping("/employees/show-add-form")
    public String showEmployeeForm(Model model){
        Employee employee = new Employee();
        model.addAttribute("employeeAdd", employee);
        model.addAttribute("status", EmployeeStatus.values());
        return "employee/add";
    }

    @PostMapping("/employees/add")
    public String addEmployee(@ModelAttribute("employeeAdd") Employee employee,
    BindingResult result, Model model){
        employeeRepository.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/employees/delete/{id}")
    public String deleteEmployee(@PathVariable("id") long id){
        Employee employee = employeeRepository.findById(id).orElse(new Employee());
        employeeRepository.delete(employee);
        return "redirect:/employees";
    }

    @GetMapping("/employees/show-edit-form/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model){
        Employee employee = employeeRepository.findById(id).orElse(null);
        model.addAttribute("employeeUpdate", employee);
        model.addAttribute("status", EmployeeStatus.values());
        return "employee/update";
    }

    @PostMapping("employees/update/{id}")
    public String updateEmployee(@PathVariable("id") long id, @ModelAttribute("employeeUpdate")
                                 Employee updateEmployee){
        Employee employee = employeeRepository.findById(id).orElse(null);
        if(employee != null){
            employee.setFullname(updateEmployee.getFullname());
            employee.setAddress(updateEmployee.getAddress());
            employee.setDob(updateEmployee.getDob());
            employee.setEmail(updateEmployee.getEmail());
            employee.setPhone(updateEmployee.getPhone());
            employee.setStatus(updateEmployee.getStatus());
            employeeRepository.save(employee);
        }
        return "redirect:/employees";
    }
}
