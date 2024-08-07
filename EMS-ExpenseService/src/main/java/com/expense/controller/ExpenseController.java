package com.expense.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.dto.ExpenseDto;
import com.expense.entity.Expense;
import com.expense.exception.ResourceNotFoundException;
import com.expense.service.ExpenseService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/expenses")
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;
	
	@PostMapping
    public Expense createUser(@RequestBody ExpenseDto expenseDTO) {
        return expenseService.saveExpense(expenseDTO);
    }

    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) throws ResourceNotFoundException{
    	Expense expense = expenseService.getExpenseById(id);
        return ResponseEntity.ok().body(expense);
    }
    
    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable Long id, @RequestBody ExpenseDto expenseDTO) throws ResourceNotFoundException{
    	return expenseService.updateExpense(id, expenseDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {
    	expenseService.deleteExpense(id);
    }
}
