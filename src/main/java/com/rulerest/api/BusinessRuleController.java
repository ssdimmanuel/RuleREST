package com.rulerest.api;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expr.brule.editing.RuleExpression;
import com.expr.brule.evaluate.RuleExecutionContext;
import com.expr.brule.evaluate.RuleResult;
import com.rulerest.model.RuleData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping("/rules")
@Api(value="rules")
public class BusinessRuleController {
	
	@GetMapping("/test")
	public String test() {
		return "Hello";
	}
	
	@ApiOperation(value = "Get All expression from a Rule", response = ArrayList.class)
	@PostMapping(path="/exprs", produces="application/json")
	public ArrayList<RuleExpression> getExpressions(@RequestBody RuleData data) {
		System.out.println("Received rule: "+data.getRule());
		
		return RuleUtils.getExpressions(data.getRule());		
	}
	
	@ApiOperation(value = "Balance a business rule")
	@PostMapping("/balance")
	public String getBalancedRule(@RequestBody RuleData data) {
		System.out.println("Received rule: "+data.getRule());
		return RuleUtils.balanceRule(data.getRule());		
	}
	
	@ApiOperation(value = "Execute the given rule", response = RuleResult.class)
	@PostMapping("/execute")
	public RuleResult execute(@RequestBody RuleExecutionContext data) {
		System.out.println("Received rule: "+data.getRule());
		return RuleUtils.executeRule(data.getRule(), data.getData());	
	}

}
