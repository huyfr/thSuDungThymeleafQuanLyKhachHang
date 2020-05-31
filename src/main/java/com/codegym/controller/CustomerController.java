package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.CustomerServiceImp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CustomerController {
    private CustomerServiceImp customerServiceImp = new CustomerServiceImp();

    @RequestMapping("/")
    public String index(Model model) {
        List customerList = customerServiceImp.findAll();
        model.addAttribute("customers", customerList);
        return "/index";
    }

    @RequestMapping(value = "/customer/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("customer", new Customer());
        return "/create";
    }

    @RequestMapping(value = "/customer/save", method = RequestMethod.POST)
    public String save(Customer customer, RedirectAttributes redirectAttributes) {
        customer.setId((int) (Math.random() * 10000));
        customerServiceImp.save(customer);
        redirectAttributes.addFlashAttribute("success", "Save customer successfully!");
        return "redirect:/";
    }

    @RequestMapping(value = "/customer/{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("customer", customerServiceImp.findById(id));
        return "/edit";
    }

    @RequestMapping(value = "/customer/update", method = RequestMethod.POST)
    public String update(Customer customer, RedirectAttributes redirectAttributes) {
        customerServiceImp.update(customer.getId(), customer);
        redirectAttributes.addFlashAttribute("success", "Modified customer successfully!");
        return "redirect:/";
    }

    @RequestMapping(value = "/customer/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable int id, Model model) {
        model.addAttribute("customer", customerServiceImp.findById(id));
        return "/delete";
    }

    @RequestMapping(value = "/customer/delete", method = RequestMethod.POST)
    public String delete(Customer customer, RedirectAttributes redirectAttributes) {
        customerServiceImp.remove(customer.getId());
        redirectAttributes.addFlashAttribute("success", "Removed customer successfully!");
        return "redirect:/";
    }

    @RequestMapping(value = "/customer/{id}/view", method = RequestMethod.GET)
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("customer", customerServiceImp.findById(id));
        return "/view";
    }
}
