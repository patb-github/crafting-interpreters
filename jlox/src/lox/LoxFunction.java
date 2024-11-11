package lox;

import java.util.List;

/*
 * NOTE: MODIFIED FOR CHALLENGE 10.1
 */
class LoxFunction implements LoxCallable {
    private final Environment closure;

    private final List<Stmt> body;
    private final List<Token> params;
    private final Token name;

    LoxFunction(Stmt.Function declaration, Environment closure) {
        this.closure = closure;
        this.name = declaration.name;
        this.params = declaration.params;
        this.body = declaration.body;
    }
    
    LoxFunction(Expr.Function declaration, Environment closure) {
        this.closure = closure;
        this.name = null;
        this.params = declaration.params;
        this.body = declaration.body;
    }

    @Override
    public String toString() {
        String fnName = name == null ? "(anonymous)" : name.lexeme;
        return "<fn " + fnName + ">";
    }

    @Override
    public int arity() {
        return params.size();
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments) {
        
        Environment environment = new Environment(closure);
        for (int i = 0; i < params.size(); i++) {
            environment.define(params.get(i).lexeme, arguments.get(i));
        }

        try {
            interpreter.executeBlock(body, environment);
        } catch (Return returnValue) {
            return returnValue.value;
        }
        return null;
    }
}