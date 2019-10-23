package com.rulerest.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.expr.brule.editing.RuleExpression;
import com.expr.brule.edits.RuleBalancer;
import com.expr.brule.evaluate.ExecutionEngine;
import com.expr.brule.evaluate.RuleResult;
import com.expr.brule.stats.ExtractExpression;

public class RuleUtils {

	public static ArrayList<RuleExpression> getExpressions(String rule) {
		ExtractExpression exp = new ExtractExpression(rule);
		try {
			exp.parseRule();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return exp.getExpressions();
	}
	
	
	public static String balanceRule(String rule) {
		
		RuleBalancer bal = new RuleBalancer(rule);
		try {
			bal.parseRule();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return bal.getLatestRule();
	}

	/**
	 * Execute a business rule
	 * @param rule
	 * @param values
	 * @return
	 */
	public static RuleResult executeRule(String rule, HashMap values) {
		ExecutionEngine eng = new ExecutionEngine(rule, values);
		eng.evaluate();
		return eng.getRuleResult();
	}

}
