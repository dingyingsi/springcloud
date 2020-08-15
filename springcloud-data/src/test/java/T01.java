import org.junit.jupiter.api.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class T01 {

    @Test
    public void t01() {
        ExpressionParser ep = new SpelExpressionParser();
        EvaluationContext ctx = new StandardEvaluationContext();
        ctx.setVariable("date", "2018-09-26");
        ctx.setVariable("location", "公司");
        Object value = ep.parseExpression("时间 #{#date}，位置#{#location}", new TemplateParserContext()).getValue(ctx);
        System.out.println(value);

    }
}
