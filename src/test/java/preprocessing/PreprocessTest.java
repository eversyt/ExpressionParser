package preprocessing;

import org.junit.Test;
import utils.ExpressionTestHelper;

import static org.junit.Assert.assertEquals;
import static preprocessing.BasicPreprocessRules.*;

public class PreprocessTest extends ExpressionTestHelper
{
    @Test
    public void remove_spaces_before_and_after_expression()
    {
        assertEquals(EXPRESSION_1_WITHOUT_SPACE
                , REMOVE_SPACES.preprocess(EXPRESSION_1_WRONG_WITH_NUMBERS));
    }

    @Test
    public void replace_square_bracket_with_round_one()
    {
        assertEquals(EXPRESSION_2_WITHOUT_SQUARE_BRACKETS
                , REPLACE_SQUARE_BRACKET_WITH_ROUND_ONE.preprocess(EXPRESSION_2_WRONG_WITH_OPERATIONS_AND_BRACKETS));
    }

    @Test
    public void replace_commas_with_dots()
    {
        assertEquals(EXPRESSION_1_WITH_DOTS
                , REPLACE_COMMAS_WITH_DOTS.preprocess(EXPRESSION_1_WRONG_WITH_NUMBERS));
    }

    @Test
    public void add_multiplication_sign_between_brackets()
    {
        assertEquals(EXPRESSION_3_MULTIPLICATION_1
                , ADD_MULTIPLICATION_SIGN_BETWEEN_BRACKETS.preprocess(EXPRESSION_3_WRONG));
    }

    @Test
    public void add_multiplication_sign_between_number_and_open_bracketn()
    {
        assertEquals(EXPRESSION_3_MULTIPLICATION_2
                , ADD_MULTIPLICATION_SIGN_BETWEEN_NUMBER_AND_OPEN_BRACKET.preprocess(EXPRESSION_3_WRONG));
    }

    @Test
    public void add_multiplication_sign_between_close_bracket_and_number()
    {
        assertEquals(EXPRESSION_3_MULTIPLICATION_3
                , ADD_MULTIPLICATION_SIGN_BETWEEN_CLOSE_BRACKET_AND_NUMBER.preprocess(EXPRESSION_3_WRONG));
    }
}
