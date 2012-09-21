package de.javamagazin.activiti.spring.persistence.evaluator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import de.javamagazin.activiti.spring.persistence.Order;
import de.javamagazin.activiti.spring.persistence.OrderAttribute;

public class OrderExpressionEvaluator {

    private ExpressionParser parser = new SpelExpressionParser();

    private ParserContext parserContext = new TemplateParserContext();

    public Map<String, Object> evaluate(Map<String, String> expressions, Order order, Object expressionRoot) {
        StandardEvaluationContext evaluationContext = new StandardEvaluationContext(expressionRoot);

        for (Map.Entry<OrderAttribute, Object> entry : order.getAttributes().entrySet()) {
            evaluationContext.setVariable(entry.getKey().toString(), entry.getValue());
        }

        Map<String, Object> values = new HashMap<String, Object>(expressions.size());
        for (Map.Entry<String, String> expression : expressions.entrySet()) {
            Object value = parser.parseExpression(expression.getValue(), parserContext).getValue(evaluationContext);
            values.put(expression.getKey(), value);
        }

        return values;
    }

    public List<Object> evaluate(List<String> expressions, Order order, Object expressionRoot) {
        Map<String, String> expressionMap = new HashMap<String, String>(expressions.size());
        
        int index = 0;
        for (String expression : expressions) {
            expressionMap.put(String.valueOf(index), expression);
            index++;
        }

        Map<String, Object> valueMap = evaluate(expressionMap, order, expressionRoot);
        List<Object> values = new ArrayList<Object>(Collections.nCopies(expressions.size(), null));
        for (Map.Entry<String, Object> valueEntry : valueMap.entrySet()) {
            values.set(Integer.parseInt(valueEntry.getKey()), valueEntry.getValue());
        }
        
        return values;
    }

    public Object evaluate(String expression, Order order, Object expressionRoot) {
        return evaluate(Collections.singletonMap("dummy", expression), order, expressionRoot).get("dummy");
    }

}
