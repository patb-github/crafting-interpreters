package lox;

import java.util.List;

/*
 * NOTE: MODIFIED FOR CHALLENGE 10.1
 */
class LoxFunction implements LoxCallable {
    private final Environment closure;
    private final Stmt.Function declaration;

    private final List<Stmt> body;
    private final List<Token> params;
    private final Token name;

    private final boolean isInitializer;

    LoxFunction(Stmt.Function declaration, Environment closure, boolean isInitializer) {
        this.isInitializer = isInitializer;
        this.declaration = declaration;

        this.closure = closure;
        this.name = declaration.name;
        this.params = declaration.params;
        this.body = declaration.body;
    }
    
    LoxFunction(Expr.Function declaration, Environment closure) {
        this.declaration = null;
        this.isInitializer = false;

        this.closure = closure;
        this.name = null;
        this.params = declaration.params;
        this.body = declaration.body;
    }

    LoxFunction bind(LoxInstance instance) {
        Environment environment = new Environment(closure);
        environment.define("this", instance);
        return new LoxFunction(declaration, environment, isInitializer);
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
            if (isInitializer) return closure.getAt(0, "this");
            
            return returnValue.value;
        }

        if (isInitializer) return closure.getAt(0, "this");
        return null;
    }
}