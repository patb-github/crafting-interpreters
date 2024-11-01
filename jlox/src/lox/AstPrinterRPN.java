package lox;

/*
 * Converts an expression to Reverse Polish Notation (RPN).
 */
class AstPrinterRPN implements Expr.Visitor<String> {
    String print(Expr expr) {
        return expr.accept(this);
    }

    @Override
    public String visitBinaryExpr(Expr.Binary expr) {
        return toRPN(expr.operator.lexeme,
                            expr.left, expr.right);
    }

    @Override
    public String visitGroupingExpr(Expr.Grouping expr) {
        return toRPN("group", expr.expression);
    }

    @Override
    public String visitLiteralExpr(Expr.Literal expr) {
        if (expr.value == null) return "nil";
        return expr.value.toString();
    }

    @Override
    public String visitUnaryExpr(Expr.Unary expr) {
        return toRPN(expr.operator.lexeme, expr.right);
    }

    private String toRPN(String name, Expr... exprs) {
        StringBuilder builder = new StringBuilder();

        for (Expr expr : exprs) {
            builder.append(expr.accept(this));
            builder.append(" ");
        }
        builder.append(name);

        return builder.toString();
    }

    // testing
    public static void main(String[] args) {
        Expr expression = new Expr.Binary(
                new Expr.Unary(
                        new Token(TokenType.MINUS, "-", null, 1),
                        new Expr.Literal(123)),
                new Token(TokenType.STAR, "*", null, 1),
                new Expr.Grouping(
                        new Expr.Literal(45.67)));

        Expr expression2 = new Expr.Binary(
            new Expr.Binary(
                new Expr.Literal(1),
                new Token(TokenType.PLUS, "+", null, 1),
                new Expr.Literal(2)),

            new Token(TokenType.STAR, "*", null, 1),
            
            new Expr.Binary(
                new Expr.Literal(4),
                new Token(TokenType.MINUS, "-", null, 1),
                new Expr.Literal(3))
        );

        AstPrinter astPrinter = new AstPrinter();
        AstPrinterRPN astPrinterRPN = new AstPrinterRPN();

        System.out.println(astPrinter.print(expression));
        System.out.println(astPrinterRPN.print(expression));

        System.out.println();
        System.out.println(astPrinter.print(expression2));
        System.out.println(astPrinterRPN.print(expression2));
    }
}