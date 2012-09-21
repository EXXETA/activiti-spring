package de.javamagazin.activiti.spring.engine.activiti;

import java.util.Map;

import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.VariableScope;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.ExpressionException;
import org.springframework.expression.ParseException;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import de.javamagazin.activiti.spring.persistence.Order;
import de.javamagazin.activiti.spring.persistence.OrderAttribute;

public class SpelExpression implements Expression {

    private org.springframework.expression.Expression spelExpression;
    
    private SpelExpressionManager expressionManager;

    public SpelExpression(String expressionText, SpelExpressionManager expressionManager) {
        this.expressionManager = expressionManager;
        spelExpression = expressionManager.expressionCache.get(expressionText);
        if (spelExpression == null) {
            try {
                spelExpression = expressionManager.parser.parseExpression(expressionText, expressionManager.parserContext);
            } catch (ParseException e) {
                throw new ExpressionException("Error parsing " + expressionText, e);
            }
            expressionManager.expressionCache.put(expressionText, spelExpression);
        }
    }

    @Override
    public Object getValue(VariableScope variableScope) {
        EvaluationContext evaluationContext = createEvaluationContext(variableScope);
        try {
            Object value = spelExpression.getValue(evaluationContext);
            return value;
        } catch (EvaluationException e) {
            throw new EvaluationException("Error evaluating [" + spelExpression.getExpressionString() + "]", e);
        }
    }

    @Override
    public void setValue(Object value, VariableScope variableScope) {
        EvaluationContext evaluationContext = createEvaluationContext(variableScope);
        spelExpression.setValue(evaluationContext, value);
    }

    @Override
    public String getExpressionText() {
        return spelExpression.getExpressionString();
    }

    private EvaluationContext createEvaluationContext(VariableScope variableScope) {
        StandardEvaluationContext evaluationContext = new StandardEvaluationContext(variableScope);
        evaluationContext.setBeanResolver(expressionManager.beanResolver);
        evaluationContext.setVariables(variableScope.getVariables());
        Order order = (Order) variableScope.getVariable("order");
        if (order != null) {
            Map<OrderAttribute, Object> attributes = order.getAttributes();
            for (OrderAttribute attribute : attributes.keySet()) {
                evaluationContext.setVariable(attribute.toString(), order.getAttribute(attribute));
            }
        }
        return evaluationContext;
    }

}
