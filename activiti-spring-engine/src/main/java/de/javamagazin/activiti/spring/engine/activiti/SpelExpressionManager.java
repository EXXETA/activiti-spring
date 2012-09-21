package de.javamagazin.activiti.spring.engine.activiti;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.activiti.engine.delegate.Expression;
import org.activiti.engine.impl.el.ExpressionManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.BeanResolver;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class SpelExpressionManager extends ExpressionManager {

    ExpressionParser parser = new SpelExpressionParser();

    ParserContext parserContext = new TemplateParserContext();

    BeanResolver beanResolver;

    Map<String, org.springframework.expression.Expression> expressionCache = new ConcurrentHashMap<String, org.springframework.expression.Expression>();

    public SpelExpressionManager(ApplicationContext applicationContext, Map<Object, Object> beans) {
        if (beans == null) {
            beanResolver = new BeanFactoryResolver(applicationContext);
        } else {
            beanResolver = new MapBeanResolver(beans);
        }
    }

    @Override
    public Expression createExpression(String expression) {
        return new SpelExpression(expression, this);
    }

    private static class MapBeanResolver implements BeanResolver {
        private Map<Object, Object> beans;

        private MapBeanResolver(Map<Object, Object> beans) {
            this.beans = beans;
        }

        @Override
        public Object resolve(EvaluationContext context, String beanName) {
            return beans.get(beanName);
        }
    }

}
