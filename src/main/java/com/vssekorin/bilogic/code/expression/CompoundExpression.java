/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code.expression;

import com.vssekorin.bilogic.error.IncorrectExpression;
import com.vssekorin.bilogic.method.MethodInfo;
import com.vssekorin.bilogic.util.ChainInsnList;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;

/**
 * Compound expression.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class CompoundExpression implements Expression {

    /**
     * The information about method.
     */
    private final MethodInfo info;

    /**
     * Expression text.
     */
    private final String string;

    /**
     * List of operations.
     */
    private static final List<String> OPERATIONS = Arrays.asList(
        " " + Implication.NAME + " ",
        " " + Or.NAME + " ",
        " " + And.NAME + " ",
        Not.NAME + " "
    );

    @Override
    public ChainInsnList asBytecode() {
        final Expression expression;
        switch (this.nextOperation()) {
            case 0: expression = new Implication(this.info, this.string); break;
            case 1: expression = new Or(this.info, this.string); break;
            case 2: expression = new And(this.info, this.string); break;
            case 3: expression = new Not(this.info, this.string); break;
            default: throw new IncorrectExpression(this.string);
        }
        return expression.asBytecode();
    }

    /**
     * Get number of next operation to expression.
     *
     * @return The number
     */
    private int nextOperation() {
        int result = -1;
        for (int i = 0; i < OPERATIONS.size(); i++) {
            if (this.string.contains(OPERATIONS.get(i))) {
                result = i;
                break;
            }
        }
        return result;
    }
}
