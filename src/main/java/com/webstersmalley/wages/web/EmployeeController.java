package com.webstersmalley.wages.web;

import com.webstersmalley.wages.domain.Employee;
import com.webstersmalley.wages.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by: Matthew Smalley
 * Date: 07/03/14
 */
@Controller("employeeController")
public class EmployeeController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private EmployeeService employeeService;

    @RequestMapping(value = "/employees")
    public ModelAndView employeesPage() {
        ModelAndView mav = new ModelAndView("employees");
        List<Employee> employees = employeeService.getAllEmployees();
        logger.info("Returning: {} employees", employees.size());
        mav.addObject("employees", employeeService.getAllEmployees());
        return mav;
    }

    @RequestMapping(value = "/editEmployee")
    public ModelAndView editEmployee(@RequestParam("id") Long id) {
        ModelAndView mav = new ModelAndView("employeeDetail");
        Employee employee = employeeService.findById(id);
        mav.addObject("employee", employee);
        return mav;
    }

    @RequestMapping(value = "/saveEmployee")
    public String saveEmployee(@ModelAttribute("command")Employee employee,
                               BindingResult result){
        logger.info("Saving employee: {}", employee);
        employeeService.save(employee);
        return "redirect:employees.html";
    }

    @RequestMapping(value = "/deleteEmployee")
    public String deleteEmployee(@RequestParam Long id) {
        employeeService.delete(id);
        return "redirect:employees.html";
    }
}
